package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import com.imooc.o2o.service.impl.ShopServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Ignore
    @Test
    public void testAddShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺4");
        shop.setShopDesc("test4");
        shop.setShopAddr("test4");
        shop.setPhone("test4");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/jensen/Desktop/pika.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

    @Test
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        long shopId = 35L;
        Shop shop = new Shop();
        File shopImg = new File("/Users/jensen/Desktop/pikk.png");
        shop.setShopId(shopId);
        InputStream is = new FileInputStream(shopImg);
        shop.setShopDesc("一个8月29日测试的更新");
        shop.setShopName("奶茶飞了");
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "pikk.png");
        assertEquals("奶茶飞了", shopExecution.getShop().getShopName());
        assertEquals("一个8月29日测试的更新", shopExecution.getShop().getShopDesc());
    }
}
