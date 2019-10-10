package com.yanke.information.dao;

import com.yanke.information.domain.ResultAdjustingDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 调节灵敏度
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 14:08:23
 */
@Mapper
public interface ResultAdjustingDao {

	ResultAdjustingDO get(Integer tAdjustingId);
	
	List<ResultAdjustingDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ResultAdjustingDO resultAdjusting);
	
	int update(ResultAdjustingDO resultAdjusting);
	
	int remove(Integer t_adjusting__id);
	
	int batchRemove(Integer[] tAdjustingIds);
}
