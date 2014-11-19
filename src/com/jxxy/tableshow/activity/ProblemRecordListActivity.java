package com.jxxy.tableshow.activity;

import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ContractListAdapter;
import com.jxxy.tableshow.adapter.ProblemRecordAdapter;
import com.jxxy.tableshow.bean.ContractBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TopBar;

/**
 * @ClassName: ProblemRecordListActivity
 * @Description: 产品质量技术问题记录列表
 * @author LiLong
 * @date 2014-6-28 下午3:15:08
 * 
 */
public class ProblemRecordListActivity extends BaseActivity implements
		OnTopBarListener, OnItemClickListener, OnClickListener, OnItemLongClickListener {

	private ListView mListView;
	private TitleView mTopBar;
	private ProblemRecordAdapter mProblemRecordAdapter;
	public List<ProductProblemBean> mProductProblemList = null;
	private Button btn_add_problem_record;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.problem_record_list_layout);
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
		SQLiteDatabase db = sql.getReadableDatabase();

		mProductProblemList = (List<ProductProblemBean>) sql.GetDataByWhere(
				db,
				ProductProblemBean.class,
				 "taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'",null);

		if (null != mProductProblemList && mProductProblemList.size() > 0) {
			mProblemRecordAdapter = new ProblemRecordAdapter(this,
					mProductProblemList);
			mListView.setAdapter(mProblemRecordAdapter);
			if (null != mProblemRecordAdapter) {
				mProblemRecordAdapter.notifyDataSetChanged();
			}
		}
	}

	private void initView() {
//		mTopBar = (TitleView) findViewById(R.id.problem_record_topbar);
		mTopBar.setTitle("产品质量技术问题列表");
		mTopBar.setLeftButton("返回", new com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		

		mListView = (ListView) findViewById(R.id.problem_record_listview);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		
		btn_add_problem_record = (Button) findViewById(R.id.btn_add_problem_record);
		btn_add_problem_record.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent i = new Intent(ProblemRecordListActivity.this,
				ProblemRecordActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY,
				mProductProblemList.get(arg2));
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
		case R.id.btn_add_problem_record:
			Intent i = new Intent(ProblemRecordListActivity.this,
					ProblemRecordActivity.class);
			i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
			startActivity(i);

			// Bundle bundle = new Bundle();
			// bundle.putSerializable(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY,
			// mProductProblemList.get(arg2));
			// i.putExtras(bundle);
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
				ProblemRecordListActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：删除后数据不可恢复）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleDbInfo(mProductProblemList,itemId);
						mProductProblemList.remove(itemId);
						if (null != mProblemRecordAdapter) {
							mProblemRecordAdapter.notifyDataSetChanged();
							mListView.invalidate();
							LogUtils.d("重绘");
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<ProductProblemBean> mProblemList, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(ProblemRecordListActivity.this);
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, ProductProblemBean.class, "htbh like '"
						+ mProblemList.get(itemId).getHtbh()
						+ "' and cpbh like '"
						+ mProblemList.get(itemId).getCpbh()
						+"'and cpbh like '"
						+ mProblemList.get(itemId).getQcsbmc()+"'");
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
