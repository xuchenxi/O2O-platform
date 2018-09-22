package com.xcx.o2o.service.impi;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcx.o2o.dao.HeadLineDao;
import com.xcx.o2o.entity.HeadLine;
import com.xcx.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		// TODO 自动生成的方法存根
		return headLineDao.queryHeadLine(headLineCondition);
	}

}
