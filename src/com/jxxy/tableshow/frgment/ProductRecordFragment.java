package com.jxxy.tableshow.frgment;

import java.util.List;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.ProductRecordDetailsActivity;
import com.jxxy.tableshow.adapter.ProductRecordAdapter;
import com.jxxy.tableshow.bean.InspectionRecordBean;
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
 * 质量检验验收记录表
* @ClassName: ProductRecordFragment 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-8 上午5:05:42 
*
 */
public class ProductRecordFragment extends BaseFragment implements OnItemLongClickListener, OnItemClickListener, OnClickListener{

	private Button btn_add_product_record;
	private List<InspectionRecordBean> mInspectionRecordList;
	private ProductRecordAdapter mProductRecordAdapter;
	private ListView mListView;
	private TitleView mTopBar;
	Fragment fragment = null;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.product_record_list,
				container, false);
		initView(view);
		
		mListView.setAdapter(mProductRecordAdapter);
		if (null != mProductRecordAdapter) {
			mProductRecordAdapter.notifyDataSetChanged();
		}
		return view;
	}
	

	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {
		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getWritableDatabase();
		mInspectionRecordList = (List<InspectionRecordBean>) sql
				.GetDataByWhere(
						db,
						InspectionRecordBean.class,
						"taskId like '"
								+ PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID)
								+ "'" + " GROUP BY qcsbmc,htbh,sjcpbh",null);

		if (null != mInspectionRecordList && mInspectionRecordList.size() > 0) {
			mProductRecordAdapter = new ProductRecordAdapter(getActivity(),
					mInspectionRecordList);
			mListView.setAdapter(mProductRecordAdapter);
			if (null != mProductRecordAdapter) {
				mProductRecordAdapter.notifyDataSetChanged();
			}
		}

	}

	private void initView(View view) {

		mListView = (ListView) view.findViewById(R.id.product_record_listview);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);

		btn_add_product_record = (Button) view.findViewById(R.id.btn_add_product_record);
		btn_add_product_record.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_add_product_record:
			Intent i = new Intent(getActivity(), ProductRecordDetailsActivity.class);
			i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
			startActivity(i);
			break;

		default:
			break;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		ProductRecordAdapter.SELECT = arg2;
		if (null != mProductRecordAdapter) {
			mProductRecordAdapter.notifyDataSetChanged();
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

	private void ShowDeleteDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否删除本条数据（注意：删除产品检验记录，会删除相关的检验项目记录，删除后数据不可恢复）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleDbInfo(mInspectionRecordList,itemId);
						mInspectionRecordList.remove(itemId);
						if (null != mProductRecordAdapter) {
							mProductRecordAdapter.notifyDataSetChanged();
							mListView.invalidate();
						}
						dialog.dismiss();
					}

					private void deleDbInfo(List<InspectionRecordBean> mInspectionRecordList, int itemId) {
						SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, InspectionRecordBean.class, "htbh like '"
								+ mInspectionRecordList.get(itemId).getHtbh()
								+ "' and qcsbmc like '"
								+ mInspectionRecordList.get(itemId).getQcsbmc()
								+"' and sjcpbh like '"
								+ mInspectionRecordList.get(itemId).getSjcpbh()
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

	
	private void ShowSelectDialog(final int itemId) {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("选择编辑产品检验记录或查看检验项目记录")
				.setPositiveButton("编辑", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						Intent i = new Intent(getActivity(), ProductRecordDetailsActivity.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
								mInspectionRecordList.get(itemId));
						i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
						i.putExtras(bundle);
						startActivity(i);
					}

				})
				.setNegativeButton("查看", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						fragment = new ProductRecordConclusionFrgment();
						Bundle bundle = new Bundle();
						bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
								mInspectionRecordList.get(itemId));
						fragment.setArguments(bundle);
						getFragmentManager().beginTransaction()
						.replace(R.id.product_record_frame, fragment).commit();
					}
				});
		dialog = customBuilder.create();
		dialog.show();
	}
	
}
