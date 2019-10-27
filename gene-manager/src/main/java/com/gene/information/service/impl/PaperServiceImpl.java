package com.gene.information.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gene.common.config.BootdoConfig;
import com.gene.common.utils.DateUtils;
import com.gene.common.utils.Query;
import com.gene.common.utils.R;
import com.gene.common.utils.ShiroUtils;
import com.gene.common.utils.StringUtils;
import com.gene.information.dao.PaperDao;
import com.gene.information.domain.ChoiceDO;
import com.gene.information.domain.ChoiceProductDO;
import com.gene.information.domain.PaperDO;
import com.gene.information.domain.ProductDO;
import com.gene.information.domain.QuestionDO;
import com.gene.information.service.ChoiceService;
import com.gene.information.service.PaperService;
import com.gene.information.service.QuestionService;

import sun.misc.BASE64Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	private BootdoConfig bootdoConfig;
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
	                String img = question.getImg();
	                if(!StringUtils.isBlank(img)){
	                	String  r = this.uploadImg("question",img, "png");
	                	question.setImg("/files/ailande"+r);
	                }
	        		if("".equals(question.getIfStop())) question.setIfStop("QI_TA");
	                paperDao.saveQuestionDO(question);
	                List<ChoiceDO> choiceList = question.getChoiceList();
	                for (ChoiceDO choice : choiceList) {
	            	   choice.setQuestion(question.getId());
	            	   choice.setCreateBy(ShiroUtils.getUser().getUsername());
	            	   choice.setCreateTime(date);
	            	   paperDao.saveChoiceDO(choice);
	            	   List<ChoiceProductDO> choiceProductList = choice.getChoiceProductList();
	                   for(ChoiceProductDO choiceProductDO :choiceProductList){
	                	   choiceProductDO.setChoose(choice.getId());
	                	   paperDao.saveChoiceProductDO(choiceProductDO);
	                   }
	                }
	            }
	        }
	        
	        return R.ok();
		}
		return R.error();	
	}

	@Override
	public R removeChoice(Integer id) {
		if(paperDao.removeChoice(id)>0)
			return R.ok();
		return R.error();
	}

	@Override
	public R removeQuestion(Integer id) {
		if(paperDao.removeQuestion(id)>0)
			return R.ok();
		return R.error();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public R editSave(PaperDO paper) {
		for(int i=0;i<paper.getRemovedQuestions().length;i++){
			paperDao.removeQuestion(paper.getRemovedQuestions()[i]);
		}
		for(int i=0;i<paper.getRemovedChooses().length;i++){
			paperDao.removeChoice(paper.getRemovedChooses()[i]);
		}
		Date date = new Date();
		paper.setUpdateBy(ShiroUtils.getUser().getUsername());
		paper.setUpdateTime(date);
		if(paperDao.update(paper)>0){
			for(QuestionDO q :paper.getQuestionList()){
				if(q.getId()!=null){
					q.setUpdateBy(ShiroUtils.getUser().getName());
					q.setUpdateTime(date);
					  String img = q.getImg();
		                if(!StringUtils.isBlank(img)){
		                	String  r = this.uploadImg("question",img, "png");
		                	q.setImg("/files/ailande"+r);
		                }
					paperDao.updateQuestion(q);
					for (ChoiceDO choice : q.getChoiceList()) {
						if(choice.getId()!=null){
							choice.setUpdateBy(ShiroUtils.getUser().getUsername());
							choice.setUpdateTime(date);
							paperDao.updateChoice(choice);
							 List<ChoiceProductDO> choiceProductList = choice.getChoiceProductList();
							 for(ChoiceProductDO choiceProductDO :choiceProductList){
								 paperDao.updateChoiceProductDO(choiceProductDO);
							 }
						}
						else{
							choice.setQuestion(q.getId());
							choice.setCreateBy(ShiroUtils.getUser().getUsername());
			            	choice.setCreateTime(date);
			            	paperDao.saveChoiceDO(choice);
			            	List<ChoiceProductDO> choiceProductList = choice.getChoiceProductList();
			                for(ChoiceProductDO choiceProductDO :choiceProductList){
			                	choiceProductDO.setChoose(choice.getId());
			                	paperDao.saveChoiceProductDO(choiceProductDO);
			                }
						}
                    }
				}
			
			else{
				q.setPaperId(paper.getId());
		        q.setSort(0);
		        q.setRequired("1");
		        q.setCreateBy(ShiroUtils.getUser().getUsername());
		        q.setCreateTime(date);
		        String img = q.getImg();
                if(!StringUtils.isBlank(img)){
                	String  r = this.uploadImg("question",img, "png");
                	q.setImg("/files/ailande"+r);
                }
		        if("".equals(q.getIfStop())) q.setIfStop("QI_TA");
		        	paperDao.saveQuestionDO(q);
		            List<ChoiceDO> choiceList = q.getChoiceList();
		            for (ChoiceDO choice : choiceList) {
		            	choice.setQuestion(q.getId());
		            	choice.setCreateBy(ShiroUtils.getUser().getUsername());
		            	choice.setCreateTime(date);
		            	paperDao.saveChoiceDO(choice);
		            	List<ChoiceProductDO> choiceProductList = choice.getChoiceProductList();
		                for(ChoiceProductDO choiceProductDO :choiceProductList){
		                	System.out.println(choiceProductDO);
		                	choiceProductDO.setChoose(choice.getId());
		                	paperDao.saveChoiceProductDO(choiceProductDO);
		                }
		           }
		       }
		        }
			}
			
	
		return R.ok();
	}

	@Override
	public List<ProductDO> listAll() {
		return paperDao.listAll();
	}

	
	/**
	 * 图片附件保存
	 */
	@SuppressWarnings("restriction")
	public String uploadImg(String namespace, String imgStr, String fileType){
		String imgFileTempPath = "";
		try {
			String base64String = imgStr.substring(imgStr.indexOf(",")+1);
			// Base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64String);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			String baseDir = bootdoConfig.getUploadPath()+"ailande/";
			File file = new File(baseDir);
			if(!file.isDirectory()){
				file.mkdirs();
			}
			
			File file2 = new File(baseDir+ "/" + namespace + "/");
			if(!file2.isDirectory()){
				file2.mkdirs();
			}
			// 生成jpeg图片
			imgFileTempPath = "/" + namespace +"/"+ System.currentTimeMillis() + "." + fileType;//新生成的图片
			OutputStream out = new FileOutputStream(baseDir + imgFileTempPath);
			out.write(b);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgFileTempPath;	
	}
}
