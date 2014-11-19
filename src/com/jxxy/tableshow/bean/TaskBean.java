package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 任务表实体bean
* @ClassName: TaskBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-7-23 下午2:33:15 
*
 */
public class TaskBean implements Serializable {

	int _id;
	/**任务名称*/
	public String taskName;
	public String taskId;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**序号*/
	public String xh;
	/**合同检验验收单位*/
	public String htjyysdw;
	/**合同编号*/
	public String htbh;
	/**承制单位*/
	public String czdw;
	/**项数*/
	public String xs;
	/**件数*/
	public String js;
	/**合同金额*/
	public String htje;
	/**产品名称*/
	public String cpmc;
	/**交付期限*/
	public String jfqx;
	/**备注*/
	public String bz;
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getHtjyysdw() {
		return htjyysdw;
	}
	public void setHtjyysdw(String htjyysdw) {
		this.htjyysdw = htjyysdw;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getCzdw() {
		return czdw;
	}
	public void setCzdw(String czdw) {
		this.czdw = czdw;
	}
	public String getXs() {
		return xs;
	}
	public void setXs(String xs) {
		this.xs = xs;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getHtje() {
		return htje;
	}
	public void setHtje(String htje) {
		this.htje = htje;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public String getJfqx() {
		return jfqx;
	}
	public void setJfqx(String jfqx) {
		this.jfqx = jfqx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}
