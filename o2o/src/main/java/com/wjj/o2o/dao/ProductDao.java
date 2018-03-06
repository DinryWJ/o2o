package com.wjj.o2o.dao;

import com.wjj.o2o.entity.Product;

public interface ProductDao {
	Product queryProdcutById(long productId);

	int updateProduct(Product product);

	int insertProduct(Product product);

	int updateProductCategoryToNull(long productCategoryId);
}
