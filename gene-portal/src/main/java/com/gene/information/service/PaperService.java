package com.gene.information.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.gene.common.utils.R;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.QuestionDO;

public interface PaperService {
    /**
     * 获取所有的产品
     */
	Map<String, Object> getAllProduct();

	CustomerPaperDO saveChoosedProduct(Integer[] products,String name);
    /**
     * 保存用户的基本信息
     */
	void updateCustomerPaperDO(CustomerPaperDO customerPaperDO);
	
	/**
	 * 查询问卷题目
	 */
	List<QuestionDO> getQuestionDOType(Integer[] products, String flag);
	/**
	 *保存问卷答题
	 */
	R saveWenJuan(String objs, HttpServletRequest request);

}
