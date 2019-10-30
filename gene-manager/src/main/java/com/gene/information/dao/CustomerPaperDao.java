package com.gene.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gene.information.domain.CustomerPaperDO;

/**
 * 客户问卷表
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 11:20:31
 */
@Mapper
public interface CustomerPaperDao{

	CustomerPaperDO get(Integer id);
	
	List<CustomerPaperDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CustomerPaperDO customerPaper);
	
	int update(CustomerPaperDO customerPaper);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<CustomerPaperDO> queryUserQuestionDetails(Integer id);
	
	/*CustomerPaperDO getChoiceContent(Integer id);*/
	
	List<CustomerPaperDO> getUserList(Long userId);
		
	List<CustomerPaperDO> queryAnswer(Long userId);
	
	List<CustomerPaperDO> queryUserList();

}
