package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 检验验收明细表
* @ClassName: CheckDetailBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-2 下午5:06:43 
*
 */
public class CheckDetailBean  implements Serializable {

	int _id;
	
	/** 编制年度*/
	String bznd;
	/** 器材设备名称*/
	String qcsbmc;
	/** 规格型号*/
	String ggxh;
	/** 器材设备代码*/
	String qcsbdm;
	/** 检验项目*/
	String jyxm;
	/** 检验内容*/
	String jynr;
	/** 技术要求*/
	String jsyq;
	/** 检验方式*/
	String jyfs;
	/** 备注*/
	String bz;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getBznd() {
		return bznd;
	}
	public void setBznd(String bznd) {
		this.bznd = bznd;
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
	public String getJyxm() {
		return jyxm;
	}
	public void setJyxm(String jyxm) {
		this.jyxm = jyxm;
	}
	public String getJynr() {
		return jynr;
	}
	public void setJynr(String jynr) {
		this.jynr = jynr;
	}
	public String getJsyq() {
		return jsyq;
	}
	public void setJsyq(String jsyq) {
		this.jsyq = jsyq;
	}
	public String getJyfs() {
		return jyfs;
	}
	public void setJyfs(String jyfs) {
		this.jyfs = jyfs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
