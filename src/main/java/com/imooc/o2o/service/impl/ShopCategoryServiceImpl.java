package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
