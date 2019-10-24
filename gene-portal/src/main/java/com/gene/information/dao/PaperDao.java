package com.gene.information.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gene.information.domain.AnswerDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.CustomerPaperDO;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.ProductpaperDO;
import com.gene.information.domain.QuestionDO;

@Mapper
public interface PaperDao {

	/**
	 * 获取所有的产品
	 */
	List<ProductDO> getAllProduct();

	ProductDO getProductByProductId(Integer integer);

	int saveProductPaperDO(ProductpaperDO productpaperDO);

	/**
	 * 保存参与测试的用户
	 */
	int saveCustomerPaperDO(CustomerPaperDO customerPaperDO);
	/**
	 * 保存用户的基本信息
	 */
	void updateCustomerPaperDO(CustomerPaperDO customerPaperDO);
	
	/**
	 * 查询题目
	 */
	Collection<? extends QuestionDO> getQuestionDOType(@Param("integer") Integer integer,@Param("flag") String flag);

	int saveAnswerDO(AnswerDO answerDO);

	ProductpaperDO getProductpaperDO(Integer productpaper);

	ChoiceProductDO getChoiceProductDO(@Param("product") Integer product,@Param("choiceId") Integer choiceId);
	/**
	 * 获取所有检测过的产品
	 */
	List<ProductpaperDO> listProductPaperDO(ProductpaperDO productpaperDO);

	ProductpaperDO getProductPaperDO(@Param("user") Integer user,@Param("product") Integer product);
	
	/**
	 * 按题目的分类查询用户答题及答案
	 */
	List<QuestionDO> getQuestionDOList(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);

	Integer getChoicedScores(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);
	
	Integer getAllChoicedScores(@Param("productpaper") Integer productpaper,@Param("product") Integer product,@Param("fenlei") String fenlei);

}
