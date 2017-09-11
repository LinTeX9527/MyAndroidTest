package net.learn2develop.myuitest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author LinTeX9527
 * �û����е�Activity���̳������BaseActivity����Ϊ�����ڵײ�ʵ���˶�����Activity�Ĺ���ע���ע�����С�
 * 
 */
public class BaseActivity extends Activity {

	private static final String TAG_STRING = "BaseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG_STRING, getClass().getSimpleName());
		
		// ��ӹ���
		String desc = getResources().getString(R.string.empty_description);
		ActivityCollector.addActivity(this, desc);
	}

	/**
	 * ������ÿһ��Activity��ʱ����Ҫ��ActivityCollector��Ҳɾ���������
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG_STRING, "����ڹر�");
		ActivityCollector.removeActivity(this);
	}
}
