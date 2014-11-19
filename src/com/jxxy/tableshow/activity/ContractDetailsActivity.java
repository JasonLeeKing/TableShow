package com.jxxy.tableshow.activity;

import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ContractDetailsAdapter;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.CreateExcelUtils;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

/**
 * 合同列表详情
 * 
 * @ClassName: ContractDetailsActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-6-28 上午1:00:17
 * 
 */
public class ContractDetailsActivity extends BaseActivity implements
		OnTopBarListener {

	private TitleView mTopBar;
	private Button contract_out;
	private List<TaskBean> mTaskBeanList;
	private ContractDetailsAdapter mContractDetailsAdapter;
	private ListView  mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.contract_details_layout);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {
		
		SQLiteUtils sql = SQLiteUtils.getInstance(this);
		SQLiteDatabase db = sql.getWritableDatabase();
		
		mTaskBeanList = (List<TaskBean>) sql.GetDataByWhere(
				db,
				TaskBean.class,
				"taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'",null);
		
		if (null != mTaskBeanList && mTaskBeanList.size() > 0) {
			mContractDetailsAdapter = new ContractDetailsAdapter(this,
					mTaskBeanList);
			mListView.setAdapter(mContractDetailsAdapter);
			if (null != mContractDetailsAdapter) {
				mContractDetailsAdapter.notifyDataSetChanged();
			}
		}
		
	}


	private void initView() {
		
		mListView = (ListView) findViewById(R.id.contrace_detail_listview);
//		mTopBar = (TitleView) findViewById(R.id.contracet_details_topbar);
		mTopBar.setTitle("工作任务分工表");
		mTopBar.setLeftButton("返回", new com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		mTopBar.setRightButton("更多", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				Intent intent = new Intent (ContractDetailsActivity.this,MainTopRightDialog.class);			
				startActivity(intent);	
//				ShowNetDialog();
			}
		});
		
		contract_out = (Button) findViewById(R.id.contract_out);
		contract_out.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ShowNetDialog();
			}
		});
		
	}

	@Override
	public void onTopBarLeftBtn() {
		finish();
	}

	@Override
	public void onTopBarRightBtn() {

	}

	@Override
	public void onTopbarCenter() {

	}


	private void ShowNetDialog() {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				ContractDetailsActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否导出Excel文件（文件会保存在SD卡tableCatalogue目录下）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String timeStamp = String.valueOf(new Date().getTime());
						new CreateExcelUtils().createNewExcel(
								ContractDetailsActivity.this,
								CreateExcelUtils.SD_PATH + "/File_" + timeStamp
										+ ".xls", PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID),PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKNAME));
						dialog.dismiss();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialog = customBuilder.create();
		dialog.show();
	}

}
