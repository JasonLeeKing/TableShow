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
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ContractListAdapter;
import com.jxxy.tableshow.adapter.InspectionRecordAdapter;
import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.ContractBean;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.CreateExcelUtils;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener;
import com.jxxy.tableshow.view.TopBar;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

/**
 * 任务列表
 * 
 * @ClassName: ContractListActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-6-19 下午10:14:33
 * 
 */
public class ContractListActivity extends BaseActivity implements
		OnTopBarListener, OnItemClickListener, OnItemLongClickListener {

	private ListView mListView;
	private TitleView mTopBar;
	public ContractListAdapter mContractListAdapter;
	public List<TaskBean> mTaskList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contract_layout);
		initView();
	}

	private void initView() {

		mTopBar = (TitleView) findViewById(R.id.contrace_topbar);
		mTopBar.setTitle("工作任务清单");
		mTopBar.hiddenRightButton();
		mTopBar.removeRightButton();
		mTopBar.setLeftButton("导入", new OnLeftButtonClickListener(){

			public void onClick(View button) {
				
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					Intent i = new Intent(ContractListActivity.this, FileManager.class);
					startActivity(i);
				}else{
					Toast.makeText(ContractListActivity.this, "没有SD卡", Toast.LENGTH_SHORT).show();
				}
			
			}
			
		});

		mListView = (ListView) findViewById(R.id.contrace_listview);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
	}

	protected void onResume() {
		super.onResume();
		LogUtils.d("onResume");
		SQLiteDatabase db = SQLiteUtils.getInstance(ContractListActivity.this)
				.getReadableDatabase();
		
		mTaskList = (List<TaskBean>) SQLiteUtils.getInstance(
				ContractListActivity.this).GetDataByWhere(
				db,
				TaskBean.class,null
				, "taskName");
		
 		if (null != mTaskList && mTaskList.size() > 0) {
			mContractListAdapter = new ContractListAdapter(this,
					mTaskList);
			mListView.setAdapter(mContractListAdapter);
			if (null != mContractListAdapter) {
				mContractListAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public void onTopBarLeftBtn() {}

	@Override
	public void onTopBarRightBtn() {

	}

	@Override
	public void onTopbarCenter() {

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(ContractListActivity.this,
				ContractDetailsActivity.class);
		PreferencesUtils.init(this);
		PreferencesUtils.setShareStringData(PreferencesUtils.TASKID, mTaskList.get(arg2).getTaskId());
		startActivity(i);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		System.out.println("长按："+arg3);
		ShowNetDialog(arg2);
		return true;
	}

	private void ShowNetDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				ContractListActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：任务清单删除，将无法展示质量技术问题表，校验单及产品检验记录表）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("");
						
						deleDbInfo(mTaskList,itemId);
						mTaskList.remove(itemId);
						if (null != mContractListAdapter) {
							mContractListAdapter.notifyDataSetChanged();
							mListView.invalidate();
							LogUtils.d("重绘");
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<TaskBean> mTaskList, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(ContractListActivity.this);
						SQLiteDatabase db = sql.getWritableDatabase();
						
//						db.execSQL("DELETE FROM student WHERE id IN (SELECT id FROM student ORDER BY id ASC LIMIT 0,1000)");  
//						sql.DeleteById(db, ContractListActivity.class,  String.valueOf(mTaskList.get(itemId).get_id()));
						
						sql.DeleteByWhere(db, TaskBean.class, "taskId like '"
						+ mTaskList.get(itemId).getTaskId()
						+ "' and taskName like '"
						+ mTaskList.get(itemId).getTaskName()
						+"'");
						
						sql.close();
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
