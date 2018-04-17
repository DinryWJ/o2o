package com.wjj.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjj.o2o.dao.ProductCategoryDao;
import com.wjj.o2o.dao.ProductDao;
import com.wjj.o2o.dto.ProductCategoryExecution;
import com.wjj.o2o.entity.ProductCategory;
import com.wjj.o2o.enums.ProductCategoryStateEnum;
import com.wjj.o2o.exceptions.ProductCategoryOperationException;
import com.wjj.o2o.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if(productCategoryList !=null && productCategoryList.size() > 0){
			try{
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum <=0){
					throw new ProductCategoryOperationException("创建商铺类别失败");
				}else{
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e){
				throw new ProductCategoryOperationException("batchAddProductCategory error" +e.getMessage());
			}
		}else{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		try{
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		}catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error"+e.getMessage());
		}
		try{
			int effentedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effentedNum<=0){
				throw new ProductCategoryOperationException("商品删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("deleteProductCategory error"+e.getMessage());
		}
	}

	@Override
	public List<ProductCategory> getByShopId(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

}
