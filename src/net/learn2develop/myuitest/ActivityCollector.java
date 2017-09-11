package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.util.Log;

/**
 * HashMap<Activity, String> ÿһ��Ԫ�طֱ����һ��Activity�Ͷ�Ӧ��������
 * ͨ������������������е�Activity������һ���ر����еĻ
 * 
 * �ԴӼ�����TestPageActivity.java������ļ���û�б�Ҫ�ˡ�
 * ���ڲ��Գ�����ԣ�ֻ��Ҫһ��HomePage�ṩ���в�������б������б���ת����ɵ�����Ծͻص�HomePage���У�
 * ����Ҫ�������л���������ActivityCollector������������ĵط����á�
 * @author LinTeX9527
 * @since v0.1
 * @see BaseActivity
 */
public class ActivityCollector {

	private final static String TAG_STRING = "ActivityCollector";
	
	public static HashMap<Activity, String> activitySet = new HashMap<Activity, String>();
	
	/**
	 * ���һ��Activity�������У�������˵��������
	 * @param activity
	 * @param description
	 */
	public static void addActivity(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			Log.d(TAG_STRING, "Ҫ��ӵ�Activity:" + activity.toString() + "�Ѿ�����");
			return;
		}
		
		activitySet.put(activity, description);
		Log.d(TAG_STRING, "���Activity:" + activity.toString() + "���");
	}

	
	/**
	 * �޸�ĳ��activity�Ͷ�Ӧ������description
	 * @param activity
	 * @param description
	 */
	public static void modifyDescription(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity); // �����
			activitySet.put(activity, description);	// �����
		} else {
			activitySet.put(activity, description);
		}
		Log.d(TAG_STRING, "�޸�Activity:" + activity.toString() + "��������ɡ�");
	}
	
	/**
	 * �Ӽ�����ɾ�����Activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
			Log.d(TAG_STRING, "�ɹ�remove���Activity:" + activity.toString());
		}
		Log.d(TAG_STRING, "���Activity��δ��ӵ�������");
	}

	
	/**
	 * ����X��Ӧ��description���ض�Ӧ�Ļ�����򷵻�null
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
	 * �ر����е�Activity������ռ���
	 */
	public static void finishAll(){
		for (Activity activity: activitySet.keySet()){
			if (!activity.isFinishing()){
				activity.finish();
				Log.d(TAG_STRING, "�Ѿ��ر���� activity");
			}
		}
		activitySet.clear();
		Log.d(TAG_STRING, "�ر�����Activity����չ�����");
	}
	
	public static ArrayList<String> getAllDescription(){
		return new ArrayList<String>(activitySet.values());
	}
}
