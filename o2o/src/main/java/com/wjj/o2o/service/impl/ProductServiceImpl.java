package com.wjj.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjj.o2o.dao.ProductDao;
import com.wjj.o2o.dao.ProductImgDao;
import com.wjj.o2o.dto.ImageHolder;
import com.wjj.o2o.dto.ProductExecution;
import com.wjj.o2o.entity.Product;
import com.wjj.o2o.entity.ProductImg;
import com.wjj.o2o.enums.ProductStateEnum;
import com.wjj.o2o.exceptions.ProductOperationException;
import com.wjj.o2o.service.ProductService;
import com.wjj.o2o.utils.ImageUtil;
import com.wjj.o2o.utils.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgHolderList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	// @Override
	// @Transactional
	// public ProductExecution modifyProduct(Product product,
	// CommonsMultipartFile thumbnail,
	// List<CommonsMultipartFile> productImgs) throws RuntimeException {
	// if (product != null && product.getShop() != null &&
	// product.getShop().getShopId() != null) {
	// product.setLastEditTime(new Date());
	// if (thumbnail != null) {
	// Product tempProduct =
	// productDao.queryProductByProductId(product.getProductId());
	// if (tempProduct.getImgAddr() != null) {
	// FileUtil.deleteFile(tempProduct.getImgAddr());
	// }
	// addThumbnail(product, thumbnail);
	// }
	// if (productImgs != null && productImgs.size() > 0) {
	// deleteProductImgs(product.getProductId());
	// addProductImgs(product, productImgs);
	// }
	// try {
	// int effectedNum = productDao.updateProduct(product);
	// if (effectedNum <= 0) {
	// throw new RuntimeException("更新商品信息失败");
	// }
	// return new ProductExecution(ProductStateEnum.SUCCESS, product);
	// } catch (Exception e) {
	// throw new RuntimeException("更新商品信息失败:" + e.toString());
	// }
	// } else {
	// return new ProductExecution(ProductStateEnum.EMPTY);
	// }
	// }

	private void addProductImgHolderList(Product product, List<ImageHolder> productImgHolderList) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNomalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}

		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	// private void deleteProductImgs(long productId) {
	// List<ProductImg> productImgList =
	// productImgDao.queryProductImgList(productId);
	// for (ProductImg productImg : productImgList) {
	// FileUtil.deleteFile(productImg.getImgAddr());
	// }
	// productImgDao.deleteProductImgByProductId(productId);
	// }

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
}
