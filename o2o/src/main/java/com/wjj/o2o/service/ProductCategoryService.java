package com.wjj.o2o.service;

import java.util.List;

import com.wjj.o2o.dto.ProductCategoryExecution;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * 
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getByShopId(long shopId);
	
	List<ProductCategory> getProductCategoryList(long shopId);

	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
