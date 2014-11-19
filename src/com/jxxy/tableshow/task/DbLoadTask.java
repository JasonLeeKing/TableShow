package com.jxxy.tableshow.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jxxy.tableshow.activity.FileManager;
import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.CheckDetailBean;
import com.jxxy.tableshow.bean.ContractDetailBean;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.TableChangeDbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 数据加载
 * 
 * @ClassName: DbLoadTask
 * @author LiLong
 * @date 2014-6-21 下午1:52:49
 * 
 */
public class DbLoadTask extends AsyncTask<Void, Void, Boolean> {

	private static final int SAVE_INSTRUCTION = 0;
	private static final int UPDATA_INSTRUCTION = 1;
	private Context mContext;
	private Handler mHandler;
	private boolean mFlag = false;
	public String mFilePath;
	Message message;
	private ArrayList cellList = new ArrayList();

	private ArrayList<AccountRenderedBean> rowsAccountRenderedList = new ArrayList<AccountRenderedBean>();
	private ArrayList<InspectionRecordBean> rowsInspectionRecordList = new ArrayList<InspectionRecordBean>();
	private ArrayList<SuperviseBean> rowsSuperviseBeanList = new ArrayList<SuperviseBean>();
	private ArrayList<ErrorBean> rowsErrorBeanList = new ArrayList<ErrorBean>();
	private ArrayList<ContractDetailBean> rowsContractDetailBeanList = new ArrayList<ContractDetailBean>();
	private ArrayList<CheckDetailBean> rowsCheckDetailBeanList = new ArrayList<CheckDetailBean>();
	private ArrayList<TaskBean> rowsTaskList = new ArrayList<TaskBean>();

	private TaskBean mTaskBean;
	private ProductProblemBean mProductProblemBean;
	private AccountRenderedBean mAccountRenderedBean;
	private InspectionRecordBean mInspectionRecordBean;
	private SuperviseBean mSuperviseBean;
	private ContractDetailBean mContractDetailBean;
	private CheckDetailBean mCheckDetailBean;
	private ErrorBean mErrorBean;
	
	public DbLoadTask(Handler handler, Context context, String filePath) {
		this.mContext = context;
		this.mHandler = handler;
		this.mFilePath = filePath;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		Message msg = new Message();
		msg.obj = result;
		if (mFlag) {
			msg.what = FileManager.FORMAL_END;
		} else {
			msg.what = FileManager.FORMAL_ERROR;

		}
		mHandler.sendMessage(msg);
	}

	@Override
	protected Boolean doInBackground(Void... arg0) {

		// return disposePoiDbLoad(mFilePath);
		return disposeJxlDbLoad(mFilePath);
	}

	private Boolean disposeJxlDbLoad(String filPath) {

		LogUtils.d("task:" + filPath);
		File file = new File(filPath);
		String name = file.getName();
		if (name.trim().toLowerCase().endsWith(".xls")
				|| name.trim().toLowerCase().endsWith(".xlsx")) { // 如果扩展名等于xls文件
			LogUtils.d("进入解析");
			try {
				Workbook book = Workbook.getWorkbook(new File(filPath));
				Sheet[] sheets = book.getSheets();

				String taskName = book.getSheet(0).getName();

				for (int sheetNum = 0; sheetNum < sheets.length; sheetNum++) {
					Sheet sheet = book.getSheet(sheetNum);

					String[] strarray = book.getSheet(0).getName().split("-");

					// TODO: 质量问题记录表

					if (sheet.getName().equals("质量问题记录表")) {
						LogUtils.d("正在解析产品质量问题列表sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mProductProblemBean = null;
							mProductProblemBean = new ProductProblemBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mProductProblemBean.setTaskName(strarray[0]);
							mProductProblemBean.setTaskId(strarray[1]);
							LogUtils.e("解析问题表1行");
							setProductProbleamBean(mProductProblemBean,
									cellList);
						}
						
						// TODO:交验单
					} else if (sheet.getName().equals("交验单")) {
						LogUtils.d("正在解析交验单sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows - 1; i++) { // 按行循环
							cellList.clear();
							mAccountRenderedBean = null;
							mAccountRenderedBean = new AccountRenderedBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mAccountRenderedBean.setTaskName(strarray[0]);
							mAccountRenderedBean.setTaskId(strarray[1]);
							setAccountRenderedBeanBean(mAccountRenderedBean,
									cellList);
						}
						SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
						SQLiteDatabase db = sql.getReadableDatabase();

						String htbh = "";
						String tjpc = "";

						ArrayList<AccountRenderedBean> list = null;

						if (null != rowsAccountRenderedList
								&& rowsAccountRenderedList.size() > 0) {
							if (null != rowsAccountRenderedList.get(0)) {
								if (!isNull(rowsAccountRenderedList.get(0)
										.getHtbh())) {
									htbh = rowsAccountRenderedList.get(0)
											.getHtbh();
								}

								if (!isNull(rowsAccountRenderedList.get(0)
										.getTjpc())) {
									tjpc = rowsAccountRenderedList.get(0)
											.getTjpc();
								}
							}
						}

						if (!isNull(htbh) && !isNull(tjpc)) {
							list = (ArrayList<AccountRenderedBean>) sql
									.GetDataByWhere(db,
											AccountRenderedBean.class,
											"htbh like '"
													+ rowsAccountRenderedList
															.get(0).getHtbh()
													+ "' and tjpc like '"
													+ rowsAccountRenderedList
															.get(0).getTjpc()
													+ "'", null);
						}
						if (null != list && list.size() > 0) {
//							saveDbList(rowsAccountRenderedList,
//									UPDATA_INSTRUCTION);
						} else {
//							saveDbList(rowsAccountRenderedList,
//									SAVE_INSTRUCTION);
						}
						sql.close();

						// TODO:产品质量检验验收记录表

					} else if (sheet.getName().equals("产品质量检验验收记录表")) {

						LogUtils.d("正在解析产品检验记录sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mInspectionRecordBean = null;
							mInspectionRecordBean = new InspectionRecordBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mInspectionRecordBean.setTaskName(strarray[0]);
							mInspectionRecordBean.setTaskId(strarray[1]);
							setInspectionRecordBean(mInspectionRecordBean,
									cellList);
						}
					}

					// TODO: 质量监督检查记录表

					else if (sheet.getName().equals("质量监督检查记录表")) {

						LogUtils.d("解析质量监督检查记录表sheet");

						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mSuperviseBean = null;
							mSuperviseBean = new SuperviseBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mSuperviseBean.setTaskName(strarray[0]);
							mSuperviseBean.setTaskId(strarray[1]);
							setSuperviseBean(mSuperviseBean, cellList);
						}
					}

					// TODO: 产品检验验收发现问题汇总表
					else if (sheet.getName().equals("产品检验验收发现问题汇总表")) {

						LogUtils.d("解析产品检验验收发现问题汇总表sheet");

						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mErrorBean = null;
							mErrorBean = new ErrorBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mErrorBean.setTaskName(strarray[0]);
							mErrorBean.setTaskId(strarray[1]);
							setErrorBean(mErrorBean, cellList);
						}
					}

					
					// TODO: 合同明细表
					else if (sheet.getName().equals("合同明细表")) {

						LogUtils.d("解析合同明细表sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mContractDetailBean = null;
							mContractDetailBean = new ContractDetailBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
//							mContractDetailBean.setTaskName(strarray[0]);
//							mContractDetailBean.setTaskId(strarray[1]);
							setmContractDetailBean(mContractDetailBean, cellList);
						}
					}
					
					// TODO: 检验验收明细表
					else if (sheet.getName().equals("检验验收明细表")) {

						LogUtils.d("解析检验验收明细表sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mCheckDetailBean = null;
							mCheckDetailBean = new CheckDetailBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								LogUtils.d(sheet.getCell(j, i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
//							mContractDetailBean.setTaskName(strarray[0]);
//							mContractDetailBean.setTaskId(strarray[1]);
							setCheckDetailBean(mCheckDetailBean, cellList);
						}
					}
					
					// TODO: 任务表

					else if(sheet.getName().contains("-")){
						LogUtils.d("正在解析任务表sheet");
						int rows = sheet.getRows(); // 行
						int cols = sheet.getColumns(); // 列
						for (int i = 1; i < rows; i++) { // 按行循环
							cellList.clear();
							mTaskBean = null;
							mTaskBean = new TaskBean();
							for (int j = 0; j < cols; j++) { // 从列取值
								 LogUtils.d("rows:"+i+"cols:"+j+"value:"+sheet.getCell(j,
								 i).getContents()); // 得到行结果
								cellList.add((sheet.getCell(j, i))
										.getContents() + "");
							}
							mTaskBean.setTaskName(strarray[0]);
							mTaskBean.setTaskId(strarray[1]);
							LogUtils.e("设置任务一行数据");
							setTaskRowBean(mTaskBean, cellList);
						}
					}
				}
				book.close();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			mFlag = true;
		} else { // 格式错误
			mFlag = false;
		}
		return mFlag;

	}

	private void setCheckDetailBean(CheckDetailBean mCheckDetailBean,
			ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString())) {
					mCheckDetailBean.setBznd(cellList.get(i).toString());
				} 
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setQcsbmc(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setGgxh(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setQcsbdm(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setJyxm(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setJynr(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setJsyq(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setJyfs(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					mCheckDetailBean.setBz(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}

		if (null!=mCheckDetailBean.getBznd()&&!mCheckDetailBean.getBznd().equals("")) {
			rowsCheckDetailBeanList.add(mCheckDetailBean);
			new TableChangeDbUtils(mContext).setCheckDetailDb(mCheckDetailBean);
		}
		
	}

	private void setmContractDetailBean(
			ContractDetailBean mContractDetailBean, ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString())) {
					mContractDetailBean.setHtbh(cellList.get(i).toString());
				} else {
					mContractDetailBean.setHtbh("");
				}
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setQcsbmc(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setGgxh(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setQcsbdm(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setJldw(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setSl(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					mContractDetailBean.setBz(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}
		if (!mContractDetailBean.getHtbh().equals("")) {
			rowsContractDetailBeanList.add(mContractDetailBean);
			new TableChangeDbUtils(mContext).setContractDetailDb(mContractDetailBean);
		}
	}

	private void setErrorBean(ErrorBean mErrorBean, ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString())) {
					mErrorBean.setHtbh(cellList.get(i).toString());
				} else {
					mErrorBean.setHtbh("");
				}
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setBh(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setTxrq(cellList.get(i).toString());
				break;
		
			case 3:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setJyyszfxwt(cellList.get(i).toString());
				break;
				
			case 4:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setCpbh(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setYsryqz(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setCzdwptrqz(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setQcsbmc(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setGgxh(cellList.get(i).toString());
				break;
			case 9:
				if (!isNull(cellList.get(i).toString()))
					mErrorBean.setQcsbdm(cellList.get(i).toString());
				break;
				
			default:
				break;
			}
		}
		if (null!=mErrorBean.getHtbh()&&!mErrorBean.getHtbh().equals("")) {
			rowsErrorBeanList.add(mErrorBean);
			new TableChangeDbUtils(mContext).setErrorDb(mErrorBean);
		}
	}

	private void setSuperviseBean(SuperviseBean mSuperviseBean,
			ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString())) {
					mSuperviseBean.setHtbh(cellList.get(i).toString());
				} else {
					mSuperviseBean.setHtbh("");
				}
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdsj(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setCzdw(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setCzdwptr(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setQcsbmc(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdjl(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdr(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setGgxh(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setQcsbdm(cellList.get(i).toString());
				break;
			case 9:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setXh(cellList.get(i).toString());
				break;
			case 10:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdxm(cellList.get(i).toString());
				break;
			case 11:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdnr(cellList.get(i).toString());
				break;
			case 12:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdyq(cellList.get(i).toString());
				break;
			case 13:
				if (!isNull(cellList.get(i).toString()))
					mSuperviseBean.setJdjg(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}
		if (!mSuperviseBean.getHtbh().equals("")) {
			rowsSuperviseBeanList.add(mSuperviseBean);
			new TableChangeDbUtils(mContext).setSuperviseDb(mSuperviseBean);
		}
	}

	private void setInspectionRecordBean(
			InspectionRecordBean inspectionRecordBean, ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			LogUtils.e("第"+i+"条："+cellList.get(i).toString());
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString())) {
					inspectionRecordBean.setQcsbmc(cellList.get(i).toString());
				} else {
					inspectionRecordBean.setQcsbmc("");
				}
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setHtbh(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setDgsl(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setSjcpbh(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setBctjsl(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setCzdw(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setCppch(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJyrq(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setCpjyjlpdjl(cellList.get(i)
							.toString());
				break;
			case 9:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setXh(cellList.get(i).toString());
				break;
			case 10:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJyxm(cellList.get(i).toString());
				break;
			case 11:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJsyq(cellList.get(i).toString());
				break;
			case 12:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJyjg(cellList.get(i).toString());
				break;
			case 13:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJyjl(cellList.get(i).toString());
				break;
			case 14:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setYsry(cellList.get(i).toString());
				break;
			case 15:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setBz(cellList.get(i).toString());
				break;
			case 16:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setZjzysjl(cellList.get(i).toString());
				break;
			case 17:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setZjzcyqm(cellList.get(i).toString());
				break;

			case 18:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setJjdd(cellList.get(i).toString());
				break;

			case 19:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setGgxh(cellList.get(i).toString());
				break;

			case 20:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setQcsbdm(cellList.get(i).toString());
				break;

			case 21:
				if (!isNull(cellList.get(i).toString()))
					inspectionRecordBean.setRq(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}
		if (null!=inspectionRecordBean.getHtbh()&&!inspectionRecordBean.getHtbh().equals("")) {
			rowsInspectionRecordList.add(inspectionRecordBean);
			new TableChangeDbUtils(mContext).setInspectionRecordDb(inspectionRecordBean);
		}
	}

	private void setAccountRenderedBeanBean(
			AccountRenderedBean accountRenderedBean, ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjdw(cellList.get(i).toString());
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjsj(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setXmmc(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setHtbh(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setXmdh(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjcs(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setCpmc(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjsl(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjpc(cellList.get(i).toString());
				break;
			case 9:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setCpbh(cellList.get(i).toString());
				break;
			case 10:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjdwxyjl(cellList.get(i).toString());
				break;
			case 11:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setXytjsc(cellList.get(i).toString());
				break;
			case 12:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setXycs(cellList.get(i).toString());
				break;
			case 13:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setXybz(cellList.get(i).toString());
				break;
			case 14:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setTjtjscyj(cellList.get(i).toString());
				break;
			case 15:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setZjzsc(cellList.get(i).toString());
				break;
			case 16:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setScrq(cellList.get(i).toString());
				break;
			case 17:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setYsjl(cellList.get(i).toString());
				break;
			case 18:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setZjzys(cellList.get(i).toString());
				break;
			case 19:
				if (!isNull(cellList.get(i).toString()))
					accountRenderedBean.setYsrq(cellList.get(i).toString());
				break;
			default:
				break;
			}
		}
		if (null!=accountRenderedBean.getHtbh()&&!accountRenderedBean.getHtbh().equals("")) {
			rowsAccountRenderedList.add(accountRenderedBean);
		}
	}

	private void setProductProbleamBean(ProductProblemBean productProblemBean,
			ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setCzdw(cellList.get(i).toString());
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setHtbh(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setCpbh(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setWtfsrq(cellList.get(i).toString());
				break;

			case 4:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setWtfsdd(cellList.get(i).toString());
				break;

			case 5:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setQcsbmc(cellList.get(i).toString());
				break;

			case 6:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setGgxh(cellList.get(i).toString());
				break;

			case 7:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setQcsbdm(cellList.get(i).toString());
				break;

			case 8:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setZlwtqkms(cellList.get(i).toString());
				break;

			case 9:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setZjzzz(cellList.get(i).toString());
				break;

			case 10:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setZjzzzrq(cellList.get(i).toString());
				break;

			case 11:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setCzdwqr(cellList.get(i).toString());
				break;
			case 12:
				if (!isNull(cellList.get(i).toString()))
					productProblemBean.setCzdwqrrq(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}
		
		if (null!=productProblemBean.getHtbh()&&!productProblemBean.getHtbh().equals("")) {
			new TableChangeDbUtils(mContext).setProductProbleamDb(productProblemBean);
		}

	}

	private void setTaskRowBean(TaskBean taskBean, ArrayList cellList) {

		for (int i = 0; i < cellList.size(); i++) {
			switch (i) {
			case 0:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setXh(cellList.get(i).toString());
				break;
			case 1:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setHtjyysdw(cellList.get(i).toString());
				break;
			case 2:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setHtbh(cellList.get(i).toString());
				break;
			case 3:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setCzdw(cellList.get(i).toString());
				break;
			case 4:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setXs(cellList.get(i).toString());
				break;
			case 5:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setJs(cellList.get(i).toString());
				break;
			case 6:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setHtje(cellList.get(i).toString());
				break;
			case 7:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setCpmc(cellList.get(i).toString());
				break;
			case 8:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setJfqx(cellList.get(i).toString());
				break;
			case 9:
				if (!isNull(cellList.get(i).toString()))
					taskBean.setBz(cellList.get(i).toString());
				break;

			default:
				break;
			}
		}
		if (null != taskBean && !isNull(taskBean.getHtbh())){
			rowsTaskList.add(taskBean);
			new TableChangeDbUtils(mContext).setTaskDb(taskBean);
		}
	}

	/**
	 * 保存数据库
	 * 
	 * @param 设定文件
	 * @return 返回类型
	 * @throws
	 */
	private void saveDbList(List list, int instruction,int pos) {
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();
		switch (instruction) {
		case SAVE_INSTRUCTION:
			sql.SavaData(db, list);
			break;
		case UPDATA_INSTRUCTION:
			sql.Update(db, list.get(pos));
			break;
		default:
			break;
		}
		sql.close();
	}

	private Boolean disposePoiDbLoad(String filPath) {
		LogUtils.d("task:" + filPath);
		File file = new File(filPath);
		String name = file.getName();
		if (name.trim().toLowerCase().endsWith(".xls")
				|| name.trim().toLowerCase().endsWith(".xlsx")) { // 如果扩展名等于xls文件
			LogUtils.d("进入解析");
			try {
				HSSFSheet sheet = null;
				HSSFWorkbook workbook = null;
				workbook = new HSSFWorkbook(new FileInputStream(file)); // 获得整个Excel

				for (int sheetIndex = 0; sheetIndex < workbook
						.getNumberOfSheets(); sheetIndex++) {
					sheet = workbook.getSheetAt(sheetIndex); // 获所有得sheet
					String sheetName = workbook.getSheetName(sheetIndex); // sheetName
					String taskName = workbook.getSheetName(0);
					if (sheet != null) {
						if (sheetName.equals("合同列表")) {
							int firstRowNum = sheet.getFirstRowNum(); // 第一行
							int lastRowNum = sheet.getLastRowNum(); // 最后一行

							for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
								if (sheet.getRow(rowNum) != null) { // 如果行不为空
									HSSFRow row = sheet.getRow(rowNum);
									short firstCellNum = row.getFirstCellNum(); // 该行的第一个单元格
									short lastCellNum = row.getLastCellNum(); // 该行的最后一个单元格

									for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) { // 循环该行的每一个单元格
										HSSFCell cell = row.getCell(cellNum);
										if (cell != null) {
											if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
												continue;
											} else {
												LogUtils.d("cellNum:"
														+ cellNum
														+ "___"
														+ row.getCell(cellNum)
																.getStringCellValue());
												row.getCell(cellNum)
														.getStringCellValue();
											}
										}
									}
								}
							}
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtils.d("error");
			}
			mFlag = true;
		} else { // 格式错误
			mFlag = false;
		}
		return mFlag;
	}

	public boolean isNull(String str) {
		if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}
}
