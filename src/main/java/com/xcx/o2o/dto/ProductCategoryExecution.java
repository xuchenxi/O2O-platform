package com.xcx.o2o.dto;

import java.util.List;

import com.xcx.o2o.entity.ProductCategory;
import com.xcx.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	//结果状态
	private int state;
	/**
	 * @return state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state 要设置的 state
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}
	/**
	 * @param stateInfo 要设置的 stateInfo
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	/**
	 * @return productCategoryList
	 */
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}
	/**
	 * @param productCategoryList 要设置的 productCategoryList
	 */
	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	//状态标识
	private String stateInfo;
	
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExecution() {
		
	}
	//操作失败的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
	}
	//操作成功的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
		this.productCategoryList=productCategoryList;
	}
}
