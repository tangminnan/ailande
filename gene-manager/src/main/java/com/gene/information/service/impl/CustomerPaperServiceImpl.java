package com.gene.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gene.common.utils.Query;
import com.gene.information.dao.CustomerPaperDao;
import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;
import com.gene.information.service.CustomerPaperService;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public List<AnswerDO> listAnswerDO(Map<String, Object> map) {
		return customerPaperDao.listAnswerDO(map);
	}

	@Override
	public int countAnswerDO(Map<String,Object> map) {
		return customerPaperDao.countAnswerDO(map);
	}
    /************************************转PDF****************************************/
	@Override
	public List<ProductpaperDO> getNewProductpaperDO(String openid, Integer product, Date date) {
		return customerPaperDao.getNewProductpaperDO(openid,product,date);
	}
	@Override
	public Double getChoicedScores(Integer productpaper, String fenlei) {
		return customerPaperDao.getChoicedScores(productpaper,fenlei);
	}
	@Override
	public Double getAllChoicedScores(Integer productpaper,Integer product, String fenlei) {
		double max=0.0;//要返回的最高分
		List<QuestionDO> questionDOList = customerPaperDao.getQuestionDOType(product,fenlei);//分类下的所有题目
		if(questionDOList==null || questionDOList.size()==0) return 0.0;
		for(QuestionDO questionDO :questionDOList){
			List<ChoiceDO> choiceDOList = questionDO.getChoiceList();//题目下的所有选项
			List<Double> cmaxList = new ArrayList<Double>();
			for(ChoiceDO choiceDO :choiceDOList){
				
				ChoiceProductDO choiceProductDO = choiceDO.getChoiceProductList().get(0);//选项下的所有分值  我靠
				Double cmax=0.0; 
				Double score = choiceProductDO.getScore();
				Double scores = choiceProductDO.getScores();
				score= score==null?0:score;
				scores=scores==null?0:scores;
				cmax=score>scores ? score:scores;
				cmaxList.add(cmax);
				//获取最大的分值
			}
			max+=getMaxFenInList(cmaxList);
		}  
		
		return max;
	}
	@Override
	public List<ReportTalkDO> listReportTalk(HashMap<String, Object> hashMap) {
		
		return customerPaperDao.listReportTalk(hashMap);
	}
	@Override
	public CustomerPaperDO getCustomerPaperDO(String openid, Integer product, Date date) {
		return customerPaperDao.getCustomerPaperDO(openid,product,date);
	}
	@Override
	public List<ProductpaperDO> getProductpaperDOByOpenId(String openid, Integer product,Date date) {
		return customerPaperDao.getProductPaperDObyOpenid(openid,product,date);
	}
	@Override
	public List<QuestionDO> getQuestionDOList(Integer productpaper, String fenlei) {
		return customerPaperDao.getQuestionDOList(productpaper,fenlei);
	}
	@Override
	public List<String> getChoosedContent(String id, Integer product, String string,Date date) {
		return customerPaperDao.getChoosedContent(id,product,string,date);
	}
	
	/**
	 * 获取最大的分值
	 */
	private Double getMaxFenInList(List<Double> list){
		if(list==null || list.size()==0)  return 0.0;
		double max=list.get(0);
		for(int i=1;i<list.size();i++){
			if(list.get(i)>max)
				max=list.get(i);
		}
		return max;
	}

	
	@Override
	public CustomerPaperDO getCustomerPaperDOByUser(Integer productpaper) {
		return customerPaperDao.getCustomerPaperDOByUser(productpaper);
	}

	@Override
	public List<String> getChoosedContentBySomeString(Integer productpaper, String string) {
		return customerPaperDao.getChoosedContentBySomeString(productpaper,string);
	}
}
