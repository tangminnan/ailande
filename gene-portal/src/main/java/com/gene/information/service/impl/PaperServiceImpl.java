package com.gene.information.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gene.information.dao.PaperDao;
import com.gene.information.domain.ProductDO;
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

}
