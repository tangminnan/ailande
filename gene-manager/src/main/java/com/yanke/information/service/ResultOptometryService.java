package com.yanke.information.service;

import com.yanke.information.domain.ResultOptometryDO;

import java.util.List;
import java.util.Map;

/**
 * 验光数据表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 14:08:23
 */
public interface ResultOptometryService {
	
	ResultOptometryDO get(Integer tOptometryId);
	
	List<ResultOptometryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ResultOptometryDO resultOptometry);
	
	int update(ResultOptometryDO resultOptometry);
	
	int remove(Integer tOptometryId);
	
	int batchRemove(Integer[] tOptometryIds);
}
