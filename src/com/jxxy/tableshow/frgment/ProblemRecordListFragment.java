package com.jxxy.tableshow.frgment;

import java.util.List;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.ProblemRecordActivity;
import com.jxxy.tableshow.activity.ProblemRecordListActivity;
import com.jxxy.tableshow.adapter.ProblemRecordAdapter;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;

/**
 * 质量技术问题记录表
* @ClassName: ProblemRecordListFragment 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-7 下午7:06:49 
*
 */
public class ProblemRecordListFragment extends BaseFragment implements OnItemClickListener, OnItemLongClickListener, OnClickListener{
	
	private ListView mListView;
	private TitleView mTopBar;
	private ProblemRecordAdapter mProblemRecordAdapter;
	public List<ProductProblemBean> mProductProblemList = null;
	private Button btn_add_problem_record;
	View view ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.problem_record_list_layout, container,false);
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

		mProductProblemList = (List<ProductProblemBean>) sql.GetDataByWhere(
				db,
				ProductProblemBean.class,
				 "taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'",null);

		if (null != mProductProblemList && mProductProblemList.size() > 0) {
			mProblemRecordAdapter = new ProblemRecordAdapter(getActivity(),
					mProductProblemList);
			mListView.setAdapter(mProblemRecordAdapter);
			if (null != mProblemRecordAdapter) {
				mProblemRecordAdapter.notifyDataSetChanged();
			}
		}
	}

	private void initView() {

		mListView = (ListView) view.findViewById(R.id.problem_record_listview);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		
		btn_add_problem_record = (Button)view.findViewById(R.id.btn_add_problem_record);
		btn_add_problem_record.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ProblemRecordAdapter.SELECT = arg2;
		if (null != mProblemRecordAdapter) {
			mProblemRecordAdapter.notifyDataSetChanged();
		}
		Intent i = new Intent(getActivity(),
				ProblemRecordActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY,
				mProductProblemList.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_add_problem_record:
			Intent i = new Intent(getActivity(),
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
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否删除本条数据 （注意：删除后数据不可恢复）")
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
						SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, ProductProblemBean.class, "htbh like '"
						+ mProblemList.get(itemId).getHtbh()
						+ "' and cpbh like '"
						+ mProblemList.get(itemId).getCpbh()
						+"'and qcsbmc like '"
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
