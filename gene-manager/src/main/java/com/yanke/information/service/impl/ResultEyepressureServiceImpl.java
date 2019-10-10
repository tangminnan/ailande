package com.yanke.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yanke.information.dao.ResultEyepressureDao;
import com.yanke.information.domain.ResultEyepressureDO;
import com.yanke.information.service.ResultEyepressureService;



@Service
public class ResultEyepressureServiceImpl implements ResultEyepressureService {
	@Autowired
	private ResultEyepressureDao resultEyepressureDao;
	
	@Override
	public ResultEyepressureDO get(Integer tEyepressureId){
		return resultEyepressureDao.get(tEyepressureId);
	}
	
	@Override
	public List<ResultEyepressureDO> list(Map<String, Object> map){
		return resultEyepressureDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return resultEyepressureDao.count(map);
	}
	
	@Override
	public int save(ResultEyepressureDO resultEyepressure){
		return resultEyepressureDao.save(resultEyepressure);
	}
	
	@Override
	public int update(ResultEyepressureDO resultEyepressure){
		return resultEyepressureDao.update(resultEyepressure);
	}
	
	@Override
	public int remove(Integer tEyepressureId){
		return resultEyepressureDao.remove(tEyepressureId);
	}
	
	@Override
	public int batchRemove(Integer[] tEyepressureIds){
		return resultEyepressureDao.batchRemove(tEyepressureIds);
	}
	
}
