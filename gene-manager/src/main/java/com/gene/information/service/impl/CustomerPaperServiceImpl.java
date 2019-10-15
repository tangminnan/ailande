package com.gene.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gene.information.dao.CustomerPaperDao;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.service.CustomerPaperService;

import java.util.List;
import java.util.Map;




@Service
public class CustomerPaperServiceImpl implements CustomerPaperService {
	@Autowired
	private CustomerPaperDao customerPaperDao;
	
	@Override
	public CustomerPaperDO get(Integer id){
		return customerPaperDao.get(id);
	}
	
	@Override
	public List<CustomerPaperDO> list(Map<String, Object> map){
		return customerPaperDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerPaperDao.count(map);
	}
	
	@Override
	public int save(CustomerPaperDO customerPaper){
		return customerPaperDao.save(customerPaper);
	}
	
	@Override
	public int update(CustomerPaperDO customerPaper){
		return customerPaperDao.update(customerPaper);
	}
	
	@Override
	public int remove(Integer id){
		return customerPaperDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return customerPaperDao.batchRemove(ids);
	}

	@Override
	public List<CustomerPaperDO> queryUserQuestionDetails(Integer id) {
		return customerPaperDao.queryUserQuestionDetails(id);
	}

	
}
