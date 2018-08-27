package com.xcx.o2o.service.impi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcx.o2o.dao.ProductCategoryDao;
import com.xcx.o2o.dto.ProductCategoryExecution;
import com.xcx.o2o.entity.ProductCategory;
import com.xcx.o2o.enums.ProductCategoryStateEnum;
import com.xcx.o2o.exceptions.ProductCategoryOperationException;
import com.xcx.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		// TODO 自动生成的方法存根

		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		// TODO 自动生成的方法存根
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺类别创建失败");
				} else {

					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}

			} catch (Exception e) {
				throw new RuntimeException("batchAddProductCategory error: " + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}

	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO 将此商品类别下的商品的类别id置为空
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum<=0) {
				throw new ProductCategoryOperationException("店铺商品类别删除失败");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
		}
	}

}
