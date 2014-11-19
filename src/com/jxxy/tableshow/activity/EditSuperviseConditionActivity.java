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

import com.example.scrollerpicker.SelectBirthday;
import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.SuperviseBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

public class EditSuperviseConditionActivity extends BaseActivity implements OnTopBarListener{

	protected static final int RQ_FLAG = 0;
	String changeStr = "";
	long _id = 0;
	TitleView mTopBar;
	SuperviseBean mSuperviseBean;
	private EditText et_conclusion_details_jdxm;
	private EditText et_conclusion_details_jdnr;
	private EditText et_conclusion_details_jdyq;
	private EditText et_conclusion_details_jdjg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conclusion_details_layout);
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		changeStr = "";
		getIntentData();
	}
	private void getIntentData() {
		
		if(getIntent().getStringExtra(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY)!=null){
			changeStr = getIntent().getStringExtra(IConfig.INTENT_PRODUCT_CONCLUSION_CHANGE_KEY);
		}
		
		if(getIntent().getLongExtra(IConfig.INTENT_PRODUCT_CONCLUSION_ID_KEY,0)!=0){
			_id = getIntent().getLongExtra(IConfig.INTENT_PRODUCT_CONCLUSION_ID_KEY,0);
		}
		
		if(getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY)!=null){
			mSuperviseBean = (SuperviseBean) getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		}
		
		 if(mSuperviseBean!=null){
			 installViewData();
		 }else{
			 SQLiteUtils sql = SQLiteUtils.getInstance(this);
				SQLiteDatabase db = sql.getWritableDatabase();
				ArrayList<SuperviseBean> getInfo  = (ArrayList<SuperviseBean>) sql.GetDataByWhere(db, SuperviseBean.class, "_id like '" + String.valueOf(_id)
						+ "'", null);
				
				if(null!=getInfo&&getInfo.size()>0)
				mSuperviseBean = getInfo.get(0);
				
				if(null==mSuperviseBean)
			 mSuperviseBean = new SuperviseBean(); 
		 }
	}

	private void installViewData() {
		
		et_conclusion_details_jdxm.setText(mSuperviseBean.getJdxm());
		et_conclusion_details_jdnr.setText(mSuperviseBean.getJdnr());
		et_conclusion_details_jdyq.setText(mSuperviseBean.getJdyq());
		et_conclusion_details_jdjg.setText(mSuperviseBean.getJdjg());
		
	}

	private void initView() {
		
		mTopBar = (TitleView) findViewById(R.id.product_conclusion_details_topbar);
		mTopBar.setTitle("监督情况");
		mTopBar.setLeftButton("返回", new com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		mTopBar.setRightButton("保存", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				if(!isNull(et_conclusion_details_jdxm.getText().toString())){
					onRightSave();
				}else{
					toast("检验项目为必填");
				}
			}
		});
		
		et_conclusion_details_jdxm = (EditText) findViewById(R.id.et_conclusion_details_jdxm);
		et_conclusion_details_jdnr = (EditText) findViewById(R.id.et_conclusion_details_jdnr);
		et_conclusion_details_jdyq = (EditText) findViewById(R.id.et_conclusion_details_jdyq);
		et_conclusion_details_jdjg = (EditText) findViewById(R.id.et_conclusion_details_jdjg);
	}

	protected void onRightSave() {
		
		/*Map<String,String> sendMap = new HashMap<String,String>();
		sendMap.put("jyxm", et_conclusionDetails_jyxm.getText().toString());
		sendMap.put("jsyq", et_conclusionDetails_jsyq.getText().toString());
		sendMap.put("jcjg", et_conclusionDetails_jcjg.getText().toString());
		sendMap.put("jcjl", et_conclusionDetails_jcjl.getText().toString());
		sendMap.put("zjzqz", et_conclusionDetails_zjzqz.getText().toString());
		sendMap.put("bz", et_conclusionDetails_bz.getText().toString());
		sendMap.put("zjzysjl", et_conclusionDetails_zjzysjl.getText().toString());
		sendMap.put("zjzcyqm", et_conclusionDetails_zjzcyqm.getText().toString());
		
		Intent i = new Intent(this,
				ProductRecordConclusionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY, (Serializable) sendMap);
		i.putExtras(bundle);
		startActivity(i);*/
		SQLiteUtils sql = SQLiteUtils.getInstance(this);
		SQLiteDatabase db = sql.getWritableDatabase();
		ArrayList<SuperviseBean> getInfo  = (ArrayList<SuperviseBean>) sql.GetDataByWhere(db, SuperviseBean.class, "_id like '" + String.valueOf(_id)
				+ "'", null);
		
		if(null!=getInfo&&getInfo.size()>0)
		mSuperviseBean = getInfo.get(0);
		/*et_conclusionDetails_jyxm.setText(mInspectionRecordBean.getJyxm());
		et_conclusionDetails_jsyq.setText(mInspectionRecordBean.getJsyq());
		et_conclusionDetails_jcjg.setText(mInspectionRecordBean.getJyjg());
		et_conclusionDetails_jcjl.setText(mInspectionRecordBean.getJyjl());
		et_conclusionDetails_zjzqz.setText(mInspectionRecordBean.getZjzqz());
		et_conclusionDetails_bz.setText(mInspectionRecordBean.getBz());
		et_conclusionDetails_zjzysjl.setText(mInspectionRecordBean.getZjzysjl());
		et_conclusionDetails_zjzcyqm.setText(mInspectionRecordBean.getZjzcyqm());*/
		
		mSuperviseBean.setJdxm(et_conclusion_details_jdxm.getText().toString());
		mSuperviseBean.setJdnr(et_conclusion_details_jdnr.getText().toString());
		mSuperviseBean.setJdyq(et_conclusion_details_jdyq.getText().toString());
		mSuperviseBean.setJdjg(et_conclusion_details_jdjg.getText().toString());
		
		if(changeStr.equals("save")){
			mSuperviseBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
			mSuperviseBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
			sql.SavaDataSingle(db, mSuperviseBean);
		}else{
			mSuperviseBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
			mSuperviseBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
			sql.Update(db, mSuperviseBean);
		}
		
		sql.close();
		finish();
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

	
}
