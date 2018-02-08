package com.wjj.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wjj.o2o.BaseTest;
import com.wjj.o2o.dto.ShopExecution;
import com.wjj.o2o.entity.Area;
import com.wjj.o2o.entity.PersonInfo;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.entity.ShopCategory;
import com.wjj.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		Area area = new Area();
		PersonInfo owner = new PersonInfo();
		ShopCategory sc = new ShopCategory();
		owner.setUserId(9L);
		area.setAreaId(3L);
		sc.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(sc);
		shop.setShopName("mytest3");
		shop.setShopDesc("mytest3");
		shop.setShopAddr("testaddr3");
		shop.setPhone("13810524523");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("E:/a.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, is,shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}