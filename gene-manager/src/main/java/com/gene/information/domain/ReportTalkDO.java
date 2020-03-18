package com.gene.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 报告话术
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-03-18 10:49:48
 */
public class ReportTalkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//话术名称
	private String talkName;
	//较大的范围数
	private Integer scoreBig;
	//较小的范围数
	private Integer scoreLittle;
	//话术内容
	private String talkContent;
	//添加时间
	private Date addTime;
	//更新时间
	private Date updateTime;
	//状态（1正常2禁止）
	private Integer status;
	//话术类型（肠胃--CHANGWEI，，体型--TIXING）
	private String talkType;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：话术名称
	 */
	public void setTalkName(String talkName) {
		this.talkName = talkName;
	}
	/**
	 * 获取：话术名称
	 */
	public String getTalkName() {
		return talkName;
	}
	/**
	 * 设置：较大的范围数
	 */
	public void setScoreBig(Integer scoreBig) {
		this.scoreBig = scoreBig;
	}
	/**
	 * 获取：较大的范围数
	 */
	public Integer getScoreBig() {
		return scoreBig;
	}
	/**
	 * 设置：较小的范围数
	 */
	public void setScoreLittle(Integer scoreLittle) {
		this.scoreLittle = scoreLittle;
	}
	/**
	 * 获取：较小的范围数
	 */
	public Integer getScoreLittle() {
		return scoreLittle;
	}
	/**
	 * 设置：话术内容
	 */
	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}
	/**
	 * 获取：话术内容
	 */
	public String getTalkContent() {
		return talkContent;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddTime() {
		return addTime;
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
	 * 设置：状态（1正常2禁止）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态（1正常2禁止）
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：话术类型（肠胃--CHANGWEI，，体型--TIXING）
	 */
	public void setTalkType(String talkType) {
		this.talkType = talkType;
	}
	/**
	 * 获取：话术类型（肠胃--CHANGWEI，，体型--TIXING）
	 */
	public String getTalkType() {
		return talkType;
	}
}
