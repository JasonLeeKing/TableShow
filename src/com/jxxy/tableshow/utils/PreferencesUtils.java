package com.jxxy.tableshow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 轻量存储
* @ClassName: PreferencesUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiLong
* @date 2014-7-31 下午03:45:33 
*
 */
public class PreferencesUtils {

	private static final String SP_NAME = "table_sharedata";
	private static Context context;
	private static SharedPreferences sp;
	
	public final static String TASKID= "taskId";
	public final static String TASKNAME= "taskName";
	public final static String HTBH= "htbh";
	public final static String UPDATA_STATE= "updataState";
	public final static String REHTBH = "reHtbh";
	
	
	public static void init(Context initContext){
		// TODO Auto-generated method stub
		if (sp == null) {
			context = initContext;
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
	}

	public static int getShareIntData(String key) {
		return sp.getInt(key, 0);
	}

	public static void setShareIntData(String key, int value) {
		sp.edit().putInt(key, value).commit();
	}

	public static String getShareStringData(String key) {
		return sp.getString(key, "");
	}

	public static void setShareStringData(String key, String value) {
		sp.edit().putString(key, value).commit();
	}

	public static boolean getShareBooleanData(String key){
		return sp.getBoolean(key, false);
	}

	public static void setShareBooleanData(String key, boolean value){
		sp.edit().putBoolean(key, value).commit();
	}

	public static float getShareFloatData(String key) {
		return sp.getFloat(key, 0f);
	}

	public static void setShareFloatData(String key, float value) {
		sp.edit().putFloat(key, value).commit();
	}

	public static long getShareLongData(String key) {
		return sp.getLong(key, 0l);
	}

	public static void setShareLongData(String key, long value) {
		sp.edit().putLong(key, value).commit();
	}

	public static void deleShareData(String key) {
		sp.edit().remove(key).commit();
	}
	
	public static void clearShareData() {
		sp.edit().clear().commit();
	}
}
