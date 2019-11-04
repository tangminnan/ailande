package com.gene.information.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



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
	//性别
	private String sex;
	//身高
	private Integer high;
	//体重
	private Float weight;
	//邮箱
	private String email;
	//邮箱
	private String openid;
	//年龄
	private Integer age;
	//联系方式
	private String phone;
	//创建时间
	private Date createTime;
	private Integer bmi;
	
	//年龄
	private Integer productId;
	
	private List<Integer> list = new ArrayList<Integer>();
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
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getHigh() {
		return high;
	}
	public void setHigh(Integer high) {
		this.high = high;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public Integer getBmi() {
		return bmi;
	}
	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}
}
