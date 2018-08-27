package com.xcx.o2o.service.impi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcx.o2o.dao.AreaDao;
import com.xcx.o2o.entity.Area;
import com.xcx.o2o.service.AreaService;
@Service
public class AreaServiceImol implements AreaService {
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return areaDao.queryArea();
	}

}
