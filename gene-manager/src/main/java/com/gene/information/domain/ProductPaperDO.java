package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品问卷中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 14:15:57
 */
public class ProductPaperDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//产品ID
	private Integer productId;
	//问卷ID
	private Integer paperId;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
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
}
