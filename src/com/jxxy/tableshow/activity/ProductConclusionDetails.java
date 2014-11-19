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
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TopBar;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;

/**
 * 查看产品检验记录及判定结论
* @ClassName: ProductConclusionDetails 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-7-31 上午10:19:42 
*
 */
public class ProductConclusionDetails extends BaseActivity implements OnTopBarListener, OnClickListener, DiaLogCallback{

	protected static final int RQ_FLAG = 0;
	String changeStr = "";
	long _id = 0;
	TitleView mTopBar;
	InspectionRecordBean mInspectionRecordBean;
	private Button et_conclusionDetails_jyxm;
	private Button et_conclusionDetails_jsyq;
	private EditText et_conclusionDetails_jcjg;
	private EditText et_conclusionDetails_jcjl;
	private EditText et_conclusionDetails_zjzqz;
	private EditText et_conclusionDetails_bz;
	private EditText et_conclusionDetails_zjzysjl;
	private EditText et_conclusionDetails_zjzcyqm;
	
	private EditText et_conclusionDetails_jydd;
	private Button et_conclusionDetails_ggxh;
	private Button et_conclusionDetails_qcsbdm;
	private Button et_conclusionDetails_rq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_conclusion_details);
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
			mInspectionRecordBean = (InspectionRecordBean) getIntent().getSerializableExtra(IConfig.INTENT_PRODUCT_RECORD_DETAILS_KEY);
		}
		
		 if(mInspectionRecordBean!=null){
			 installViewData();
		 }else{
			 SQLiteUtils sql = SQLiteUtils.getInstance(this);
				SQLiteDatabase db = sql.getWritableDatabase();
				ArrayList<InspectionRecordBean> getInfo  = (ArrayList<InspectionRecordBean>) sql.GetDataByWhere(db, InspectionRecordBean.class, "_id like '" + String.valueOf(_id)
						+ "'", null);
				
				if(null!=getInfo&&getInfo.size()>0)
				mInspectionRecordBean = getInfo.get(0);
				
				if(null==mInspectionRecordBean)
			 mInspectionRecordBean = new InspectionRecordBean(); 
		 }
	}

	private void installViewData() {
		if(null!=mInspectionRecordBean.getJyxm())
		et_conclusionDetails_jyxm.setText(mInspectionRecordBean.getJyxm());
		if(null!=mInspectionRecordBean.getJsyq())
		et_conclusionDetails_jsyq.setText(mInspectionRecordBean.getJsyq());
		if(null!=mInspectionRecordBean.getJyjg())
		et_conclusionDetails_jcjg.setText(mInspectionRecordBean.getJyjg());
		if(null!=mInspectionRecordBean.getJyjl())
		et_conclusionDetails_jcjl.setText(mInspectionRecordBean.getJyjl());
		if(null!=mInspectionRecordBean.getYsry())
		et_conclusionDetails_zjzqz.setText(mInspectionRecordBean.getYsry());
		if(null!=mInspectionRecordBean.getBz())
		et_conclusionDetails_bz.setText(mInspectionRecordBean.getBz());
		if(null!=mInspectionRecordBean.getZjzysjl())
		et_conclusionDetails_zjzysjl.setText(mInspectionRecordBean.getZjzysjl());
		if(null!=mInspectionRecordBean.getZjzcyqm())
		et_conclusionDetails_zjzcyqm.setText(mInspectionRecordBean.getZjzcyqm());
		
		if(null!=mInspectionRecordBean.getJjdd())
		et_conclusionDetails_jydd.setText(mInspectionRecordBean.getJjdd());
		if(null!=mInspectionRecordBean.getGgxh())
		et_conclusionDetails_ggxh.setText(mInspectionRecordBean.getGgxh());
		if(null!=mInspectionRecordBean.getQcsbdm())
		et_conclusionDetails_qcsbdm.setText(mInspectionRecordBean.getQcsbdm());
		if(null!=mInspectionRecordBean.getRq())
		et_conclusionDetails_rq.setText(mInspectionRecordBean.getRq());
		
	}

	private void initView() {
		
		mTopBar = (TitleView) findViewById(R.id.product_conclusion_details_topbar);
		mTopBar.setTitle("判定结论");
		mTopBar.setLeftButton("返回", new com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener(){

			public void onClick(View button) {
				finish();
			}
			
		});
		mTopBar.setRightButton("保存", new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				if(!isNull(et_conclusionDetails_jyxm.getText().toString())){
					onRightSave();
				}else{
					toast("检验项目为必填");
				}
			}
		});
		
		et_conclusionDetails_jyxm = (Button) findViewById(R.id.et_conclusionDetails_jyxm);
		et_conclusionDetails_jyxm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(null!=mInspectionRecordBean){
					DialogUtils mTextHtbhUtils = new DialogUtils(ProductConclusionDetails.this);
					mTextHtbhUtils.dialogJyxm(mInspectionRecordBean.getQcsbmc());
					mTextHtbhUtils.setDialogcallback(ProductConclusionDetails.this);
				}
			}
		});
		et_conclusionDetails_jsyq = (Button) findViewById(R.id.et_conclusionDetails_jsyq);
		et_conclusionDetails_jsyq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(null!=mInspectionRecordBean){
					DialogUtils mTextHtbhUtils = new DialogUtils(ProductConclusionDetails.this);
					mTextHtbhUtils.dialogJsyq(mInspectionRecordBean.getQcsbmc());
					mTextHtbhUtils.setDialogcallback(ProductConclusionDetails.this);
				}
			}
		});
		et_conclusionDetails_jcjg = (EditText) findViewById(R.id.et_conclusionDetails_jcjg);
		et_conclusionDetails_jcjl = (EditText) findViewById(R.id.et_conclusionDetails_jcjl);
		et_conclusionDetails_zjzqz = (EditText) findViewById(R.id.et_conclusionDetails_zjzqz);
		et_conclusionDetails_bz = (EditText) findViewById(R.id.et_conclusionDetails_bz);
		et_conclusionDetails_zjzysjl = (EditText) findViewById(R.id.et_conclusionDetails_zjzysjl);
		et_conclusionDetails_zjzcyqm = (EditText) findViewById(R.id.et_conclusionDetails_zjzcyqm);
		
		et_conclusionDetails_jydd = (EditText) findViewById(R.id.et_conclusionDetails_jydd);
		et_conclusionDetails_ggxh = (Button) findViewById(R.id.et_conclusionDetails_ggxh);
		et_conclusionDetails_ggxh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(ProductConclusionDetails.this);
				mTextHtbhUtils.dialogGgxh(mInspectionRecordBean.getHtbh());
				mTextHtbhUtils.setDialogcallback(ProductConclusionDetails.this);
			}
		});
		et_conclusionDetails_qcsbdm = (Button) findViewById(R.id.et_conclusionDetails_qcsbdm);
		et_conclusionDetails_qcsbdm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(ProductConclusionDetails.this);
				mTextHtbhUtils.dialogQcsbdm(mInspectionRecordBean.getHtbh());
				mTextHtbhUtils.setDialogcallback(ProductConclusionDetails.this);
			}
		});
		et_conclusionDetails_rq = (Button) findViewById(R.id.et_conclusionDetails_rq);
		et_conclusionDetails_rq.setOnClickListener(this);
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
		ArrayList<InspectionRecordBean> getInfo  = (ArrayList<InspectionRecordBean>) sql.GetDataByWhere(db, InspectionRecordBean.class, "_id like '" + String.valueOf(_id)
				+ "'", null);
		
		if(null!=getInfo&&getInfo.size()>0)
		mInspectionRecordBean = getInfo.get(0);
		/*et_conclusionDetails_jyxm.setText(mInspectionRecordBean.getJyxm());
		et_conclusionDetails_jsyq.setText(mInspectionRecordBean.getJsyq());
		et_conclusionDetails_jcjg.setText(mInspectionRecordBean.getJyjg());
		et_conclusionDetails_jcjl.setText(mInspectionRecordBean.getJyjl());
		et_conclusionDetails_zjzqz.setText(mInspectionRecordBean.getZjzqz());
		et_conclusionDetails_bz.setText(mInspectionRecordBean.getBz());
		et_conclusionDetails_zjzysjl.setText(mInspectionRecordBean.getZjzysjl());
		et_conclusionDetails_zjzcyqm.setText(mInspectionRecordBean.getZjzcyqm());*/
		
		mInspectionRecordBean.setJyxm(et_conclusionDetails_jyxm.getText().toString());
		mInspectionRecordBean.setJsyq(et_conclusionDetails_jsyq.getText().toString());
		mInspectionRecordBean.setJyjg(et_conclusionDetails_jcjg.getText().toString());
		mInspectionRecordBean.setJyjl(et_conclusionDetails_jcjl.getText().toString());
		mInspectionRecordBean.setYsry(et_conclusionDetails_zjzqz.getText().toString());
		mInspectionRecordBean.setBz(et_conclusionDetails_bz.getText().toString());
		mInspectionRecordBean.setZjzysjl(et_conclusionDetails_zjzysjl.getText().toString());
		mInspectionRecordBean.setZjzcyqm(et_conclusionDetails_zjzcyqm.getText().toString());
		mInspectionRecordBean.setJjdd(et_conclusionDetails_jydd.getText().toString());
		mInspectionRecordBean.setGgxh(et_conclusionDetails_ggxh.getText().toString());
		mInspectionRecordBean.setQcsbdm(et_conclusionDetails_qcsbdm.getText().toString());
		mInspectionRecordBean.setRq(et_conclusionDetails_rq.getText().toString());
		
		if(changeStr.equals("save")){
			mInspectionRecordBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
			mInspectionRecordBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
			sql.SavaDataSingle(db, mInspectionRecordBean);
		}else{
			mInspectionRecordBean.setTaskId(PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
			mInspectionRecordBean.setTaskName(PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME));
			sql.Update(db, mInspectionRecordBean);
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.et_conclusionDetails_rq:
			InputMethodManager();
			SelectBirthday birth3 = new SelectBirthday(this,handler,et_conclusionDetails_rq.getText().toString(),RQ_FLAG);
			birth3.showAtLocation(this.findViewById(R.id.product_conclusion_details_root),
					Gravity.BOTTOM, 0, 0);
			break;

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
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
			case RQ_FLAG:
				if(msg.obj != null){
					et_conclusionDetails_rq.setText((String)msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void dialogdo(int flag, String str) {
		switch (flag) {
		case DialogUtils.SHOW_QCSBDM_FLAG:
			et_conclusionDetails_qcsbdm.setText(str);
			break;
		case DialogUtils.SHOW_GGXH_FLAG:
			et_conclusionDetails_ggxh.setText(str);
			break;
		case DialogUtils.SHOW_JYXM_FLAG:
			et_conclusionDetails_jyxm.setText(str);
			break;
		case DialogUtils.SHOW_JSYQ_FLAG:
			et_conclusionDetails_jsyq.setText(str);
			break;
		default:
			break;
		}
	}
}
