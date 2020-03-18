package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
public class AnswerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//客户问卷ID
	private Integer customerPaperId;
	//答案ID
	private String choiceId;
	//问题ID
	private Integer questionId;
	//创建者
	private String createBy;
	//创建时间
	private Date createTime;
	//更新者
	private String updateBy;
	//更新时间
	private Date updateTime;
	//备注
	private String remark;
	//删除标志（0代表存在 2代表删除）
	private String delFlag;
	//题目内容
	private String quentContent;
	//选项内容
	private String chooseContent;
	//调整方向
	private String tadf;
	//具体的建议
	private String tadjianyi;

	
	public String getQuentContent() {
		return quentContent;
	}
	public void setQuentContent(String quentContent) {
		this.quentContent = quentContent;
	}
	public String getChooseContent() {
		return chooseContent;
	}
	public void setChooseContent(String chooseContent) {
		this.chooseContent = chooseContent;
	}
	public String getTadf() {
		return tadf;
	}
	public void setTadf(String tadf) {
		this.tadf = tadf;
	}
	public String getTadjianyi() {
		return tadjianyi;
	}
	public void setTadjianyi(String tadjianyi) {
		this.tadjianyi = tadjianyi;
	}
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
	/**
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：删除标志（0代表存在 2代表删除）
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标志（0代表存在 2代表删除）
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
