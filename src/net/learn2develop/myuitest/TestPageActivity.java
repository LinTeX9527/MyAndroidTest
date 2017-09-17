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
	/**
	 * 这个 data 表示所有的测试项目
	 * 其中的每一个HashMap 表示一个测试项目的信息：
	 * 1、String description 对测试项目的描述
	 * 2、String action 测试项目Activity 的action
	 * 
	 */
	private List<HashMap<String, Object>> data = null;
	// 标记每一个测试项加入到这个列表中的序号
	private static int index = 0;
	
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
				
				// 第一个版本启动测试项的方法
//				TextView lbl = (TextView)view.findViewById(R.id.tvDesc);
//				startTestItem(lbl.getText().toString());

				// 第二个版本启动测试项的方法
				startTestItem(position);
			}
		});
	}
	
	protected void initData(){
	
		data = new ArrayList<HashMap<String, Object>>();
		
		/**
		 * 如果没有屏蔽 index=0，那么可以看到每次回到测试菜单页时，序号都增加了，说明每次都有Activity的 onCreate()，对应的应该
		 * 也发生了onDestroy()事件。需要增加Activity生命周期，在销毁TestPageActivity之前通过 Bundle 保存菜单项，
		 * 在回到 TestPageActivity中恢复菜单项 data。
		 * 
		 * 当然，下面归零index，可以暂时解决这个问题，但是绝对不完美。
		 */
		index = 0;
		
		// 经过测试，下面不能使用同一个 map 进行添加，而是每一测试项对应一个map对象。
		// 测试项 AutoCompleteTextView
		addTestItem(R.string.test_autocomplete, "net.learn2develop.myuitest.AutoCompleteTextViewTestActivity");
		
		// 测试项 ImageTest
		addTestItem(R.string.test_image, "net.learn2develop.myuitest.ImageTestActivity");

		// 测试项 ListView
		addTestItem(R.string.test_listview, "net.learn2develop.myuitest.ListViewTestActivity");
		
		// 测试项 SimpleAdapter
		addTestItem(R.string.test_simpleadapter, "net.learn2develop.myuitest.SimpleAdapterTestActivity");
		
		// 测试项 AsyncTask
		addTestItem(R.string.test_asynctask, "com.lintex9527.network.AsyncTaskTestActivity");
		
		// 测试项 Gallery
		addTestItem(R.string.test_gallery, "net.learn2develop.myuitest.GalleryTestActivity");
		
		// 测试项 ImageSwitcher
		addTestItem(R.string.test_imageswitcher, "net.learn2develop.myuitest.ImageSwitcherTestActivity");
		
		// 测试项 菜单
		addTestItem(R.string.test_menu, "net.learn2develop.myuitest.MenuTestActivity");
		
		// 测试项 GridView
		addTestItem(R.string.test_gridview, "net.learn2develop.myuitest.GridViewTestActivity");
		
		
		// 测试项 Volley
		addTestItem(R.string.test_volley, "com.lintex9527.network.VolleyTestActivity");
		
		// 测试项 录音机
		addTestItem(R.string.test_audiorecorder, "com.lintex9527.recorderplayer.MyRecorderTestActivity");
		
		
		// 官方例程，使用 MediaRecord 录制音频
		addTestItem(R.string.test_mediarecord, "com.lintex9527.recorderplayer.AudioRecordTestActivity");
		
	}
	
	protected void addTestItem(int idDescription, String action){
		HashMap<String, Object> mapx = new HashMap<String, Object>();
		// 在列表中显示每一个测试item的描述性文本，前面加入序号，例如：
		// 1、 测试ListView
		// 2、 测试ImageView
		mapx.put(ITEM_DESCRIPTION, "" + (++index) +  "、 " +getResources().getString(idDescription));
		mapx.put(ITEM_ACTION, action);
		data.add(mapx);
	}
	

	/**
	 * <p>传入测试项的description进而启动这个测试项 </p>
	 * 
	 * 第一个版本中采用，每个测试项的描述性文本都是唯一的，想通过这个方法在ListView的 onItemClickListener() 中确定测试项是哪一个
	 * 后来需要在列表中加入序号，所以描述性文本前面加入了其他的字符，不方便。改进版本是通过序号启动测试项。
	 * @param description
	 * @since v0.1
	 * @deprecated
	 * 
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
	 * 传入测试项的序号进而启动这个测试项
	 * @param itemindex
	 */
	protected void startTestItem(int itemindex){
		if (itemindex >= 0 && itemindex < data.size()){
			startActivity(new Intent(data.get(itemindex).get(ITEM_ACTION).toString()));
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
