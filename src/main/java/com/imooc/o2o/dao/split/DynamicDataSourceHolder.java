package com.imooc.o2o.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xinyu Zhu
 * @version 29/8/2022 12:30
 */
public class DynamicDataSourceHolder {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    /**
     * 获取线程的db type
     * @return
     */
    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     * 设置线程的db type
     * @param str
     */
    public static void setDbType(String str){
        logger.debug("所使用的数据源为" + str);
        contextHolder.set(str);
    }
}
