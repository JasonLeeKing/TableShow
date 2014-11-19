package com.jxxy.tableshow.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.bean.TaskBean;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 创建excel
 * 
 * @ClassName: CreateExcelUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-7-30 上午12:42:22
 * 
 */
public class CreateExcelUtils {

	public static final String SD_PATH = Environment
			.getExternalStorageDirectory() + "/tableCatalogue/";
	
	public void createNewExcel(Context context, String filPath, String taskId,String taskName) {

		File mStorageDir = new File(SD_PATH);
		
		if (!mStorageDir.exists())
		{
			mStorageDir.mkdirs();
			if (!mStorageDir.mkdirs())
			{
				Log.d("T", "路径失败");
			}
		}
		
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(filPath));

			SQLiteUtils sql = SQLiteUtils.getInstance(context);
			SQLiteDatabase db = sql.getReadableDatabase();
			WritableSheet sheet = null;
			/** 创建任务表 */
			ArrayList<TaskBean> list = (ArrayList<TaskBean>) sql
					.GetDataByWhere(db, TaskBean.class, "taskId like '" + taskId
							+ "' and taskName like '" + taskName
							+ "'",null);
				// 生成任务表工作表，参数0表示这是第一页
				 sheet = book.createSheet(list.get(0)
						.getTaskName()+"-"+list.get(0)
						.getTaskId(), 0);
				// 设置任务表标题 第一列第一行(0,0)
				sheet.addCell(new Label(0, 0, "序号"));
				sheet.addCell(new Label(1, 0, "合同检验验收单位"));
				sheet.addCell(new Label(2, 0, "合同编号"));
				sheet.addCell(new Label(3, 0, "承制单位"));
				sheet.addCell(new Label(4, 0, "项数"));
				sheet.addCell(new Label(5, 0, "件数"));
				sheet.addCell(new Label(6, 0, "合同金额"));
				sheet.addCell(new Label(7, 0, "产品名称"));
				sheet.addCell(new Label(8, 0, "交货时间"));
				sheet.addCell(new Label(9, 0, "备注"));
				
				if (null != list && list.size() > 0) {
				// 设置任务表数据源
				for (int i = 0; i < list.size(); i++) {
					sheet.addCell(new Label(0, i+1, list.get(i).getXh()));
					sheet.addCell(new Label(1, i+1, list.get(i).getHtjyysdw()));
					sheet.addCell(new Label(2, i+1, list.get(i).getHtbh()));
					sheet.addCell(new Label(3, i+1, list.get(i).getCzdw()));
					sheet.addCell(new Label(4, i+1, list.get(i).getXs()));
					sheet.addCell(new Label(5, i+1, list.get(i).getJs()));
					sheet.addCell(new Label(6, i+1, list.get(i).getHtje()));
					sheet.addCell(new Label(7, i+1, list.get(i).getCpmc()));
					sheet.addCell(new Label(8, i+1, list.get(i).getJfqx()));
					sheet.addCell(new Label(9, i+1, list.get(i).getBz()));
				}
			}
			
			
			/**质量问题记录表 */
			ArrayList<ProductProblemBean> list2 = (ArrayList<ProductProblemBean>) sql
					.GetDataByWhere(db, ProductProblemBean.class, "taskId like '" + taskId
							+ "' and taskName like '" + taskName
							+ "'",null);
				// 生成任务表工作表，参数0表示这是第一页
				sheet = book.createSheet("质量问题记录表", 1);
				// 设置任务表标题 第一列第一行(0,0)
				sheet.addCell(new Label(0, 0, "承制单位"));
				sheet.addCell(new Label(1, 0, "合同编号"));
				sheet.addCell(new Label(2, 0, "产品编号"));
				sheet.addCell(new Label(3, 0, "问题发生日期"));
				sheet.addCell(new Label(4, 0, "问题发生地点"));
				sheet.addCell(new Label(5, 0, "器材设备名称"));
				sheet.addCell(new Label(6, 0, "规格型号"));
				sheet.addCell(new Label(7, 0, "器材设备代码"));
				sheet.addCell(new Label(8, 0, "质量问题情况描述"));
				sheet.addCell(new Label(9, 0, "质检组组长"));
				sheet.addCell(new Label(10, 0, "日期"));
				sheet.addCell(new Label(11, 0, "承制单位确认"));
				sheet.addCell(new Label(12, 0, "日期"));
				
				if (null != list2 && list2.size() > 0) {
				// 设置任务表数据源
				for (int i = 0; i < list2.size(); i++) {
					sheet.addCell(new Label(0, i+1, list2.get(i).getCzdw()));
					sheet.addCell(new Label(1, i+1, list2.get(i).getHtbh()));
					sheet.addCell(new Label(2, i+1, list2.get(i).getCpbh()));
					sheet.addCell(new Label(3, i+1, list2.get(i).getWtfsrq()));
					sheet.addCell(new Label(4, i+1, list2.get(i).getWtfsdd()));
					sheet.addCell(new Label(5, i+1, list2.get(i).getQcsbmc()));
					sheet.addCell(new Label(6, i+1, list2.get(i).getGgxh()));
					sheet.addCell(new Label(7, i+1, list2.get(i).getQcsbdm()));
					sheet.addCell(new Label(8, i+1, list2.get(i).getZlwtqkms()));
					sheet.addCell(new Label(9, i+1, list2.get(i).getZjzzz()));
					sheet.addCell(new Label(10, i+1, list2.get(i).getZjzzzrq()));
					sheet.addCell(new Label(11, i+1, list2.get(i).getCzdwqr()));
					sheet.addCell(new Label(12, i+1, list2.get(i).getCzdwqrrq()));
				}
			}
			
			/** 产品交验申请单 */
			ArrayList<AccountRenderedBean> list3 = (ArrayList<AccountRenderedBean>) sql
					.GetDataByWhere(db, AccountRenderedBean.class, "taskId like '" + taskId
							+ "' and taskName like '" + taskName
							+ "'",null);
				// 生成任务表工作表，参数0表示这是第一页
			   sheet = book.createSheet("产品交验申请单", 2);
				// 设置任务表标题 第一列第一行(0,0)
				sheet.addCell(new Label(0, 0, "提交单位"));
				sheet.addCell(new Label(1, 0, "提交时间"));
				sheet.addCell(new Label(2, 0, "合同编号"));
				sheet.addCell(new Label(3, 0, "提交次数"));
				sheet.addCell(new Label(4, 0, "委托验收单位"));
				sheet.addCell(new Label(5, 0, "提交地点"));
				sheet.addCell(new Label(6, 0, "产品批次号"));
				sheet.addCell(new Label(7, 0, "提交数量及编号"));
				sheet.addCell(new Label(8, 0, "产品检验验收条件"));
				sheet.addCell(new Label(9, 0, "产品质量"));
				sheet.addCell(new Label(10, 0, "质量负责人签字"));
				sheet.addCell(new Label(11, 0, "承制单位结论"));
				sheet.addCell(new Label(12, 0, "承制单位"));
				sheet.addCell(new Label(13, 0, "日期"));
				
//				sheet.addCell(new Label(14, 0, "提交条件审查意见"));
//				sheet.addCell(new Label(15, 0, "质检组"));
//				sheet.addCell(new Label(16, 0, "日期"));
//				sheet.addCell(new Label(17, 0, "验收结论"));
//				sheet.addCell(new Label(18, 0, "质检组"));
//				sheet.addCell(new Label(19, 0, "日期"));
				if (null != list3 && list3.size() > 0) {
				// 设置任务表数据源
//				for (int i = 0; i < list3.size(); i++) {
//					sheet.addCell(new Label(0, i+1, list3.get(i).getTjdw()));
//					sheet.addCell(new Label(1, i+1, list3.get(i).getTjsj()));
//					sheet.addCell(new Label(2, i+1, list3.get(i).getXmmc()));
//					sheet.addCell(new Label(3, i+1, list3.get(i).getHtbh()));
//					sheet.addCell(new Label(4, i+1, list3.get(i).getXmdh()));
//					sheet.addCell(new Label(5, i+1, list3.get(i).getTjcs()));
//					sheet.addCell(new Label(6, i+1, list3.get(i).getCpmc()));
//					sheet.addCell(new Label(7, i+1, list3.get(i).getTjsl()));
//					sheet.addCell(new Label(8, i+1, list3.get(i).getTjpc()));
//					sheet.addCell(new Label(9, i+1, list3.get(i).getCpbh()));
//					sheet.addCell(new Label(10, i+1, list3.get(i).getTjdwxyjl()));
//					sheet.addCell(new Label(11, i+1, list3.get(i).getXytjsc()));
//					sheet.addCell(new Label(12, i+1, list3.get(i).getXycs()));
//					sheet.addCell(new Label(13, i+1, list3.get(i).getXybz()));
//					
//					sheet.addCell(new Label(14, i+1, list3.get(i).getTjtjscyj()));
//					sheet.addCell(new Label(15, i+1, list3.get(i).getZjzsc()));
//					sheet.addCell(new Label(16, i+1, list3.get(i).getScrq()));
//					sheet.addCell(new Label(17, i+1, list3.get(i).getYsjl()));
//					sheet.addCell(new Label(18, i+1, list3.get(i).getZjzys()));
//					sheet.addCell(new Label(19, i+1, list3.get(i).getYsrq()));
//				}
			}
			
			/**产品质量检验验收记录表 */
			ArrayList<InspectionRecordBean> list4 = (ArrayList<InspectionRecordBean>) sql
					.GetDataByWhere(db, InspectionRecordBean.class, "taskId like '" + taskId
							+ "' and taskName like '" + taskName
							+ "'",null);
				// 生成任务表工作表，参数0表示这是第一页
				 sheet = book.createSheet("产品质量检验验收记录表", 3);
				// 设置任务表标题 第一列第一行(0,0)
				sheet.addCell(new Label(0, 0, "器材设备名称"));
				sheet.addCell(new Label(1, 0, "合同编号"));
				sheet.addCell(new Label(2, 0, "订购数量"));
				sheet.addCell(new Label(3, 0, "受检产品编号"));
				sheet.addCell(new Label(4, 0, "本次验收数量"));
				sheet.addCell(new Label(5, 0, "承制单位"));
				sheet.addCell(new Label(6, 0, "产品批次号"));
				sheet.addCell(new Label(7, 0, "检验日期"));
				sheet.addCell(new Label(8, 0, "产品检验记录及判定结论"));
				sheet.addCell(new Label(9, 0, "序号"));
				sheet.addCell(new Label(10, 0, "检验项目"));
				sheet.addCell(new Label(11, 0, "技术要求"));
				sheet.addCell(new Label(12, 0, "检测结果"));
				sheet.addCell(new Label(13, 0, "检验结论"));
				sheet.addCell(new Label(14, 0, "验收人员"));
				sheet.addCell(new Label(15, 0, "备注"));
				sheet.addCell(new Label(16, 0, "质检组验收结论"));
				sheet.addCell(new Label(17, 0, "质检组成员签名"));
				sheet.addCell(new Label(18, 0, "检验地点"));
				sheet.addCell(new Label(19, 0, "规格型号"));
				sheet.addCell(new Label(20, 0, "器材设备代码"));
				sheet.addCell(new Label(21, 0, "日期"));
				
				if (null != list4 && list4.size() > 0) {
				// 设置任务表数据源
				for (int i = 0; i < list4.size(); i++) {
					sheet.addCell(new Label(0, i+1, list4.get(i).getQcsbmc()));
					sheet.addCell(new Label(1, i+1, list4.get(i).getHtbh()));
					sheet.addCell(new Label(2, i+1, list4.get(i).getDgsl()));
					sheet.addCell(new Label(3, i+1, list4.get(i).getSjcpbh()));
					sheet.addCell(new Label(4, i+1, list4.get(i).getBctjsl()));
					sheet.addCell(new Label(5, i+1, list4.get(i).getCzdw()));
					sheet.addCell(new Label(6, i+1, list4.get(i).getCppch()));
					sheet.addCell(new Label(7, i+1, list4.get(i).getJyrq()));
					
//					sheet.addCell(new Label(8, i+1, list4.get(i).getXh())); 此列是空列
					sheet.addCell(new Label(9, i+1, list4.get(i).getXh()));
					sheet.addCell(new Label(10, i+1, list4.get(i).getJyxm()));
					sheet.addCell(new Label(11, i+1, list4.get(i).getJsyq()));
					sheet.addCell(new Label(12, i+1, list4.get(i).getJyjg()));
					sheet.addCell(new Label(13, i+1, list4.get(i).getJyjl()));
					sheet.addCell(new Label(14, i+1, list4.get(i).getYsry()));
					sheet.addCell(new Label(15, i+1, list4.get(i).getBz()));
					sheet.addCell(new Label(16, i+1, list4.get(i).getZjzysjl()));
					sheet.addCell(new Label(17, i+1, list4.get(i).getZjzcyqm()));
					sheet.addCell(new Label(18, i+1, list4.get(i).getJjdd()));
					sheet.addCell(new Label(19, i+1, list4.get(i).getGgxh()));
					sheet.addCell(new Label(20, i+1, list4.get(i).getQcsbdm()));
					sheet.addCell(new Label(21, i+1, list4.get(i).getRq()));
				}
			}
				
				/**质量监督检查记录表 */
				ArrayList<SuperviseBean> list5 = (ArrayList<SuperviseBean>) sql
						.GetDataByWhere(db, SuperviseBean.class, "taskId like '" + taskId
								+ "' and taskName like '" + taskName
								+ "'",null);
					// 生成任务表工作表，参数0表示这是第一页
					sheet = book.createSheet("质量监督检查记录表", 4);
					// 设置任务表标题 第一列第一行(0,0)
					sheet.addCell(new Label(0, 0, "合同编号"));
					sheet.addCell(new Label(1, 0, "监督时间"));
					sheet.addCell(new Label(2, 0, "承制单位"));
					sheet.addCell(new Label(3, 0, "承制单位陪同人"));
					sheet.addCell(new Label(4, 0, "器材设备名称"));
					sheet.addCell(new Label(5, 0, "监督结论"));
					sheet.addCell(new Label(6, 0, "监督人"));
					sheet.addCell(new Label(7, 0, "规格型号"));
					sheet.addCell(new Label(8, 0, "器材设备代码"));
					sheet.addCell(new Label(9, 0, "序号"));
					sheet.addCell(new Label(10, 0, "监督项目"));
					sheet.addCell(new Label(11, 0, "监督内容"));
					sheet.addCell(new Label(12, 0, "监督要求"));
					sheet.addCell(new Label(13, 0, "监督结果"));
					
					if (null != list5 && list5.size() > 0) {
					// 设置任务表数据源
					for (int i = 0; i < list5.size(); i++) {
						sheet.addCell(new Label(0, i+1, list5.get(i).getHtbh()));
						sheet.addCell(new Label(1, i+1, list5.get(i).getJdsj()));
						sheet.addCell(new Label(2, i+1, list5.get(i).getCzdw()));
						sheet.addCell(new Label(3, i+1, list5.get(i).getCzdwptr()));
						sheet.addCell(new Label(4, i+1, list5.get(i).getQcsbmc()));
						sheet.addCell(new Label(5, i+1, list5.get(i).getJdjl()));
						sheet.addCell(new Label(6, i+1, list5.get(i).getJdr()));
						sheet.addCell(new Label(7, i+1, list5.get(i).getGgxh()));
						sheet.addCell(new Label(8, i+1, list5.get(i).getQcsbdm()));
						sheet.addCell(new Label(9, i+1, list5.get(i).getXh()));
						sheet.addCell(new Label(10, i+1, list5.get(i).getJdxm()));
						sheet.addCell(new Label(11, i+1, list5.get(i).getJdnr()));
						sheet.addCell(new Label(12, i+1, list5.get(i).getJdyq()));
						sheet.addCell(new Label(13, i+1, list5.get(i).getJdjg()));
					}
				}
					/**产品检验验收发现问题汇总表 */
					ArrayList<ErrorBean> list6 = (ArrayList<ErrorBean>) sql
							.GetDataByWhere(db, ErrorBean.class, "taskId like '" + taskId
									+ "' and taskName like '" + taskName
									+ "'",null);
					// 生成任务表工作表，参数0表示这是第一页
					sheet = book.createSheet("产品检验验收发现问题汇总表", 5);
					// 设置任务表标题 第一列第一行(0,0)
					sheet.addCell(new Label(0, 0, "合同编号"));
					sheet.addCell(new Label(1, 0, "编号"));
					sheet.addCell(new Label(2, 0, "填写日期"));
					sheet.addCell(new Label(3, 0, "检验验收中发现问题"));
					sheet.addCell(new Label(4, 0, "产品编号"));
					sheet.addCell(new Label(5, 0, "验收人员签字"));
					sheet.addCell(new Label(6, 0, "承制单位陪同人签字"));
					sheet.addCell(new Label(7, 0, "器材设备名称"));
					sheet.addCell(new Label(8, 0, "规格型号"));
					sheet.addCell(new Label(9, 0, "器材设备代码"));
					
					if (null != list6 && list6.size() > 0) {
						// 设置任务表数据源
						for (int i = 0; i < list6.size(); i++) {
							sheet.addCell(new Label(0, i+1, list6.get(i).getHtbh()));
							sheet.addCell(new Label(1, i+1, list6.get(i).getBh()));
							sheet.addCell(new Label(2, i+1, list6.get(i).getTxrq()));
							sheet.addCell(new Label(3, i+1, list6.get(i).getJyyszfxwt()));
							sheet.addCell(new Label(4, i+1, list6.get(i).getCpbh()));
							sheet.addCell(new Label(5, i+1, list6.get(i).getYsryqz()));
							sheet.addCell(new Label(6, i+1, list6.get(i).getCzdwptrqz()));
							sheet.addCell(new Label(7, i+1, list6.get(i).getQcsbmc()));
							sheet.addCell(new Label(8, i+1, list6.get(i).getGgxh()));
							sheet.addCell(new Label(9, i+1, list6.get(i).getQcsbdm()));
						}
					}
					
					/**合同明细表 */
//					ArrayList<ErrorBean> list6 = (ArrayList<ErrorBean>) sql
//							.GetDataByWhere(db, SuperviseBean.class, "taskId like '" + taskId
//									+ "' and taskName like '" + taskName
//									+ "'",null);
					// 生成任务表工作表，参数0表示这是第一页
					sheet = book.createSheet("合同明细表", 6);
					// 设置任务表标题 第一列第一行(0,0)
					sheet.addCell(new Label(0, 0, "合同编号"));
					sheet.addCell(new Label(1, 0, "器材设备名称"));
					sheet.addCell(new Label(2, 0, "规格型号"));
					sheet.addCell(new Label(3, 0, "器材设备代码"));
					sheet.addCell(new Label(4, 0, "计量单位"));
					sheet.addCell(new Label(5, 0, "数量"));
					sheet.addCell(new Label(6, 0, "备注"));
					
				
					/**检验验收明细表 */
//					ArrayList<ErrorBean> list6 = (ArrayList<ErrorBean>) sql
//							.GetDataByWhere(db, SuperviseBean.class, "taskId like '" + taskId
//									+ "' and taskName like '" + taskName
//									+ "'",null);
					// 生成任务表工作表，参数0表示这是第一页
					sheet = book.createSheet("检验验收明细表", 7);
					// 设置任务表标题 第一列第一行(0,0)
					sheet.addCell(new Label(0, 0, "编制年度"));
					sheet.addCell(new Label(1, 0, "器材设备名称"));
					sheet.addCell(new Label(2, 0, "规格型号"));
					sheet.addCell(new Label(3, 0, "器材设备代码"));
					sheet.addCell(new Label(4, 0, "检验项目"));
					sheet.addCell(new Label(5, 0, "检验内容"));
					sheet.addCell(new Label(6, 0, "技术要求"));
					sheet.addCell(new Label(7, 0, "检验方式"));
					sheet.addCell(new Label(8, 0, "备注"));
					
					/**工作纪要 */
//					ArrayList<ErrorBean> list6 = (ArrayList<ErrorBean>) sql
//							.GetDataByWhere(db, SuperviseBean.class, "taskId like '" + taskId
//									+ "' and taskName like '" + taskName
//									+ "'",null);
					sheet = book.createSheet("工作纪要", 8);
					sheet.addCell(new Label(0, 0, "工作纪要"));
					
					
					
			Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
			// 写入数据并关闭文件
			book.write();
			book.close();
			sql.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
