package com.gene.information.service;

import com.gene.information.domain.ProductPaperDO;

import java.util.List;
import java.util.Map;

/**
 * 产品问卷中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
public interface ProductPaperService {
	
	ProductPaperDO get(Integer id);
	
	List<ProductPaperDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductPaperDO productPaper);
	
	int update(ProductPaperDO productPaper);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
