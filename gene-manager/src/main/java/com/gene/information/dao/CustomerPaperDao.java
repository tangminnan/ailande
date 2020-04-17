package com.gene.information.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;

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
	/******************************************转PDF*****************************************/
	List<ProductpaperDO> getNewProductpaperDO(String openid, Integer product, Date date);

	Double getChoicedScores(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);

	List<QuestionDO> getQuestionDOType(@Param("product") Integer product,@Param("fenlei") String fenlei);

	List<ReportTalkDO> listReportTalk(HashMap<String, Object> hashMap);

	CustomerPaperDO getCustomerPaperDO(String openid, Integer product, Date date);

	List<ProductpaperDO> getProductPaperDObyOpenid(String openid, Integer product, Date date);

	List<QuestionDO> getQuestionDOList(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);

	List<String> getChoosedContent(String id, Integer product, String string, Date date);

	CustomerPaperDO getCustomerPaperDOByUser(Integer productpaper);

	List<String> getChoosedContentBySomeString(@Param("productpaper") Integer productpaper,@Param("string") String string);


}
