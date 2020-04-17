package com.gene.information.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gene.common.utils.Query;
import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;

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
	
	List<CustomerPaperDO> queryUserQuestionDetails(String id);
	
	List<CustomerPaperDO> getUserList(String userId);
				
	List<Object> userList();

	List<AnswerDO> listAnswerDO(Map<String,Object> map);

	int countAnswerDO(Map<String,Object> map);
	CustomerPaperDO getCustomerPaperDOByUser(Integer productpaper);
	List<String> getChoosedContentBySomeString(Integer productpaper, String string);

	/******************************转PDF*****************************************************/
	List<ProductpaperDO> getNewProductpaperDO(String openid, Integer product, Date date);

	Double getChoicedScores(Integer productpaper, String fenlei);

	Double getAllChoicedScores(Integer productpaper, Integer product, String fenlei);

	List<ReportTalkDO> listReportTalk(HashMap<String, Object> hashMap);

	CustomerPaperDO getCustomerPaperDO(String openid, Integer product, Date date);

	List<ProductpaperDO> getProductpaperDOByOpenId(String openid, Integer product, Date date);

	List<QuestionDO> getQuestionDOList(Integer productpaper, String fenlei);

	List<String> getChoosedContent(String openid, Integer product, String string, Date date);



	
}
