package com.jxxy.tableshow.view;

import com.jxxy.tableshow.R;
import com.jxxy.tableshow.imp.OnTopBarListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopBar extends LinearLayout implements OnClickListener{
	private Context mContext;
	private OnTopBarListener mOnTopBarListener;
	private ImageView topLeftButton, topRightButton;
	private TextView topLeftText;
	private TextView topRightText;
	private TextView topCenterText;
	public TopBar(Context context){
		super(context);
		this.mContext = context;
		initView(mContext);
	}
	public TopBar(Context context, AttributeSet attrs){
		super(context, attrs);
		this.mContext = context;
		initView(mContext);
	}
	private void initView(Context context){
		LayoutInflater.from(context).inflate(R.layout.topbar, this);
		
		topLeftButton = (ImageView) findViewById(R.id.topbar_left_button);
		topLeftButton.requestFocus();
	
		topRightButton = (ImageView) findViewById(R.id.topbar_right_button);
		topRightButton.requestFocus();
		
		topLeftText = (TextView) findViewById(R.id.topbar_left_text);
		topRightText =  (TextView) findViewById(R.id.topbar_right_text);
		topCenterText = (TextView) findViewById(R.id.topbar_center_text);
		initEvent();
	}
	private void initEvent(){
		topLeftButton.setOnClickListener(this);
		topRightButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.topbar_left_button:
			getOnTopBarListener().onTopBarLeftBtn();
			break;
		case R.id.topbar_right_button:
			getOnTopBarListener().onTopBarRightBtn();
			break;
		}
	}
	/**
	 * 设置顶�?�左边�?????
	 * @param topLeftText
	 */
	public void setTopLeftText(String topLeftText){		 
		 this.topLeftText.setText(topLeftText);
	} 
	/**
	 * ??��??顶�?�左边�?????
	 * @param getLeftText
	 */
	public String getTopLeftText(){
		return (String) topLeftText.getText();
	}

    /**
    * @Title: setLeftTextViewSize
    * @author fujijin
    * @Description: 设置�?边�??�?大�??
    * @param @param size    ??????
    * @return void    �????类�??
    * @throws
     */
    public void setLeftTextViewSize(float size){
    	topLeftText.setTextSize(size);
    }
    public void setLeftImage(int id){
    	topLeftButton.setImageResource(id);
    	invalidate();
    }
    /**
    * @Description 设置�?边�??�???????
    * @param isShow
    * @author ll
    * @date 2013-8-28
    * @modified by ll  topLeftButton??�为topLeftText
    * @update-time 2013-8-28 �????04:52:32
     */
    public void setLeftTextViewIsShow(boolean isShow){
    	if(isShow){
    		topLeftText.setVisibility(View.VISIBLE);	
    	}else {
    		topLeftText.setVisibility(View.GONE);
		}
    }
    public void setLeftImageIsShow(boolean IsShow){
    	if(IsShow){
    		topLeftButton.setVisibility(View.VISIBLE);
    	}else {
    		topLeftButton.setVisibility(View.INVISIBLE);
		}
    }
	/**
	 * 设置顶�?��?�边??????
	 * @param topRightText
	 */
	public void setTopRightText(String topRightText){
		this.topRightText.setText(topRightText);
	}
	/**
	 * 
	 * @Description 设置??�边??????大�??
	 * @author fujijin
	 * @date 2013-8-22
	 * @modified by
	 * @update-time 2013-8-22 �????4:37:14  
	 * @param size 
	 */
	public void setTopRightTextSize(float size){
		topRightText.setTextSize(size);
	}
	/**
	 * ??��??顶�?��?�边??????
	 * @param getLeftText
	 */
	public String getTopRightText(){
		return (String) topRightText.getText();
	}
	/**
	 * 设置顶�?��?�边??��??
	 * @param id
	 */
	public void setTopRightImage(int id){
		topRightButton.setImageResource(id);
		invalidate();
	}
	public void setRightTextViewIsShow(boolean isShow){
		if(isShow){
			topRightText.setVisibility(View.VISIBLE);
		}else {
			topRightText.setVisibility(View.GONE);
		}
	}
	public void setRightImageIsShow(boolean IsShow){
		if(IsShow){
			topRightButton.setVisibility(View.VISIBLE);
		}else{
			topRightButton.setVisibility(View.INVISIBLE);
		}
	}
	/**
	 * 设置顶�?��??�??????? 
	 * @param topText
	 */
	public void setTopBarCenterText(String topcenterText){
	    topCenterText.setText(topcenterText);    
		invalidate();
	}
	/**
	 * 
	 * @Description ??��??�?�???????
	 * @author fujijin
	 * @date 2013-8-22
	 * @modified by
	 * @update-time 2013-8-22 �????4:37:42  
	 * @return
	 */
	public String getTopBarCenterText(){
		return (String) topCenterText.getText();
	}
	/**
	 * 
	 * @Description 设置�?�???????�?�?大�??
	 * @author fujijin
	 * @date 2013-8-23
	 * @modified by
	 * @update-time 2013-8-23 �????1:43:05  
	 * @param size
	 */
	public void setTopBarCenterTextSize(float size){
	    topCenterText.setTextSize(size);    
		invalidate();
	}
	public void setOnTopBarListener(OnTopBarListener onTopBarListener){
		this.mOnTopBarListener = onTopBarListener;
	}
	private OnTopBarListener getOnTopBarListener(){
		return mOnTopBarListener;
	}	
}