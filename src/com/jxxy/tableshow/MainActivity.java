package com.jxxy.tableshow;

import com.jxxy.tableshow.activity.InspectionBillsActivity;
import com.jxxy.tableshow.activity.ProblemRecordActivity;
import com.jxxy.tableshow.adapter.TableListAdapter;
import com.jxxy.tableshow.utils.LogUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stubandroid:targetSdkVersion
		mListView = (ListView) findViewById(R.id.table_listview);
		TableListAdapter adapter = new TableListAdapter(this);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		LogUtils.d("点击" + arg2);
		switch (arg2) {
		case 0:			// 产品质量技术问题记录表
			gotoActivity(ProblemRecordActivity.class);
			break;
			
		case 1: // 交验单
			gotoActivity(InspectionBillsActivity.class);
			break;    
		case 2:	     // 产品检验记录
			toast("开发中");
			break;
		default:
			break;
		}
	}
}
