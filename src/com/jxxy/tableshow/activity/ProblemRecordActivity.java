package com.jxxy.tableshow.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrollerpicker.SelectBirthday;
import com.jxxy.table.tableshow.config.IConfig;
import com.jxxy.tableshow.BaseActivity;
import com.jxxy.tableshow.ExitManager;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;
import com.jxxy.tableshow.imp.OnTopBarListener;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;
import com.jxxy.tableshow.utils.DialogUtils;
import com.jxxy.tableshow.utils.DialogUtils.DiaLogCallback;
import com.jxxy.tableshow.view.TitleView;
import com.jxxy.tableshow.view.TitleView.OnLeftButtonClickListener;
import com.jxxy.tableshow.view.TitleView.OnRightButtonClickListener;
import com.jxxy.tableshow.view.TopBar;

/**
 * @ClassName: ProblemRecordActivity
 * @Description: 质量问题记录表
 * @author LiLong
 * @date 2014-6-4 上午12:39:33
 * 
 */
public class ProblemRecordActivity extends BaseActivity implements
		OnTopBarListener, DiaLogCallback, OnClickListener {

	private ProductProblemBean mProductProblemBean;
	private TitleView mTopBar;

	private Button et_problem_record_wirite_czdw;
	private TextView et_problem_record_wirite_htbh;
	private EditText et_problem_record_wirite_cpbh;
	private Button et_problem_record_wirite_wtfsrq;
	private EditText et_problem_record_wirite_wtfsdd;
	private Button et_problem_record_wirite_qcsbmc;
	private Button et_problem_record_wirite_ggxh;
	private Button et_problem_record_wirite_qcsbdm;
	private EditText et_problem_record_wirite_zlwtms;
	private EditText et_problem_record_wirite_zjzzzqr;
	private Button et_problem_record_wirite_zjzqrrq;

	private EditText et_problem_record_wirite_czdwqr;
	private Button et_problem_record_wirite_czdwqrrq;

	private final int WTFSRQ_FLAG = 0;
	private final int ZJZQRRQ_FLAG = 1;
	private final int CZDWQRRQ_FLAG = 2;
	
	String dbColect = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		setContentView(R.layout.problem_record_wirite_layout);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getIntentData();
	}

	private void getIntentData() {

		PreferencesUtils.init(this);

		if (null != getIntent().getExtras().getString(
				IConfig.INTENT_PROBLEM_SAVE_KEY))
			dbColect = getIntent().getExtras().getString(
					IConfig.INTENT_PROBLEM_SAVE_KEY);

		mProductProblemBean = (ProductProblemBean) getIntent()
				.getSerializableExtra(IConfig.INTENT_PROBLEM_RECORD_BEAN_KEY);
		if (mProductProblemBean != null) {
			installViewData();
		} else {
			mProductProblemBean = new ProductProblemBean();
		}

		// if(!htbhStr.equals("")){
		// SQLiteUtils sql = SQLiteUtils.getInstance(this);
		// SQLiteDatabase db = sql.getWritableDatabase();
		// List<ProductProblemBean> list = (List<ProductProblemBean>)
		// sql.GetDataByWhere(db, ProductProblemBean.class, "htbh like '"
		// + htbhStr + "'");
		// LogUtils.d(list.size()+"");
		// }
	}

	private void installViewData() {

		et_problem_record_wirite_czdw.setText(mProductProblemBean.getCzdw());
		et_problem_record_wirite_htbh.setText(mProductProblemBean.getHtbh());
		et_problem_record_wirite_cpbh.setText(mProductProblemBean.getCpbh());
		et_problem_record_wirite_wtfsrq
				.setText(mProductProblemBean.getWtfsrq());
		et_problem_record_wirite_wtfsdd
				.setText(mProductProblemBean.getWtfsdd());
		et_problem_record_wirite_qcsbmc
				.setText(mProductProblemBean.getQcsbmc());
		et_problem_record_wirite_ggxh.setText(mProductProblemBean.getGgxh());
		et_problem_record_wirite_qcsbdm
				.setText(mProductProblemBean.getQcsbdm());
		et_problem_record_wirite_zlwtms.setText(mProductProblemBean.getGgxh());
		et_problem_record_wirite_zjzzzqr
				.setText(mProductProblemBean.getZjzzz());
		et_problem_record_wirite_zjzqrrq.setText(mProductProblemBean
				.getZjzzzrq());
		et_problem_record_wirite_czdwqr
				.setText(mProductProblemBean.getCzdwqr());
		et_problem_record_wirite_czdwqrrq.setText(mProductProblemBean
				.getCzdwqrrq());
	}

	private void initView() {
		et_problem_record_wirite_czdw = (Button) findViewById(R.id.et_problem_record_wirite_czdw);
		et_problem_record_wirite_czdw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (!et_problem_record_wirite_htbh.getText().toString()
						.trim().equals("")) {
					DialogUtils mTextHtbhUtils = new DialogUtils(
							ProblemRecordActivity.this);
					mTextHtbhUtils
							.dialogCzdw(et_problem_record_wirite_htbh
									.getText().toString().trim());
					mTextHtbhUtils
							.setDialogcallback(ProblemRecordActivity.this);
				} else {
					toast("请先选择合同编号");
				}
			
			}
		});
		et_problem_record_wirite_htbh = (TextView) findViewById(R.id.et_problem_record_wirite_htbh);
		et_problem_record_wirite_cpbh = (EditText) findViewById(R.id.et_problem_record_wirite_cpbh);
		et_problem_record_wirite_wtfsrq = (Button) findViewById(R.id.et_problem_record_wirite_wtfsrq);
		et_problem_record_wirite_wtfsdd = (EditText) findViewById(R.id.et_problem_record_wirite_wtfsdd);
		et_problem_record_wirite_qcsbmc = (Button) findViewById(R.id.et_problem_record_wirite_cpxhmc);
		et_problem_record_wirite_qcsbmc
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						if (!et_problem_record_wirite_htbh.getText().toString()
								.trim().equals("")) {
							DialogUtils mTextHtbhUtils = new DialogUtils(
									ProblemRecordActivity.this);
							mTextHtbhUtils
									.dialogQcsbmc(et_problem_record_wirite_htbh
											.getText().toString().trim());
							mTextHtbhUtils
									.setDialogcallback(ProblemRecordActivity.this);
						} else {
							toast("请先选择合同编号");
						}
					}
				});
		et_problem_record_wirite_ggxh = (Button) findViewById(R.id.et_problem_record_wirite_ggxh);
		et_problem_record_wirite_ggxh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (!et_problem_record_wirite_htbh.getText().toString().trim()
						.equals("")) {
					DialogUtils mTextHtbhUtils = new DialogUtils(
							ProblemRecordActivity.this);
					mTextHtbhUtils.dialogGgxh(et_problem_record_wirite_htbh
							.getText().toString().trim());
					mTextHtbhUtils
							.setDialogcallback(ProblemRecordActivity.this);
				} else {
					toast("请先选择合同编号");
				}
			}
		});
		et_problem_record_wirite_qcsbdm = (Button) findViewById(R.id.et_problem_record_wirite_qcsbdm);
		et_problem_record_wirite_qcsbdm
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						if (!et_problem_record_wirite_htbh.getText().toString()
								.trim().equals("")) {
							DialogUtils mTextHtbhUtils = new DialogUtils(
									ProblemRecordActivity.this);
							mTextHtbhUtils
									.dialogQcsbdm(et_problem_record_wirite_htbh
											.getText().toString().trim());
							mTextHtbhUtils
									.setDialogcallback(ProblemRecordActivity.this);
						} else {
							toast("请先选择合同编号");
						}
					}
				});
		et_problem_record_wirite_zlwtms = (EditText) findViewById(R.id.et_problem_record_wirite_zlwtms);
		et_problem_record_wirite_zjzzzqr = (EditText) findViewById(R.id.et_problem_record_wirite_zjzqr);
		et_problem_record_wirite_zjzqrrq = (Button) findViewById(R.id.et_problem_record_wirite_zjzqrrq);
		et_problem_record_wirite_czdwqr = (EditText) findViewById(R.id.et_problem_record_wirite_czdwqr);
		et_problem_record_wirite_czdwqrrq = (Button) findViewById(R.id.et_problem_record_wirite_czdwqrrq);

		et_problem_record_wirite_wtfsrq.setOnClickListener(this);
		et_problem_record_wirite_zjzqrrq.setOnClickListener(this);
		et_problem_record_wirite_czdwqrrq.setOnClickListener(this);

		et_problem_record_wirite_htbh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogUtils mTextHtbhUtils = new DialogUtils(
						ProblemRecordActivity.this);
				mTextHtbhUtils.dialogHybhUtils(PreferencesUtils
						.getShareStringData(PreferencesUtils.TASKNAME),
						PreferencesUtils
								.getShareStringData(PreferencesUtils.TASKID));
				mTextHtbhUtils.setDialogcallback(ProblemRecordActivity.this);
			}
		});

		mTopBar = (TitleView) findViewById(R.id.problem_record_wirite_topbar);
		mTopBar.setTitle("质量问题记录表");
		mTopBar.setLeftButton("返回", new OnLeftButtonClickListener() {

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

		ArrayList<ProductProblemBean> list = null;
		if (!et_problem_record_wirite_qcsbmc.getText().toString().equals("")
				&& !et_problem_record_wirite_htbh.getText().toString()
						.equals("")
				&& !et_problem_record_wirite_cpbh.getText().toString()
						.equals("")) {
			SQLiteUtils sql = SQLiteUtils.getInstance(this);
			SQLiteDatabase db = sql.getWritableDatabase();
			mProductProblemBean.setCzdw(et_problem_record_wirite_czdw.getText()
					.toString());
			mProductProblemBean.setHtbh(et_problem_record_wirite_htbh.getText()
					.toString());
			mProductProblemBean.setCpbh(et_problem_record_wirite_cpbh.getText()
					.toString());
			mProductProblemBean.setWtfsrq(et_problem_record_wirite_wtfsrq
					.getText().toString());
			mProductProblemBean.setWtfsdd(et_problem_record_wirite_wtfsdd
					.getText().toString());
			mProductProblemBean.setQcsbmc(et_problem_record_wirite_qcsbmc
					.getText().toString());
			mProductProblemBean.setGgxh(et_problem_record_wirite_ggxh.getText()
					.toString());
			mProductProblemBean.setQcsbdm(et_problem_record_wirite_qcsbdm
					.getText().toString());
			mProductProblemBean.setZlwtqkms(et_problem_record_wirite_zlwtms
					.getText().toString());
			mProductProblemBean.setZjzzz(et_problem_record_wirite_zjzzzqr
					.getText().toString());
			mProductProblemBean.setZjzzzrq(et_problem_record_wirite_zjzqrrq
					.getText().toString());
			mProductProblemBean.setCzdwqr(et_problem_record_wirite_czdwqr
					.getText().toString());
			mProductProblemBean.setCzdwqrrq(et_problem_record_wirite_czdwqrrq
					.getText().toString());

			if (dbColect.equals("save")) {
				mProductProblemBean.setTaskId(PreferencesUtils
						.getShareStringData(PreferencesUtils.TASKID));
				mProductProblemBean.setTaskName(PreferencesUtils
						.getShareStringData(PreferencesUtils.TASKNAME));
				sql.SavaDataSingle(db, mProductProblemBean);
			} else {
				mProductProblemBean.setTaskId(PreferencesUtils
						.getShareStringData(PreferencesUtils.TASKID));
				mProductProblemBean.setTaskName(PreferencesUtils
						.getShareStringData(PreferencesUtils.TASKNAME));
				sql.Update(db, mProductProblemBean);
			}
			toast("保存成功");
			finish();
		} else {
			toast("合同编号，产品编号，器材设备名称为必填项");
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
	public void onTopBarRightBtn() {
	}

	@Override
	public void onTopbarCenter() {
	}

	@Override
	public void dialogdo(int flag, String str) {
		switch (flag) {
		case DialogUtils.SHOW_HTBH_FLAG:
			et_problem_record_wirite_htbh.setText(str);
			break;
		case DialogUtils.SHOW_QCSBMC_FLAG:
			et_problem_record_wirite_qcsbmc.setText(str);
			break;
		case DialogUtils.SHOW_GGXH_FLAG:
			et_problem_record_wirite_ggxh.setText(str);
			break;
		case DialogUtils.SHOW_QCSBDM_FLAG:
			et_problem_record_wirite_qcsbdm.setText(str);
			break;
		case DialogUtils.SHOW_CZDW_FLAG:
			et_problem_record_wirite_czdw.setText(str);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.et_problem_record_wirite_wtfsrq:
			InputMethodManager();
			SelectBirthday birth = new SelectBirthday(this, handler,
					et_problem_record_wirite_wtfsrq.getText().toString(),
					WTFSRQ_FLAG);
			birth.showAtLocation(
					this.findViewById(R.id.problem_record_wirite_root),
					Gravity.BOTTOM, 0, 0);
			break;

		case R.id.et_problem_record_wirite_zjzqrrq:
			InputMethodManager();
			SelectBirthday birth2 = new SelectBirthday(this, handler,
					et_problem_record_wirite_zjzqrrq.getText().toString(),
					ZJZQRRQ_FLAG);
			birth2.showAtLocation(
					this.findViewById(R.id.problem_record_wirite_root),
					Gravity.BOTTOM, 0, 0);
			break;
		case R.id.et_problem_record_wirite_czdwqrrq:
			InputMethodManager();
			SelectBirthday birth3 = new SelectBirthday(this, handler,
					et_problem_record_wirite_czdwqrrq.getText().toString(),
					CZDWQRRQ_FLAG);
			birth3.showAtLocation(
					this.findViewById(R.id.problem_record_wirite_root),
					Gravity.BOTTOM, 0, 0);
			break;
		default:
			break;
		}
	}

	public void InputMethodManager() {
		  // 输入法是否弹出
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
                //关闭输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
//        
//		// 获取输入法状态
//		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		boolean isOpen = imm.isActive();
//		// 隐藏输入法
//		if (isOpen) {
//			InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//		}
	}

	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
			case WTFSRQ_FLAG:
				if (msg.obj != null) {
					et_problem_record_wirite_wtfsrq.setText((String) msg.obj);
				}
				break;
			case ZJZQRRQ_FLAG:
				if (msg.obj != null) {
					et_problem_record_wirite_zjzqrrq.setText((String) msg.obj);
				}
				break;
			case CZDWQRRQ_FLAG:
				if (msg.obj != null) {
					et_problem_record_wirite_czdwqrrq.setText((String) msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};
	// String test =hybhDialog(ContractDetailsActivity.this,
	// PreferencesUtils.getShareStringData(PreferencesUtils.TASKNAME),
	// PreferencesUtils.getShareStringData(PreferencesUtils.TASKID));
}
