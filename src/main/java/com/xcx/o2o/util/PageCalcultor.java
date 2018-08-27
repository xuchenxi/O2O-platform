package com.xcx.o2o.util;

public class PageCalcultor {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
