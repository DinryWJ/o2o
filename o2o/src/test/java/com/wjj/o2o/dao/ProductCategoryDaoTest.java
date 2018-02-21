package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.entity.ProductCategory;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testBQueryByShopId(){
		List<ProductCategory> list = productCategoryDao.queryProductCategoryList(4L);
		assertEquals(10,list.size());
	}
	@Test
	public void testABatchInsertCategory(){
		ProductCategory p1 = new ProductCategory();
		p1.setCreateTime(new Date());
		p1.setProductCategoryName("新商品类别1");
		p1.setPriority(0);
		p1.setShopId(4L);
		ProductCategory p2 = new ProductCategory();
		p2.setCreateTime(new Date());
		p2.setProductCategoryName("新商品类别2");
		p2.setPriority(5);
		p2.setShopId(4L);
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		list.add(p1);
		list.add(p2);
		int eff = productCategoryDao.batchInsertProductCategory(list);
		assertEquals(2,eff);
	}
	@Test
	public void testCDeleteProductCategory() throws Exception{
		long shopId = 4;
		List<ProductCategory> productCategoryList= productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc : productCategoryList){
			if("新商品类别1".equals(pc.getProductCategoryName())|| "新商品类别2".equals(pc.getProductCategoryName())){
				int eff = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1,eff);
			}
		}
	}
}
