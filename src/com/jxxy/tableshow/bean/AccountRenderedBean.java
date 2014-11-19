package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 交验单
 * 
 * @ClassName: AccountRenderedBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-6-26 上午10:57:22
 * 
 */
public class AccountRenderedBean implements Serializable {
	
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
	/** 提交单位 */
	String tjdw;
	/** 提交时间 */
	String tjsj;
	/** 项目名称 */
	String xmmc;
	/**合同编号*/ 
	String htbh;
	/**项目代号*/ 
	String xmdh;
	/**提交次数*/ 
	String tjcs;
	/**产品名称*/ 
	String cpmc;
	/**提交数量*/ 
	String tjsl;
	/**提交批次*/ 
	String tjpc;
	/**产品编号*/ 
	String cpbh;
	/**提交单位校验结论*/ 
	String tjdwxyjl;
	/**校验条件审查*/ 
	String xytjsc;
	/**校验场所*/ 
	String xycs;
	/**校验标准*/ 
	String xybz;
	/**提交条件审查意见*/ 
	String tjtjscyj;
	/**质检组审查*/ 
	String zjzsc;
	/**审查日期*/ 
	String scrq;
	/**验收结论*/ 
	String ysjl;
	/**质检组验收*/ 
	String zjzys;
	/**验收日期*/ 
	String ysrq;
	
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTjdw() {
		return tjdw;
	}
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getXmdh() {
		return xmdh;
	}
	public void setXmdh(String xmdh) {
		this.xmdh = xmdh;
	}
	public String getTjcs() {
		return tjcs;
	}
	public void setTjcs(String tjcs) {
		this.tjcs = tjcs;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public String getTjsl() {
		return tjsl;
	}
	public void setTjsl(String tjsl) {
		this.tjsl = tjsl;
	}
	public String getTjpc() {
		return tjpc;
	}
	public void setTjpc(String tjpc) {
		this.tjpc = tjpc;
	}
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	public String getTjdwxyjl() {
		return tjdwxyjl;
	}
	public void setTjdwxyjl(String tjdwxyjl) {
		this.tjdwxyjl = tjdwxyjl;
	}
	public String getXytjsc() {
		return xytjsc;
	}
	public void setXytjsc(String xytjsc) {
		this.xytjsc = xytjsc;
	}
	public String getXycs() {
		return xycs;
	}
	public void setXycs(String xycs) {
		this.xycs = xycs;
	}
	public String getXybz() {
		return xybz;
	}
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}
	public String getTjtjscyj() {
		return tjtjscyj;
	}
	public void setTjtjscyj(String tjtjscyj) {
		this.tjtjscyj = tjtjscyj;
	}
	public String getZjzsc() {
		return zjzsc;
	}
	public void setZjzsc(String zjzsc) {
		this.zjzsc = zjzsc;
	}
	public String getScrq() {
		return scrq;
	}
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	public String getYsjl() {
		return ysjl;
	}
	public void setYsjl(String ysjl) {
		this.ysjl = ysjl;
	}
	public String getZjzys() {
		return zjzys;
	}
	public void setZjzys(String zjzys) {
		this.zjzys = zjzys;
	}
	public String getYsrq() {
		return ysrq;
	}
	public void setYsrq(String ysrq) {
		this.ysrq = ysrq;
	}
	
	
}
