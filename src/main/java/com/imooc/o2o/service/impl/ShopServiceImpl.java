package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop temp = shopDao.queryByShopId(shop.getShopId());
                    if (temp.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(temp.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                shop.setLastEditTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    Shop shopNew = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shopNew);
                }
            }catch (Exception e){
                throw new ShopOperationException("Shop Modification Error" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        // ????????????
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // ???????????????????????????
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // ??????????????????
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("??????????????????");
            } else {
                if (shopImgInputStream != null) {
                    // ????????????
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // ???????????????????????????
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("????????????????????????");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        // ??????shop??????????????????????????????
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}