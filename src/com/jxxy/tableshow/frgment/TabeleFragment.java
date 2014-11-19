package com.jxxy.tableshow.frgment;

import java.util.Date;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jxxy.tableshow.BaseFragment;
import com.jxxy.tableshow.R;
import com.jxxy.tableshow.utils.CreateExcelUtils;
import com.jxxy.tableshow.utils.CustomDialog;
import com.jxxy.tableshow.utils.LogUtils;
import com.jxxy.tableshow.utils.PreferencesUtils;

/**
 * tab分页
* @ClassName: TabeleFragment 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-9 下午11:13:27 
*
 */
public class TabeleFragment extends BaseFragment implements OnClickListener{

	/** 跳转标示*/
	private int tableFlag = 0;
	/** 合同明细按钮 */
	private TextView tv_contract_details_bt;
	/** 质量问题记录按钮*/
	private TextView tv_quality_problem_bt;
	/** 质量检验验收记录表*/
	private TextView tv_product_record_bt;
	/** 质量监督检查记录表*/
	private TextView tv_supervise_examine_bt;
	private TextView tv_check_problem_vt;
	Button contract_out;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.table_fragment_layout, container,false);
		tv_contract_details_bt = (TextView) view.findViewById(R.id.tv_contract_details_bt);
		tv_contract_details_bt.setOnClickListener(this);
		tv_quality_problem_bt = (TextView) view.findViewById(R.id.tv_quality_problem_bt);
		tv_quality_problem_bt.setOnClickListener(this);
		tv_product_record_bt = (TextView) view.findViewById(R.id.tv_product_record_bt);
		tv_product_record_bt.setOnClickListener(this);
		tv_supervise_examine_bt = (TextView) view.findViewById(R.id.tv_supervise_examine_bt);
		tv_supervise_examine_bt.setOnClickListener(this);
		tv_check_problem_vt = (TextView) view.findViewById(R.id.tv_check_problem_vt);
		tv_check_problem_vt.setOnClickListener(this);
		
		contract_out = (Button) view.findViewById(R.id.contract_out);
		contract_out.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ShowOutDialog();
			}
		});
		
		return view;
	}
	
	private void ShowOutDialog() {
		Dialog dialog = null;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				getActivity());
		customBuilder
				.setTitle("提示")
				.setMessage("是否导出Excel文件（文件会保存在SD卡tableCatalogue目录下）")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String timeStamp = String.valueOf(new Date().getTime());
						new CreateExcelUtils().createNewExcel(
								getActivity(),
								CreateExcelUtils.SD_PATH + "/File_" + timeStamp
										+ ".xls", PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID),PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKNAME));
						dialog.dismiss();
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
	
	
	// 当ACTIVITY 创建完毕，判断有没有DETAILS_LAYOUT元素，如果有说明是平板（双页）
		// 如果没有说明是手机（单页）
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			LogUtils.d("onActivityCreated");
		}

		@Override
		public void onClick(View arg0) {
			
			Fragment fragment = null;
			switch (arg0.getId()) {
			case R.id.tv_contract_details_bt:
				setPressTab(tv_contract_details_bt);
				fragment = new ContractDetailsFrgment();
				break;
			case R.id.tv_quality_problem_bt:
				setPressTab(tv_quality_problem_bt);
				fragment = new ProblemRecordListFragment();
				break;
			case R.id.tv_product_record_bt:
				setPressTab(tv_product_record_bt);
				fragment = new ProductRecordFragment();
				break;
			case R.id.tv_supervise_examine_bt:
				setPressTab(tv_supervise_examine_bt);
				fragment = new SuperviseExamineFrament();
				break;
			case R.id.tv_check_problem_vt:
				setPressTab(tv_check_problem_vt);
				fragment = new CheckProblemFragment();
				break;
			default:
				break;
			}
			getFragmentManager().beginTransaction()
			.replace(R.id.body_fragment, fragment).commit();
		}

		private void setPressTab(TextView tv) {
			if(tv == tv_contract_details_bt){
				LogUtils.d("1");
				tv_contract_details_bt.setTextColor(getResources().getColor(R.color.blue));
				tv_quality_problem_bt.setTextColor(getResources().getColor(R.color.white));
				tv_product_record_bt.setTextColor(getResources().getColor(R.color.white));
				tv_supervise_examine_bt.setTextColor(getResources().getColor(R.color.white));
				tv_check_problem_vt.setTextColor(getResources().getColor(R.color.white));
			}
			
			if(tv == tv_quality_problem_bt){
				LogUtils.d("2");
				tv_contract_details_bt.setTextColor(getResources().getColor(R.color.white));
				tv_quality_problem_bt.setTextColor(getResources().getColor(R.color.blue));
				tv_product_record_bt.setTextColor(getResources().getColor(R.color.white));
				tv_supervise_examine_bt.setTextColor(getResources().getColor(R.color.white));
				tv_check_problem_vt.setTextColor(getResources().getColor(R.color.white));
			}
			if(tv == tv_product_record_bt){
				LogUtils.d("3");
				tv_contract_details_bt.setTextColor(getResources().getColor(R.color.white));
				tv_quality_problem_bt.setTextColor(getResources().getColor(R.color.white));
				tv_product_record_bt.setTextColor(getResources().getColor(R.color.blue));
				tv_supervise_examine_bt.setTextColor(getResources().getColor(R.color.white));
				tv_check_problem_vt.setTextColor(getResources().getColor(R.color.white));
			}
			if(tv == tv_supervise_examine_bt){
				LogUtils.d("4");
				tv_contract_details_bt.setTextColor(getResources().getColor(R.color.white));
				tv_quality_problem_bt.setTextColor(getResources().getColor(R.color.white));
				tv_product_record_bt.setTextColor(getResources().getColor(R.color.white));
				tv_supervise_examine_bt.setTextColor(getResources().getColor(R.color.blue));
				tv_check_problem_vt.setTextColor(getResources().getColor(R.color.white));
			}
			if(tv == tv_check_problem_vt){
				LogUtils.d("5");
				tv_contract_details_bt.setTextColor(getResources().getColor(R.color.white));
				tv_quality_problem_bt.setTextColor(getResources().getColor(R.color.white));
				tv_product_record_bt.setTextColor(getResources().getColor(R.color.white));
				tv_supervise_examine_bt.setTextColor(getResources().getColor(R.color.white));
				tv_check_problem_vt.setTextColor(getResources().getColor(R.color.blue));
			}
			
			
		}
}
