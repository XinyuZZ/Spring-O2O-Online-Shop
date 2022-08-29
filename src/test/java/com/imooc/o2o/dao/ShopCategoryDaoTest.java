package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        ShopCategory test1 = new ShopCategory();
        ShopCategory test2 = new ShopCategory();
        test1.setShopCategoryId(1L);
        test2.setParent(test1);
        shopCategoryList = shopCategoryDao.queryShopCategory(test2);
        System.out.println("--------------");
        System.out.println(shopCategoryList.get(2).getShopCategoryName());
    }
}

