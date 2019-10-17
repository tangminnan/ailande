package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;

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
     * 选项分值
     */
    private Integer score;
   
    /**
     * 是否选中
     */
 
    private String isCheck;
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
    //调整方向1
    private String tadf;
    //调整方向2
    private String tads;
    //调整原因
    private String tadreason;
    //营养补充及建议
	private String tadjianyi;
    /**
     * 以下的选项只有涉及到运动的时候才有效
     */
    private String bmi;
    private String bmis;
    //选项分值
  	private Integer scores;
  	//调整方向1
      private String tadfs;
    //调整方向2
    private String tadss;
    //调整原因
    private String tadreasons;
    //营养补充及建议
	private String tadjianyis;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTadf() {
		return tadf;
	}
	public void setTadf(String tadf) {
		this.tadf = tadf;
	}
	public String getTads() {
		return tads;
	}
	public void setTads(String tads) {
		this.tads = tads;
	}
	public String getTadreason() {
		return tadreason;
	}
	public void setTadreason(String tadreason) {
		this.tadreason = tadreason;
	}
	public String getTadjianyi() {
		return tadjianyi;
	}
	public void setTadjianyi(String tadjianyi) {
		this.tadjianyi = tadjianyi;
	}
	public Integer getScores() {
		return scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	public String getTadfs() {
		return tadfs;
	}
	public void setTadfs(String tadfs) {
		this.tadfs = tadfs;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getBmis() {
		return bmis;
	}
	public void setBmis(String bmis) {
		this.bmis = bmis;
	}
	public String getTadss() {
		return tadss;
	}
	public void setTadss(String tadss) {
		this.tadss = tadss;
	}
	public String getTadreasons() {
		return tadreasons;
	}
	public void setTadreasons(String tadreasons) {
		this.tadreasons = tadreasons;
	}
	public String getTadjianyis() {
		return tadjianyis;
	}
	public void setTadjianyis(String tadjianyis) {
		this.tadjianyis = tadjianyis;
	}
}
