package com.yanke.information.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yanke.information.domain.ResultEyeaxisDO;

/**
 * 眼轴长度
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-16 09:43:26
 */
@Mapper
public interface ResultEyeaxisDao {
	void saveEyeaxisDO(ResultEyeaxisDO resultEyeaxisDO);
	ResultEyeaxisDO getEyeaxisDO(@Param("studentId") Long studentId,@Param("lastCheckTime") Date lastCheckTime);

	void updateEyeaxisDO(ResultEyeaxisDO resultEyeaxisDO);
}
