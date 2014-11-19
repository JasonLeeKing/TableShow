package com.jxxy.tableshow.utils;

import android.util.Log;

/**
 * log 工�?�类
 * 
 * @author lilong
 * 
 */
public final class LogUtils {

	private static boolean sIsLogEnabled = true;// ?????????�?LOG

	private static String sApplicationTag = "MYTAG";// LOG�?�?TAG

	private static final String TAG_CONTENT_PRINT = "%s:%s.%s:%d";

	private static StackTraceElement getCurrentStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];

	}

	// ??????LOG
	public static void trace() {
		if (sIsLogEnabled) {
			android.util.Log.d(sApplicationTag,
					getContent(getCurrentStackTraceElement()));
		}
	}

	// ??��??LOG
	private static String getContent(StackTraceElement trace) {
		return String.format(TAG_CONTENT_PRINT, sApplicationTag,
				trace.getClassName(), trace.getMethodName(),
				trace.getLineNumber());
	}

	/**
	 * ?????��??�?TAG???LOG
	 */
	public static void traceStack() {
		if (sIsLogEnabled) {
			traceStack(sApplicationTag, android.util.Log.ERROR);
		}
	}

	/**
	 * ??????Log�????�???��??信�??
	 */
	public static void traceStack(String tag, int priority) {

		if (sIsLogEnabled) {
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			android.util.Log.println(priority, tag, stackTrace[4].toString());
			StringBuilder str = new StringBuilder();
			String prevClass = null;
			for (int i = 5; i < stackTrace.length; i++) {
				String className = stackTrace[i].getFileName();
				int idx = className.indexOf(".java");
				if (idx >= 0) {
					className = className.substring(0, idx);
				}
				if (prevClass == null || !prevClass.equals(className)) {

					str.append(className.substring(0, idx));

				}
				prevClass = className;
				str.append(".").append(stackTrace[i].getMethodName())
						.append(":").append(stackTrace[i].getLineNumber())
						.append("->");
			}
			android.util.Log.println(priority, tag, str.toString());
		}
	}

	/**
	 * ???�?TAG??????�????容�????��??
	 */
	public static void d(String tag, String msg) {
		if (sIsLogEnabled) {
			Log.d(tag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * �?�?TAG?????��?????容�????��??
	 */
	public static void d(String msg) {
		if (sIsLogEnabled) {
			Log.d(sApplicationTag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	// �???��??�?�????�???��?��????��??�????以�??�?�????�?级�??Debugger
	
	public static void i(String tag, String msg) {
		if (sIsLogEnabled) {
			Log.i(tag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void i(String msg) {
		if (sIsLogEnabled) {
			Log.i(sApplicationTag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void w(String tag, String msg){
		if (sIsLogEnabled){
			Log.w(tag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void w(String msg) {
		if (sIsLogEnabled) {
			Log.w(sApplicationTag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (sIsLogEnabled) {
			Log.e(tag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void e(String msg) {
		if (sIsLogEnabled) {
			Log.e(sApplicationTag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}
	
	
	public static void v(String tag, String msg) {
		if (sIsLogEnabled) {
			Log.v(tag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}

	public static void v(String msg) {
		if (sIsLogEnabled) {
			Log.v(sApplicationTag, getContent(getCurrentStackTraceElement())
					+ ">" + msg);
		}
	}
	
	/**
	* @Description ??�代�?认�??system.out.println
	* @author ll
	* @date 2013-6-6
	* @modified by
	* @update-time 2013-6-6 �????05:43:26
	 */
	public static void sysPrintln(Object msg){
		if (sIsLogEnabled) {
			System.out.println(getContent(getCurrentStackTraceElement())+"->"+msg.toString());
		}
	}
}