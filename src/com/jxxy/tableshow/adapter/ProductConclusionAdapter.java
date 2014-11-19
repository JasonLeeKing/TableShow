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
import com.jxxy.tableshow.adapter.ProductRecordAdapter.ViewHolder;
import com.jxxy.tableshow.bean.InspectionRecordBean;

public class ProductConclusionAdapter extends BaseAdapter {

	public List<InspectionRecordBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	public static int SELECT = -1 ;
	
	public static class ViewHolder {
		TextView product_conclusion_list_jyxm;
		TextView product_conclusion_list_jsyq;
		TextView product_conclusion_list_jcjg;
		TextView product_conclusion_list_jyjl;
		LinearLayout product_conclusion_linear;
	};

	public ProductConclusionAdapter(Context context, List<InspectionRecordBean> list) {
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
			convertView = inflater.inflate(R.layout.product_conclusion_item, null);
			holder = new ViewHolder();
			holder.product_conclusion_list_jyxm = (TextView) convertView
					.findViewById(R.id.product_conclusion_list_jyxm);
			holder.product_conclusion_list_jsyq = (TextView) convertView
					.findViewById(R.id.product_conclusion_list_jsyq);
			
			holder.product_conclusion_list_jcjg = (TextView) convertView
					.findViewById(R.id.product_conclusion_list_jcjg);
			holder.product_conclusion_list_jyjl = (TextView) convertView
					.findViewById(R.id.product_conclusion_list_jyjl);
			holder.product_conclusion_linear = (LinearLayout) convertView.findViewById(R.id.product_conclusion_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(SELECT == positition){
			holder.product_conclusion_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
		}else{
			holder.product_conclusion_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));
		}
		
		holder.product_conclusion_list_jyxm.setText(mList.get(positition).getJyxm());
		holder.product_conclusion_list_jsyq.setText(mList.get(positition).getJsyq());
		holder.product_conclusion_list_jcjg.setText(mList.get(positition).getJyjg());
		holder.product_conclusion_list_jyjl.setText(mList.get(positition).getJyjl());
		return convertView;
	}

}
