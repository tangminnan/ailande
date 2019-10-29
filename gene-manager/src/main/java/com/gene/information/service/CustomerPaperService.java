package com.gene.information.service;

import java.util.List;
import java.util.Map;

import com.gene.information.domain.CustomerPaperDO;

/**
 * 客户问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 11:20:31
 */
public interface CustomerPaperService{
	
	CustomerPaperDO get(Integer id);
	
	List<CustomerPaperDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CustomerPaperDO customerPaper);
	
	int update(CustomerPaperDO customerPaper);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<CustomerPaperDO> queryUserQuestionDetails(Integer id);
	
	/*CustomerPaperDO getChoiceContent(Integer id);*/
	
	List<CustomerPaperDO> getUserList(Long userId);
	
	List<CustomerPaperDO> getUserListId();
	
	List<Object> userList();
	
}
