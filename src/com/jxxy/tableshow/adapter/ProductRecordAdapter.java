package com.jxxy.tableshow.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.InspectionRecordBean;

public class ProductRecordAdapter  extends BaseAdapter {

	public List<InspectionRecordBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	public static int SELECT =-1; 
	
	public static class ViewHolder {
		TextView product_record_list_cpxh;
		TextView product_record_list_htbh;
		TextView product_record_list_dgsl;
		
		TextView product_record_list_sjcpbh;
		TextView product_record_list_bctjsl;
		TextView product_record_list_tjdw;
		TextView product_record_list_scrq;
		TextView product_record_list_jyrq;
		LinearLayout product_record_linear;
	};

	public ProductRecordAdapter(Context context, List<InspectionRecordBean> list) {
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
	public View getView(int positition, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.product_record_item, null);
			holder = new ViewHolder();
			holder.product_record_list_cpxh = (TextView) convertView
					.findViewById(R.id.product_record_list_cpxh);
			holder.product_record_list_htbh = (TextView) convertView
					.findViewById(R.id.product_record_list_htbh);
			
			holder.product_record_list_dgsl = (TextView) convertView
					.findViewById(R.id.product_record_list_dgsl);
			
			holder.product_record_list_sjcpbh = (TextView) convertView
					.findViewById(R.id.product_record_list_sjcpbh);
			holder.product_record_list_bctjsl = (TextView) convertView
					.findViewById(R.id.product_record_list_bctjsl);
			holder.product_record_list_tjdw = (TextView) convertView
					.findViewById(R.id.product_record_list_tjdw);
			holder.product_record_list_scrq = (TextView) convertView
					.findViewById(R.id.product_record_list_scrq);
			holder.product_record_list_jyrq = (TextView) convertView
					.findViewById(R.id.product_record_list_jyrq);
			
			holder.product_record_linear = (LinearLayout) convertView.findViewById(R.id.product_record_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		if(positition == SELECT)
		{
			holder.product_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
		}else
		{
			holder.product_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));
		}
		
		holder.product_record_list_cpxh.setText(mList.get(positition).getQcsbmc());
			holder.product_record_list_htbh.setText(mList.get(positition).getHtbh());
			holder.product_record_list_dgsl.setText(mList.get(positition).getDgsl());
			
			holder.product_record_list_sjcpbh.setText(mList.get(positition).getSjcpbh());
			holder.product_record_list_bctjsl.setText(mList.get(positition).getBctjsl());
			holder.product_record_list_tjdw.setText(mList.get(positition).getCzdw());
			holder.product_record_list_scrq.setText(mList.get(positition).getCppch());
			holder.product_record_list_jyrq.setText(mList.get(positition).getJyrq());

		return convertView;
	}

}
