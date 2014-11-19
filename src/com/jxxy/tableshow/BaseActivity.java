package com.jxxy.tableshow;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jxxy.tableshow.activity.ContractListActivity;
import com.jxxy.tableshow.adapter.ContractDetailsAdapter;
import com.jxxy.tableshow.adapter.ContractListAdapter;
import com.jxxy.tableshow.bean.TaskBean;
import com.jxxy.tableshow.utils.PreferencesUtils;
import com.jxxy.tableshow.utils.SQLiteUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Title: BaseActivity.java
 * @Package com.orientscape.treespurchase.body
 * @Description: activity父类
 * @author LiLong
 * @date 
 * @version V1.0
 */
public class BaseActivity extends Activity {
	/* dialog状态 */
	/**
	 * 退出对话框
	 */
	private static final int EXIT_DIALOG = 0x01;
	/**
	 * 没有网络提示对话框
	 */
	private static final int NONETWORK_DIALOG = 0x02;
	/**
	 * 编辑苗圃信息对话框
	 */
	private static final int EDITNURSERY_DIALOG = 0x03;

	/**
	 * pHandler状态
	 */
	private static final int EXIT_HANDLER = 0x0111;// 退出对话框

	private static final char SPACE = 0x20;

	/* 用户状态 */
	public static final String isLogIn = "";// 是否已经登录
	public static final String QYBH = "";// 企业编号
	public static final String HYBH = "";// 会员编号

	protected Resources pRes; // 资源对象
	private Toast pToast;
	
	Handler pHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case EXIT_HANDLER:
				// Context context = (Context)msg.obj;
				// exitApp(context);
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		ExitManager.getInstance().addActivity(this);
		// ShareData.init(this);
		pRes = getResources();
	}

	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case EXIT_DIALOG:

			break;
		case NONETWORK_DIALOG:

			break;

		case EDITNURSERY_DIALOG:

			break;
		}
		return dialog;
	}

	/**
	 * 
	 * @Description 显示Toast，防止一直点击弹出Toast

	 */
	public void showToast(String text, int showType) {
		if (pToast != null) {
			pToast.cancel();
			pToast.setText(text);
			pToast.setDuration(showType);
		} else {
			pToast = Toast.makeText(this, text, showType);
		}
		pToast.show();
	}

	/**
	 * 
	 * @Description 不防止一直点击弹出Toast

	 */
	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * @Title: isNull
	
	 */
	public boolean isNull(String str) {
		if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Description 判断字符串是否为空

	 */
	public boolean isString(String str) {
		return !isNull(str);
	}

	/**
	 * 
	 * @Description 从资源获取字符串
	]
	 */
	public String pGetString(int resId) {
		return pRes.getString(resId);
	}

	/**
	 * 
	 * @Description 从EditText 获取字符串
	 
	 */
	public String getEditTextString(EditText editText) {
		return editText.getText().toString();
	}

	/**
	 * 
	 * @Description 显示或者隐藏view
	
	 */
	public void setViewState(View v, boolean isShow) {
		if (isShow) {
			v.setVisibility(View.VISIBLE);
		} else {
			v.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * @Description 从当前activity跳转到目标activity, 如果目标activity曾经打开过,就重新展现,
	 *              如果从来没打开过,就新建一个打开
	 * 
	 */
	public void gotoActivity(Class<?> cls) {
		Intent intent;
		intent = new Intent(this, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}


	public void gotoExtraActivity(Class<?> cls, Bundle bundle, String key) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null && key != null)
			intent.putExtra(key, bundle);
		startActivity(intent);
	}

	/**
	 * @Description 带参数跳转activity
	 * @param cls
	 * @param bundle
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public void gotoExtraActivity(Class<?> cls, Bundle bundle) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * @Description 跳转activity 并返回数据
	 * @param cls
	 *            准备跳转的类
	 * @param requestCode
	 *            请求码，用来匹配是哪个类的跳转，用来判断返回的方位标识
	 * @param bundle
	 *            传递值,可为空
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public void gotoActivityForResult(Class<?> cls, int requestCode,
			Bundle bundle) {

		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/**
	 * 
	 * @Description 执行退出App操作
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 * @param context
	 */
	protected void executeExitApp(Context context) {
		pHandler.sendMessage(pHandler.obtainMessage(EXIT_HANDLER, context));
	}

	protected String getValueOf(int flag) {
		return String.valueOf(flag);
	}

	/**
	 * @Description 去除空格
	 * @param s
	 * @return
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public String removeAllSpace(String s) {
		String endString = "";
		StringBuilder builder = new StringBuilder(endString);
		int len = s.length();
		for (int i = 0; i < len; i++) {
			// 获得字符
			char c = s.charAt(i);
			// 如果该字符不是空格
			if (c != SPACE) {
				builder.append(String.valueOf(c));
			}
		}
		return builder.toString();
	}

	/**
	 * @Description 该字符串是否包含中文字符
	 * @param str
	 * @return 包含中文返回true ，不包含返回false
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public boolean isChinese(String str) {
		if ((str != null) && (!"".equals(str))) {
			char[] ch = str.toCharArray();
			int len = ch.length;
			for (int i = 0; i < len; i++) {
				if (isChinese(ch[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @Description 该字符是否包含中文字符
	 * @param c
	 * @return 包含中文返回true ，不包含返回false
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * @Description 判断文本是什么类型的
	 * @param str
	 * @return
	 * @author LiLong
	 * @date 
	 * @modified by
	 * @update-time 
	 */
	public String judgeStrType(String str) {
		String retrunStr = "";
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(str);
		if (m.matches()) {
			retrunStr = "数字";
		}
		p = Pattern.compile("[a-zA-Z]");
		m = p.matcher(str);
		if (m.matches()) {
			retrunStr = "字母";
		}
		p = Pattern.compile("[\u4e00-\u9fa5]");
		m = p.matcher(str);
		if (m.matches()) {
			retrunStr = "汉字";
		}
		p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）_——+|{}【】‘；：”“’。，、？]");
		m = p.matcher(str);
		if(m.matches()){
			retrunStr = "符号";
		}
		return retrunStr;
	}
}
