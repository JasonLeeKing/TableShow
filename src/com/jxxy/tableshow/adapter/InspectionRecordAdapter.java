package com.jxxy.tableshow.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.AccountRenderedBean;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.ProductProblemBean;


public class InspectionRecordAdapter  extends BaseAdapter {

	public List<AccountRenderedBean> mList;
	public Context mContext;
	public LayoutInflater inflater;

	public static class ViewHolder {
		TextView ispection_bill_list_htbh;
		TextView ispection_bill_list_cpbh;
		
		TextView ispection_bill_list_tjdw;
		TextView ispection_bill_list_tjsj;
		TextView ispection_bill_list_xmmc;
		TextView ispection_bill_list_xmdh;
		TextView ispection_bill_list_tjcs;
		TextView ispection_bill_list_cpmc;
		TextView ispection_bill_list_tjsl;
		TextView ispection_bill_list_cppc;
		TextView ispection_bill_list_xycs;
		TextView ispection_bill_list_xybz;
		
	};

	public InspectionRecordAdapter(Context context, List<AccountRenderedBean> list) {
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
			convertView = inflater.inflate(R.layout.inspection_bill_list_item, null);
			holder = new ViewHolder();
			holder.ispection_bill_list_htbh = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_htbh);
			holder.ispection_bill_list_cpbh = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_cpbh);
			
			holder.ispection_bill_list_tjdw = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_tjdw);
			holder.ispection_bill_list_tjsj = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_tjsj);
			holder.ispection_bill_list_xmmc = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_xmmc);
			holder.ispection_bill_list_xmdh = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_xmdh);
			holder.ispection_bill_list_tjcs = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_tjcs);
			holder.ispection_bill_list_cpmc = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_cpmc);
			holder.ispection_bill_list_tjsl = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_tjsl);
			holder.ispection_bill_list_cppc = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_cppc);
			holder.ispection_bill_list_xycs = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_xycs);
			holder.ispection_bill_list_xybz = (TextView) convertView
					.findViewById(R.id.ispection_bill_list_xybz);

			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

			holder.ispection_bill_list_htbh.setText(mList.get(positition).getHtbh());
			holder.ispection_bill_list_cpbh.setText(mList.get(positition).getCpbh());
			
			holder.ispection_bill_list_tjdw.setText(mList.get(positition).getTjdw());
			holder.ispection_bill_list_tjsj.setText(mList.get(positition).getTjsj());
			holder.ispection_bill_list_xmmc.setText(mList.get(positition).getXmmc());
			holder.ispection_bill_list_xmdh.setText(mList.get(positition).getXmdh());
			holder.ispection_bill_list_tjcs.setText(mList.get(positition).getTjcs());
			holder.ispection_bill_list_cpmc.setText(mList.get(positition).getCpmc());
			holder.ispection_bill_list_tjsl.setText(mList.get(positition).getTjsl());
			holder.ispection_bill_list_cppc.setText(mList.get(positition).getTjpc());
			holder.ispection_bill_list_xycs.setText(mList.get(positition).getXycs());
			holder.ispection_bill_list_xybz.setText(mList.get(positition).getXybz());
			
		return convertView;
	}

}
