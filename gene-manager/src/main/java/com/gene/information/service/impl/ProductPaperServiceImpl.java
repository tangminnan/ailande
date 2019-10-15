package com.gene.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gene.information.dao.ProductPaperDao;
import com.gene.information.domain.ProductPaperDO;
import com.gene.information.service.ProductPaperService;



@Service
public class ProductPaperServiceImpl implements ProductPaperService {
	@Autowired
	private ProductPaperDao productPaperDao;
	
	@Override
	public ProductPaperDO get(Integer id){
		return productPaperDao.get(id);
	}
	
	@Override
	public List<ProductPaperDO> list(Map<String, Object> map){
		return productPaperDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productPaperDao.count(map);
	}
	
	@Override
	public int save(ProductPaperDO productPaper){
		return productPaperDao.save(productPaper);
	}
	
	@Override
	public int update(ProductPaperDO productPaper){
		return productPaperDao.update(productPaper);
	}
	
	@Override
	public int remove(Integer id){
		return productPaperDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productPaperDao.batchRemove(ids);
	}
	
}
