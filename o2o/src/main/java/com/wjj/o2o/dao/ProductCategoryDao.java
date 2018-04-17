package com.wjj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjj.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> queryByShopId(long shopId);
	
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	int batchInsertProductCategory(List<ProductCategory> productCategorylist);
	
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
