package com.wjj.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ProductExecution;
import com.wjj.o2o.entity.Product;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws FileNotFoundException{
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(4L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建缩略图文件流
		File thumbnailFIle = new File("E://a.jpg");
		InputStream is = new FileInputStream(thumbnailFIle);
		ImageHolder thumbnail = new ImageHolder(thumbnailFIle.getName(),is);
		//创建商品详情图文件流
		File productImg1 = new File("E://pimg1.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("E://pimg2.jpg");
		InputStream is2 = new FileInputStream(productImg2);	
		List<ImageHolder> list = new ArrayList<ImageHolder>();
		list.add(new ImageHolder(productImg1.getName(),is1));
		list.add(new ImageHolder(productImg2.getName(),is2));	
		
		ProductExecution pe = productService.addProduct(product, thumbnail, list);
		assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
	}
}
