package net.learn2develop.myuitest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author LinTeX9527
 * 用户所有的Activity都继承自这个BaseActivity，因为这里在底层实现了对所有Activity的管理，注册和注销都有。
 * 
 */
public class BaseActivity extends Activity {

	private static final String TAG_STRING = "BaseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG_STRING, getClass().getSimpleName());
		
		// 添加管理
		String desc = getResources().getString(R.string.empty_description);
		ActivityCollector.addActivity(this, desc);
	}

	/**
	 * 在销毁每一个Activity的时候需要从ActivityCollector中也删除这个对象
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG_STRING, "活动正在关闭");
		ActivityCollector.removeActivity(this);
	}
}
