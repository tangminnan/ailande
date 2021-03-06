package com.gene.information.domain;

import java.util.Date;

public class ProductpaperDO {
	private Integer id;
	//问卷ID
	private Integer  paper;
	//产品ID
	private Integer  product ;
	//用户ID
    private Integer user;
    //状态   0=未完成  1=已完成 未生成报告  2=已生成报告
    private String status;
    //创建时间
    private Date answerTime;
    //备注
    private String remark;
    //微信openid
    private String openid;
    //答题记录
    private Integer answerStatus;
    private String productName;
    private String username;
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPaper() {
		return paper;
	}
	public void setPaper(Integer paper) {
		this.paper = paper;
	}
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(Integer answerStatus) {
		this.answerStatus = answerStatus;
	} 
    
 
}
