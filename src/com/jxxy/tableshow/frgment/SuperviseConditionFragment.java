package com.jxxy.tableshow.frgment;

import java.util.ArrayList;
import java.util.List;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.EditSuperviseConditionActivity;
import com.jxxy.tableshow.adapter.SuperviseConditionAdapter;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class SuperviseConditionFragment extends BaseFragment implements
		OnClickListener, OnItemClickListener, OnItemLongClickListener {

	public ListView mListView;
	private Button btn_add_supervise_condit_conclusion;
	SuperviseBean mSuperviseBean;
	ArrayList<SuperviseBean> list;
	SuperviseConditionAdapter mSuperviseConditionAdapter;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.supervise_condition_layout,
				container, false);
		mListView = (ListView) view
				.findViewById(R.id.supervise_condit_listview);
		btn_add_supervise_condit_conclusion = (Button) view
				.findViewById(R.id.btn_add_supervise_condit_conclusion);
		btn_add_supervise_condit_conclusion.setOnClickListener(this);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		getIntentData();

	}

	private void getIntentData() {

		mSuperviseBean = (SuperviseBean) getArguments().getSerializable(
				IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		;
		if (mSuperviseBean != null) {
			installViewData(mSuperviseBean.getHtbh(), mSuperviseBean.getJdsj());
		} else {
			mSuperviseBean = new SuperviseBean();
		}
	}

	private void installViewData(String htbh, String jdsj) {

		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getReadableDatabase();
		if (!isNull(htbh) && !isNull(jdsj)) {

			// ArrayList<InspectionRecordBean> testList =
			// (ArrayList<InspectionRecordBean>) sql.GetAllData(db,
			// InspectionRecordBean.class);
			ArrayList<SuperviseBean> testList = (ArrayList<SuperviseBean>) sql
					.GetAllData(db, SuperviseBean.class);
			list = (ArrayList<SuperviseBean>) sql.GetDataByWhere(db,
					SuperviseBean.class, "htbh like '" + htbh
							+ "' and jdsj like '" + jdsj + "'", null);

			// + "' and jdxm like '"
			// + jdxm
			//
			if (null != list && list.size() > 0) {
				mSuperviseConditionAdapter = new SuperviseConditionAdapter(
						getActivity(), list);
				mListView.setAdapter(mSuperviseConditionAdapter);
				if (null != mSuperviseConditionAdapter) {
					mSuperviseConditionAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		System.out.println("长按：" + arg3);
		ShowNetDialog(arg2);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		SuperviseConditionAdapter.SELECT = arg2;
		if (null != mSuperviseConditionAdapter) {
			mSuperviseConditionAdapter.notifyDataSetChanged();
		}
		Intent i = new Intent(getActivity(),
				EditSuperviseConditionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "updata");
		bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
				list.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_supervise_condit_conclusion:
			Intent i = new Intent(getActivity(),
					EditSuperviseConditionActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
					mSuperviseBean);
			i.putExtra(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "save");
			i.putExtras(bundle);
			startActivity(i);
			break;

		default:
			break;
		}
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

						deleDbInfo(list, itemId);
						list.remove(itemId);
						if (null != mSuperviseConditionAdapter) {
							mSuperviseConditionAdapter.notifyDataSetChanged();
							mListView.invalidate();
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<SuperviseBean> mSuperviseBean,
							int itemId) {
						SQLiteUtils sql = SQLiteUtils
								.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, SuperviseBean.class,
								"htbh like '"
										+ mSuperviseBean.get(itemId).getHtbh()
										+ "' and jdsj like '"
										+ mSuperviseBean.get(itemId).getJdsj()
										+ "' and jdxm like '"
										+ mSuperviseBean.get(itemId).getJdxm()
										+ "'");

						// +"'and sjcpbh like '"
						// + mSuperviseBean.get(itemId).getSjcpbh()
						// +"'and xh like '"
						// + mSuperviseBean.get(itemId).getXh()

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
