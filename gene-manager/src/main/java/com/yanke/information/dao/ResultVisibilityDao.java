package com.yanke.information.dao;

import com.yanke.information.domain.ResultVisibilityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 14:08:23
 */
@Mapper
public interface ResultVisibilityDao {

	ResultVisibilityDO get(Integer tVisibilityId);
	
	List<ResultVisibilityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ResultVisibilityDO resultVisibility);
	
	int update(ResultVisibilityDO resultVisibility);
	
	int remove(Integer t_visibility_id);
	
	int batchRemove(Integer[] tVisibilityIds);
}
