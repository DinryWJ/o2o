package com.wjj.o2o.service;

import java.io.InputStream;

import com.wjj.o2o.dto.ShopExecution;
import com.wjj.o2o.entity.Shop;

public interface ShopService {

	/**
	 * 创建商铺
	 * 
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException;

}
