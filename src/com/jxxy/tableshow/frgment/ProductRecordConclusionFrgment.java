package com.jxxy.tableshow.frgment;

import java.util.ArrayList;
import java.util.List;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.ProductConclusionDetails;
import com.jxxy.tableshow.activity.ProductRecordConclusionActivity;
import com.jxxy.tableshow.adapter.ContractDetailsAdapter;
import com.jxxy.tableshow.adapter.ProductConclusionAdapter;
import com.jxxy.tableshow.bean.InspectionRecordBean;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ProductRecordConclusionFrgment extends BaseFragment implements OnClickListener, OnItemClickListener, OnItemLongClickListener{
	
	public ListView mListView;
	private Button btn_add_product_record;
	InspectionRecordBean mInspectionRecord;
	ArrayList<InspectionRecordBean> list;
	ProductConclusionAdapter mProductConclusionAdapter;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.product_record_conclusion,
				container, false);
		mListView = (ListView) view.findViewById(R.id.product_conclusion_listview);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		btn_add_product_record = (Button) view.findViewById(R.id.btn_add_product_conclusion);
		btn_add_product_record.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		getIntentData() ;
		
	}
	
	private void getIntentData() {
		
		 mInspectionRecord = (InspectionRecordBean) getArguments().getSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);;
		 if(mInspectionRecord!=null){
			 installViewData( mInspectionRecord.getQcsbmc(),mInspectionRecord.getHtbh(),mInspectionRecord.getSjcpbh());
		 }else{
			 mInspectionRecord = new InspectionRecordBean();
		 }
	}

private void installViewData(String qcsbmc,String htbh,String sjcpbh) {
		
		PreferencesUtils.init(getActivity());
		SQLiteUtils sql = SQLiteUtils.getInstance(getActivity());
		SQLiteDatabase db = sql.getReadableDatabase();
		if (!isNull(qcsbmc)&&!isNull(htbh)&&!isNull(sjcpbh)) {
			
//			ArrayList<InspectionRecordBean> testList = (ArrayList<InspectionRecordBean>) sql.GetAllData(db, InspectionRecordBean.class);
			
			list = (ArrayList<InspectionRecordBean>) sql
					.GetDataByWhere(db,
							InspectionRecordBean.class,
							"qcsbmc like '"
									+ qcsbmc
									+ "' and htbh like '"
									+ htbh
									+ "' and sjcpbh like '"
									+ sjcpbh
									+ "'",null);
			
			if(null!=list&&list.size()>0){
				mProductConclusionAdapter = new ProductConclusionAdapter(getActivity(),list);
				mListView.setAdapter(mProductConclusionAdapter);
				if (null != mProductConclusionAdapter) {
					mProductConclusionAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		System.out.println("长按："+arg3);
		ShowNetDialog(arg2);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		ProductConclusionAdapter.SELECT = arg2;
		if (null != mProductConclusionAdapter) {
			mProductConclusionAdapter.notifyDataSetChanged();
		}
		
		Intent i = new Intent(getActivity(),
				ProductConclusionDetails.class);
		Bundle bundle = new Bundle();
		bundle.putString(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "updata");
		bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, list.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_product_conclusion:
			Intent i = new Intent(getActivity(),
					ProductConclusionDetails.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, mInspectionRecord);
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
						
						deleDbInfo(list,itemId);
						list.remove(itemId);
						if (null != mProductConclusionAdapter) {
							mProductConclusionAdapter.notifyDataSetChanged();
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
								+"'and sjcpbh like '"
								+ mInspectionRecordList.get(itemId).getSjcpbh()
								+"'and jyxm like '"
								+  mInspectionRecordList.get(itemId).getJyxm()
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
