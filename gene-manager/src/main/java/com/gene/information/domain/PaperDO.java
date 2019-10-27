package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 问卷表 qu_paper
 *
 * @author gene
 * @date 2019-08-27
 */
public class PaperDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 问卷编号
     */
    private String code;
    /**
     * 问卷名称
     */
    private String name;
    /**
     * 问卷简介
     */
    private String description;
    /**
     * 问卷状态（0未完成 1已完成）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;
    //创建者
    private String  createBy;
    //创建时间
    private Date createTime;
    //更新者
    private String updateBy;
    //更新时间
    private Date updateTime;
    private String remark;

    List<QuestionDO> questionList;
    /**
     * 编辑中待删除的题目
     */
    private Integer[] removedQuestions;
    /**
     * 编辑中待删除的选项
     */
    private Integer[] removedChooses;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}


	public List<QuestionDO> getQuestionList() {
		return questionList;
	}


	public void setQuestionList(List<QuestionDO> questionList) {
		this.questionList = questionList;
	}


	public String getCreateBy() {
		return createBy;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getUpdateBy() {
		return updateBy;
	}


	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return "PaperDO [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", status="
				+ status + ", delFlag=" + delFlag + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", remark=" + remark + ", questionList="
				+ questionList + "]";
	}


	public Integer[] getRemovedQuestions() {
		return removedQuestions;
	}


	public void setRemovedQuestions(Integer[] removedQuestions) {
		this.removedQuestions = removedQuestions;
	}


	public Integer[] getRemovedChooses() {
		return removedChooses;
	}


	public void setRemovedChooses(Integer[] removedChooses) {
		this.removedChooses = removedChooses;
	}


	
	
	
    
}
