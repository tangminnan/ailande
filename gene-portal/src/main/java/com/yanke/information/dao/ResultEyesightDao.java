package com.yanke.information.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yanke.information.domain.ResultEyesightDO;

/**
 * 视力检查
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 09:43:26
 */
@Mapper
public interface ResultEyesightDao {
	void saveEyesightDO(ResultEyesightDO resultEyesightDO);
	ResultEyesightDO getEyesightDO(@Param("studentId") Long studentId,@Param("lastCheckTime") Date lastCheckTime);
	void updateEyesightDO(ResultEyesightDO resultEyesightDO);
}
