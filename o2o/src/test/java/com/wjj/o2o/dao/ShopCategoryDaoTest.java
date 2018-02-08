package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.entity.ShopCategory;


public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;



	@Test
	public void testBQueryShopCategory() throws Exception {

		List<ShopCategory> shopCategoryList = shopCategoryDao
				.queryShopCategory(new ShopCategory());
		assertEquals(1, shopCategoryList.size());
		ShopCategory pc = new ShopCategory();
		ShopCategory sc = new ShopCategory();
		pc.setShopCategoryId(1L);
		sc.setParent(pc);
		shopCategoryList = shopCategoryDao
				.queryShopCategory(sc);
		assertEquals(1, shopCategoryList.size());
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
	}

	

}
