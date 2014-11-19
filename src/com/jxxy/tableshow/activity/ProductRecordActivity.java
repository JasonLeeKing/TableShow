package com.jxxy.tableshow.activity;

import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import com.jxxy.tableshow.adapter.InspectionRecordAdapter;
import com.jxxy.tableshow.adapter.ProductRecordAdapter;
import com.jxxy.tableshow.bean.AccountRenderedBean;
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
 * 产品检验记录
 * 
 * @ClassName: ProductRecordActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiLong
 * @date 2014-7-29 下午4:16:37
 * 
 */
public class ProductRecordActivity extends BaseActivity implements
		OnClickListener, OnTopBarListener, OnItemClickListener, OnItemLongClickListener {

	private Button btn_add_product_record;
	private List<InspectionRecordBean> mInspectionRecordList;
	private ProductRecordAdapter mProductRecordAdapter;
	private ListView mListView;
	private TitleView mTopBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_record_list);
		initView();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {
		PreferencesUtils.init(this);
		SQLiteUtils sql = SQLiteUtils.getInstance(this);
		SQLiteDatabase db = sql.getWritableDatabase();
		mInspectionRecordList = (List<InspectionRecordBean>) sql
				.GetDataByWhere(
						db,
						InspectionRecordBean.class,
						"taskId like '"
								+ PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID)
								+ "'" + " GROUP BY cpxh,htbh,sjcpbh",null);

		if (null != mInspectionRecordList && mInspectionRecordList.size() > 0) {
			mProductRecordAdapter = new ProductRecordAdapter(this,
					mInspectionRecordList);
			mListView.setAdapter(mProductRecordAdapter);
			if (null != mProductRecordAdapter) {
				mProductRecordAdapter.notifyDataSetChanged();
			}
		}

	}

	private void initView() {

		mListView = (ListView) findViewById(R.id.product_record_listview);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);

//		mTopBar = (TitleView) findViewById(R.id.product_record_list_topbar);
		mTopBar.setTitle("产品检验记录");
		mTopBar.hiddenRightButton();
		mTopBar.removeRightButton();
		mTopBar.setLeftButton("返回", new OnLeftButtonClickListener() {

			public void onClick(View button) {
				finish();
			}

		});

		btn_add_product_record = (Button) findViewById(R.id.btn_add_product_record);
		btn_add_product_record.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_add_product_record:
			Intent i = new Intent(this, ProductRecordDetailsActivity.class);
			i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
			startActivity(i);
			break;

		default:
			break;
		}
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

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent i = new Intent(this, ProductRecordDetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY,
				mInspectionRecordList.get(arg2));
		i.putExtra(IConfig.INTENT_PROBLEM_SAVE_KEY, "save");
		i.putExtras(bundle);
		startActivity(i);
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
				ProductRecordActivity.this);
		customBuilder
				.setTitle("提示")
				.setMessage("是否本条数据（注意：删除产品检验记录，会删除相关的检验项目记录，删除后数据不可恢复）")
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
						SQLiteUtils sql = SQLiteUtils.getInstance(ProductRecordActivity.this);
						SQLiteDatabase db = sql.getWritableDatabase();

						sql.DeleteByWhere(db, InspectionRecordBean.class, "htbh like '"
								+ mInspectionRecordList.get(itemId).getHtbh()
								+ "' and cpxh like '"
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
}
