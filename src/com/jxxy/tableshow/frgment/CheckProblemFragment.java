package com.jxxy.tableshow.frgment;

import java.util.List;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.EditCheckProblemActivity;
import com.jxxy.tableshow.activity.ProblemRecordActivity;
import com.jxxy.tableshow.adapter.CheckProblemAdapter;
import com.jxxy.tableshow.adapter.ProblemRecordAdapter;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * 产品检验验收发现的问题汇总表
* @ClassName: CheckProblemFragment 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-7 下午11:20:52 
*
 */
public class CheckProblemFragment extends BaseFragment implements OnItemClickListener, OnItemLongClickListener, OnClickListener{

	private ListView mListView;
	private TitleView mTopBar;
	private CheckProblemAdapter mCheckProblemAdapter;
	public List<ErrorBean> mCheckProblemList = null;
	private Button btn_add_check_problem_record;
	View view ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.check_problem_layout, container,false);
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initView();
		getIntentData();
	}
	

	private void getIntentData() {

		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getReadableDatabase();

		mCheckProblemList = (List<ErrorBean>) sql.GetDataByWhere(
				db,
				ErrorBean.class,
				 "taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'",null);

		if (null != mCheckProblemList && mCheckProblemList.size() > 0) {
			mCheckProblemAdapter = new CheckProblemAdapter(getActivity(),
					mCheckProblemList);
			mListView.setAdapter(mCheckProblemAdapter);
			if (null != mCheckProblemAdapter) {
				mCheckProblemAdapter.notifyDataSetChanged();
			}
		}
	}

	private void initView() {

		mListView = (ListView) view.findViewById(R.id.check_problem_listview);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		btn_add_check_problem_record = (Button)view.findViewById(R.id.btn_add_check_problem_record);
		btn_add_check_problem_record.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		CheckProblemAdapter.SELECT = arg2;
		Intent i = new Intent(getActivity(),
				EditCheckProblemActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY,
				mCheckProblemList.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_add_check_problem_record:
			
			Intent i = new Intent(getActivity(),
					EditCheckProblemActivity.class);
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
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否删除本条数据（注意：删除后数据不可恢复）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleDbInfo(mCheckProblemList,itemId);
						mCheckProblemList.remove(itemId);
						if (null != mCheckProblemAdapter) {
							mCheckProblemAdapter.notifyDataSetChanged();
							mListView.invalidate();
							LogUtils.d("重绘");
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<ErrorBean> mProblemList, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, ErrorBean.class, "htbh like '"
						+ mProblemList.get(itemId).getHtbh()
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
