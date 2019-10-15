package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-10-10 15:34:26
 */
public class PaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//问卷ID
	private Integer id;
	//问卷编号
	private String code;
	//问卷名称
	private String name;
	//问卷简介
	private String description;
	//问卷状态（0 待发布 1已分布  2   已停用）
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

	/**
	 * 设置：问卷ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：问卷ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：问卷编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：问卷编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：问卷名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：问卷名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：问卷简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：问卷简介
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：问卷状态（0 待发布 1已分布  2   已停用）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：问卷状态（0 待发布 1已分布  2   已停用）
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
}
