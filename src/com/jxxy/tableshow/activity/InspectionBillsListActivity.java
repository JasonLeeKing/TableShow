package com.jxxy.tableshow.activity;

import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.InspectionRecordAdapter;
import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TopBar;
import com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener;

/**
 * @ClassName: InspectionBillsListActivity
 * @Description: 校验单列表
 * @author LiLong
 * @date 2014-6-28 下午4:48:15
 * 
 */
public class InspectionBillsListActivity extends BaseActivity implements
		OnTopBarListener, OnItemClickListener, OnClickListener, OnItemLongClickListener {

	private ListView mListView;
	private TitleView mTopBar;

	private InspectionRecordAdapter mInspectionRecordAdapter;
	public List<AccountRenderedBean> mInspectionRecordList;
	private Button btn_add_inspection_bills;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.inspection_bill_list_layout);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {

		PreferencesUtils.init(this);
		SQLiteUtils sql = SQLiteUtils.getInstance(this);
		SQLiteDatabase db = sql.getWritableDatabase();
		mInspectionRecordList = (List<AccountRenderedBean>) sql.GetDataByWhere(
				db,
				AccountRenderedBean.class,
				 "taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'",null);
		
		if (null != mInspectionRecordList && mInspectionRecordList.size() > 0) {
			mInspectionRecordAdapter = new InspectionRecordAdapter(this,
					mInspectionRecordList);
			mListView.setAdapter(mInspectionRecordAdapter);
			if (null != mInspectionRecordAdapter) {
				mInspectionRecordAdapter.notifyDataSetChanged();
			}
		}

	}

	private void initView() {
		mTopBar = (TitleView) findViewById(R.id.inspection_bills_topbar);
		
		mTopBar.setTitle("交验单列表");
		mTopBar.hiddenRightButton();
		mTopBar.removeRightButton();
		mTopBar.setLeftButton("返回", new OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});

		mListView = (ListView) findViewById(R.id.inspection_bills_listview);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		
		btn_add_inspection_bills = (Button) findViewById(R.id.btn_add_inspection_bills);
		btn_add_inspection_bills.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent i = new Intent(this, InspectionBillsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_INSPECTION_BILLS_BEAN_KEY,
				mInspectionRecordList.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_add_inspection_bills:
			Intent i = new Intent(this, InspectionBillsActivity.class);
			i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
			startActivity(i);
			break;

		default:
			break;
		}
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
				InspectionBillsListActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：删除后数据不可恢复）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleDbInfo(mInspectionRecordList,itemId);
						mInspectionRecordList.remove(itemId);
						if (null != mInspectionRecordAdapter) {
							mInspectionRecordAdapter.notifyDataSetChanged();
							mListView.invalidate();
							LogUtils.d("重绘");
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<AccountRenderedBean> mAccountRenderedList, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(InspectionBillsListActivity.this);
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, AccountRenderedBean.class, "htbh like '"
						+ mAccountRenderedList.get(itemId).getHtbh()
						+ "' and tjpc like '"
						+ mAccountRenderedList.get(itemId).getTjpc()
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
