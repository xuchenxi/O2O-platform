package com.xcx.o2o.service;

import java.util.List;
import com.xcx.o2o.dto.ImageHolder;
import com.xcx.o2o.dto.ProductExecution;
import com.xcx.o2o.entity.Product;
import com.xcx.o2o.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 添加商品信息以及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs)throws ProductOperationException;
	
}
