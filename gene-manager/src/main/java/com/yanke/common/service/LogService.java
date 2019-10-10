package com.yanke.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yanke.common.domain.LogDO;
import com.yanke.common.domain.PageDO;
import com.yanke.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
