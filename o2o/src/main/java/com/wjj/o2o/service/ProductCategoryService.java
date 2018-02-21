package com.wjj.o2o.service;

import java.util.List;

import com.wjj.o2o.dto.ProductCategoryExecution;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	List<ProductCategory> getProductCategoryList(long shopId);

	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
