package com.xcx.o2o.service;

import java.util.List;

import com.xcx.o2o.dto.ImageHolder;
import com.xcx.o2o.dto.ProductExecution;
import com.xcx.o2o.entity.Product;
import com.xcx.o2o.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 查询商品列表并分页,可输入条件有:商品名,商品状态,店铺Id,商品类别
	 * 
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

	/**
	 * 通过商品Id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * 添加商品信息
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs)
			throws ProductOperationException;

	/**
	 * 修改商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs)
			throws ProductOperationException;

}
