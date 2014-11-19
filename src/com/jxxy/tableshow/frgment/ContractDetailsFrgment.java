package com.jxxy.tableshow.frgment;

import java.util.List;

import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ContractDetailsAdapter;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * 工作任务分工表
 * 
 * @ClassName: ContractDetailsFrgment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-10-4 上午12:28:26
 * 
 */
public class ContractDetailsFrgment extends BaseFragment {

	private Button contract_out;
	private List<TaskBean> mTaskBeanList;
	private ContractDetailsAdapter mContractDetailsAdapter;
	private ListView mListView;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contract_details_layout,
				container, false);
		mListView = (ListView) view.findViewById(R.id.contrace_detail_listview);
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getWritableDatabase();

		mTaskBeanList = (List<TaskBean>) sql.GetDataByWhere(
				db,
				TaskBean.class,
				"taskId like '"
						+ PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID)
						+ "'", null);
		
		if (null != mTaskBeanList && mTaskBeanList.size() > 0) {
			LogUtils.e("一共几条任务："+mTaskBeanList.size());
			mContractDetailsAdapter = new ContractDetailsAdapter(getActivity(),
					mTaskBeanList);
		}
		
		mListView.setAdapter(mContractDetailsAdapter);
		if (null != mContractDetailsAdapter) {
			mContractDetailsAdapter.notifyDataSetChanged();
		}
		
		
	}

}
