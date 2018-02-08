package com.wjj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjj.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	/**
	 * 
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryShopCategory(
			@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);



}
