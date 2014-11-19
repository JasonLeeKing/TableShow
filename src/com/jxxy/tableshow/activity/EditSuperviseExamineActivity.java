package com.jxxy.tableshow.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
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
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

public class EditSuperviseExamineActivity extends BaseActivity implements OnTopBarListener, OnClickListener, DiaLogCallback{

	private static final int TTRQ_FLAG = 0;
	SuperviseBean mSuperviseBean; 
	TitleView mTopBar;
	
	private TextView et_edit_supervise_htbh;
	private Button et_edit_supervise_jdsj;
	private Button et_edit_supervise_czdw;
	private EditText et_edit_supervise_czdwptr;
	private Button et_edit_supervise_qcsbmc;
	private EditText et_edit_supervise_jdjl;
	private EditText et_edit_supervise_jdr;
	private Button et_edit_supervise_ggxh;
	private Button et_edit_supervise_qcsbdm;

	String dbColect = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_supervise_layout);
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
		
		 mSuperviseBean = (SuperviseBean) getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		 if(mSuperviseBean!=null){
			 installViewData();
		 }else{
			 mSuperviseBean = new SuperviseBean();
		 }
	}
	
	private void installViewData() {

		et_edit_supervise_htbh.setText(mSuperviseBean.getHtbh());
		et_edit_supervise_czdw.setText(mSuperviseBean.getCzdw());
		et_edit_supervise_czdwptr.setText(mSuperviseBean.getCzdwptr());
		et_edit_supervise_qcsbmc.setText(mSuperviseBean.getQcsbmc());
		et_edit_supervise_jdjl.setText(mSuperviseBean.getJdjl());
		et_edit_supervise_jdr.setText(mSuperviseBean.getJdr());
		et_edit_supervise_ggxh.setText(mSuperviseBean.getGgxh());
		et_edit_supervise_jdsj.setText(mSuperviseBean.getJdsj());
		et_edit_supervise_qcsbdm.setText(mSuperviseBean.getQcsbdm());
	}

	private void initView() {

		mTopBar = (TitleView) findViewById(R.id.product_record_topbar);
		 
			mTopBar.setTitle("质量监督检查记录表");
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
			
			et_edit_supervise_htbh = (TextView) findViewById(R.id.et_edit_supervise_htbh);
			et_edit_supervise_jdsj = (Button) findViewById(R.id.et_edit_supervise_jdsj);
			et_edit_supervise_jdsj.setOnClickListener(this);
		et_edit_supervise_czdw = (Button) findViewById(R.id.et_edit_supervise_czdw);
		et_edit_supervise_czdw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_edit_supervise_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditSuperviseExamineActivity.this);
					mTextHtbhUtils.dialogCzdw(et_edit_supervise_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditSuperviseExamineActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			
			}
		});
		et_edit_supervise_czdwptr = (EditText) findViewById(R.id.et_edit_supervise_czdwptr);
		et_edit_supervise_qcsbmc = (Button) findViewById(R.id.et_edit_supervise_qcsbmc);
		et_edit_supervise_qcsbmc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_edit_supervise_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditSuperviseExamineActivity.this);
					mTextHtbhUtils.dialogQcsbmc(et_edit_supervise_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditSuperviseExamineActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			}
		});
		et_edit_supervise_jdjl = (EditText) findViewById(R.id.et_edit_supervise_jdjl);
		et_edit_supervise_jdr = (EditText) findViewById(R.id.et_edit_supervise_jdr);
		et_edit_supervise_ggxh = (Button) findViewById(R.id.et_edit_supervise_ggxh);
		
		et_edit_supervise_ggxh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_edit_supervise_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditSuperviseExamineActivity.this);
					mTextHtbhUtils.dialogGgxh(et_edit_supervise_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditSuperviseExamineActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			}
		});
		et_edit_supervise_qcsbdm = (Button) findViewById(R.id.et_edit_supervise_qcsbdm);
		et_edit_supervise_qcsbdm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if(!et_edit_supervise_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(EditSuperviseExamineActivity.this);
					mTextHtbhUtils.dialogQcsbdm(et_edit_supervise_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(EditSuperviseExamineActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			
			}
		});
		et_edit_supervise_jdsj.setOnClickListener(this);
//		btn_product_record = (Button) findViewById(R.id.btn_product_record);
//		btn_product_record.setOnClickListener(this);
		et_edit_supervise_htbh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(EditSuperviseExamineActivity.this);
				mTextHtbhUtils.dialogHybhUtils(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME), PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mTextHtbhUtils.setDialogcallback(EditSuperviseExamineActivity.this);
			
			}
		});
	}

	protected void onRightSave() {

		ArrayList<ProductProblemBean> list = null;
		if(!et_edit_supervise_jdsj.getText().toString().equals("")&&!et_edit_supervise_htbh.getText().toString().equals("")){
			SQLiteUtils sql = SQLiteUtils.getInstance(this);
			SQLiteDatabase db = sql.getWritableDatabase();
			
			mSuperviseBean.setHtbh(et_edit_supervise_htbh.getText().toString());
			mSuperviseBean.setJdsj(et_edit_supervise_jdsj.getText().toString());
			mSuperviseBean.setCzdw(et_edit_supervise_czdw.getText().toString());
			mSuperviseBean.setCzdwptr(et_edit_supervise_czdwptr.getText().toString());
			mSuperviseBean.setQcsbmc(et_edit_supervise_qcsbmc.getText().toString());
			mSuperviseBean.setJdjl(et_edit_supervise_jdjl.getText().toString());
			mSuperviseBean.setJdr(et_edit_supervise_jdr.getText().toString());
			mSuperviseBean.setGgxh(et_edit_supervise_ggxh.getText().toString());
			mSuperviseBean.setQcsbdm(et_edit_supervise_qcsbdm.getText().toString());
			
			/*测试清空数据*/
			mSuperviseBean.setJdxm("");
			mSuperviseBean.setJdnr("");
			mSuperviseBean.setJdyq("");
			mSuperviseBean.setJdjg("");
			
			long _id = 0;
			
			 if (dbColect.equals("save")){
				 mSuperviseBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				 mSuperviseBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				  _id = sql.SavaDataSingle(db, mSuperviseBean);
			 }
			 
			 toast("保存成功");
			 finish();
//			 Intent i = new Intent(this,
//					 EditSuperviseConditionActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "updata");
//				bundle.putLong(IConfig.INTENT_PRODUCT_CONCLUSION_ID_KEY, _id);
//				bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, mSuperviseBean);
//				i.putExtras(bundle);
//				startActivity(i);
		}else{
			toast("合同编号，监督时间为必填项");
		}
	
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.et_edit_supervise_jdsj:
			InputMethodManager();
			SelectBirthday birth = new SelectBirthday(this,handler,et_edit_supervise_jdsj.getText().toString(),TTRQ_FLAG);
			birth.showAtLocation(this.findViewById(R.id.et_edit_supervise_jdsj),
					Gravity.BOTTOM, 0, 0);
			break;
//		case R.id.btn_product_record:
//			
//			Intent i = new Intent(this,
//					ProductRecordConclusionActivity.class);
//			Bundle bundle = new Bundle();
//			bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, mInspectionRecord);
//			i.putExtras(bundle);
//			startActivity(i);
////			mInspectionRecord.getHtbh();
////			mInspectionRecord.getCpxh();
////			mInspectionRecord.getSjcpbh();
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
			et_edit_supervise_htbh.setText(str);
			break;
		case DialogUtils.SHOW_QCSBMC_FLAG:
			et_edit_supervise_qcsbmc.setText(str);
			break;
		case DialogUtils.SHOW_QCSBDM_FLAG:
			et_edit_supervise_qcsbdm.setText(str);
			break;
		case DialogUtils.SHOW_GGXH_FLAG:
			et_edit_supervise_ggxh.setText(str);
			break;
		case DialogUtils.SHOW_CZDW_FLAG:
			et_edit_supervise_czdw.setText(str);
			break;

		default:
			break;
		}
	}
	
	
	Handler handler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
			case TTRQ_FLAG:
				if(msg.obj != null){
					et_edit_supervise_jdsj.setText((String)msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};
}
