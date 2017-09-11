package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����ҳ�棬���в�����ļ��ϣ������б��е�ĳһ��Ϳ���ֱ����ת����Ӧ�Ĳ�����
 * 
 * @author LinTeX9527
 */
public class TestPageActivity extends Activity {

	public final static String ITEM_DESCRIPTION = "DESCRIPTION";
	public final static String ITEM_ACTION = "ACTION";
	
	private ListView lvAllItem = null;
	private List<HashMap<String, Object>> data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testpage);

		lvAllItem = (ListView) findViewById(R.id.lv_alltest);
		
		initData();
		
		String[] from = new String[]{ITEM_DESCRIPTION};
		int[] to = new int[]{R.id.tvDesc};
		
		lvAllItem.setAdapter(new SimpleAdapter(this, data, R.layout.testpage_list_item, from, to));
		lvAllItem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(TestPageActivity.this, "������ǣ�" + view.getClass().toString(), Toast.LENGTH_SHORT).show();
				
				TextView lbl = (TextView)view.findViewById(R.id.tvDesc);
				Toast.makeText(TestPageActivity.this, "������ǣ�" + lbl.getText().toString(), Toast.LENGTH_SHORT).show();
				startTestItem(lbl.getText().toString());
			}
		});
	}
	
	protected void initData(){
		data = new ArrayList<HashMap<String, Object>>();
		
		// �������ԣ����治��ʹ��ͬһ�� map ������ӣ�����ÿһ�������Ӧһ��map����
		// ������ AutoCompleteTextView
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_autocomplete));
		map.put(ITEM_ACTION, "net.learn2develop.myuitest.AutoCompleteTextViewTestActivity");
		data.add(map);
		
		// ������ ImageTest
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_image));
		map2.put(ITEM_ACTION, "net.learn2develop.myuitest.ImageTestActivity");
		data.add(map2);

		// ������ ListView
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_listview));
		map3.put(ITEM_ACTION, "net.learn2develop.myuitest.ListViewTestActivity");
		data.add(map3);
		
		// ������ SimpleAdapter
		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_simpleadapter));
		map4.put(ITEM_ACTION, "net.learn2develop.myuitest.SimpleAdapterTestActivity");
		data.add(map4);
		
		// ������ AsyncTask
		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_asynctask));
		map5.put(ITEM_ACTION, "com.lintex9527.network.AsyncTaskTestActivity");
		data.add(map5);
	}
	
	
	/**
	 * ����������description�����������������
	 * @param description
	 */
	protected void startTestItem(String description) {
		if (description != null){
			for (HashMap<String, Object> map: data){
				
				if (description == map.get(ITEM_DESCRIPTION)){
					//Toast.makeText(TestPageActivity.this, "��ǰ��key:" + description + "��Ӧ�� action:" + map.get(ITEM_ACTION), Toast.LENGTH_SHORT).show();
					startActivity(new Intent(map.get(ITEM_ACTION).toString()));
				}
			}
		}
	}
}
