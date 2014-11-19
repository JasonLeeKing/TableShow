package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 产品质量技术问题列表
 * @author LiLong 
 * @date 2014-6-23 下午02:15:19 
 * @update-time
 * @modified by
 */
public class ProductProblemBean implements Serializable {

	public String taskName;
	public String taskId;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	int _id;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	/** 承制单位*/
	public String czdw;
	/** 合同编号*/
	public String htbh;
	/** 产品编号*/
	public String cpbh;
	/** 问题发生日期*/
	public String wtfsrq;
	/** 问题发生地点*/
	public String wtfsdd;
	/** 器材设备名称 */ 
	public String qcsbmc;
	
	/**规格型号*/
	public String ggxh;
	
	/**器材设备代码*/
	public String qcsbdm;
	
	/** 质量问题情况描述*/
	public String zlwtqkms;
	
	/** 质检组组长*/
	public String zjzzz;
	
	/** 质检组组长日期*/
	public String zjzzzrq;
	
	
	/** 承制单位确认*/
	public String czdwqr;
	/** 承制单位确认日期*/
	public String czdwqrrq;
	
	
	
	
	public String getZjzzzrq() {
		return zjzzzrq;
	}
	public void setZjzzzrq(String zjzzzrq) {
		this.zjzzzrq = zjzzzrq;
	}
	public String getZlwtqkms() {
		return zlwtqkms;
	}
	public void setZlwtqkms(String zlwtqkms) {
		this.zlwtqkms = zlwtqkms;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getCzdw() {
		return czdw;
	}
	public void setCzdw(String czdw) {
		this.czdw = czdw;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	public String getWtfsrq() {
		return wtfsrq;
	}
	public void setWtfsrq(String wtfsrq) {
		this.wtfsrq = wtfsrq;
	}
	public String getWtfsdd() {
		return wtfsdd;
	}
	public void setWtfsdd(String wtfsdd) {
		this.wtfsdd = wtfsdd;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getCzdwqr() {
		return czdwqr;
	}
	public void setCzdwqr(String czdwqr) {
		this.czdwqr = czdwqr;
	}
	public String getCzdwqrrq() {
		return czdwqrrq;
	}
	public void setCzdwqrrq(String czdwqrrq) {
		this.czdwqrrq = czdwqrrq;
	}

	public String getQcsbmc() {
		return qcsbmc;
	}
	public void setQcsbmc(String qcsbmc) {
		this.qcsbmc = qcsbmc;
	}
	public String getQcsbdm() {
		return qcsbdm;
	}
	public void setQcsbdm(String qcsbdm) {
		this.qcsbdm = qcsbdm;
	}
	public String getZjzzz() {
		return zjzzz;
	}
	public void setZjzzz(String zjzzz) {
		this.zjzzz = zjzzz;
	}
	
	
}
