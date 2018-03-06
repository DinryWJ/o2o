package com.wjj.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjj.o2o.BaseTest;
import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ProductExecution;
import com.wjj.o2o.entity.Product;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.enums.ProductStateEnum;
import com.wjj.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
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
	@Test
	public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
		Product product  = new Product();
		Shop shop = new Shop();
		shop.setShopId(4L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(6L);
		product.setProductId(9L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("正式的商品");
		product.setProductDesc("正式的商品");
		
		File file = new File("E:/c.jpg");
		InputStream is = new FileInputStream(file);
		ImageHolder thumbnail  = new ImageHolder(file.getName(), is);
		
		File f1 = new File("E:/d.jpg");
		File f2 = new File("E:/e.jpg");
		InputStream is1 = new FileInputStream(f1);
		InputStream is2 = new FileInputStream(f2);
		
		List<ImageHolder> list = new ArrayList<ImageHolder>();
		list.add(new ImageHolder(f1.getName(), is1));
		list.add(new ImageHolder(f2.getName(), is2));
		
		ProductExecution pe = productService.modifyProduct(product, thumbnail, list);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
