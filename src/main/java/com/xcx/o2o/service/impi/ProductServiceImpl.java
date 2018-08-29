package com.xcx.o2o.service.impi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcx.o2o.dao.ProductDao;
import com.xcx.o2o.dao.ProductImgDao;
import com.xcx.o2o.dto.ImageHolder;
import com.xcx.o2o.dto.ProductExecution;
import com.xcx.o2o.entity.Product;
import com.xcx.o2o.entity.ProductImg;
import com.xcx.o2o.enums.ProductStateEnum;
import com.xcx.o2o.exceptions.ProductOperationException;
import com.xcx.o2o.service.ProductService;
import com.xcx.o2o.util.ImageUtil;
import com.xcx.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	@Override
	@Transactional
	//1.处理缩略图,获取缩略图相对路径并赋值给product
	//2.往tb_product写入商品信息,获取productId
	//3.结合productId批量处理商品详情图
	//4.将商品详情图列表批量插入tb_product_img中
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		// TODO Auto-generated method stub
		if (product != null&&product.getShop() !=null && product.getShop().getShopId() !=null) {
			//设置商品默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			//默认为上架状态
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThunbnail(product,thumbnail);
			}
			try {
				//创建商品信息
				int effctedNum = productDao.insertProduct(product);
				if(effctedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (ProductOperationException e) {
				// TODO: handle exception
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			//若商品详情图不为空则添加
			if(productImgHolderList != null && productImgHolderList.size() >0) {
				addProductImgList(product,productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		}else {
			//传参为空则返回空值错误信息
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	/**
	 * 添加缩略图
	 * @param product
	 * @param thumbnail
	 */
	private void addThunbnail(Product product, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thunbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thunbnailAddr);
	}
	/**
	 * 批量添加图片
	 * @param product
	 * @param productImgList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// TODO Auto-generated method stub
		//获取图片存储路径,直接存放到相应店铺文件夹目录下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		//遍历图片一次去处理,并添加进productImg实体类里
		for(ImageHolder productImgHolder:productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果确实是有图片需要添加,就执行批量添加操作
		if (productImgList.size() > 0 ) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
	
}
