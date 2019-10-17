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
	//性别
	private String sex;
	//年龄
	private Integer age;
	//状态（0未完成 1已完成）
	private String status;
	private Integer productId;
	//检测码
	private String code;
	//联系方式
	private String phone;
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
}
