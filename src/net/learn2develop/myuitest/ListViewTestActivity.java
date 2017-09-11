package net.learn2develop.myuitest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ListView 和 ArrayAdapter 的测试
 * 这是ListView最简单的使用方法，每一个item都只是一行文本。
 * 步骤如下： 
 * 1、找到listview
 * 2、准备数据data（data 必须是String 数组或者集合，总之必须是纯粹的String）
 * 3、新建一个适配器ArrayAdapter，一般传递3个参数new ArrayAdapter(Context context, android.R.layout.simple_list_item_1, data)
 * 4、给listview装上适配器：listview.setAdapter(myArrayAdapter);
 * 
 * @author LinTeX9527
 */
public class ListViewTestActivity extends BaseActivity {

	private ListView myListView = null;
	private String[] data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 添加管理
		String description = getResources().getString(R.string.test_listview);
		ActivityCollector.modifyDescription(ListViewTestActivity.this, description);
		
		setContentView(R.layout.activity_listview_test);
		
		initData();
		myListView = (ListView) findViewById(R.id.id_listview);
		
//		ArrayAdapter<String> myaArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
		ArrayAdapter<String> myaArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, data);
		myListView.setAdapter(myaArrayAdapter);
		myListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		
		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tempTextView = (TextView) view;
				if(tempTextView != null){
					Toast.makeText(getBaseContext(), "点击的文本是 : " + tempTextView.getText().toString(), Toast.LENGTH_SHORT).show();
					
//					startActivity(new Intent(ListViewTestActivity.this, 
//											ActivityCollector.getActivity(tempTextView.getText().toString())));
				} else {
					Toast.makeText(getBaseContext(), "理解错了，原来这个 View 不是承载item的View", Toast.LENGTH_SHORT).show();
				}
				//startActivity(new Intent(ListViewTestActivity.this, SimpleAdapterTestActivity.class));
			}
			
		});
		
		myListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	protected void initData(){
		data = new String[]{"谢霆锋",
							"吴彦祖",
							"刘德华",
							"吴京"};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_action_bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		switch(id){
		
		case R.id.action_new:
			Toast.makeText(getBaseContext(), "新建", Toast.LENGTH_SHORT).show();
			startActivity(new Intent("net.learn2develop.myuitest.SimpleAdapterTestActivity"));
			break;
			
		case R.id.action_back:
			startActivity(new Intent("net.learn2develop.myuitest.SimpleAdapterTestActivity"));
			break;
		default:
			break;
		}
		
		return true;
	}

	
	
}
