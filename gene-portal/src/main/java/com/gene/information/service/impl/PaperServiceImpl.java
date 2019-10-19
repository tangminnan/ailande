package com.gene.information.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gene.common.utils.R;
import com.gene.information.dao.PaperDao;
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
	public Map<String, Object> getAllProduct() {
		List<ProductDO> list=paperDao.getAllProduct();
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
	public CustomerPaperDO saveChoosedProduct(Integer[] products,String name) {
		CustomerPaperDO customerPaperDO=new CustomerPaperDO();
		customerPaperDO.setUsername(name);
		customerPaperDO.setCreateTime(new Date());
		paperDao.saveCustomerPaperDO(customerPaperDO);
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<products.length;i++){
			ProductDO productDO=paperDao.getProductByProductId(products[i]);
			if(productDO!=null){
				ProductpaperDO productpaperDO=new ProductpaperDO();
				productpaperDO.setPaper(productDO.getPaperId());
				productpaperDO.setProduct(products[i]);
				productpaperDO.setStatus("0");//未完成
				productpaperDO.setAnswerTime(new Date());
				productpaperDO.setRemark("用户答题");
				productpaperDO.setUser(customerPaperDO.getId());
				paperDao.saveProductPaperDO(productpaperDO);
				list.add(productpaperDO.getId());
			}
		}
		customerPaperDO.setList(list);
		return customerPaperDO;
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
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<products.length;i++){
			ProductDO productDO=paperDao.getProductByProductId(products[i]);
			set.add(productDO.getPaperId());
		}
		List<QuestionDO> list = new ArrayList<QuestionDO>();
		for(Integer i:set){
			list.addAll(paperDao.getQuestionDOType(i,flag));
		}
		return list;
	}

	@Override
	public R saveWenJuan(String objs, HttpServletRequest request) {
		JSONArray jsonArray = JSON.parseArray(objs);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Integer choiceId=jsonObject.getInteger("choiceId");
			Integer questionId=jsonObject.getInteger("questionId");
			Integer customerPaperId=jsonObject.getInteger("customerPaperId");
			Integer paperId=jsonObject.getInteger("paperId");
			CustomerPaperDO customerPaperDO= (CustomerPaperDO) request.getSession().getAttribute("customerPaperDO");
			
		}
		return null;
	}
}
