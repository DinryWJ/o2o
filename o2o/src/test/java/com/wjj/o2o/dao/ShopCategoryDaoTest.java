package com.wjj.o2o.dao;

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
				.queryShopCategory(null);
		System.out.println(shopCategoryList.size());
	}

	

}
