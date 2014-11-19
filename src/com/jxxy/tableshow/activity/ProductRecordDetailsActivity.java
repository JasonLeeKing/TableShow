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
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TopBar;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

/**
 * 产品检验记录详情
* @ClassName: ProductRecordDetailsActivity 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-7-30 下午4:01:25 
*
 */
public class ProductRecordDetailsActivity extends BaseActivity implements OnTopBarListener, OnClickListener, DiaLogCallback{

	private static final int TTRQ_FLAG = 0;
	InspectionRecordBean mInspectionRecordBean; 
	TitleView mTopBar;
	Button btn_product_record;
	private Button et_qcsbmc;
	private TextView et_htbh;
	private EditText et_htsl;
	private EditText et_sjcpbh;
	private EditText et_bctjsl;
	private Button et_tjdw;
	private EditText et_scrq;
	private Button et_jyrq;

	String dbColect = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_record);
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
		
		 mInspectionRecordBean = (InspectionRecordBean) getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		 if(mInspectionRecordBean!=null){
			 installViewData();
		 }else{
			 mInspectionRecordBean = new InspectionRecordBean();
		 }
	}
	
	private void installViewData() {
		et_qcsbmc.setText(mInspectionRecordBean.getQcsbmc());
		et_htbh.setText(mInspectionRecordBean.getHtbh());
		et_htsl.setText(mInspectionRecordBean.getDgsl());
		et_sjcpbh.setText(mInspectionRecordBean.getSjcpbh());
		et_bctjsl.setText(mInspectionRecordBean.getBctjsl());
		et_tjdw.setText(mInspectionRecordBean.getCzdw());
		et_scrq.setText(mInspectionRecordBean.getCppch());
		et_jyrq.setText(mInspectionRecordBean.getJyrq());
	}

	private void initView() {

		mTopBar = (TitleView) findViewById(R.id.product_record_topbar);
		 
			mTopBar.setTitle("产品检验记录详情");
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
			
		et_qcsbmc = (Button) findViewById(R.id.et_qcsbmc);
		et_qcsbmc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!et_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(ProductRecordDetailsActivity.this);
					mTextHtbhUtils.dialogQcsbmc(et_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(ProductRecordDetailsActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			}
		});
		et_htbh = (TextView) findViewById(R.id.et_htbh);
		et_htsl = (EditText) findViewById(R.id.et_htsl);
		et_sjcpbh = (EditText) findViewById(R.id.et_sjcpbh);
		et_bctjsl = (EditText) findViewById(R.id.et_bctjsl);
		et_tjdw = (Button) findViewById(R.id.et_tjdw);
		et_tjdw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				if(!et_htbh.getText().toString().trim().equals("")){
					DialogUtils mTextHtbhUtils = new DialogUtils(ProductRecordDetailsActivity.this);
					mTextHtbhUtils.dialogCzdw(et_htbh.getText().toString().trim());
					mTextHtbhUtils.setDialogcallback(ProductRecordDetailsActivity.this);
				}else{
					toast("请先填写合同编号");
				}
			
			}
		});
		et_scrq = (EditText) findViewById(R.id.et_scrq);
		et_jyrq = (Button) findViewById(R.id.et_jyrq);
		et_jyrq.setOnClickListener(this);
//		btn_product_record = (Button) findViewById(R.id.btn_product_record);
//		btn_product_record.setOnClickListener(this);
		et_htbh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(ProductRecordDetailsActivity.this);
				mTextHtbhUtils.dialogHybhUtils(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME), PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				mTextHtbhUtils.setDialogcallback(ProductRecordDetailsActivity.this);
			
			}
		});
	}

	protected void onRightSave() {

		ArrayList<ProductProblemBean> list = null;
		if(!et_qcsbmc.getText().toString().equals("")&&!et_htbh.getText().toString().equals("")&&!et_sjcpbh.getText().toString().equals("")){
			SQLiteUtils sql = SQLiteUtils.getInstance(this);
			SQLiteDatabase db = sql.getWritableDatabase();
			mInspectionRecordBean.setQcsbmc(et_qcsbmc.getText().toString());
			mInspectionRecordBean.setHtbh(et_htbh.getText().toString());
			mInspectionRecordBean.setDgsl(et_htsl.getText().toString());
			mInspectionRecordBean.setSjcpbh(et_sjcpbh.getText().toString());
			mInspectionRecordBean.setBctjsl(et_bctjsl.getText().toString());
			mInspectionRecordBean.setCzdw(et_tjdw.getText().toString());
			mInspectionRecordBean.setCppch(et_scrq.getText().toString());
			mInspectionRecordBean.setJyrq(et_jyrq.getText().toString());
			
			/* 测试清空数据 */
			mInspectionRecordBean.setJyxm("");
			mInspectionRecordBean.setJsyq("");
			mInspectionRecordBean.setJyjg("");
			mInspectionRecordBean.setJyjl("");
			mInspectionRecordBean.setYsry("");
			mInspectionRecordBean.setBz("");
			mInspectionRecordBean.setZjzysjl("");
			mInspectionRecordBean.setZjzcyqm("");
			mInspectionRecordBean.setJjdd("");
			mInspectionRecordBean.setGgxh("");
			mInspectionRecordBean.setQcsbdm("");
			mInspectionRecordBean.setRq("");
			
			
			long _id = 0;
			
			 if (dbColect.equals("save")){
				 mInspectionRecordBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
				 mInspectionRecordBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
				  _id = sql.SavaDataSingle(db, mInspectionRecordBean);
			 }
			 
			 toast("保存成功");
			 
//			 Intent i = new Intent(this,
//					 ProductConclusionDetails.class);
//				Bundle bundle = new Bundle();
//				bundle.putString(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY, "updata");
//				bundle.putLong(IConfig.INTENT_PRODUCT_CONCLUSION_ID_KEY, _id);
//				bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, mInspectionRecord);
//				i.putExtras(bundle);
//				startActivity(i);
			 finish();
		}else{
			toast("合同编号，器材设备名称，受检产品编号为必填项");
		}
	
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.et_jyrq:
			InputMethodManager();
			SelectBirthday birth = new SelectBirthday(this,handler,et_jyrq.getText().toString(),TTRQ_FLAG);
			birth.showAtLocation(this.findViewById(R.id.product_record_root),
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
			et_htbh.setText(str);
			break;
		case DialogUtils.SHOW_QCSBMC_FLAG:
			et_qcsbmc.setText(str);
			break;
		case DialogUtils.SHOW_CZDW_FLAG:
			et_tjdw.setText(str);
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
					et_jyrq.setText((String)msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};
}
