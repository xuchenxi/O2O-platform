package com.xcx.o2o.service.impi;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcx.o2o.dao.ShopDao;
import com.xcx.o2o.dto.ShopExecution;
import com.xcx.o2o.entity.Shop;
import com.xcx.o2o.enums.ShopStateEnum;
import com.xcx.o2o.exceptions.ShopOperationException;
import com.xcx.o2o.service.ShopService;
import com.xcx.o2o.util.ImageUtil;
import com.xcx.o2o.util.PageCalcultor;
import com.xcx.o2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
		// TODO Auto-generated method stub
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺信息赋初值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(shopImgInputStream!=null) {
					//存储图片
					try{
						addShopImg(shop,shopImgInputStream,fileName);
					}catch (Exception e) {
						// TODO: handle exception
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					//更新店铺图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:"+e.getMessage());
			// TODO: handle exception
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		// 获取shop图片目录的相对值路径
		
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName ,dest);
		shop.setShopImg(shopImgAddr);
	}
	@Override
	public Shop getByShopId(long shopId) {
		// TODO 自动生成的方法存根
		return shopDao.queryByShopId(shopId);
	}
	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		// TODO 自动生成的方法存根
		if (shop==null||shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			//1.判断是否处理图片
			try {
			if (shopImgInputStream!=null&&!"".equals(fileName)) {
				Shop tempShop = shopDao.queryByShopId(shop.getShopId());
				if (tempShop.getShopImg()!=null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop, shopImgInputStream, fileName);
			}
			//2.更新店铺信息
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if (effectedNum<=0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				shop = shopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}}catch (Exception e) {
				// TODO: handle exception
				throw new ShopOperationException("modifyShop error:"+e.getMessage());
			}
		}
		
	}
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		// TODO 自动生成的方法存根
		int rowIndex = PageCalcultor.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList!=null) {
			se.setShoplist(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		
		return se;
	}

}
