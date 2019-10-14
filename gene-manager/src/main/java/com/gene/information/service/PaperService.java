package com.gene.information.service;

import java.util.List;
import java.util.Map;

import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.information.domain.PaperDO;

/**
 * 问卷 服务层
 * 
 * @author gene
 * @date 2019-08-27
 */
public interface PaperService{
    /**
    * 列表查询
    */
    List<PaperDO> list(Map<String,Object> map);

    
    PaperDO getPaperById(Integer id);

	int count(Map<String,Object> map);

	int update(PaperDO paperDO);


	R save(PaperDO paper);


	R removeChoice(Integer id);


	R removeQuestion(Integer id);


	R editSave(PaperDO paper);
}
