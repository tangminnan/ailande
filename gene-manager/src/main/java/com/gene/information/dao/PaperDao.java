package com.gene.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gene.common.utils.R;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.PaperDO;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.QuestionDO;

/**
 * 问卷 数据层
 * 
 * @author gene
 * @date 2019-08-27
 */
@Mapper
public interface PaperDao{

	List<PaperDO> list(Map<String, Object> map);

	int update(PaperDO paperDO);

	int count(Map<String, Object> map);

	int saveQuPaper(PaperDO paper);

	void saveQuestionDO(QuestionDO question);

	void saveChoiceDO(ChoiceDO choice);

	PaperDO getPaperById(Integer id);

	int  removeChoice(Integer id);

	int removeQuestion(Integer id);

	void updateQuestion(QuestionDO q);

	void updateChoice(ChoiceDO choice);

	List<ProductDO> listAll();

	void saveChoiceProductDO(ChoiceProductDO choiceProductDO);

	void updateChoiceProductDO(ChoiceProductDO choiceProductDO);
}