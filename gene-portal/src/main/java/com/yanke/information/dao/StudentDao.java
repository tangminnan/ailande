package com.yanke.information.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yanke.information.domain.ResultAdjustingDO;
import com.yanke.information.domain.ResultCornealDO;
import com.yanke.information.domain.ResultDiopterDO;
import com.yanke.information.domain.ResultEyeaxisDO;
import com.yanke.information.domain.ResultEyepressureDO;
import com.yanke.information.domain.ResultEyesightDO;
import com.yanke.information.domain.ResultOptometryDO;
import com.yanke.information.domain.ResultVisibilityDO;
import com.yanke.information.domain.StudentDO;

/**
 * 学生表
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-08-14 17:21:00
 */
@Mapper
public interface StudentDao {

	StudentDO get(Long id);

	void updateLastCheckTime(@Param("studentId") Long studentId, @Param("date") Date date);

	List<StudentDO> getStudentInfo(String identityCard);
	
	/*List<StudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<StudentDO> getList();
*/
	
}
