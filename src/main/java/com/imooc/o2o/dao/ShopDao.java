package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 分页查询店铺
     * rowIndex、pageSize从第几行开始取返回的行数
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
    /**
     * 获取店铺信息
     * @param shopId
     * @return
     */
    /**
     *
     * @param shopCondition
     * @return 店铺总数
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    Shop queryByShopId(long shopId);

    /**
     * 新增店铺信息
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
