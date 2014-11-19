package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 产品检验验收发现问题汇总表
* @ClassName: ErrorBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-2 下午4:24:50 
*
 */
public class ErrorBean implements Serializable  {
	
	int _id;
	public String taskName;
	public String taskId;
	
	/** 合同编号*/
	String htbh;
	/** 编号*/
	String bh;
	/** 填写日期*/
	String txrq;
	/**  检验验收中发现问题*/
	String jyyszfxwt;
	/** 产品编号*/
	String cpbh;
	/** 验收人员签字*/
	String ysryqz;
	/** 承制单位陪同人签字*/
	String czdwptrqz;
	/** 器材设备名称*/
	String qcsbmc;
	/** 规格型号*/
	String ggxh;
	/** 器材设备代码*/
	String qcsbdm;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getTxrq() {
		return txrq;
	}
	public void setTxrq(String txrq) {
		this.txrq = txrq;
	}
	public String getJyyszfxwt() {
		return jyyszfxwt;
	}
	public void setJyyszfxwt(String jyyszfxwt) {
		this.jyyszfxwt = jyyszfxwt;
	}
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	public String getYsryqz() {
		return ysryqz;
	}
	public void setYsryqz(String ysryqz) {
		this.ysryqz = ysryqz;
	}
	public String getCzdwptrqz() {
		return czdwptrqz;
	}
	public void setCzdwptrqz(String czdwptrqz) {
		this.czdwptrqz = czdwptrqz;
	}
	public String getQcsbmc() {
		return qcsbmc;
	}
	public void setQcsbmc(String qcsbmc) {
		this.qcsbmc = qcsbmc;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getQcsbdm() {
		return qcsbdm;
	}
	public void setQcsbdm(String qcsbdm) {
		this.qcsbdm = qcsbdm;
	}
	
}
