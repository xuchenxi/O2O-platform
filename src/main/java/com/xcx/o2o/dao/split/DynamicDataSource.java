package com.xcx.o2o.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO 自动生成的方法存根
		return DynamicDataSourceHolder.getDbType();
	}

}
