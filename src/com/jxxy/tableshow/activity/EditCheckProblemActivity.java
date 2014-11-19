package com.jxxy.tableshow.activity;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scrollerpicker.SelectBirthday;
import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

public class EditCheckProblemActivity extends BaseActivity implements OnTopBarListener, DiaLogCallback, OnClickListener{

	private ErrorBean mErrorBean;
	private TitleView mTopBar;
	private TextView et_check_problem_wirite_htbh;
	private EditText et_check_problem_wirite_bh;
	private Button et_check_problem_wirite_txrq;
	private EditText et_check_problem_wirite_jyyszfxwt;
	private EditText et_check_problem_wirite_cpbh;
	private EditText et_check_problem_wirite_ysryqz;
	private EditText et_check_problem_wirite_czdwptrqz;
	private Button et_check_problem_wirite_qcsbmc;
	private Button et_check_problem_wirite_ggxh;
	private Button et_check_problem_wirite_qcsbdm;
	
	private final int TXRQ_FLAG = 0;
	private final int ZJZQRRQ_FLAG = 1;
	private final int CZDWQRRQ_FLAG = 2;
	String dbColect = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.check_problem_wirite_layout);
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getIntentData();
	}
	private void getIntentData() {

		PreferencesUtils.init(this);
		if(null!= getIntent().getExtras().getString(IConfig.INTENT_PROBLEM_SAVE_KEY))
		dbColect = getIntent().getExtras().getString(IConfig.INTENT_PROBLEM_SAVE_KEY);
	 
		 mErrorBean = (ErrorBean)getIntent().getSerializableExtra(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY);
		 if(mErrorBean!=null){
			 installViewData();
		 }else{
			 mErrorBean = new ErrorBean();
		 }
		 
//		if(!htbhStr.equals("")){
//			SQLiteUtils sql = SQLiteUtils.getInstance(this);
//			SQLiteDatabase db = sql.getWritableDatabase();
//			List<ProductProblemBean> list = (List<ProductProblemBean>) sql.GetDataByWhere(db, ProductProblemBean.class, "htbh like '"
//					+ htbhStr + "'");
//			LogUtils.d(list.size()+"");
//		}
	}

	private void installViewData() {
		
		et_check_problem_wirite_htbh.setText(mErrorBean.getHtbh());
		et_check_problem_wirite_bh.setText(mErrorBean.getBh());
		et_check_problem_wirite_txrq.setText(mErrorBean.getTxrq());
		et_check_problem_wirite_jyyszfxwt.setText(mErrorBean.getJyyszfxwt());
		et_check_problem_wirite_cpbh.setText(mErrorBean.getCpbh());
		et_check_problem_wirite_ysryqz.setText(mErrorBean.getYsryqz());
		et_check_problem_wirite_czdwptrqz.setText(mErrorBean.getCzdwptrqz());
		et_check_problem_wirite_qcsbmc.setText(mErrorBean.getQcsbmc());
		et_check_problem_wirite_ggxh.setText(mErrorBean.getGgxh());
		et_check_problem_wirite_qcsbdm.setText(mErrorBean.getQcsbdm());
	}

	private void initView() {
		et_check_problem_wirite_htbh = (TextView) findViewById(R.id.et_check_problem_wirite_htbh);
		et_check_problem_wirite_bh = (EditText) findViewById(R.id.et_check_problem_wirite_bh);
		et_check_problem_wirite_txrq = (Button) findViewById(R.id.et_check_problem_wirite_txrq);
		et_check_problem_wirite_jyyszfxwt = (EditText) findViewById(R.id.et_check_problem_wirite_jyyszfxwt);
		et_check_problem_wirite_cpbh = (EditText) findViewById(R.id.et_check_problem_wirite_cpbh);
		et_check_problem_wirite_ysryqz = (EditText) findViewById(R.id.et_check_problem_wirite_ysryqz);
		et_check_problem_wirite_czdwptrqz = (EditText) findViewById(R.id.et_check_problem_wirite_czdwptrqz);		
		et_check_problem_wirite_qcsbmc = (Button) findViewById(R.id.et_check_problem_wirite_qcsbmc);
		et_check_problem_wirite_qcsbmc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_check_problem_wirite_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditCheckProblemActivity.this);
					mTextHtbhUtils.dialogQcsbmc(et_check_problem_wirite_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditCheckProblemActivity.this);
				}else{
					toast("请先选择合同编号");
				}
			}
		});
		et_check_problem_wirite_ggxh = (Button) findViewById(R.id.et_check_problem_wirite_ggxh);
		et_check_problem_wirite_ggxh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(!et_check_problem_wirite_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditCheckProblemActivity.this);
					mTextHtbhUtils.dialogGgxh(et_check_problem_wirite_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditCheckProblemActivity.this);
				}else{
					toast("请先选择合同编号");
				}
			}
		});
		et_check_problem_wirite_qcsbdm = (Button) findViewById(R.id.et_check_problem_wirite_qcsbdm);
		et_check_problem_wirite_qcsbdm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_check_problem_wirite_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditCheckProblemActivity.this);
					mTextHtbhUtils.dialogQcsbdm(et_check_problem_wirite_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditCheckProblemActivity.this);
				}else{
					toast("请先选择合同编号");
				}
			}
		});
		
		et_check_problem_wirite_txrq.setOnClickListener(this);
		et_check_problem_wirite_htbh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(EditCheckProblemActivity.this);
				mTextHtbhUtils.dialogHybhUtils(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME), PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mTextHtbhUtils.setDialogcallback(EditCheckProblemActivity.this);
			}
		});
		
		mTopBar = (TitleView) findViewById(R.id.problem_record_wirite_topbar);
		mTopBar.setTitle("产品检验验收问题汇总");
		mTopBar.setLeftButton("返回", new OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
		});
		
		mTopBar.setRightButton("保存", new OnRightButtonClickListener() {
			
			public void onClick(View button) {
				OnRightSave();
			}
		});

	}

	protected void OnRightSave() {

		ArrayList<ErrorBean> list = null;
		if(!et_check_problem_wirite_htbh.getText().toString().equals("")){
			SQLiteUtils sql = SQLiteUtils.getInstance(this);
			SQLiteDatabase db = sql.getWritableDatabase();
			mErrorBean.setHtbh(et_check_problem_wirite_htbh.getText().toString());
			mErrorBean.setBh(et_check_problem_wirite_bh.getText().toString());
			mErrorBean.setTxrq(et_check_problem_wirite_txrq.getText().toString());
			mErrorBean.setJyyszfxwt(et_check_problem_wirite_jyyszfxwt.getText().toString());
			mErrorBean.setCpbh(et_check_problem_wirite_cpbh.getText().toString());
			mErrorBean.setYsryqz(et_check_problem_wirite_ysryqz.getText().toString());
			mErrorBean.setCzdwptrqz(et_check_problem_wirite_czdwptrqz.getText().toString());
			mErrorBean.setQcsbmc(et_check_problem_wirite_qcsbmc.getText().toString());
			mErrorBean.setGgxh(et_check_problem_wirite_ggxh.getText().toString());
			mErrorBean.setQcsbdm(et_check_problem_wirite_qcsbdm.getText().toString());
			 
			 if (dbColect.equals("save")){
				 mErrorBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mErrorBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				 sql.SavaDataSingle(db, mErrorBean);
			 }else{
				 mErrorBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				 mErrorBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				 sql.Update(db,mErrorBean);
			 }
			 toast("保存成功");
			 finish();
		}else{
			toast("合同编号必须填写");
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
			et_check_problem_wirite_htbh.setText(str);
			break;
		case DialogUtils.SHOW_GGXH_FLAG:
			et_check_problem_wirite_ggxh.setText(str);
			break;
		case DialogUtils.SHOW_QCSBDM_FLAG:
			et_check_problem_wirite_qcsbdm.setText(str);
			break;
		case DialogUtils.SHOW_QCSBMC_FLAG:
			et_check_problem_wirite_qcsbmc.setText(str);
			break;

		default:
			break;
		}
	}
	
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.et_check_problem_wirite_txrq:
			InputMethodManager();
			SelectBirthday birth = new SelectBirthday(this,handler,et_check_problem_wirite_txrq.getText().toString(),TXRQ_FLAG);
			birth.showAtLocation(this.findViewById(R.id.check_problem_wirite_root),
					Gravity.BOTTOM, 0, 0);
			break;
			
//		case R.id.et_problem_record_wirite_zjzqrrq:
//			InputMethodManager();
//			SelectBirthday birth2 = new SelectBirthday(this,handler,et_problem_record_wirite_zjzqrrq.getText().toString(),ZJZQRRQ_FLAG);
//			birth2.showAtLocation(this.findViewById(R.id.problem_record_wirite_root),
//					Gravity.BOTTOM, 0, 0);
//			break;
//		case R.id.et_problem_record_wirite_czdwqrrq:
//			InputMethodManager();
//			SelectBirthday birth3 = new SelectBirthday(this,handler,et_problem_record_wirite_czdwqrrq.getText().toString(),CZDWQRRQ_FLAG);
//			birth3.showAtLocation(this.findViewById(R.id.problem_record_wirite_root),
//					Gravity.BOTTOM, 0, 0);
//			break;
		default:
			break;
		}
	}
	
	public void InputMethodManager(){
		// 获取输入法状态
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					boolean isOpen=imm.isActive(); 
					// 隐藏输入法
					if(isOpen){
						InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
						m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
					}
	}
	
	Handler handler = new Handler(){
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
			case TXRQ_FLAG:
				if(msg.obj != null){
					et_check_problem_wirite_txrq.setText((String)msg.obj);
				}
				break;
			case ZJZQRRQ_FLAG:
				if(msg.obj != null){
//					et_problem_record_wirite_zjzqrrq.setText((String)msg.obj);
				}
				break;
			case CZDWQRRQ_FLAG:
				if(msg.obj != null){
//					et_problem_record_wirite_czdwqrrq.setText((String)msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};
//	String test =hybhDialog(ContractDetailsActivity.this, PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME), PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
}
