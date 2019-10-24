package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 问卷表 qu_question
 * 
 * @author gene
 * @date 2019-08-27
 */
public class QuestionDO implements Serializable{
    private static final long serialVersionUID = 1L;

    
    private Integer id;
	
    /**
    * 所属问卷ID
    */
    private Long paperId;
    /**
    * 分类名称
    * JIBEN_XINXI=基本信息
	  SHENTI_ZHUNGKUANG=身体状况
	  SHANSHI_XIGUAN=膳食习惯
	  SHENGHUO_FANGSHI=生活方式
	  YUNDONG_XIGUANG=运动习惯
	  SHUIMIAN_XIGUANG=睡眠与压力
    */
    private String category;
    /**
    * 报告题目内容
    */
    private String content;
    /**
     * 文件题目内容
     */
    private String  contenw;
    /**
    * 题目类型（0单选 1多选）
    */
    private String type;
    //图片
    private String img; 
    /**
    * 排序
    */
    private Integer sort;
    private String delFlag;
    //创建者
    private String  createBy;
    //创建时间
    private Date createTime;
    //更新者
    private String updateBy;
    //更新时间
    private Date updateTime;
    /*  题型分类
     *  YUN_DONG=运动题型
     *  QI_TA=其他题型
     */
    private String ifStop;
    /**
    * 是否必填（0必填 1选填）
    */
    private String required;
    private List<ChoiceDO> choiceList;
    private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public List<ChoiceDO> getChoiceList() {
		return choiceList;
	}
	public void setChoiceList(List<ChoiceDO> choiceList) {
		this.choiceList = choiceList;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContenw() {
		return contenw;
	}
	public void setContenw(String contenw) {
		this.contenw = contenw;
	}
	
	public String getIfStop() {
		return ifStop;
	}
	public void setIfStop(String ifStop) {
		this.ifStop = ifStop;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "QuestionDO [id=" + id + ", paperId=" + paperId + ", category=" + category + ", content=" + content
				+ ", contenw=" + contenw + ", type=" + type + ", sort=" + sort + ", delFlag=" + delFlag + ", createBy="
				+ createBy + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ ", required=" + required + ", choiceList=" + choiceList + ", remark=" + remark + "]";
	}
	
	
    
}
