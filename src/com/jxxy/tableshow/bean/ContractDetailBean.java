package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 合同明细表
* @ClassName: ContractDetailBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-2 下午4:55:03 
*
 */
public class ContractDetailBean implements Serializable {

	int _id;
	/** 合同编号*/
	String htbh;
	/** 器材设备名称*/
	String qcsbmc;
	/** 规格型号*/
	String ggxh;
	/** 器材设备代码*/
	String qcsbdm;
	/** 计量单位*/
	String jldw;
	/** 数量*/
	String sl;
	/** 备注*/
	String bz;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
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
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
