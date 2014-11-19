package com.jxxy.tableshow.activity;


import com.jxxy.tableshow.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainTopRightDialog extends Activity implements OnClickListener {
	//private MyDialog dialog;
	private LinearLayout layout;
	private TextView btn_contract_details_problem_record;
	private TextView btn_contract_details_inspection_bills;
	private TextView btn_contract_details_yanzheng_record;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_top_right_dialog);
		//dialog=new MyDialog(this);
		initView();
		
	}

	private void initView() {
		btn_contract_details_problem_record = (TextView) findViewById(R.id.btn_contract_details_problem_record);
		btn_contract_details_inspection_bills = (TextView) findViewById(R.id.btn_contract_details_inspection_bills);
		btn_contract_details_yanzheng_record = (TextView) findViewById(R.id.btn_contract_details_yanzheng_record);
		btn_contract_details_yanzheng_record.setOnClickListener(this);
		btn_contract_details_inspection_bills.setOnClickListener(this);
		btn_contract_details_problem_record.setOnClickListener(this);		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	/*
	public void exitbutton1(View v) {  
    	this.finish();    	
      }  
	public void exitbutton0(View v) {  
    	this.finish();
    	MainWeixin.instance.finish();//¹Ø±ÕMain Õâ¸öActivity
      }  
	*/

	@Override
	public void onClick(View arg0) {

		Intent i = null;
		switch (arg0.getId()) {
		case R.id.btn_contract_details_problem_record:
			i = new Intent(this, ProblemRecordListActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.btn_contract_details_inspection_bills:
			i = new Intent(this, InspectionBillsListActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.btn_contract_details_yanzheng_record:
			i = new Intent(this, ProductRecordActivity.class);
			startActivity(i);
			finish();
			break;

		default:
			break;
		}
	
	}
}
