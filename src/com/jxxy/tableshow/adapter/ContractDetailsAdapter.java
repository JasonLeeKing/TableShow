package com.jxxy.tableshow.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.adapter.ContractListAdapter.ViewHolder;
import com.jxxy.tableshow.bean.TaskBean;

public class ContractDetailsAdapter extends BaseAdapter {

	public List<TaskBean> mList;
	public Context mContext;
	public LayoutInflater inflater;

	public static class ViewHolder {
		 TextView tv_contract_details_xh;
		 TextView tv_contract_details_htjyysdw;
		 TextView tv_contract_details_htbh;
		 TextView tv_contract_details_czdw;
		 TextView tv_contract_details_xs;
		 TextView tv_contract_details_js;
		 TextView tv_contract_details_htje;
		 TextView tv_contract_details_cpmc;
		 TextView tv_contract_details_jhqx;
		 TextView tv_contract_details_bz;
	};

	public ContractDetailsAdapter(Context context, List<TaskBean> list) {
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
			convertView = inflater.inflate(R.layout.contract_details_item, null);
			holder = new ViewHolder();
			holder.tv_contract_details_xh = (TextView) convertView.findViewById(R.id.tv_contract_details_xh);
			holder.tv_contract_details_htjyysdw = (TextView)convertView. findViewById(R.id.tv_contract_details_htjyysdw);
			holder.tv_contract_details_htbh = (TextView) convertView.findViewById(R.id.tv_contract_details_htbh);
			holder.tv_contract_details_czdw = (TextView) convertView.findViewById(R.id.tv_contract_details_czdw);
			holder.tv_contract_details_xs = (TextView) convertView.findViewById(R.id.tv_contract_details_xs);
			holder.tv_contract_details_js = (TextView) convertView.findViewById(R.id.tv_contract_details_js);
			holder.tv_contract_details_htje = (TextView) convertView.findViewById(R.id.tv_contract_details_htje);
			holder.tv_contract_details_cpmc = (TextView) convertView.findViewById(R.id.tv_contract_details_cpmc);
			holder.tv_contract_details_jhqx = (TextView) convertView.findViewById(R.id.tv_contract_details_jhqx);
			holder.tv_contract_details_bz = (TextView) convertView.findViewById(R.id.tv_contract_details_bz);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

			holder.tv_contract_details_xh.setText(mList.get(positition).getXh());
			holder.tv_contract_details_htjyysdw.setText(mList.get(positition).getHtjyysdw());
			holder.tv_contract_details_htbh.setText(mList.get(positition).getHtbh());
			holder.tv_contract_details_czdw.setText(mList.get(positition).getCzdw());
			holder.tv_contract_details_xs.setText(mList.get(positition).getXs());
			holder.tv_contract_details_js.setText(mList.get(positition).getJs());
			holder.tv_contract_details_htje.setText(mList.get(positition).getHtje());
			holder.tv_contract_details_cpmc.setText(mList.get(positition).getCpmc());
			holder.tv_contract_details_jhqx.setText(mList.get(positition).getJfqx());
			holder.tv_contract_details_bz.setText(mList.get(positition).getBz());

		return convertView;
	}

}
