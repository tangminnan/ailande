package com.gene.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.gene.information.domain.AnswerDO;
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
	
	List<CustomerPaperDO> queryUserQuestionDetails(String id);
	
	/*CustomerPaperDO getChoiceContent(Integer id);*/
	
	List<CustomerPaperDO> getUserList(String userId);
		
	List<CustomerPaperDO> queryAnswer(String userId);
	
	List<CustomerPaperDO> queryUserList();

	List<AnswerDO> listAnswerDO(Map<String, Object> map);

	int countAnswerDO(Map<String, Object> map);


}
