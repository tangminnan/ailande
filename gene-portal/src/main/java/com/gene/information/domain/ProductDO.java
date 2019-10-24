package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
public class ProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//产品ID
	private Integer id;
	//产品编号
	private String code;
	//模版URL
	private String url;
	//产品名称
	private String name;
	//产品简介
	private String description;
	
	//问卷ID
	private Integer paperId;
	
	//是否检测 0=未检测  1=已检测
	private Integer ifCheck;
	
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	/**
	 * 设置：产品ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：产品ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：产品编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：产品编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：模版URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：模版URL
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：产品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：产品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：产品简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：产品简介
	 */
	public String getDescription() {
		return description;
	}
	public Integer getIfCheck() {
		return ifCheck;
	}
	public void setIfCheck(Integer ifCheck) {
		this.ifCheck = ifCheck;
	}
	
	
}
