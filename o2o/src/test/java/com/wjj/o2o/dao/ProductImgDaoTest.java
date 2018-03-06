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
import com.wjj.o2o.entity.ProductImg;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest{

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testABatchInsertProductImg(){
		ProductImg p1 = new ProductImg();
		p1.setImgAddr("图片1");
		p1.setImgDesc("aaa测试图片1");
		p1.setCreateTime(new Date());
		p1.setPriority(1);
		p1.setProductId(9L);
		
		ProductImg p2 = new ProductImg();
		p2.setImgAddr("图片2");
		p2.setImgDesc("aaa测试图片2");
		p2.setCreateTime(new Date());
		p2.setPriority(2);
		p2.setProductId(9L);
		
		List<ProductImg> list = new ArrayList<ProductImg>();
		list.add(p1);
		list.add(p2);
		
		int eff = productImgDao.batchInsertProductImg(list);
		assertEquals(2,eff);
	}
	
	@Test
	public void testBQueryProdcutImgList(){
		List<ProductImg> list = productImgDao.queryProductImgList(9L);
		assertEquals(2,list.size());
	}
	@Test
	public void testCDeleteProductImgByProductId() throws Exception{
		long productId = 9L;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2,effectedNum);
	}
}
