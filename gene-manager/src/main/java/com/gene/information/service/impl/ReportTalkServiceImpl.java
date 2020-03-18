package com.gene.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gene.information.dao.ReportTalkDao;
import com.gene.information.domain.ReportTalkDO;
import com.gene.information.service.ReportTalkService;



@Service
public class ReportTalkServiceImpl implements ReportTalkService {
	@Autowired
	private ReportTalkDao reportTalkDao;
	
	@Override
	public ReportTalkDO get(Integer id){
		return reportTalkDao.get(id);
	}
	
	@Override
	public List<ReportTalkDO> list(Map<String, Object> map){
		return reportTalkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return reportTalkDao.count(map);
	}
	
	@Override
	public int save(ReportTalkDO reportTalk){
		return reportTalkDao.save(reportTalk);
	}
	
	@Override
	public int update(ReportTalkDO reportTalk){
		return reportTalkDao.update(reportTalk);
	}
	
	@Override
	public int remove(Integer id){
		return reportTalkDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return reportTalkDao.batchRemove(ids);
	}
	
}
