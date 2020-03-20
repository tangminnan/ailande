package com.gene.information.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.gene.common.utils.R;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.domain.ReportTalkDO;

public interface PaperService {
    /**
     * 获取所有的产品
     */
	Map<String, Object> getAllProduct(String openid);

	void saveChoosedProduct(Integer[] products,HttpServletRequest request,String openid);
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
	R saveWenJuan(String objs, HttpServletRequest request,String openid);

	/**
	 *获取记录
	 */
	List<ProductpaperDO> getProductPaperDO2(String user, Integer product);
	
	List<ProductpaperDO> getProductpaperDOByOpenId(String openid, Integer product,Date date);
	
	CustomerPaperDO getLatestCustomerPaperDO(String openid,Integer product);

	List<QuestionDO> getQuestionDOList(Integer productpaper, String fenlei);
	/**
	 * 计算提分类得分
	 */
	Integer getChoicedScores(Integer productpaper, String fenlei);
	/**
	 * 计算分类总分
	 */
	Integer getAllChoicedScores(Integer productpaper,Integer product, String fenlei);
	
	int getgetQuestionDOSize(Integer[] products);

	List<Float> getSingleJiBenXinxi(String user,Integer product, String string);
	/**\
	 * 是否有赘肉
	 */
	List<String> getChoosedContent(String id, Integer product, String string,Date date);

	/**根据openid和产品去拿最新的检测结果
	 */
	List<ProductpaperDO> getNewProductpaperDO(String openid, Integer product,Date date);

	R saveCustomerPaperDO(CustomerPaperDO customerPaperDO);
	/**
	 * 获取最新选择的产品
	 */
	List<ProductpaperDO> getLatestProductpaperDO(String openid);

	ProductpaperDO getLatestProductPaper(String openid, int i);
	
	void updateProductPaper(ProductpaperDO productpaperDO);

	List<ReportTalkDO> listReportTalk(HashMap<String, Object> hashMap);
	/**
	 * 获取检查的历史记录时间
	 */
	List<ProductpaperDO> getHistoryRecord(String openid,Integer product);

	CustomerPaperDO getCustomerPaperDO(String openid, Integer product, Date date);

	
}
