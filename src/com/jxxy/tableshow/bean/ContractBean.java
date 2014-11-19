package com.jxxy.tableshow.bean;

import java.io.Serializable;

/**
 * 合同列表
 * 
 * @author LiLong
 * @date 2014-6-23 上午10:46:41
 * @update-time
 * @modified by
 */
public class ContractBean implements Serializable {
	int _id;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	/**序号*/
	public String xh;
	/**一级专业分类*/
	public String yjzyfl;
	/**二级专业分类*/
	public String ejzyfl;
	/**合同签定年度*/
	public String htqdnd;
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
	/** 产品名称*/
	public String cpmc;
	/**交货期限*/
	public String jhqx;
	/**采购方式*/
	public String cgfs;
	/**订购单位*/
	public String dgdw;
	/**采购合同经费来源 */
	public String cghtjfly;
	/**产品类型 */
	public String cplx;
	/**合同交接时间 */
	public String htjjsj;
	/**合同履行年度 */
	public String htlxnd;
	/**合格证签发时间 */
	public String hgzqfsj;
	/**合格证编号 */
	public String hgzbh;
	/**入库时间 */
	public String rksj;
	/**结算手续接收时间 */
	public String jssxjssj;
	/**结算手续移交时间 */
	public String jssxyjsj;
	/** 结算时间 */
	public String jssj;
	/**合同当前状态 */
	public String htdqzt;
	/**备注 */
	public String bz;
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYjzyfl() {
		return yjzyfl;
	}
	public void setYjzyfl(String yjzyfl) {
		this.yjzyfl = yjzyfl;
	}
	public String getEjzyfl() {
		return ejzyfl;
	}
	public void setEjzyfl(String ejzyfl) {
		this.ejzyfl = ejzyfl;
	}
	public String getHtqdnd() {
		return htqdnd;
	}
	public void setHtqdnd(String htqdnd) {
		this.htqdnd = htqdnd;
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
	public String getJhqx() {
		return jhqx;
	}
	public void setJhqx(String jhqx) {
		this.jhqx = jhqx;
	}
	public String getCgfs() {
		return cgfs;
	}
	public void setCgfs(String cgfs) {
		this.cgfs = cgfs;
	}
	public String getDgdw() {
		return dgdw;
	}
	public void setDgdw(String dgdw) {
		this.dgdw = dgdw;
	}
	public String getCghtjfly() {
		return cghtjfly;
	}
	public void setCghtjfly(String cghtjfly) {
		this.cghtjfly = cghtjfly;
	}
	public String getCplx() {
		return cplx;
	}
	public void setCplx(String cplx) {
		this.cplx = cplx;
	}
	public String getHtjjsj() {
		return htjjsj;
	}
	public void setHtjjsj(String htjjsj) {
		this.htjjsj = htjjsj;
	}
	public String getHtlxnd() {
		return htlxnd;
	}
	public void setHtlxnd(String htlxnd) {
		this.htlxnd = htlxnd;
	}
	public String getHgzqfsj() {
		return hgzqfsj;
	}
	public void setHgzqfsj(String hgzqfsj) {
		this.hgzqfsj = hgzqfsj;
	}
	public String getHgzbh() {
		return hgzbh;
	}
	public void setHgzbh(String hgzbh) {
		this.hgzbh = hgzbh;
	}
	public String getRksj() {
		return rksj;
	}
	public void setRksj(String rksj) {
		this.rksj = rksj;
	}
	public String getJssxjssj() {
		return jssxjssj;
	}
	public void setJssxjssj(String jssxjssj) {
		this.jssxjssj = jssxjssj;
	}
	public String getJssxyjsj() {
		return jssxyjsj;
	}
	public void setJssxyjsj(String jssxyjsj) {
		this.jssxyjsj = jssxyjsj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getHtdqzt() {
		return htdqzt;
	}
	public void setHtdqzt(String htdqzt) {
		this.htdqzt = htdqzt;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}
