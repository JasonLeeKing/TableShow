package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 产品检验记录
 * 
 * @ClassName: InspectionRecordBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-6-26 上午11:48:12
 * 
 */
public class InspectionRecordBean implements Serializable {

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

	// 器材设备名称
	String qcsbmc;
	// 合同编号
	String htbh;
	// 订购数量
	String dgsl;
	// 受检产品编号
	String sjcpbh;
	// 本次提交数量
	String bctjsl;
	// 承制单位
	String czdw;
	// 产品批次号
	String cppch;
	// 检验日期
	String jyrq;
	// 产品检验记录及判定结论
	String cpjyjlpdjl;
	// 序号
	String xh;
	// 检验项目
	String jyxm;
	// 技术要求
	String jsyq;
	// 检测结果
	String jyjg;
	// 检验结论
	String jyjl;
	
	// 验收人员
	String ysry;
	// 备注
	String bz;
	// 质检组验收结论
	String zjzysjl;
	// 质检组成员签名
	String zjzcyqm;
	
	/**检验地点 */ 
	String jjdd;
	
	/** 规格型号 */ 
	String ggxh;
	
	/** 器材设备代码 */ 
	String qcsbdm;
	
	/** 日期 */ 
	String rq;
	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getQcsbmc() {
		return qcsbmc;
	}

	public void setQcsbmc(String qcsbmc) {
		this.qcsbmc = qcsbmc;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getDgsl() {
		return dgsl;
	}

	public void setDgsl(String dgsl) {
		this.dgsl = dgsl;
	}

	public String getSjcpbh() {
		return sjcpbh;
	}

	public void setSjcpbh(String sjcpbh) {
		this.sjcpbh = sjcpbh;
	}

	public String getBctjsl() {
		return bctjsl;
	}

	public void setBctjsl(String bctjsl) {
		this.bctjsl = bctjsl;
	}

	public String getCzdw() {
		return czdw;
	}

	public void setCzdw(String czdw) {
		this.czdw = czdw;
	}

	public String getCppch() {
		return cppch;
	}

	public void setCppch(String scrq) {
		this.cppch = scrq;
	}

	public String getJyrq() {
		return jyrq;
	}

	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}

	public String getCpjyjlpdjl() {
		return cpjyjlpdjl;
	}

	public void setCpjyjlpdjl(String cpjyjlpdjl) {
		this.cpjyjlpdjl = cpjyjlpdjl;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getJyxm() {
		return jyxm;
	}

	public void setJyxm(String jyxm) {
		this.jyxm = jyxm;
	}

	public String getJsyq() {
		return jsyq;
	}

	public void setJsyq(String jsyq) {
		this.jsyq = jsyq;
	}

	public String getJyjg() {
		return jyjg;
	}

	public void setJyjg(String jyjg) {
		this.jyjg = jyjg;
	}

	public String getJyjl() {
		return jyjl;
	}

	public void setJyjl(String jyjl) {
		this.jyjl = jyjl;
	}

	public String getYsry() {
		return ysry;
	}

	public void setYsry(String ysry) {
		this.ysry = ysry;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getZjzysjl() {
		return zjzysjl;
	}

	public void setZjzysjl(String zjzysjl) {
		this.zjzysjl = zjzysjl;
	}

	public String getZjzcyqm() {
		return zjzcyqm;
	}

	public void setZjzcyqm(String zjzcyqm) {
		this.zjzcyqm = zjzcyqm;
	}

	public String getJjdd() {
		return jjdd;
	}

	public void setJjdd(String jjdd) {
		this.jjdd = jjdd;
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

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	
}
