package com.jxxy.tableshow.adapter;

import java.util.List;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.bean.ContractBean;
import com.jxxy.tableshow.bean.TaskBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContractListAdapter extends BaseAdapter {

	public List<TaskBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	
	public static int SELECTED = -1;//记录所选项 
	
	public static class ViewHolder {
		TextView contractCode;

	};

	public ContractListAdapter(Context context, List<TaskBean> list) {
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
			convertView = inflater.inflate(R.layout.contract_list_item, null);
			holder = new ViewHolder();
			holder.contractCode = (TextView) convertView
					.findViewById(R.id.contract_item_code);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

			if (SELECTED == positition) {
				holder.contractCode.setTextColor(mContext.getResources().getColor(R.color.blue));
			}else{
				holder.contractCode.setTextColor(mContext.getResources().getColor(R.color.white));
			}
			holder.contractCode.setText(mList.get(positition).getTaskName());

		return convertView;
	}

}
