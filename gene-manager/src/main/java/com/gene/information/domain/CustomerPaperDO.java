package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 11:20:31
 */
public class CustomerPaperDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户ID
	private Integer id;
	//姓名
	private String username;
	//身高
	private String high;
	//性别
	private String sex;
	//体重
	private Integer weight;
	//年龄
	private Integer age;
	//状态（0未完成 1已完成）
	private String status;
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
	//产品ID
	private Integer productId;
	//问卷ID
	private Integer paperId;
	//检测码
	private String code;
	//渠道来源
	private String source;
	//BMI
	private Integer bmi;
	//联系方式
	private String phone;
	//邮箱
	private String email;
	//地址
	private String address;
	//题目内容
	private String questionContent;
	//答案内容
	private String choiceContent;
	//答案Id
	private String choiceId;
	//JIBEN_XINXI=基本信息  SHENTI_ZHUNGKUANG=身体状况  YINSHI_XIGUAN=饮食习惯  SHENGHUO_XIGUAN=生活习惯
	private String category;
	private String content;
	private String tiankonganswer;
	private String userId;
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTiankonganswer() {
		return tiankonganswer;
	}
	public void setTiankonganswer(String tiankonganswer) {
		this.tiankonganswer = tiankonganswer;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getChoiceContent() {
		return choiceContent;
	}
	public void setChoiceContent(String choiceContent) {
		this.choiceContent = choiceContent;
	}
	/**
	 * 设置：客户ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：客户ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：身高
	 */
	public void setHigh(String high) {
		this.high = high;
	}
	/**
	 * 获取：身高
	 */
	public String getHigh() {
		return high;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：体重
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：状态（0未完成 1已完成）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态（0未完成 1已完成）
	 */
	public String getStatus() {
		return status;
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
	/**
	 * 设置：产品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：问卷ID
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	/**
	 * 获取：问卷ID
	 */
	public Integer getPaperId() {
		return paperId;
	}
	/**
	 * 设置：检测码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：检测码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：渠道来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：渠道来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：BMI
	 */
	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}
	/**
	 * 获取：BMI
	 */
	public Integer getBmi() {
		return bmi;
	}
	/**
	 * 设置：联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系方式
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
}
