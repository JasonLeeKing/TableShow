package com.jxxy.tableshow.frgment;

import java.util.List;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.EditSuperviseExamineActivity;
import com.jxxy.tableshow.activity.ProductRecordDetailsActivity;
import com.jxxy.tableshow.adapter.ContractDetailsAdapter;
import com.jxxy.tableshow.adapter.ProductRecordAdapter;
import com.jxxy.tableshow.adapter.SuperviseRecordAdapter;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;

import android.app.Activity;
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
 * 质量监督检查记录表
* @ClassName: SuperviseExamineFrament 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-7 下午11:15:45 
*
 */
public class SuperviseExamineFrament extends BaseFragment implements OnClickListener, OnItemLongClickListener, OnItemClickListener{

	private Button btn_add_supervise_examine;
	private List<SuperviseBean> mSuperviseList;
	private SuperviseRecordAdapter mSuperviseRecordAdapter;
	private ListView mListView;
	Fragment fragment = null;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.supervise_examin_frament_layout,
				container, false);
		initView(view);
		mListView.setAdapter(mSuperviseRecordAdapter);
		if (null != mSuperviseRecordAdapter) {
			mSuperviseRecordAdapter.notifyDataSetChanged();
		}
		return view;
	}

	private void initView(View view) {
		
		mListView = (ListView) view.findViewById(R.id.supervise_examine_listview);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		btn_add_supervise_examine = (Button) view.findViewById(R.id.btn_add_supervise_examine);
		btn_add_supervise_examine.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {
		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getWritableDatabase();
		mSuperviseList = (List<SuperviseBean>) sql
				.GetDataByWhere(
						db,
						SuperviseBean.class,
						"taskId like '"
								+ PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID)
								+ "'" + " GROUP BY jdsj,htbh",null);

		if (null != mSuperviseList && mSuperviseList.size() > 0) {
			mSuperviseRecordAdapter = new SuperviseRecordAdapter(getActivity(),
					mSuperviseList);
			mListView.setAdapter(mSuperviseRecordAdapter);
			if (null != mSuperviseRecordAdapter) {
				mSuperviseRecordAdapter.notifyDataSetChanged();
			}
		}
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		SuperviseRecordAdapter.SELECT = arg2;
		if (null != mSuperviseRecordAdapter) {
			mSuperviseRecordAdapter.notifyDataSetChanged();
		}
		if(null!=fragment){
			getFragmentManager().beginTransaction().remove(fragment).commit();
		}
		ShowSelectDialog(arg2);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		System.out.println("长按："+arg3);
		ShowDeleteDialog(arg2);
		return true;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.btn_add_supervise_examine:
			Intent i = new Intent(getActivity(), EditSuperviseExamineActivity.class);
			i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
			startActivity(i);
			break;

		default:
			break;
		}
	}

	private void ShowDeleteDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否删除本条数据（注意：删除记录，会删除相关的监督情况，删除后数据不可恢复）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleDbInfo(mSuperviseList,itemId);
						mSuperviseList.remove(itemId);
						if (null != mSuperviseRecordAdapter) {
							mSuperviseRecordAdapter.notifyDataSetChanged();
							mListView.invalidate();
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<SuperviseBean> mSuperviseBean, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, SuperviseBean.class, "htbh like '"
								+ mSuperviseBean.get(itemId).getHtbh()
								+ "' and jdsj like '"
								+ mSuperviseBean.get(itemId).getJdsj()
								+"'");
						
//						+"' and jdxm like '"
//						+ mSuperviseBean.get(itemId).getJdxm()
						
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

	
	private void ShowSelectDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("选择编辑记录或查看监督情况")
				.setPositiveButton("编辑", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						Intent i = new Intent(getActivity(), EditSuperviseExamineActivity.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
								mSuperviseList.get(itemId));
						i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
						i.putExtras(bundle);
						startActivity(i);
					}

				})
				.setNegativeButton("查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						fragment = new SuperviseConditionFragment();
						Bundle bundle = new Bundle();
						bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
								mSuperviseList.get(itemId));
						fragment.setArguments(bundle);
						getFragmentManager().beginTransaction()
						.replace(R.id.supervise_condition_frament, fragment).commit();
					}
				});
		dialog = customBuilder.create();
		dialog.show();
	}
	
}
