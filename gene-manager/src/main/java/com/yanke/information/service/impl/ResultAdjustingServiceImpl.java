package com.yanke.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yanke.information.dao.ResultAdjustingDao;
import com.yanke.information.domain.ResultAdjustingDO;
import com.yanke.information.service.ResultAdjustingService;



@Service
public class ResultAdjustingServiceImpl implements ResultAdjustingService {
	@Autowired
	private ResultAdjustingDao resultAdjustingDao;
	
	@Override
	public ResultAdjustingDO get(Integer tAdjustingId){
		return resultAdjustingDao.get(tAdjustingId);
	}
	
	@Override
	public List<ResultAdjustingDO> list(Map<String, Object> map){
		return resultAdjustingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return resultAdjustingDao.count(map);
	}
	
	@Override
	public int save(ResultAdjustingDO resultAdjusting){
		return resultAdjustingDao.save(resultAdjusting);
	}
	
	@Override
	public int update(ResultAdjustingDO resultAdjusting){
		return resultAdjustingDao.update(resultAdjusting);
	}
	
	@Override
	public int remove(Integer tAdjustingId){
		return resultAdjustingDao.remove(tAdjustingId);
	}
	
	@Override
	public int batchRemove(Integer[] tAdjustingIds){
		return resultAdjustingDao.batchRemove(tAdjustingIds);
	}
	
}
