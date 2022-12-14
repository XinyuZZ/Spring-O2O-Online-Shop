package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Ignore
    @Test
    public void testAreaService() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("南苑", areaList.get(0).getAreaName());
    }

    @Test
    public void testAreaService2() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals(3, areaList.size());
    }
}
