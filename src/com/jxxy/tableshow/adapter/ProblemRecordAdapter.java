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
import com.jxxy.tableshow.bean.ProductProblemBean;

/**
* @ClassName: ProblemRecordAdapter 
* @Description: 产品质量技术问题表适配器 
* @author LiLong
* @date 2014-6-28 下午3:34:57 
*
 */
public class ProblemRecordAdapter extends BaseAdapter {

	public static int SELECT = -1;
	public List<ProductProblemBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
    
	public static class ViewHolder {
		TextView problem_record_item_htbh;
		TextView problem_record_item_cpbh;
		TextView problem_record_item_cpxhmc;
		
		TextView problem_record_item_czdw;
		TextView problem_record_item_wtfsrq;
		TextView problem_record_item_wtfsdd;
		LinearLayout problem_record_linear;
		
	};

	public ProblemRecordAdapter(Context context, List<ProductProblemBean> list) {
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
			convertView = inflater.inflate(R.layout.problem_record_list_item, null);
			holder = new ViewHolder();
			holder.problem_record_item_htbh = (TextView) convertView
					.findViewById(R.id.problem_record_item_htbh);
			holder.problem_record_item_cpbh = (TextView) convertView
					.findViewById(R.id.problem_record_item_cpbh);
			holder.problem_record_item_cpxhmc = (TextView) convertView
					.findViewById(R.id.problem_record_item_cpxhmc);
			
			holder.problem_record_item_czdw = (TextView) convertView
					.findViewById(R.id.problem_record_item_czdw);
			holder.problem_record_item_wtfsrq = (TextView) convertView
					.findViewById(R.id.problem_record_item_wtfsrq);
			holder.problem_record_item_wtfsdd = (TextView) convertView
					.findViewById(R.id.problem_record_item_wtfsdd);
			
			holder.problem_record_linear = (LinearLayout) convertView.findViewById(R.id.problem_record_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		if(positition == SELECT)
		{
			holder.problem_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
			
		}else
		{
			holder.problem_record_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));	
		}
			holder.problem_record_item_htbh.setText(mList.get(positition).getHtbh());
			holder.problem_record_item_cpbh.setText(mList.get(positition).getCpbh());
			holder.problem_record_item_cpxhmc.setText(mList.get(positition).getQcsbmc());
			
			holder.problem_record_item_czdw.setText(mList.get(positition).getCzdw());
			holder.problem_record_item_wtfsrq.setText(mList.get(positition).getWtfsrq());
			holder.problem_record_item_wtfsdd.setText(mList.get(positition).getWtfsdd());
			

		return convertView;
	}

}
