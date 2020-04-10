package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 选项表 qu_choice
 * 
 * @author gene
 * @date 2019-08-27
 */
public class ChoiceProductDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer choose;
	private Integer product;
    /**
    * 所属题目ID
    */
    private Integer question;
    /**
     * 选项分值
     */
    private Double score;
   
    /**
     * 是否选中
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
  	private Double scores;
  	//调整方向1
      private String tadfs;
    //调整方向2
    private String tadss;
    //调整原因
    private String tadreasons;
    //营养补充及建议
	private String tadjianyis;
	//产品名字
	private String productName;
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Double getScores() {
		return scores;
	}
	public void setScores(Double scores) {
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
	public Integer getChoose() {
		return choose;
	}
	public void setChoose(Integer choose) {
		this.choose = choose;
	}
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ChoiceProductDO [id=" + id + ", choose=" + choose + ", product=" + product + ", question=" + question
				+ ", score=" + score + ", delFlag=" + delFlag + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", remark=" + remark + ", tadf=" + tadf
				+ ", tads=" + tads + ", tadreason=" + tadreason + ", tadjianyi=" + tadjianyi + ", bmi=" + bmi
				+ ", bmis=" + bmis + ", scores=" + scores + ", tadfs=" + tadfs + ", tadss=" + tadss + ", tadreasons="
				+ tadreasons + ", tadjianyis=" + tadjianyis + ", productName=" + productName + "]";
	}
	
	
}
