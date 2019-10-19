package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 答题表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
public class AnswerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//记录表ID
	private Integer productpaper;
	//问卷ID
	private Integer customerPaperId;
	//问题ID
	private Integer questionId;
	//答案ID
	private String choiceId;
	//分值 
	private Integer score;
	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：客户问卷ID
	 */
	public void setCustomerPaperId(Integer customerPaperId) {
		this.customerPaperId = customerPaperId;
	}
	/**
	 * 获取：客户问卷ID
	 */
	public Integer getCustomerPaperId() {
		return customerPaperId;
	}
	/**
	 * 设置：答案ID
	 */
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	/**
	 * 获取：答案ID
	 */
	public String getChoiceId() {
		return choiceId;
	}
	/**
	 * 设置：问题ID
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	/**
	 * 获取：问题ID
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getProductpaper() {
		return productpaper;
	}
	public void setProductpaper(Integer productpaper) {
		this.productpaper = productpaper;
	}
	
}