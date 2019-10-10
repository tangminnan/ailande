package com.yanke.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yanke.information.dao.ResultEyeaxisDao;
import com.yanke.information.domain.ResultEyeaxisDO;
import com.yanke.information.service.ResultEyeaxisService;



@Service
public class ResultEyeaxisServiceImpl implements ResultEyeaxisService {
	@Autowired
	private ResultEyeaxisDao resultEyeaxisDao;
	
	@Override
	public ResultEyeaxisDO get(Integer tEyeaxisId){
		return resultEyeaxisDao.get(tEyeaxisId);
	}
	
	@Override
	public List<ResultEyeaxisDO> list(Map<String, Object> map){
		return resultEyeaxisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return resultEyeaxisDao.count(map);
	}
	
	@Override
	public int save(ResultEyeaxisDO resultEyeaxis){
		return resultEyeaxisDao.save(resultEyeaxis);
	}
	
	@Override
	public int update(ResultEyeaxisDO resultEyeaxis){
		return resultEyeaxisDao.update(resultEyeaxis);
	}
	
	@Override
	public int remove(Integer tEyeaxisId){
		return resultEyeaxisDao.remove(tEyeaxisId);
	}
	
	@Override
	public int batchRemove(Integer[] tEyeaxisIds){
		return resultEyeaxisDao.batchRemove(tEyeaxisIds);
	}
	
}
