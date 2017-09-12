package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 这个就是我的HomePage，从这里选择测试单元
 * 测试页面，所有测试项的集合，单击列表中的某一项就可以直接跳转到对应的测试项
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
//				Toast.makeText(TestPageActivity.this, "点击的是：" + view.getClass().toString(), Toast.LENGTH_SHORT).show();
				
				TextView lbl = (TextView)view.findViewById(R.id.tvDesc);
				Toast.makeText(TestPageActivity.this, "点击的是：" + lbl.getText().toString(), Toast.LENGTH_SHORT).show();
				startTestItem(lbl.getText().toString());
			}
		});
	}
	
	protected void initData(){
		data = new ArrayList<HashMap<String, Object>>();
		
		// 经过测试，下面不能使用同一个 map 进行添加，而是每一测试项对应一个map对象。
		// 测试项 AutoCompleteTextView
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_autocomplete));
		map.put(ITEM_ACTION, "net.learn2develop.myuitest.AutoCompleteTextViewTestActivity");
		data.add(map);
		
		// 测试项 ImageTest
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_image));
		map2.put(ITEM_ACTION, "net.learn2develop.myuitest.ImageTestActivity");
		data.add(map2);

		// 测试项 ListView
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_listview));
		map3.put(ITEM_ACTION, "net.learn2develop.myuitest.ListViewTestActivity");
		data.add(map3);
		
		// 测试项 SimpleAdapter
		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_simpleadapter));
		map4.put(ITEM_ACTION, "net.learn2develop.myuitest.SimpleAdapterTestActivity");
		data.add(map4);
		
		// 测试项 AsyncTask
		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_asynctask));
		map5.put(ITEM_ACTION, "com.lintex9527.network.AsyncTaskTestActivity");
		data.add(map5);
		
		// 测试项 Gallery
		HashMap<String, Object> map6 = new HashMap<String, Object>();
		map6.put(ITEM_DESCRIPTION, getResources().getString(R.string.test_gallery));
		map6.put(ITEM_ACTION, "net.learn2develop.myuitest.GalleryTestActivity");
		data.add(map6);
	}
	
	
	/**
	 * 传入测试项的description进而启动这个测试项
	 * @param description
	 */
	protected void startTestItem(String description) {
		if (description != null){
			for (HashMap<String, Object> map: data){
				
				if (description == map.get(ITEM_DESCRIPTION)){
					//Toast.makeText(TestPageActivity.this, "当前的key:" + description + "对应的 action:" + map.get(ITEM_ACTION), Toast.LENGTH_SHORT).show();
					startActivity(new Intent(map.get(ITEM_ACTION).toString()));
				}
			}
		}
	}

	/**
	 * 填充ActionBar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_action_bar, menu);
		return true;
	}

	/**
	 * 自定义ActionBar中各个按钮的响应事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_new:
			Toast.makeText(TestPageActivity.this, "暂时不新建", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_back:
			Toast.makeText(TestPageActivity.this, "暂时不用后退", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_find:
			Toast.makeText(TestPageActivity.this, "查找什么呢？", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_settings:
			Toast.makeText(TestPageActivity.this, "暂时不需要设置选项", Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
		return true;
	}
}
