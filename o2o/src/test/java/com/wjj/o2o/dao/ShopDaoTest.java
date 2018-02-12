package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
	public void testQueryShopList() throws Exception {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(8L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 1);
		assertEquals(1, shopList.size());
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺数："+count);
	}
	
	

	@Test
	@Ignore
	public void testQueryByShopId(){
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("AreaName:"+shop.getArea().getAreaName()+"\nAreaId:"+shop.getArea().getAreaId());
	}
	
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
	@Ignore
	public void TestUpdateShop(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("测试");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}