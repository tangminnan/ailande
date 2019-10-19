package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 选项表 qu_choice
 * 
 * @author gene
 * @date 2019-08-27
 */
public class ChoiceDO implements Serializable {
    private static final long serialVersionUID = 1L;

   
    private Integer id;
	
    /**
    * 所属题目ID
    */
    private Integer question;
    /**
    * 选项内容
    */
    private String content;
   
    /**
     * 是否选中
     */
 
    private String isCheck;
  
    //创建者
    private String  createBy;
    //创建时间
    private Date createTime;
    //更新者
    private String updateBy;
    //更新时间
    private Date updateTime;
    private String remark;
    private List<ChoiceProductDO> choiceProductList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuestion() {
		return question;
	}
	public void setQuestion(Integer question) {
		this.question = question;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public List<ChoiceProductDO> getChoiceProductList() {
		return choiceProductList;
	}
	public void setChoiceProductList(List<ChoiceProductDO> choiceProductList) {
		this.choiceProductList = choiceProductList;
	}
	
}
