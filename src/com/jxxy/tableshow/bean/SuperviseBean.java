package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 质量监督检查记录BEAN
* @ClassName: SuperviseBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-2 下午3:23:43 
*
 */
public class SuperviseBean implements Serializable{

	int _id;
	String taskName;
	String taskId;
	
	/** 合同编号*/
	String htbh;
	/** 监督时间*/
	String jdsj;
	/** 承制单位*/
	String czdw;
	/** 承制单位陪同人*/
	String czdwptr;
	/** 器材设备名称*/
	String qcsbmc;
	/** 监督结论*/
	String jdjl;
	/** 监督人*/
	String jdr;
	/** 规格型号*/
	String ggxh;
	/** 器材设备代码*/
	String qcsbdm;
	/** 序号*/
	String xh;
	/** 监督项目*/
	String jdxm;
	/** 监督内容*/
	String jdnr;
	/** 监督要求*/
	String jdyq;
	/** 监督结果*/
	String jdjg;
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
	public String getJdsj() {
		return jdsj;
	}
	public void setJdsj(String jdsj) {
		this.jdsj = jdsj;
	}
	public String getCzdw() {
		return czdw;
	}
	public void setCzdw(String czdw) {
		this.czdw = czdw;
	}
	public String getCzdwptr() {
		return czdwptr;
	}
	public void setCzdwptr(String czdwptr) {
		this.czdwptr = czdwptr;
	}
	public String getQcsbmc() {
		return qcsbmc;
	}
	public void setQcsbmc(String qcsbmc) {
		this.qcsbmc = qcsbmc;
	}
	public String getJdjl() {
		return jdjl;
	}
	public void setJdjl(String jdjl) {
		this.jdjl = jdjl;
	}
	public String getJdr() {
		return jdr;
	}
	public void setJdr(String jdr) {
		this.jdr = jdr;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJdxm() {
		return jdxm;
	}
	public void setJdxm(String jdxm) {
		this.jdxm = jdxm;
	}
	public String getJdnr() {
		return jdnr;
	}
	public void setJdnr(String jdnr) {
		this.jdnr = jdnr;
	}
	public String getJdyq() {
		return jdyq;
	}
	public void setJdyq(String jdyq) {
		this.jdyq = jdyq;
	}
	public String getJdjg() {
		return jdjg;
	}
	public void setJdjg(String jdjg) {
		this.jdjg = jdjg;
	}
	
	
	
}
