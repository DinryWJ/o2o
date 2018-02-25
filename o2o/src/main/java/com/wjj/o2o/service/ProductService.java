package com.wjj.o2o.service;


import java.util.List;

import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ProductExecution;
import com.wjj.o2o.entity.Product;
import com.wjj.o2o.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException;

}
