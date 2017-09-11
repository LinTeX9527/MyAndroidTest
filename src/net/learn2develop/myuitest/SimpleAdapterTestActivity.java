package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ListView 和 SimpleAdapter 的简单测试
 * 
 * 参见：
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
		
		// 添加管理
		String description = getResources().getString(R.string.test_simpleadapter);
		ActivityCollector.modifyDescription(this, description);
		
		
		// 预设的图片资源
		int[] images = new int[] {R.drawable.m01,
								R.drawable.m02,
								R.drawable.m03,
								R.drawable.m04,
								R.drawable.m05,
								R.drawable.m06};
		
		
		// 创建动态数组数据源 data
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 6; i ++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ITEM_IMAGE, images[i]);
			map.put(ITEM_TITLE, "这是title " + i);
			map.put(ITEM_TEXT, "这是文本 " + i);
			data.add(map);
		}
		
		// 绑定XML中的ListView，作为ListItem的容器
		ListView mylistView = (ListView) findViewById(R.id.listview_simpleadapter);
		
		// 动态数组数据源中的ListItem 中每一个显示项对应的KEY
		String[] from = new String[] {ITEM_IMAGE, ITEM_TITLE, ITEM_TEXT};
		
		// ListItem 的 XML文件里面的对应的控件ID
		int[] to = new int[] {R.id.ItemImage, R.id.ItemTitle, R.id.ItemText};
		
		
		// 最后一步，新建一个适配器，并将适配器绑定到ListView 上。
		SimpleAdapter myadapter = new SimpleAdapter(this, data, R.layout.simpleadapter_item, from, to);
		mylistView.setAdapter(myadapter);
		
	}

	/**
	 * 每一个Activity中都必须要覆盖onCreate()和onDestroy()方法，在里面添加ActivityCollector的管理
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
}
