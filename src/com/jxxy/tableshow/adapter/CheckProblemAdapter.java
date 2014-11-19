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
import com.jxxy.tableshow.adapter.ProblemRecordAdapter.ViewHolder;
import com.jxxy.tableshow.bean.ErrorBean;
import com.jxxy.tableshow.bean.ProductProblemBean;

/**
 * 检验问题记录表
* @ClassName: CheckProblemBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-10-8 上午4:46:04 
*
 */
public class CheckProblemAdapter extends BaseAdapter {

	public List<ErrorBean> mList;
	public Context mContext;
	public LayoutInflater inflater;
	public static int SELECT = -1;
	
	public static class ViewHolder {
		TextView check_problem_item_htbh;
		TextView check_problem_item_bh;
		TextView check_problem_item_txrq;
		TextView check_problem_item_jyyszfxwt;
		TextView check_problem_item_cpbh;
		TextView check_problem_item_ysryqz;
		TextView check_problem_item_czdwptrqz;
		TextView check_problem_item_qcsbmc;
		TextView check_problem_item_ggxh;
		TextView check_problem_item_qcsbdm;
		LinearLayout check_problem_linear;
	};

	public CheckProblemAdapter(Context context, List<ErrorBean> list) {
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
			convertView = inflater.inflate(R.layout.check_problem_item, null);
			holder = new ViewHolder();
			holder.check_problem_item_htbh = (TextView) convertView
					.findViewById(R.id.check_problem_item_htbh);
			holder.check_problem_item_bh = (TextView) convertView
					.findViewById(R.id.check_problem_item_bh);
			holder.check_problem_item_txrq = (TextView) convertView
					.findViewById(R.id.check_problem_item_txrq);
			holder.check_problem_item_jyyszfxwt = (TextView) convertView
					.findViewById(R.id.check_problem_item_jyyszfxwt);
			holder.check_problem_item_cpbh = (TextView) convertView
					.findViewById(R.id.check_problem_item_cpbh);
			holder.check_problem_item_ysryqz = (TextView) convertView
					.findViewById(R.id.check_problem_item_ysryqz);
			holder.check_problem_item_czdwptrqz = (TextView) convertView
					.findViewById(R.id.check_problem_item_czdwptrqz);
			holder.check_problem_item_qcsbmc = (TextView) convertView
					.findViewById(R.id.check_problem_item_qcsbmc);
			holder.check_problem_item_ggxh = (TextView) convertView
					.findViewById(R.id.check_problem_item_ggxh);
			holder.check_problem_item_qcsbdm = (TextView) convertView
					.findViewById(R.id.check_problem_item_qcsbdm);
			holder.check_problem_linear = (LinearLayout) convertView.findViewById(R.id.check_problem_linear);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		if(SELECT == positition){
			holder.check_problem_linear.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
		}else{
			holder.check_problem_linear.setBackgroundColor(mContext.getResources().getColor(R.color.black));
		}
			holder.check_problem_item_htbh.setText(mList.get(positition).getHtbh());
			holder.check_problem_item_bh.setText(mList.get(positition).getBh());
			holder.check_problem_item_txrq.setText(mList.get(positition).getTxrq());
			holder.check_problem_item_jyyszfxwt.setText(mList.get(positition).getJyyszfxwt());
			holder.check_problem_item_cpbh.setText(mList.get(positition).getCpbh());
			holder.check_problem_item_ysryqz.setText(mList.get(positition).getYsryqz());
			holder.check_problem_item_czdwptrqz.setText(mList.get(positition).getCzdwptrqz());
			holder.check_problem_item_qcsbmc.setText(mList.get(positition).getQcsbmc());
			holder.check_problem_item_ggxh.setText(mList.get(positition).getGgxh());
			holder.check_problem_item_qcsbdm.setText(mList.get(positition).getQcsbdm());
			
		return convertView;
	}

}
