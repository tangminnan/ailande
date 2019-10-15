package com.gene.information.dao;

import com.gene.information.domain.PaperEntity;
import com.gene.information.domain.ProductDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品表
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
@Mapper
public interface ProductDao {

	ProductDO get(Integer id);
	
	List<ProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<PaperEntity> paperList();
}
