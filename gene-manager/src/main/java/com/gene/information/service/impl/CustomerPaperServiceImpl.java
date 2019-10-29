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
	public List<CustomerPaperDO> queryUserQuestionDetails(Integer id) {
		return customerPaperDao.queryUserQuestionDetails(id);
	}

	/*@Override
	public CustomerPaperDO getChoiceContent(Integer id) {
		return customerPaperDao.getChoiceContent(id);
	}*/

	@Override
	public List<CustomerPaperDO> getUserList(Long userId) {
		return customerPaperDao.getUserList(userId);
	}

	@Override
	public List<CustomerPaperDO> getUserListId() {
		return customerPaperDao.getUserListId();
	}

	@Override
	public List<Object> userList() {
		List<Object> list2 = new ArrayList<>();
		List<CustomerPaperDO> userListId = customerPaperDao.getUserListId();
		for (int i = 0; i < userListId.size(); i++) {
			Long userId = userListId.get(i).getUserId();
			List<CustomerPaperDO> list = customerPaperDao.getUserList(userId);
			if(list.size()>0){
				Map<String, Object> params = new HashMap<String, Object>();
//				System.out.println(JSON.toJSONString(list));
				for (int j = 0; j < list.size(); j++) {
					params.put(list.get(j).getContent(), list.get(j).getTiankonganswer());
				}
				params.put("userId", userId);
				params.put("username", list.get(0).getUsername());
			list2.add(params);
			}	
		}			
		return list2;
	}

	
}
