package com.gene.information.dao;

import com.gene.information.domain.AnswerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷表
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
@Mapper
public interface AnswerDao {

	AnswerDO get(Integer id);
	
	List<AnswerDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AnswerDO answer);
	
	int update(AnswerDO answer);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
