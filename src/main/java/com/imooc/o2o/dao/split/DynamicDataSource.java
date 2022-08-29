package com.imooc.o2o.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Xinyu Zhu
 * @version 29/8/2022 12:30
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
    return DynamicDataSourceHolder.getDbType();
    }
}
