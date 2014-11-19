package com.jxxy.tableshow;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;


/**
* 迭代退出
* @author lilong
*
*/
public class ExitManager extends Application{

	private AsyncTask<?, ?, ?>[] tasks;
	Context mContext;

	protected void addTask(AsyncTask<?, ?, ?>... tasks) {
		this.tasks = tasks;
	}

	private List<Activity> activityList = new LinkedList<Activity>();
	private static ExitManager instance;

	private ExitManager() {
	}
	
	public static ExitManager getInstance() {
		if (instance == null) {
			instance = new ExitManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		if(!activityList.contains(activity)){
			activityList.add(activity);
			System.out.println("添加：activity");
			System.out.println("activityList.size():"+	activityList.size());
		}else{
			System.out.println("已存在不重复追加activity");
			System.out.println("activityList.size():"+	activityList.size());
		}
		
	}
	
	public void removeActivity(Activity activity){
		if(activityList.contains(activity)){
			activityList.remove(activity);
			System.out.println("移除:activity");
			System.out.println("activityList.size():"+	activityList.size());
		}else{
			System.out.println("不存在activity 移除失败");
			System.out.println("activityList.size():"+	activityList.size());
		}
	}

	public void exit() {

		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}	
		}
		int id = android.os.Process.myPid();
		if (id != 0) {
			android.os.Process.killProcess(id);
		}
	}

}
