package com.wjj.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjj.o2o.entity.Area;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest{
	@Autowired
	private AreaService areaService;
	@Autowired
	private CacheService cacheService;
	@SuppressWarnings("static-access")
	@Test
	public void testGetAreaList(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("东苑",areaList.get(0).getAreaName());
		cacheService.removeFromCache(areaService.AREALISTKEY);
		areaService.getAreaList();
	}
	
}	
