package com.wjj.o2o.dao;

import java.util.List;

import com.wjj.o2o.entity.ProductImg;

public interface ProductImgDao {
	int batchInsertProductImg(List<ProductImg> productImg);
	int deleteProductImgByProductId(long productId);
	List<ProductImg> queryProductImgList(long productId);
}
