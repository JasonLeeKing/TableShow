package com.jxxy.tableshow.adapter;

import java.util.List;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ProductRecordAdapter.ViewHolder;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.SuperviseBean;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SuperviseRecordAdapter extends BaseAdapter {

	public List<SuperviseBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	public static int SELECT = -1 ;
	
	public static class ViewHolder {
		TextView supervise_record_list_htbh;
		TextView supervise_record_list_jdsj;
		TextView supervise_record_list_czdw;
		TextView supervise_record_list_czdwptr;
		TextView supervise_record_list_qcsbmc;
		TextView supervise_record_list_jdjl;
		TextView product_record_list_jdr;
		TextView supervise_record_list_ggxh;
		TextView supervise_record_list_qcsbdm;
		LinearLayout supervise_record_linear;
	};

	public SuperviseRecordAdapter(Context context, List<SuperviseBean> list) {
		this.mList = list;
		this.mContext = context;
		inflater = (LayoutInflater) mContext
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int positition, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.supervise_record_item, null);
			holder = new ViewHolder();
			holder.supervise_record_list_htbh = (TextView) convertView
					.findViewById(R.id.supervise_record_list_htbh);
			holder.supervise_record_list_jdsj = (TextView) convertView
					.findViewById(R.id.supervise_record_list_jdsj);
			holder.supervise_record_list_czdw = (TextView) convertView
					.findViewById(R.id.supervise_record_list_czdw);
			holder.supervise_record_list_czdwptr = (TextView) convertView
					.findViewById(R.id.supervise_record_list_czdwptr);
			holder.supervise_record_list_qcsbmc = (TextView) convertView
					.findViewById(R.id.supervise_record_list_qcsbmc);
			holder.supervise_record_list_jdjl = (TextView) convertView
					.findViewById(R.id.supervise_record_list_jdjl);
			holder.product_record_list_jdr = (TextView) convertView
					.findViewById(R.id.supervise_record_list_jdr);
			holder.supervise_record_list_ggxh = (TextView) convertView
					.findViewById(R.id.supervise_record_list_ggxh);
			holder.supervise_record_list_qcsbdm = (TextView) convertView
					.findViewById(R.id.supervise_record_list_qcsbdm);
			holder.supervise_record_linear = (LinearLayout) convertView.findViewById(R.id.supervise_record_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(SELECT == positition){
			holder.supervise_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
		}else{
			holder.supervise_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));
		}
		
		holder.supervise_record_list_htbh.setText(mList.get(positition)
				.getHtbh());
		holder.supervise_record_list_jdsj.setText(mList.get(positition)
				.getJdsj());
		holder.supervise_record_list_czdw.setText(mList.get(positition)
				.getCzdw());
		holder.supervise_record_list_czdwptr.setText(mList.get(positition)
				.getCzdwptr());
		holder.supervise_record_list_qcsbmc.setText(mList.get(positition)
				.getQcsbmc());
		holder.supervise_record_list_jdjl.setText(mList.get(positition)
				.getJdjl());
		holder.product_record_list_jdr.setText(mList.get(positition).getJdr());
		holder.supervise_record_list_ggxh.setText(mList.get(positition)
				.getGgxh());
		holder.supervise_record_list_qcsbdm.setText(mList.get(positition)
				.getQcsbdm());

		return convertView;
	}

}
