package com.gene.information.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gene.information.domain.ProductDO;

@Mapper
public interface PaperDao {

	/**
	 * 获取所有的产品
	 */
	List<ProductDO> getAllProduct();

}
