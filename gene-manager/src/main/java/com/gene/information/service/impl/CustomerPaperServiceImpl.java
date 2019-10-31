package com.gene.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gene.information.dao.CustomerPaperDao;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.service.CustomerPaperService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;




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
	public List<CustomerPaperDO> queryUserQuestionDetails(String id) {
		return customerPaperDao.queryUserQuestionDetails(id);
	}
	
	@Override
	public List<CustomerPaperDO> getUserList(String userId) {
		return customerPaperDao.getUserList(userId);
	}

	@Override
	public List<Object> userList() {
		List<Object> list2 = new ArrayList<>();
		List<CustomerPaperDO> userList = customerPaperDao.queryUserList();
		for (CustomerPaperDO customerPaperDO : userList) {
			List<CustomerPaperDO> answer = customerPaperDao.queryAnswer(customerPaperDO.getUserId());
			if(answer.size()>0){
				Map<String, Object> params = new HashMap<String, Object>();
				for (CustomerPaperDO customerPaperDO2 : answer) {
					params.put(customerPaperDO2.getContent(), customerPaperDO2.getTiankonganswer());
				}
				params.put("userId", customerPaperDO.getUserId());
				list2.add(params);
			}
			
		}
		return list2;				
		
	}


	
}
