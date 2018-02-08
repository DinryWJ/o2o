package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.entity.Area;
import com.wjj.o2o.entity.PersonInfo;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	@Ignore
	public void testInsertShop() throws Exception {
		Shop shop = new Shop();
		Area area = new Area();
		area.setAreaId(3L);
		ShopCategory sc = new ShopCategory();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(8L);
		sc.setShopCategoryId(1L);
		shop.setShopName("mytest1");
		shop.setShopDesc("mytest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setOwner(owner);
		shop.setAdvice("审核中");
		shop.setArea(area);
		shop.setShopCategory(sc);
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void TestUpdateShop(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("测试");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}