package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.util.Log;

/**
 * HashMap<Activity, String> 每一个元素分别代表一个Activity和对应的描述，
 * 通过这个集合来管理所有的Activity，可以一键关闭所有的活动
 * 
 * 自从加入了TestPageActivity.java，这个文件就没有必要了。
 * 对于测试程序而言，只需要一个HomePage提供所有测试项的列表，单击列表跳转，完成单项测试就回到HomePage就行，
 * 不需要管理所有活动，但是这个ActivityCollector类可能在其他的地方有用。
 * @author LinTeX9527
 * @since v0.1
 * @see BaseActivity
 */
public class ActivityCollector {

	private final static String TAG_STRING = "ActivityCollector";
	
	public static HashMap<Activity, String> activitySet = new HashMap<Activity, String>();
	
	/**
	 * 添加一个Activity到集合中，并附带说明性文字
	 * @param activity
	 * @param description
	 */
	public static void addActivity(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			Log.d(TAG_STRING, "要添加的Activity:" + activity.toString() + "已经存在");
			return;
		}
		
		activitySet.put(activity, description);
		Log.d(TAG_STRING, "添加Activity:" + activity.toString() + "完成");
	}

	
	/**
	 * 修改某个activity和对应的描述description
	 * @param activity
	 * @param description
	 */
	public static void modifyDescription(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity); // 先清除
			activitySet.put(activity, description);	// 再添加
		} else {
			activitySet.put(activity, description);
		}
		Log.d(TAG_STRING, "修改Activity:" + activity.toString() + "的描述完成。");
	}
	
	/**
	 * 从集合中删除这个Activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
			Log.d(TAG_STRING, "成功remove这个Activity:" + activity.toString());
		}
		Log.d(TAG_STRING, "这个Activity尚未添加到管理中");
	}

	
	/**
	 * 传入活动X对应的description返回对应的活动，否则返回null
	 * @param description
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class<Activity> getActivity(String description){
		if (description != null){
			for (Activity activity: activitySet.keySet()){
				if (activitySet.get(activity) == description){
					return (Class<Activity>) activity.getClass();
				}
			}
		}
		return null;
	}
	
	/**
	 * 关闭所有的Activity，并清空集合
	 */
	public static void finishAll(){
		for (Activity activity: activitySet.keySet()){
			if (!activity.isFinishing()){
				activity.finish();
				Log.d(TAG_STRING, "已经关闭这个 activity");
			}
		}
		activitySet.clear();
		Log.d(TAG_STRING, "关闭所有Activity并清空管理集合");
	}
	
	public static ArrayList<String> getAllDescription(){
		return new ArrayList<String>(activitySet.values());
	}
}
