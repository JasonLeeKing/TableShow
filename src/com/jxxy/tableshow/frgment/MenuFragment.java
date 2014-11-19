package com.jxxy.tableshow.frgment;

import java.util.List;

import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.ContractListActivity;
import com.jxxy.tableshow.adapter.ContractListAdapter;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MenuFragment extends BaseFragment implements OnItemClickListener,
		OnItemLongClickListener {

	/** 菜单 */
	private ListView mListView;
	/** 适配器 */
	private ContractListAdapter mContractListAdapter;
	Fragment fragment = null;
	public List<TaskBean> mTaskList;

	// Activity 和 Fragment 建立关联时，初始化适配器中数据
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		LogUtils.d("onAttach");
	}

	// 加载布局文件，绑定适配器
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LogUtils.d("onCreateView");
		View view = inflater
				.inflate(R.layout.contract_layout, container, false);
		mListView = (ListView) view.findViewById(R.id.contrace_listview);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		return view;
	}

	// 当ACTIVITY 创建完毕，判断有没有DETAILS_LAYOUT元素，如果有说明是平板（双页）
	// 如果没有说明是手机（单页）
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		LogUtils.d("onActivityCreated");
		// if(getActivity().findViewById(R.id.details_layout)!=null){
		// isTwoPane = true;
		// }else{
		// isTwoPane = false;
		// }
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SQLiteDatabase db = SQLiteUtils.getInstance(getActivity())
				.getReadableDatabase();

		mTaskList = (List<TaskBean>) SQLiteUtils.getInstance(getActivity())
				.GetDataByWhere(db, TaskBean.class, null, "taskName,taskId");

		if (null != mTaskList && mTaskList.size() > 0) {
			mContractListAdapter = new ContractListAdapter(getActivity(),
					mTaskList);
		}
		
		mListView.setAdapter(mContractListAdapter);
		if (null != mContractListAdapter) {
			mContractListAdapter.notifyDataSetChanged();
		}
		
	}

	// 处理LIST点击，PAD 动态添加FRAGMENT 。手机打开新ACTIVITY
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {

		ContractListAdapter.SELECTED = index;
		if (null != mContractListAdapter) {
			mContractListAdapter.notifyDataSetChanged();
		}
		
		fragment = new TabeleFragment();
		PreferencesUtils.init(getActivity());
		PreferencesUtils.setShareStringData(PreferencesUtils.TASKID, mTaskList
				.get(index).getTaskId());
		PreferencesUtils.setShareStringData(PreferencesUtils.TASKNAME, mTaskList
				.get(index).getTaskName());
		getFragmentManager().beginTransaction()
				.replace(R.id.details_layout, fragment).commit();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		System.out.println("长按：" + arg3);
		ShowNetDialog(arg2);
		return true;
	}

	private void ShowNetDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：任务清单删除，将无法展示质量技术问题表，校验单及产品检验记录表）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("");

						deleDbInfo(mTaskList, itemId);
						mTaskList.remove(itemId);
						if (null != mContractListAdapter) {
							mContractListAdapter.notifyDataSetChanged();
							mListView.invalidate();
							LogUtils.d("重绘");
						}
						if(null!=fragment)
						getFragmentManager().beginTransaction().remove(fragment).commit();
						dialog.dismiss();
					}

					private void deleDbInfo(List<TaskBean> mTaskList, int itemId) {
						SQLiteUtils sql = SQLiteUtils
								.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						// db.execSQL("DELETE FROM student WHERE id IN (SELECT id FROM student ORDER BY id ASC LIMIT 0,1000)");
						// sql.DeleteById(db, ContractListActivity.class,
						// String.valueOf(mTaskList.get(itemId).get_id()));

						sql.DeleteByWhere(db, TaskBean.class,
								"taskId like '"
										+ mTaskList.get(itemId).getTaskId()
										+ "' and taskName like '"
										+ mTaskList.get(itemId).getTaskName()
										+ "'");

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
