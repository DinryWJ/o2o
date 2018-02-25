package com.wjj.o2o.service;

import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ShopExecution;
import com.wjj.o2o.entity.Shop;
import com.wjj.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);

	/**
	 * 创建商铺
	 * 
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

	/**
	 * 更新店铺信息（从店家角度）
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
