package com.gene.information.service;

import java.util.List;

import com.gene.information.domain.QuestionDO;

/**
 * 问卷 服务层
 * 
 * @author gene
 * @date 2019-08-27
 */
public interface QuestionService{
    /**
    * 列表查询
    */
    List<QuestionDO> list(QuestionDO question);
}
