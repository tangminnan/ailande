package com.gene.information.dao;

import com.gene.information.domain.ReportTalkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 报告话术
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-03-18 10:49:48
 */
@Mapper
public interface ReportTalkDao {

	ReportTalkDO get(Integer id);
	
	List<ReportTalkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReportTalkDO reportTalk);
	
	int update(ReportTalkDO reportTalk);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
