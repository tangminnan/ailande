package com.yanke.information.service;

import com.yanke.information.domain.ResultEyeaxisDO;

import java.util.List;
import java.util.Map;

/**
 * 眼轴长度
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 14:08:23
 */
public interface ResultEyeaxisService {
	
	ResultEyeaxisDO get(Integer tEyeaxisId);
	
	List<ResultEyeaxisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ResultEyeaxisDO resultEyeaxis);
	
	int update(ResultEyeaxisDO resultEyeaxis);
	
	int remove(Integer tEyeaxisId);
	
	int batchRemove(Integer[] tEyeaxisIds);
}
