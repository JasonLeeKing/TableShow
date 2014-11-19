package com.jxxy.tableshow.adapter;

import com.jxxy.tableshow.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class TableListAdapter extends BaseAdapter {

	String[] tableNameStr = { "产品质量技术问题记录表", "交验单", "产品检验记录" };
	private LayoutInflater inflater;
	
	public TableListAdapter(Context con) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	static class ViewHolder{
		
		TextView tableName;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tableNameStr.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(v == null){
			v = inflater.inflate(R.layout.table_listview_item, null);
			holder = new ViewHolder();
			holder.tableName = (TextView) v.findViewById(R.id.table_text_item);
			v.setTag(holder);
		}else{
			holder = (ViewHolder) v.getTag();
		}
		holder.tableName.setText(tableNameStr[position]);
		return v;
	}

}
