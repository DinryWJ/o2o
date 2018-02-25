package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.entity.Product;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testInsertProduct(){
		Shop shop1 = new Shop();
		shop1.setShopId(4L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(5L);
		Product p1 = new Product();
		p1.setProductName("test1");
		p1.setProductDesc("test1");
		p1.setImgAddr("test1");
		p1.setPriority(1);
		p1.setEnableStatus(0);
		p1.setCreateTime(new Date());
		p1.setLastEditTime(new Date());
		p1.setShop(shop1);
		p1.setProductCategory(pc1);
		
		Product p2 = new Product();
		p2.setProductName("test2");
		p2.setProductDesc("test2");
		p2.setImgAddr("test2");
		p2.setPriority(2);
		p2.setEnableStatus(0);
		p2.setCreateTime(new Date());
		p2.setLastEditTime(new Date());
		p2.setShop(shop1);
		p2.setProductCategory(pc1);
		
		Product p3 = new Product();
		p3.setProductName("test3");
		p3.setProductDesc("test3");
		p3.setImgAddr("test3");
		p3.setPriority(3);
		p3.setEnableStatus(0);
		p3.setCreateTime(new Date());
		p3.setLastEditTime(new Date());
		p3.setShop(shop1);
		p3.setProductCategory(pc1);
		
		int eff1 = productDao.insertProduct(p1);
		assertEquals(1,eff1);
		int eff2 = productDao.insertProduct(p2);
		assertEquals(1,eff2);
		int eff3 = productDao.insertProduct(p3);
		assertEquals(1,eff3);
		
	}
}
