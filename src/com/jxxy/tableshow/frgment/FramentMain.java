package com.jxxy.tableshow.frgment;

import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.activity.FileManager;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class FramentMain extends BaseActivity{

	private TitleView mTopBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		initView();
	}

	private void initView() {
		mTopBar = (TitleView) findViewById(R.id.contrace_topbar);
		mTopBar.setTitle("合同系统");
		mTopBar.hiddenRightButton();
		mTopBar.removeRightButton();
		mTopBar.setLeftButton("导入", new OnLeftButtonClickListener(){

			public void onClick(View button) {
				
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					Intent i = new Intent(FramentMain.this, FileManager.class);
					startActivity(i);
				}else{
					Toast.makeText(FramentMain.this, "没有SD卡", Toast.LENGTH_SHORT).show();
				}
			
			}
			
		});
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			 ShowNetDialog();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 退出
	private void ShowNetDialog() {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				this);
		customBuilder.setTitle("提示").setMessage("是否退出合同管理系统？")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
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
