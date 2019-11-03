package com.gene.information.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.gene.common.utils.StringUtils;
import com.gene.information.dao.PaperDao;
import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.service.PaperService;

@Service
@Transactional
public class PaperServiceImpl implements PaperService{

	@Autowired
	private PaperDao paperDao;
	/**
	 * 获取所有的产品
	 */
	@Override
	public Map<String, Object> getAllProduct(HttpServletRequest request) {
		String timeString= (String)request.getSession().getAttribute("timeString");
		List<ProductDO> list=paperDao.getAllProduct();
		for(ProductDO productDO :list){
			productDO.setIfCheck(0);//未检测
			ProductpaperDO productpaperDO = new ProductpaperDO();
			productpaperDO.setUser(request.getSession().getId()+timeString);
			productpaperDO.setProduct(productDO.getId());
			List<ProductpaperDO> productpaperDOList = paperDao.listProductPaperDO(productpaperDO);
			if(productpaperDOList.size()>0){
				if("0".equals(productpaperDOList.get(0).getStatus()))
					productDO.setIfCheck(0);//未检测
				else
					productDO.setIfCheck(1);//已检测
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		map.put("msg", "success");
		map.put("data",list);
		return map;
	}
	
	/**
	 * 保存用户所选的产品
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveChoosedProduct(Integer[] products,HttpServletRequest request) {
		if(request.getSession().getAttribute("productpaper")==null)
			request.getSession().setAttribute("productpaper",new ArrayList<Integer>());
		String timeString= (String)request.getSession().getAttribute("timeString");
	
		for(int i=0;i<products.length;i++){
			ProductDO productDO=paperDao.getProductByProductId(products[i]);
			if(productDO!=null){
				ProductpaperDO productpaperDO=new ProductpaperDO();
				productpaperDO.setPaper(productDO.getPaperId());
				productpaperDO.setProduct(products[i]);
				productpaperDO.setStatus("0");//未答完题
				productpaperDO.setAnswerTime(new Date());
				productpaperDO.setRemark("用户答题");
				productpaperDO.setUser(request.getSession().getId()+timeString);
			    List<ProductpaperDO> listaa = paperDao.getProductPaperDO2(request.getSession().getId()+timeString, products[i]);
			    if(listaa.size()>0){
			    	continue;
			    }
			    else{
			    	paperDao.saveProductPaperDO(productpaperDO);
			    	List<Integer> list = (List<Integer>) request.getSession().getAttribute("productpaper");
			    	list.add(productpaperDO.getId());
			    	request.getSession().setAttribute("productpaper", list);
			    }
				
			}
		}
		
	}

	@Override
	public void updateCustomerPaperDO(CustomerPaperDO customerPaperDO) {
		paperDao.updateCustomerPaperDO(customerPaperDO);
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
	public R saveWenJuan(String objs, HttpServletRequest request) {
		JSONArray jsonArray = JSON.parseArray(objs);
		String fenleifenlei="";
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
			if(contenw!=null){
				if(contenw.contains("身高")) request.getSession().setAttribute("total", tiankonganswer);
				if(contenw.contains("体重")) request.getSession().setAttribute("weight",tiankonganswer );
				if(contenw.contains("姓名")) request.getSession().setAttribute("name",tiankonganswer );
				if(contenw.contains("手机号")) request.getSession().setAttribute("phone",tiankonganswer );}
			List<Integer> list=  (List<Integer>) request.getSession().getAttribute("productpaper");
			for(int j=0;j<list.size();j++){
				ProductpaperDO productpaperDO2 = paperDao.getProductpaperDO(list.get(j));
				ChoiceProductDO choiceProductDO2 = paperDao.getChoiceProductDO(productpaperDO2.getProduct(), choiceId);
				if(choiceProductDO2!=null && choiceProductDO2.getScore()==null && !"JIBEN_XINXI".equals(fenlei))
					continue;
				AnswerDO answerDO = new AnswerDO();
				answerDO.setProductpaper(list.get(j));//记录的ID
				
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
					float high = Float.parseFloat((String) request.getSession().getAttribute("total"));
					float weight=  Float.parseFloat((String) request.getSession().getAttribute("weight"));
					DecimalFormat df = new DecimalFormat("0.0");
					String bmi=  df.format(weight/high/high*10000);
					request.getSession().setAttribute("bmi", bmi);
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
		List<Integer> list=  (List<Integer>) request.getSession().getAttribute("productpaper");
		for(int j=0;j<list.size();j++){
			if(!"JIBEN_XINXI".equals(fenleifenlei)){
				paperDao.updateproductpaperDOStstus(list.get(j));
			}	
		}
		return R.ok();
	}

	@Override
	public List<ProductpaperDO> getProductPaperDO2(String user, Integer product) {
		return paperDao.getProductPaperDO2(user,product);
	}

	

	@Override
	public List<QuestionDO> getQuestionDOList(Integer productpaper, String fenlei) {
		return paperDao.getQuestionDOList(productpaper,fenlei);
	}
	/**
	 * 计算得分
	 */
	@Override
	public Integer getChoicedScores(Integer productpaper, String fenlei) {
		return paperDao.getChoicedScores(productpaper,fenlei);
	}

	/**
	 * 计算分类总分
	 */
	@Override
	public Integer getAllChoicedScores(Integer productpaper,Integer product, String fenlei) {
		int max=0;//要返回的最高分
		List<QuestionDO> questionDOList = paperDao.getQuestionDOType(product,fenlei);//分类下的所有题目
		if(questionDOList==null || questionDOList.size()==0) return 0;
		for(QuestionDO questionDO :questionDOList){
			List<ChoiceDO> choiceDOList = questionDO.getChoiceList();//题目下的所有选项
			List<Integer> cmaxList = new ArrayList<Integer>();
			for(ChoiceDO choiceDO :choiceDOList){
				
				ChoiceProductDO choiceProductDO = choiceDO.getChoiceProductList().get(0);//选项下的所有分值  我靠
				Integer cmax=0; 
				Integer score = choiceProductDO.getScore();
				Integer scores = choiceProductDO.getScores();
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
	private Integer getMaxFenInList(List<Integer> list){
		if(list==null || list.size()==0)  return 0;
		int max=list.get(0);
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
	public List<String> getChoosedContent(String id, Integer product, String string) {
		return paperDao.getChoosedContent(id,product,string);
	}
}
