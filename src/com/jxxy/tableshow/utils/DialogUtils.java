package com.jxxy.tableshow.utils;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.CheckDetailBean;
import com.jxxy.tableshow.bean.ContractDetailBean;
import com.jxxy.tableshow.bean.TaskBean;

public class DialogUtils {
	
	/** 合同编号*/
	public final static int SHOW_HTBH_FLAG = 0 ;
	/** 器材设备名称*/
	public final static int SHOW_QCSBMC_FLAG = 1 ;
	/** 器材设备代码*/
	public final static int SHOW_QCSBDM_FLAG = 2 ;
	/** 规格型号*/
	public final static int SHOW_GGXH_FLAG = 3 ;
	/** 承制单位*/
	public final static int SHOW_CZDW_FLAG = 4 ;
	/** 技术要求*/
	public final static int SHOW_JSYQ_FLAG = 5 ;
	/** 检验项目*/
	public final static int SHOW_JYXM_FLAG = 6 ;
	Context mContext;
	String reHybh = "";
	
	public DialogUtils(Context context){
		mContext = context;
	}
	
	/**
	 * 技术要求 dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param htbh    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogJsyq(String qcsbmc){

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择技术要求名称");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_JSYQ_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<CheckDetailBean> list = (List<CheckDetailBean>) sql
				.GetDataByWhere(
						db,
						CheckDetailBean.class,
						"qcsbmc like '"
								+ qcsbmc
								, null);

		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_JSYQ_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_JSYQ_FLAG,list.get(arg2).getJsyq());
			}

		});
	}
	/**
	 * 检验项目 dialog
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param htbh    设定文件 
	 * @return    返回类型 
	 * @throws
	 */
	public void dialogJyxm(String qcsbmc){
		
		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择检验项目名称");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_JYXM_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);
		
		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();
		
		final List<CheckDetailBean> list = (List<CheckDetailBean>) sql
				.GetDataByWhere(
						db,
						CheckDetailBean.class,
						"qcsbmc like '"
								+ qcsbmc
								, null);
		
		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_JYXM_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_JYXM_FLAG,list.get(arg2).getJsyq());
			}
			
		});
	}
	
	
	/**
	 * 承制单位 dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param htbh    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogCzdw(String htbh){

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择承制单位名称");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_QCSBMC_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<TaskBean> list = (List<TaskBean>) sql
				.GetDataByWhere(
						db,
						TaskBean.class,
						"htbh like '"
								+ htbh
								+ "'"+ " GROUP BY czdw", null);

		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_CZDW_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_CZDW_FLAG,list.get(arg2).getCzdw());
			}

		});
	}
	
	
	/**
	 * GGXH dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param htbh    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogGgxh(String htbh){

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择规格型号");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_GGXH_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<ContractDetailBean> list = (List<ContractDetailBean>) sql
				.GetDataByWhere(
						db,
						ContractDetailBean.class,
						"htbh like '"
								+ htbh
								+ "'", null);

		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_GGXH_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_GGXH_FLAG,list.get(arg2).getGgxh());
			}

		});
	}
	
	/**
	 * 器材设备名称 dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param htbh    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogQcsbmc(String htbh){

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择器材设备名称");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_QCSBMC_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<ContractDetailBean> list = (List<ContractDetailBean>) sql
				.GetDataByWhere(
						db,
						ContractDetailBean.class,
						"htbh like '"
								+ htbh
								+ "'", null);

		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_QCSBMC_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_QCSBMC_FLAG,list.get(arg2).getQcsbmc());
			}

		});
	}
	
	/**
	 * 器材设备dm dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param htbh    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogQcsbdm(String htbh){

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择器材设备代码");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_QCSBDM_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<ContractDetailBean> list = (List<ContractDetailBean>) sql
				.GetDataByWhere(
						db,
						ContractDetailBean.class,
						"htbh like '"
								+ htbh
								+ "'", null);

		if (null != list && list.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, list,SHOW_QCSBDM_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_QCSBDM_FLAG,list.get(arg2).getQcsbdm());
			}

		});
	}
	
	/**
	 * hybh Dialog
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param taskName
	* @param @param taskId    设定文件 
	* @return    返回类型 
	* @throws
	 */
	public void dialogHybhUtils(String taskName, String taskId) {

		final Dialog dialog = new Dialog(mContext, R.style.MyListDialog);
		dialog.setContentView(R.layout.atozdialog);
		TextView dialogTitleTv = (TextView) dialog.findViewById(R.id.dilog_title_name);
		dialogTitleTv.setText("请选择合同编号");
		
		final EditText inputEt = (EditText) dialog.findViewById(R.id.dilog_input_et);
		Button inputBtn = (Button) dialog.findViewById(R.id.dilog_input_btn);
		inputBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(inputEt.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					dialog.dismiss();
					dialogcallback.dialogdo(SHOW_HTBH_FLAG,inputEt.getText().toString().trim());
				}
			}
		});
		
		ListView listView = (ListView) dialog
				.findViewById(R.id.atoz_dialog_listview);
		View headerView = LayoutInflater.from(mContext).inflate(
				R.layout.dialog_list_item, null);
		
		TextView allTextView = (TextView) headerView
				.findViewById(R.id.atoz_list_text);

		SQLiteUtils sql = SQLiteUtils.getInstance(mContext);
		SQLiteDatabase db = sql.getWritableDatabase();

		final List<TaskBean> mTaskBeanList = (List<TaskBean>) sql
				.GetDataByWhere(
						db,
						TaskBean.class,
						"taskId like '"
								+ PreferencesUtils
										.getShareStringData(PreferencesUtils.TASKID)
								+ "'"+ " GROUP BY htbh", null);

		if (null != mTaskBeanList && mTaskBeanList.size() > 0) {
			AtozDialogAdapter mAtozDialogAdapter = new AtozDialogAdapter(
					mContext, mTaskBeanList,SHOW_HTBH_FLAG);
			listView.setAdapter(mAtozDialogAdapter);
			if (null != mAtozDialogAdapter) {
				mAtozDialogAdapter.notifyDataSetChanged();
			}
		}
		dialog.show();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				reHybh = mTaskBeanList.get(arg2).getHtbh();
				dialog.dismiss();
				dialogcallback.dialogdo(SHOW_HTBH_FLAG,reHybh);
			}

		});
	}

	class ViewHolder {
		TextView text;

	}

	class AtozDialogAdapter extends BaseAdapter {
		List cData;
		private LayoutInflater inflater;
		int flag = 0;
		public AtozDialogAdapter(Context context, List arrayList,int flag) {
			this.cData = arrayList;
			this.inflater = LayoutInflater.from(context);
			this.flag = flag;
		}

		@Override
		public int getCount() {
			return cData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.dialog_list_item, null);
				holder.text = (TextView) convertView
						.findViewById(R.id.atoz_list_text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			switch (flag) {
			case SHOW_HTBH_FLAG:
				holder.text.setText(((TaskBean)cData.get(position)).getHtbh());
				break;
			case SHOW_QCSBMC_FLAG:
				holder.text.setText(((ContractDetailBean)cData.get(position)).getQcsbmc());
				break;
			case SHOW_QCSBDM_FLAG:
				holder.text.setText(((ContractDetailBean)cData.get(position)).getQcsbdm());
				break;
			case SHOW_GGXH_FLAG:
				holder.text.setText(((ContractDetailBean)cData.get(position)).getGgxh());
				break;
			case SHOW_CZDW_FLAG:
				holder.text.setText(((TaskBean)cData.get(position)).getCzdw());
				break;
			case SHOW_JSYQ_FLAG:
				holder.text.setText(((CheckDetailBean)cData.get(position)).getJsyq());
				break;
			case SHOW_JYXM_FLAG:
				holder.text.setText(((CheckDetailBean)cData.get(position)).getJyxm());
				break;

			default:
				break;
			}
			return convertView;

		}

	}

	DiaLogCallback dialogcallback;

	public interface DiaLogCallback {
		public void dialogdo(int flag,String str);
	}

	public DiaLogCallback getDialogcallback() {
		return dialogcallback;
	}

	public void setDialogcallback(DiaLogCallback dialogcallback) {
		this.dialogcallback = dialogcallback;
	}

}
