package com.jxxy.tableshow.activity;

import java.util.ArrayList;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ProductConclusionAdapter;
import com.jxxy.tableshow.adapter.ProductRecordAdapter;
import com.jxxy.tableshow.bean.InspectionRecordBean;
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
 * 查看产品检验记录及判定结论列表
* @ClassName: ProductRecordConclusionActivity 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-7-30 下午5:47:08 
*
 */
public class ProductRecordConclusionActivity extends BaseActivity implements OnClickListener, OnItemClickListener, OnTopBarListener, OnItemLongClickListener{

	TitleView mTopBar;
	public ListView mListView;
	private Button btn_add_product_record;
	InspectionRecordBean mInspectionRecord;
	ArrayList<InspectionRecordBean> list;
	ProductConclusionAdapter mProductConclusionAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_record_conclusion);
		initView();
	}
	
	private void initView() {
//		mTopBar = (TitleView) findViewById(R.id.product_record_conclusion_topbar);
		
		mTopBar.setTitle("检验项目记录");
		mTopBar.hiddenRightButton();
		mTopBar.removeRightButton();
		mTopBar.setLeftButton("返回", new OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		
		mListView = (ListView) findViewById(R.id.product_conclusion_listview);
		btn_add_product_record = (Button) findViewById(R.id.btn_add_product_conclusion);
		btn_add_product_record.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getIntentData() ;
	}

	private void getIntentData() {
		
		 mInspectionRecord = (InspectionRecordBean) getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		 if(mInspectionRecord!=null){
			 installViewData( mInspectionRecord.getQcsbmc(),mInspectionRecord.getHtbh(),mInspectionRecord.getSjcpbh());
		 }else{
			 mInspectionRecord = new InspectionRecordBean();
		 }
	}

	private void installViewData(String cpxh,String htbh,String sjcpbh) {
		
		PreferencesUtils.init(this);
		SQLiteUtils sql = SQLiteUtils.getInstance(this);
		SQLiteDatabase db = sql.getReadableDatabase();
		if (!isNull(cpxh)&&!isNull(htbh)&&!isNull(sjcpbh)) {
			list = (ArrayList<InspectionRecordBean>) sql
					.GetDataByWhere(db,
							InspectionRecordBean.class,
							"cpxh like '"
									+ cpxh
									+ "' and htbh like '"
									+ htbh
									+ "' and sjcpbh like '"
									+ sjcpbh
									+ "'",null);
			
			if(null!=list&&list.size()>0){
				mProductConclusionAdapter = new ProductConclusionAdapter(this,list);
				mListView.setAdapter(mProductConclusionAdapter);
				if (null != mProductConclusionAdapter) {
					mProductConclusionAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_product_conclusion:
			Intent i = new Intent(this,
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent i = new Intent(this,
				ProductConclusionDetails.class);
		Bundle bundle = new Bundle();
		bundle.putString(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "updata");
		bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, list.get(arg2));
		i.putExtras(bundle);
		startActivity(i);
	}

	@Override
	public void onTopBarLeftBtn() {
		finish();
	}

	@Override
	public void onTopBarRightBtn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTopbarCenter() {
		// TODO Auto-generated method stub
		
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
				ProductRecordConclusionActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：删除后数据不可恢复）")
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
						SQLiteUtils sql = SQLiteUtils.getInstance(ProductRecordConclusionActivity.this);
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, InspectionRecordBean.class, "htbh like '"
								+ mInspectionRecordList.get(itemId).getHtbh()
								+ "' and cpxh like '"
								+ mInspectionRecordList.get(itemId).getQcsbmc()
								+"'and sjcpbh like '"
								+ mInspectionRecordList.get(itemId).getSjcpbh()
								+"'and xh like '"
								+  mInspectionRecordList.get(itemId).getXh()
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
