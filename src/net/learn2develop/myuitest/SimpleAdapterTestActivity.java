package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ListView �� SimpleAdapter �ļ򵥲���
 * 
 * �μ���
 * 
 * http://blog.csdn.net/To_be_Designer/article/details/47980475
 * 
 * http://blog.csdn.net/hellohm/article/details/12356649
 * 
 * 
 * 
 * @author LinTeX9527
 */
public class SimpleAdapterTestActivity extends BaseActivity {

	private final static String ITEM_IMAGE = "ItemImage";
	private final static String ITEM_TITLE = "ItemTitle";
	private final static String ITEM_TEXT  = "ItemText";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simpleadapter_test);
		
		// ��ӹ���
		String description = getResources().getString(R.string.test_simpleadapter);
		ActivityCollector.modifyDescription(this, description);
		
		
		// Ԥ���ͼƬ��Դ
		int[] images = new int[] {R.drawable.m01,
								R.drawable.m02,
								R.drawable.m03,
								R.drawable.m04,
								R.drawable.m05,
								R.drawable.m06};
		
		
		// ������̬��������Դ data
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 6; i ++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ITEM_IMAGE, images[i]);
			map.put(ITEM_TITLE, "����title " + i);
			map.put(ITEM_TEXT, "�����ı� " + i);
			data.add(map);
		}
		
		// ��XML�е�ListView����ΪListItem������
		ListView mylistView = (ListView) findViewById(R.id.listview_simpleadapter);
		
		// ��̬��������Դ�е�ListItem ��ÿһ����ʾ���Ӧ��KEY
		String[] from = new String[] {ITEM_IMAGE, ITEM_TITLE, ITEM_TEXT};
		
		// ListItem �� XML�ļ�����Ķ�Ӧ�Ŀؼ�ID
		int[] to = new int[] {R.id.ItemImage, R.id.ItemTitle, R.id.ItemText};
		
		
		// ���һ�����½�һ���������������������󶨵�ListView �ϡ�
		SimpleAdapter myadapter = new SimpleAdapter(this, data, R.layout.simpleadapter_item, from, to);
		mylistView.setAdapter(myadapter);
		
	}

	/**
	 * ÿһ��Activity�ж�����Ҫ����onCreate()��onDestroy()���������������ActivityCollector�Ĺ���
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
}
