package com.gene.information.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
