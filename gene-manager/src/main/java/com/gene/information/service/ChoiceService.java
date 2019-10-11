package com.gene.information.service;


import java.util.List;

import com.gene.information.domain.ChoiceDO;


/**
 * 选项 服务层
 * 
 * @author gene
 * @date 2019-08-27
 */
public interface ChoiceService{
    /**
    * 列表查询
    */
    List<ChoiceDO> list(ChoiceDO choice);
}
