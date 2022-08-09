package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

public interface ShopService {

    @Transactional
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
