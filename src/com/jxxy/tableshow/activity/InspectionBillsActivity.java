package com.jxxy.tableshow.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TopBar;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

/**
* @ClassName: InspectionBillsActivity 
* @Description: 交验单 
* @author LiLong
* @date 2014-6-5 上午2:10:24 
*
 */
public class InspectionBillsActivity extends BaseActivity implements OnTopBarListener, DiaLogCallback{

	private AccountRenderedBean mAccountRenderedBean;
	private TitleView mTopBar;
	private EditText et_inspection_bill_wirite_tjdw;
	private EditText et_inspection_bill_wirite_tjsj;
	private EditText et_inspection_bill_wirite_xmmc;
	private TextView et_inspection_bill_wirite_htbh;
	private EditText et_inspection_bill_wirite_xmdh;
	private EditText et_inspection_bill_wirite_tjcs;
	private EditText et_inspection_bill_wirite_cpmc;
	private EditText et_inspection_bill_wirite_tjsl;
	
	private EditText et_inspection_bill_wirite_tjpc;
	private EditText et_inspection_bill_wirite_cpbh;
	private EditText et_inspection_bill_wirite_tjdwxyjl;
	private EditText et_inspection_bill_wirite_xytjsc;
	private EditText et_inspection_bill_wirite_xycs;
	private EditText et_inspection_bill_wirite_xybz;
	private EditText et_inspection_bill_wirite_tjtjscyj;
	
	private EditText et_inspection_bill_wirite_zjzsc;
	private EditText et_inspection_bill_wirite_scrq;
	private EditText et_inspection_bill_wirite_ysjl;
	private EditText et_inspection_bill_wirite_zjzys;
	private EditText et_inspection_bill_wirite_ysrq;
	String dbColect = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.inspection_bill_write_layout);
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getIntentData();
	}
	
	private void getIntentData() {
		
		if(null!= getIntent().getExtras().getString(IConfig.INTENT_PROBLEM_SAVE_KEY))
			dbColect = getIntent().getExtras().getString(IConfig.INTENT_PROBLEM_SAVE_KEY);
		
		mAccountRenderedBean = (AccountRenderedBean)getIntent().getSerializableExtra(IConfig.INTENT_INSPECTION_BILLS_BEAN_KEY);
		 if(mAccountRenderedBean!=null){
			 installViewData();
		 }else{
			 mAccountRenderedBean = new AccountRenderedBean();
		 }
		
	}
	private void installViewData() {
		 et_inspection_bill_wirite_htbh.setText(mAccountRenderedBean.getHtbh());
		et_inspection_bill_wirite_xytjsc.setText(mAccountRenderedBean.getXytjsc());
		et_inspection_bill_wirite_tjdw.setText(mAccountRenderedBean.getTjdw());
		et_inspection_bill_wirite_tjsj.setText(mAccountRenderedBean.getTjsj());
		et_inspection_bill_wirite_xmmc.setText(mAccountRenderedBean.getXmmc());
		et_inspection_bill_wirite_xmdh.setText(mAccountRenderedBean.getXmdh());
		et_inspection_bill_wirite_tjcs.setText(mAccountRenderedBean.getTjcs());
		et_inspection_bill_wirite_cpmc.setText(mAccountRenderedBean.getCpmc());
		et_inspection_bill_wirite_tjsl.setText(mAccountRenderedBean.getTjsl());
		et_inspection_bill_wirite_tjpc.setText(mAccountRenderedBean.getTjpc());
		
		et_inspection_bill_wirite_cpbh.setText(mAccountRenderedBean.getCpbh());
		et_inspection_bill_wirite_tjdwxyjl.setText(mAccountRenderedBean.getTjdwxyjl());
		et_inspection_bill_wirite_xycs.setText(mAccountRenderedBean.getXycs());
		et_inspection_bill_wirite_xybz.setText(mAccountRenderedBean.getXybz());
		et_inspection_bill_wirite_tjtjscyj.setText(mAccountRenderedBean.getTjtjscyj());
		
		et_inspection_bill_wirite_zjzsc.setText(mAccountRenderedBean.getZjzsc());
		et_inspection_bill_wirite_scrq.setText(mAccountRenderedBean.getScrq());
		et_inspection_bill_wirite_ysjl.setText(mAccountRenderedBean.getYsjl());
		
		et_inspection_bill_wirite_zjzys.setText(mAccountRenderedBean.getZjzys());
		et_inspection_bill_wirite_ysrq.setText(mAccountRenderedBean.getYsrq());
	}

	private void initView() {
		mTopBar = (TitleView) findViewById(R.id.inspection_bill_write_topbar);
		mTopBar.setTitle("校验单详情");
		
		mTopBar.setLeftButton("返回", new com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		mTopBar.setRightButton("保存", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				onRightSave();
			}
		});
		
		et_inspection_bill_wirite_tjdw = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjdw);
		et_inspection_bill_wirite_tjsj = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjsj);
		et_inspection_bill_wirite_xmmc = (EditText) findViewById(R.id.et_inspection_bill_wirite_xmmc);
		et_inspection_bill_wirite_htbh = (TextView) findViewById(R.id.et_inspection_bill_wirite_htbh);
		et_inspection_bill_wirite_xmdh = (EditText) findViewById(R.id.et_inspection_bill_wirite_xmdh);
		et_inspection_bill_wirite_tjcs = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjcs);
		et_inspection_bill_wirite_cpmc = (EditText) findViewById(R.id.et_inspection_bill_wirite_cpmc);
		et_inspection_bill_wirite_tjsl = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjsl);
		
		et_inspection_bill_wirite_tjpc = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjpc);
		
		et_inspection_bill_wirite_cpbh = (EditText) findViewById(R.id.et_inspection_bill_wirite_cpbh);
		et_inspection_bill_wirite_tjdwxyjl = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjdwxyjl);
		et_inspection_bill_wirite_xycs = (EditText) findViewById(R.id.et_inspection_bill_wirite_xycs);
		et_inspection_bill_wirite_xybz = (EditText) findViewById(R.id.et_inspection_bill_wirite_xybz);
		et_inspection_bill_wirite_tjtjscyj = (EditText) findViewById(R.id.et_inspection_bill_wirite_tjtjscyj);
		
		et_inspection_bill_wirite_zjzsc = (EditText) findViewById(R.id.et_inspection_bill_wirite_zjzsc);
		et_inspection_bill_wirite_scrq = (EditText) findViewById(R.id.et_inspection_bill_wirite_scrq);
		et_inspection_bill_wirite_ysjl = (EditText) findViewById(R.id.et_inspection_bill_wirite_ysjl);
		et_inspection_bill_wirite_xytjsc = (EditText) findViewById(R.id.et_inspection_bill_wirite_xytjsc);
		et_inspection_bill_wirite_zjzys = (EditText) findViewById(R.id.et_inspection_bill_wirite_zjzys);
		et_inspection_bill_wirite_ysrq = (EditText) findViewById(R.id.et_inspection_bill_wirite_ysrq);
		
		et_inspection_bill_wirite_htbh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DialogUtils mTextHtbhUtils = new DialogUtils(InspectionBillsActivity.this);
				mTextHtbhUtils.dialogHybhUtils(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME), PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mTextHtbhUtils.setDialogcallback(InspectionBillsActivity.this);
			
			}
		});
	}

	protected void onRightSave() {

		ArrayList<AccountRenderedBean> list = null;
		if(!et_inspection_bill_wirite_htbh.getText().toString().equals("")&&!et_inspection_bill_wirite_tjpc.getText().toString().equals("")){
			SQLiteUtils sql = SQLiteUtils.getInstance(this);
			SQLiteDatabase db = sql.getWritableDatabase();
			mAccountRenderedBean.setHtbh(et_inspection_bill_wirite_htbh.getText().toString());
			mAccountRenderedBean.setTjdw(et_inspection_bill_wirite_tjdw.getText().toString());
			mAccountRenderedBean.setTjsj(et_inspection_bill_wirite_tjsj.getText().toString());
			mAccountRenderedBean.setXmmc(et_inspection_bill_wirite_xmmc.getText().toString());
			mAccountRenderedBean.setXmdh(et_inspection_bill_wirite_xmdh.getText().toString());
			mAccountRenderedBean.setTjcs(et_inspection_bill_wirite_tjcs.getText().toString());
			mAccountRenderedBean.setCpmc(et_inspection_bill_wirite_cpmc.getText().toString());
			mAccountRenderedBean.setTjsl(et_inspection_bill_wirite_tjsl.getText().toString());
			mAccountRenderedBean.setTjpc(et_inspection_bill_wirite_tjpc.getText().toString());
			mAccountRenderedBean.setCpbh(et_inspection_bill_wirite_cpbh.getText().toString());
			mAccountRenderedBean.setTjdwxyjl(et_inspection_bill_wirite_tjdwxyjl.getText().toString());
			mAccountRenderedBean.setXytjsc(et_inspection_bill_wirite_xytjsc.getText().toString());
			mAccountRenderedBean.setXycs(et_inspection_bill_wirite_xycs.getText().toString());
			mAccountRenderedBean.setXybz(et_inspection_bill_wirite_xybz.getText().toString());
			mAccountRenderedBean.setTjtjscyj(et_inspection_bill_wirite_tjtjscyj.getText().toString());
			mAccountRenderedBean.setZjzsc(et_inspection_bill_wirite_zjzsc.getText().toString());
			mAccountRenderedBean.setScrq(et_inspection_bill_wirite_scrq.getText().toString());
			mAccountRenderedBean.setYsjl(et_inspection_bill_wirite_ysjl.getText().toString());
			mAccountRenderedBean.setZjzys(et_inspection_bill_wirite_zjzys.getText().toString());
			mAccountRenderedBean.setYsrq(et_inspection_bill_wirite_ysrq.getText().toString());
			 
			if (dbColect.equals("save")){
				mAccountRenderedBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mAccountRenderedBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				 sql.SavaDataSingle(db, mAccountRenderedBean);
			 }else{
				 mAccountRenderedBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				 mAccountRenderedBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				 sql.Update(db,mAccountRenderedBean);
			 }
			 toast("保存成功");
			 finish();
		}else{
			toast("合同编号，产品批次（提交批次）为必填项");
		}
	
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ExitManager.getInstance().removeActivity(this);
	}

	@Override
	public void onTopBarLeftBtn() {
		finish();
	}

	@Override
	public void onTopBarRightBtn() {}

	@Override
	public void onTopbarCenter() {
		
	}

	@Override
	public void dialogdo(int flag, String str) {
		switch (flag) {
		case DialogUtils.SHOW_HTBH_FLAG:
			et_inspection_bill_wirite_htbh.setText(str);
			break;

		default:
			break;
		}
	}
}
