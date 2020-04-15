package com.gene.information.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.Lcase;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gene.common.utils.R;
import com.gene.common.utils.ShiroUtils;
import com.gene.common.utils.StringUtils;
import com.gene.information.dao.PaperDao;
import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;
import com.gene.information.service.PaperService;

@Service
@Transactional
public class PaperServiceImpl implements PaperService{

	@Autowired
	 PaperDao paperDao;
	 String hightem = "";
	 String weighttmp = "";
	 Integer chanpin1 = 0;
	 Integer chanpin2 = 0;
	 CustomerPaperDO udo123 = new CustomerPaperDO();
	/**
	 * 获取所有的产品
	 */
	@Override
	public Map<String, Object> getAllProduct(String openid) {
		List<ProductDO> list=paperDao.getAllProduct();
		for(ProductDO productDO :list){
			productDO.setIfCheck(0);//未检测   默认的状态
			Integer product = productDO.getId();			   
			List<ProductpaperDO> productpaperDOList = paperDao.listProductPaperDOByOpenIdAndProduct(openid,product);
			if(productpaperDOList.size()>0)
					productDO.setIfCheck(1);//已检测
			
			}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", "success");
		map.put("data",list);
		return map;
	}
	
	/**
	 * 保存用户所选的产品
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveChoosedProduct(Integer[] products,HttpServletRequest request,String openid) {
		/*if(request.getSession().getAttribute("productpaper")==null)
			request.getSession().setAttribute("productpaper",new ArrayList<Integer>());
		String timeString= (String)request.getSession().getAttribute("timeString");*/
		Date date = new Date();	
		for(int i=0;i<products.length;i++){
			ProductDO productDO=paperDao.getProductByProductId(products[i]);
			if(productDO!=null){
				ProductpaperDO productpaperDO=new ProductpaperDO();
				productpaperDO.setPaper(productDO.getPaperId());
				productpaperDO.setProduct(products[i]);
				productpaperDO.setStatus("0");
				productpaperDO.setAnswerTime(date);
				productpaperDO.setRemark("用户答题");
				productpaperDO.setAnswerStatus(1);//要答题
			/*	productpaperDO.setUser(request.getSession().getId()+timeString);*/
				productpaperDO.setOpenid(openid);
//			    List<ProductpaperDO> listaa = paperDao.getProductPaperDO2(request.getSession().getId()+timeString, products[i]);
//			    if(listaa.size()>0){
//			    	continue;
//			    }
//			    else{
			    	paperDao.saveProductPaperDO(productpaperDO);
//			    	List<Integer> list = (List<Integer>) request.getSession().getAttribute("productpaper");
//			    	list.add(productpaperDO.getId());
//			    	request.getSession().setAttribute("productpaper", list);
//			    }
				
			}
		}
		
	}

	@Override
	public void updateCustomerPaperDO(CustomerPaperDO customerPaperDO) {
		paperDao.updateCustomerPaperDO(customerPaperDO);
	}

	
	
	@Override
	public CustomerPaperDO getLatestCustomerPaperDO(String openid,Integer product) {
		return paperDao.getLatestCustomerPaperDO(openid,product);
		
	}
	
	 
	/**
	 * 查询题目
	 */
	@Override
	public List<QuestionDO> getQuestionDOType(Integer[] products, String flag) {
		Map<Integer,QuestionDO> map = new HashMap<Integer,QuestionDO>();
		for(int i=0;i<products.length;i++){
			List<QuestionDO> questionDOList = paperDao.getQuestionDOType(products[i],flag);
			for(QuestionDO questionDO :questionDOList){
				map.put(questionDO.getId(), questionDO);
			}
		}
		List<QuestionDO> list =new ArrayList<QuestionDO>(map.values());
		return list; 
	}

	@Override
	public R saveWenJuan(String objs, HttpServletRequest request,String openid) {
		JSONArray jsonArray = JSON.parseArray(objs);
		String fenleifenlei="";
		Integer cwid = 0 ;
		Integer ssid = 0 ;
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Integer choiceId=jsonObject.getInteger("choiceId");//选项ID
			Integer questionId=jsonObject.getInteger("questionId");//题目ID
			Integer customerPaperId=jsonObject.getInteger("customerPaperId");//问卷ID
			String fenlei=jsonObject.getString("fenlei");
			fenleifenlei=fenlei;
			String tiankonganswer=jsonObject.getString("tiankonganswer");
			String contenw=jsonObject.getString("contenw");
			String ifStop=jsonObject.getString("ifStop");
			udo123.setOpenid(openid);
			
			System.out.println("openid======================================="+openid);
			/*List<Integer> list=  ( List<Integer>) request.getSession().getAttribute("productpaper");*/
			List<ProductpaperDO> productpaperDOList = paperDao.getProductpaperDOByOpenId(openid);
			List<ProductpaperDO> productpaperDOListTmp = new  ArrayList<ProductpaperDO>();
			System.out.println("idiididid====="+productpaperDOList.get(0).getId());
			if(productpaperDOList.size()>1){
				if(productpaperDOList.get(0).getProduct() == productpaperDOList.get(1).getProduct()){
					productpaperDOListTmp.add(productpaperDOList.get(0));
						cwid = productpaperDOList.get(0).getId();
						chanpin1=productpaperDOList.get(0).getProduct();
				}else{
					if(	productpaperDOList.get(0).getId()-productpaperDOList.get(1).getId() > 1){
						productpaperDOListTmp.add(productpaperDOList.get(0));
						cwid = productpaperDOList.get(0).getId();
						chanpin1=productpaperDOList.get(0).getProduct();
					}else{
						productpaperDOListTmp.add(productpaperDOList.get(0));
						productpaperDOListTmp.add(productpaperDOList.get(1));	
							cwid = productpaperDOList.get(0).getId();
							ssid = productpaperDOList.get(1).getId();
							chanpin1=productpaperDOList.get(0).getProduct();
							chanpin2=productpaperDOList.get(1).getProduct();
					}
					
				}
			}
			for(ProductpaperDO productpaperDO2 :productpaperDOListTmp){
				ChoiceProductDO choiceProductDO2 = paperDao.getChoiceProductDO(productpaperDO2.getProduct(), choiceId);
				if(choiceProductDO2!=null && choiceProductDO2.getScore()==null && !"JIBEN_XINXI".equals(fenlei))
					continue;
				AnswerDO answerDO = new AnswerDO();
				System.out.println("记录id======"+productpaperDO2.getId());
				answerDO.setProductpaper(productpaperDO2.getId());//记录的ID
				
				answerDO.setChoiceId(choiceId);//选项ID
				answerDO.setCustomerPaperId(customerPaperId);//问卷ID
				answerDO.setQuestionId(questionId);//问题ID
			    answerDO.setFenlei(fenlei);
			    answerDO.setTiankonganswer(tiankonganswer);
				/***************以下的代码有点垃圾，执行效率慢，时间紧张，有空的时候优化下*****/
				ProductpaperDO productpaperDO = paperDao.getProductpaperDO(answerDO.getProductpaper());
				if(productpaperDO!=null && !"JIBEN_XINXI".equals(fenlei)){
					Integer product = productpaperDO.getProduct();
					ChoiceProductDO choiceProductDO = paperDao.getChoiceProductDO(product,answerDO.getChoiceId());
					CustomerPaperDO udo1 = paperDao.getLatestCustomerPaperDO(openid,product);
					DecimalFormat df = new DecimalFormat("0.0");  
					Integer high = udo1.getHigh();
					float weight=  udo1.getWeight();  
					String bmi=  df.format(weight/high/high*10000);
					if(choiceProductDO!=null){
						if("YUN_DONG".equals(ifStop)){
							if(Float.parseFloat(bmi)<29)
								answerDO.setScore(choiceProductDO.getScores());
							
							else
								answerDO.setScore(choiceProductDO.getScore());
						}
						else if("QI_TA".equals(ifStop))
							answerDO.setScore(choiceProductDO.getScore());
					}
				}
				/***************以下的代码有点垃圾，执行效率慢，时间紧张，有空的时候优化下*****/
			
				paperDao.saveAnswerDO(answerDO);
			}
		}
	/*	List<Integer> list=  (List<Integer>) request.getSession().getAttribute("productpaper");
		for(int j=0;j<list.size();j++){
			if(!"JIBEN_XINXI".equals(fenleifenlei)){
				paperDao.updateproductpaperDOStstus(list.get(j));
			}	
		}*/
		
		if(!"JIBEN_XINXI".equals(fenleifenlei)){
			System.out.println("执行到此");
			System.out.println("执行到此");
			System.out.println("执行到此");
			System.out.println("执行到此");
			System.out.println("执行到此");
			
			paperDao.updateproductpaperDOStstus(cwid);
			if(ssid>0){
				paperDao.updateproductpaperDOStstus(ssid);
			}
			
		}
		if("JIBEN_XINXI".equals(fenleifenlei)){
			

			/*if(chanpin1>0){
				udo123.setAge(chanpin1);
				 paperDao.saveCustomerPaperDO(udo123);
			}
			if(chanpin2>0){
				udo123.setAge(chanpin2);
				 paperDao.saveCustomerPaperDO(udo123);
			}	*/
		}
		return R.ok();
	}

	@Override
	public List<ProductpaperDO> getProductPaperDO2(String user, Integer product) {
		return paperDao.getProductPaperDO2(user,product);
	}
	

	@Override
	public List<ProductpaperDO> getProductpaperDOByOpenId(String openid, Integer product,Date date) {
		return paperDao.getProductPaperDObyOpenid(openid,product,date);
	}

	@Override
	public List<QuestionDO> getQuestionDOList(Integer productpaper, String fenlei) {
		return paperDao.getQuestionDOList(productpaper,fenlei);
	}
	/**
	 * 计算得分
	 */
	@Override
	public Double getChoicedScores(Integer productpaper, String fenlei) {
		return paperDao.getChoicedScores(productpaper,fenlei);
	}

	/**
	 * 计算分类总分
	 */
	@Override
	public Double getAllChoicedScores(Integer productpaper,Integer product, String fenlei) {
		double max=0.0;//要返回的最高分
		List<QuestionDO> questionDOList = paperDao.getQuestionDOType(product,fenlei);//分类下的所有题目
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
	
	/**
	 * 获取题目数量
	 */
	@Override
	public int getgetQuestionDOSize(Integer[] products) {
		/*Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<products.length;i++){
			ProductDO productDO=paperDao.getProductByProductId(products[i]);
			set.add(productDO.getPaperId());
		}
		int count=0;
		for(Integer i:set){
			count+=paperDao.countPaperQuestion(i);
		}
		
		return count;*/
		
		List<String> fenleiList = Arrays.asList("JIBEN_XINXI","SHENTI_ZHUANG","SHANSHI_XIGUAN","SHENGHUO_FANGSHI","SHUIMIAN_XIGUAN","YUNDONG_XIGUANG");
		Map<Integer,QuestionDO> map = new HashMap<Integer,QuestionDO>();
		for(int i=0;i<products.length;i++){
			for(String flag :fenleiList){
				List<QuestionDO> questionDOList = paperDao.getQuestionDOType(products[i],flag);
				for(QuestionDO questionDO :questionDOList){
					map.put(questionDO.getId(), questionDO);
				}
			}
		}
		List<QuestionDO> list =new ArrayList<QuestionDO>(map.values());
		return list.size(); 
	}

	/**
	 * 获取单个的基本信息
	 */
	@Override
	public List<Float> getSingleJiBenXinxi(String user,Integer product,String string) {
		return paperDao.getSingleJiBenXinxi(user,product,  string);
	}

	/**
	 * 查询用户的选项内容
	 */
	@Override
	public List<String> getChoosedContent(String id, Integer product, String string,Date date) {
		return paperDao.getChoosedContent(id,product,string,date);
	}

	/**
	 *根据openid和产品去拿最新的检测结果
	 */
	@Override
	public List<ProductpaperDO> getNewProductpaperDO(String openid, Integer product,Date date) {
		return paperDao.getNewProductpaperDO(openid,product,date);
	}

	@Override
	public R saveCustomerPaperDO(CustomerPaperDO customerPaperDO) {
		
		if(paperDao.saveCustomerPaperDO(customerPaperDO)>0){
			return R.ok();
		}
			
		return R.error();
	}
	/**
	 * 获取最新选择的产品
	 */
	@Override
	public List<ProductpaperDO> getLatestProductpaperDO(String openid) {
		return paperDao.getLatestProductpaperDO(openid);
	}

	@Override
	public ProductpaperDO getLatestProductPaper(String openid, int i) {
		return paperDao.getLatestProductPaper(openid,i);
	}

	@Override
	public void updateProductPaper(ProductpaperDO productpaperDO) {
		paperDao.updateProductPaper(productpaperDO);
	}

	/**
	 * 查询报告话术
	 */
	@Override
	public List<ReportTalkDO> listReportTalk(HashMap<String, Object> hashMap) {
		
		return paperDao.listReportTalk(hashMap);
	}

	/**
	 * 获取检查的历史记录时间
	 */
	@Override
	public List<ProductpaperDO> getHistoryRecord(String openid,Integer product) {
		return paperDao.getHistoryRecord(openid,product);
	}

	@Override
	public CustomerPaperDO getCustomerPaperDO(String openid, Integer product, Date date) {
		return paperDao.getCustomerPaperDO(openid,product,date);
	}

	/**
	 * 替换旧的openid
	 */
	@Override
	public void updateOldOpenId(String openid, Integer product, String guanjianzi,Date date) {
		paperDao.updateOldOpenId(openid,product,guanjianzi,date);
	}

	@Override
	public void updateOldOIpenIdCustomer(String openid, Integer product, String guanjianzi,Date date) {
		paperDao.updateOldOIpenIdCustomer(openid,product,guanjianzi,date);
	}

	@Override
	public int getOldCouunt(String openid, Integer product, String guanjianzi,Date date) {
		
		return paperDao.getOldCouunt(openid,product,guanjianzi,date);
	}

	/**
	 * 获取选择的产品啊
	 */
	@Override
	public List<ProductpaperDO> getChoosedProductByOpenId(String openid) {
		return paperDao.getChoosedProductByOpenId(openid);
	}

	
}
