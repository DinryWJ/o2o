package com.wjj.o2o.service;

import java.util.List;

import com.wjj.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	public static final String SCLISTKEY = "shopCategorylist";
	
	/**
	 * 更具条件获取ShopCategory列表
	 * @param shopCategory
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);



}
