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
	/*Collection<? extends QuestionDO> getQuestionDOType(@Param("integer") Integer integer,@Param("flag") String flag);*/

	int saveAnswerDO(AnswerDO answerDO);

	ProductpaperDO getProductpaperDO(Integer productpaper);

	ChoiceProductDO getChoiceProductDO(@Param("product") Integer product,@Param("choiceId") Integer choiceId);
	/**
	 * 获取所有检测过的产品
	 */
	List<ProductpaperDO> listProductPaperDO(ProductpaperDO productpaperDO);

	List<ProductpaperDO> getProductPaperDO2(@Param("user") String user,@Param("product") Integer product);
	
	List<ProductpaperDO> getProductPaperDObyOpenid(@Param("openid") String openid,@Param("product") Integer product);
	
	/**
	 * 按题目的分类查询用户答题及答案
	 */
	List<QuestionDO> getQuestionDOList(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);

	Integer getChoicedScores(@Param("productpaper") Integer productpaper,@Param("fenlei") String fenlei);
	
	Integer getAllChoicedScores(@Param("productpaper") Integer productpaper,@Param("product") Integer product,@Param("fenlei") String fenlei);
	/**
	 * 获取问卷的题目数量
	 */
	int countPaperQuestion(Integer i);

	void checkIfShow(QuestionDO questionDO);
	/**
	 * 获取单个的基本信息
	 */
	List<Float> getSingleJiBenXinxi(@Param("user") String user,@Param("product") Integer product, @Param("string") String string);

	/**
	 * 查询用户选择的内容
	 */
	List<String> getChoosedContent(@Param("openid") String id,@Param("product") Integer product,@Param("string") String string);

	/**
	 * 根据产品查询题目
	 */
	List<QuestionDO> getQuestionDOType(@Param("product") Integer product,@Param("flag") String flag);

	ProductpaperDO getProductPaperDO33(ProductpaperDO productpaperDO2);

	/**
	 * 修改状态
	 * @param integer
	 */
	void updateproductpaperDOStstus(Integer integer);

	/**
	 * 根据微信id和产品去拿最新的检测结果
	 */
	List<ProductpaperDO> getNewProductpaperDO(@Param("openid") String openid,@Param("product") Integer product);
	/**
	 * openid  
	 * @param openid
	 * @param product
	 * @return
	 */
	List<ProductpaperDO> listProductPaperDOByOpenIdAndProduct(@Param("openid") String openid,@Param("product") Integer product);

	List<ProductpaperDO> getProductpaperDOByOpenId(String openid);

	CustomerPaperDO getLatestCustomerPaperDO(@Param("openid")String openid,@Param("product")Integer product);
	/**
	 * 获取最新选择的产品
	 */
	List<ProductpaperDO> getLatestProductpaperDO(String openid);

	ProductpaperDO getLatestProductPaper(@Param("openid") String openid,@Param("product") int i);

	void updateProductPaper(ProductpaperDO productpaperDO);
    

}
