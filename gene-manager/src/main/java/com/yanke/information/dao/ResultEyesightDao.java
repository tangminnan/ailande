package com.yanke.information.dao;

import com.yanke.information.domain.ResultEyesightDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 视力检查
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 14:08:23
 */
@Mapper
public interface ResultEyesightDao {

	ResultEyesightDO get(Integer tEyesightId);
	
	List<ResultEyesightDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ResultEyesightDO resultEyesight);
	
	int update(ResultEyesightDO resultEyesight);
	
	int remove(Integer t_eyesight_id);
	
	int batchRemove(Integer[] tEyesightIds);
}
