package com.jxxy.tableshow.utils;

import java.util.ArrayList;
import java.util.List;

import com.jxxy.tableshow.bean.CheckDetailBean;
import com.jxxy.tableshow.bean.ContractDetailBean;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.bean.TaskBean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TableChangeDbUtils {

	private static final int SAVE_INSTRUCTION = 0;
	private static final int UPDATA_INSTRUCTION = 1;
	public Context mContext;

	public TableChangeDbUtils(Context context) {
		context = mContext;
	}

	/**
	 * 合同分工db
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param taskBean 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void setTaskDb(TaskBean taskBean) {
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<TaskBean> list = (ArrayList<TaskBean>) sql.GetDataByWhere(db,
				TaskBean.class, "taskId like '" + taskBean.getTaskId()
						+ "' and htbh like '" + taskBean.getHtbh() + "'"
						+"' and taskName like '" + taskBean.getTaskName() + "'", null);
		sql.close();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++)
				saveDbList(taskBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(taskBean, SAVE_INSTRUCTION);
		}
	}

	/**
	 * 质量问题记录表
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param productProblemBean 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void setProductProbleamDb(ProductProblemBean productProblemBean) {

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<ProductProblemBean> list = null;

		if (!isNull(productProblemBean.getHtbh())
				&& !isNull(productProblemBean.getCpbh())) {
			list = (ArrayList<ProductProblemBean>) sql.GetDataByWhere(
					db,
					ProductProblemBean.class,
					"htbh like '" + productProblemBean.getHtbh()
							+ "' and cpbh like '"
							+ productProblemBean.getCpbh()
							+ "' and qcsbmc like '"
							+ productProblemBean.getQcsbmc()
							+"' and taskId like '" + productProblemBean.getTaskId() + "'"
							+"' and taskName like '" + productProblemBean.getTaskName() + "'", null);
			sql.close();
		}

		if (null != list && list.size() > 0) {
			saveDbList(productProblemBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(productProblemBean, SAVE_INSTRUCTION);
		}
		sql.close();
	}

	/**
	 * 产品质量检验验收记录表DB
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inspectionRecordBean 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void setInspectionRecordDb(InspectionRecordBean inspectionRecordBean) {

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();
		ArrayList<InspectionRecordBean> list = null;
		if (!isNull(inspectionRecordBean.getHtbh())
				&& !isNull(inspectionRecordBean.getQcsbmc())
				&& !isNull(inspectionRecordBean.getSjcpbh())) {
			list = (ArrayList<InspectionRecordBean>) sql.GetDataByWhere(
					db,
					InspectionRecordBean.class,
					"htbh like '" + inspectionRecordBean.getHtbh()
							+ "' and qcsbmc like '"
							+ inspectionRecordBean.getQcsbmc()
							+ "' and sjcpbh like '"
							+ inspectionRecordBean.getSjcpbh() 
							+ "' and jsyq like '"
									+ inspectionRecordBean.getJsyq()
							+ "'"
							+"' and taskId like '" + inspectionRecordBean.getTaskId() + "'"
							+"' and taskName like '" + inspectionRecordBean.getTaskName() + "'", null);
			sql.close();
		}

		if (null != list && list.size() > 0) {
			saveDbList(inspectionRecordBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(inspectionRecordBean, SAVE_INSTRUCTION);
		}
		sql.close();
	}

	/**
	 * 质量监督检查记录表
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param mSuperviseBean 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void setSuperviseDb(SuperviseBean mSuperviseBean) {
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<SuperviseBean> list = null;

		if (!isNull(mSuperviseBean.getHtbh())
				&& !isNull(mSuperviseBean.getJdsj())) {
			list = (ArrayList<SuperviseBean>) sql.GetDataByWhere(db,
					SuperviseBean.class,
					"htbh like '" + mSuperviseBean.getHtbh()
							+ "' and jdsj like '" + mSuperviseBean.getJdsj()
							+ "'"
							+"' and taskId like '" + mSuperviseBean.getTaskId() + "'"
							+"' and taskName like '" + mSuperviseBean.getTaskName() + "'", null);
			sql.close();
		}
		if (null != list && list.size() > 0) {
			saveDbList(mSuperviseBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(mSuperviseBean, SAVE_INSTRUCTION);
		}
		sql.close();
	}

	/**
	 * 产品检验验收发现问题汇总表
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param mErrorBean 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void setErrorDb(ErrorBean mErrorBean) {

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<ErrorBean> list = null;

		if (!isNull(mErrorBean.getHtbh())) {
			list = (ArrayList<ErrorBean>) sql.GetDataByWhere(db,
					ErrorBean.class,
					"htbh like '" + mErrorBean.getHtbh() + "'"
					+"' and taskId like '" + mErrorBean.getTaskId() + "'"
							+"' and taskName like '" + mErrorBean.getTaskName() + "'"
							, null);
			sql.close();
		}
		if (null != list && list.size() > 0) {
			saveDbList(mErrorBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(mErrorBean, SAVE_INSTRUCTION);
		}
	}

	/**
	 * 合同明细表
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param mContractDetailBean    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void setContractDetailDb(ContractDetailBean mContractDetailBean) {
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<ContractDetailBean> list = null;

		if (!isNull(mContractDetailBean.getHtbh())
				&& !isNull(mContractDetailBean.getQcsbmc())
				&& !isNull(mContractDetailBean.getGgxh())
				&& !isNull(mContractDetailBean.getQcsbdm())) {

			list = (ArrayList<ContractDetailBean>) sql.GetDataByWhere(
					db,
					ContractDetailBean.class,
					"htbh like '" + mContractDetailBean.getHtbh()
							+ "' and qcsbmc like '"
							+ mContractDetailBean.getQcsbmc()
							+ "' and qcsbdm like '"
							+ mContractDetailBean.getQcsbdm()
							+ "' and ggxh like '"
							+ mContractDetailBean.getGgxh() + "'", null);
			sql.close();
		}
		if (null != list && list.size() > 0) {
			saveDbList(mContractDetailBean, UPDATA_INSTRUCTION);
		} else {
			saveDbList(mContractDetailBean, SAVE_INSTRUCTION);
		}
	}

	/**
	 * @param mContractDetailBean 
	 * 检验验收明细
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void setCheckDetailDb(CheckDetailBean mCheckDetailBean){
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getReadableDatabase();

		ArrayList<CheckDetailBean> list = null;

		if (isNull(mCheckDetailBean.getBznd())) {
			list = (ArrayList<CheckDetailBean>) sql
					.GetDataByWhere(db, CheckDetailBean.class,
							 "bznd like '"
									+ mCheckDetailBean.getQcsbmc()
									+ "'", null);
			sql.close();
		}
		if (null != list && list.size() > 0) {
			saveDbList(mCheckDetailBean,
					UPDATA_INSTRUCTION);
		} else {
			saveDbList(mCheckDetailBean, SAVE_INSTRUCTION);
		}
		sql.close();
	}
	/**
	 * 保存数据库
	 * 
	 * @param 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public void saveDbList(Object ob, int instruction) {
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();
		switch (instruction) {
		case SAVE_INSTRUCTION:
			sql.SavaDataSingle(db, ob);
			break;
		case UPDATA_INSTRUCTION:
			sql.Update(db, ob);
			break;
		default:
			break;
		}
		sql.close();
	}

	public boolean isNull(String str) {
		if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}
}
