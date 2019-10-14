package com.gene.information.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.gene.common.utils.DateUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.common.utils.ShiroUtils;
import com.gene.common.utils.StringUtils;
import com.gene.information.dao.PaperDao;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.PaperDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.service.ChoiceService;
import com.gene.information.service.PaperService;
import com.gene.information.service.QuestionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 问卷 服务层实现
 *
 * @author gene
 * @date 2019-08-27
 */

@Service
@Transactional
public class PaperServiceImpl implements PaperService{

	@Autowired
	private PaperDao paperDao;
	@Override
	public List<PaperDO> list(Map<String, Object> map) {
		return paperDao.list(map);
	}

	/**
	 * 查询问卷
	 */
	@Override
	public PaperDO getPaperById(Integer id) {
		return paperDao.getPaperById(id);
	}

	@Override
	public int count(Map<String,Object> map) {
		return paperDao.count(map);
	}



	@Override
	public int update(PaperDO paperDO) {
		return  paperDao.update(paperDO);
	}

	/**
	 * 保存客户问卷
	 */

	@Override
	public R save(PaperDO paper) {
		Date date=new Date();
	    paper.setCode(DateUtils.parseDateToStr("yyyyMMddHHmmss", new Date()));
	    paper.setCreateBy(ShiroUtils.getUser().getUsername());
	    paper.setCreateTime(date);
	    paper.setStatus("0");
	    paper.setDelFlag("0");
		if(paperDao.saveQuPaper(paper)>0){
			List<QuestionDO> questionList = paper.getQuestionList();
	        if (!CollectionUtils.isEmpty(questionList)) {
	            for (int i = 0; i < questionList.size(); i++) {
	                QuestionDO question = questionList.get(i);
	                question.setPaperId(paper.getId());
	                question.setSort(i);
	                question.setRequired("1");
	                question.setCreateBy(ShiroUtils.getUser().getUsername());
	                question.setCreateTime(date);
	                paperDao.saveQuestionDO(question);
	                List<ChoiceDO> choiceList = question.getChoiceList();
	                if (!CollectionUtils.isEmpty(choiceList)) {
	                    for (ChoiceDO choice : choiceList) {
	                        choice.setQuestion(question.getId());
	                        choice.setCreateBy(ShiroUtils.getUser().getUsername());
	                        choice.setCreateTime(date);
	                        paperDao.saveChoiceDO(choice);
	                    }
	                }
	            }
	        }
	        
	        return R.ok();
		}
		return R.error();	
	}

}
