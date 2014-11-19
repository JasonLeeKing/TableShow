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
import com.jxxy.tableshow.adapter.ProductConclusionAdapter.ViewHolder;
import com.jxxy.tableshow.bean.InspectionRecordBean;
import com.jxxy.tableshow.bean.SuperviseBean;

public class SuperviseConditionAdapter extends BaseAdapter {

	public List<SuperviseBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	public static int SELECT = -1 ;
	
	public static class ViewHolder {
		
		TextView supervise_condition_item_jdxm;
		TextView supervise_condition_item_jdyq;
		TextView supervise_condition_item_jdnr;
		TextView supervise_condition_item_jdjg;
		LinearLayout supervise_condition_linear;
	};

	public SuperviseConditionAdapter(Context context, List<SuperviseBean> list) {
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
			convertView = inflater.inflate(R.layout.supervise_condition_item, null);
			holder = new ViewHolder();
			holder.supervise_condition_item_jdxm = (TextView) convertView
					.findViewById(R.id.supervise_condition_item_jdxm);
			holder.supervise_condition_item_jdyq = (TextView) convertView
					.findViewById(R.id.supervise_condition_item_jdyq);
			
			holder.supervise_condition_item_jdnr = (TextView) convertView
					.findViewById(R.id.supervise_condition_item_jdnr);
			holder.supervise_condition_item_jdjg = (TextView) convertView
					.findViewById(R.id.supervise_condition_item_jdjg);
			holder.supervise_condition_linear =(LinearLayout) convertView.findViewById(R.id.supervise_condition_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(SELECT == positition){
			holder.supervise_condition_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
		}else{
			holder.supervise_condition_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));
		}
		holder.supervise_condition_item_jdxm.setText(mList.get(positition).getJdxm());
		holder.supervise_condition_item_jdyq.setText(mList.get(positition).getJdyq());
		holder.supervise_condition_item_jdnr.setText(mList.get(positition).getJdnr());
		holder.supervise_condition_item_jdjg.setText(mList.get(positition).getJdjg());
		return convertView;
	}

}
