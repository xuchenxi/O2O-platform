package com.xcx.o2o.service.impi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcx.o2o.dao.ShopCategoryDao;
import com.xcx.o2o.entity.ShopCategory;
import com.xcx.o2o.service.ShopCategoryService;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		// TODO 自动生成的方法存根
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
