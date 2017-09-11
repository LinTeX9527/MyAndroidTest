package net.learn2develop.myuitest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ListView �� ArrayAdapter �Ĳ���
 * ����ListView��򵥵�ʹ�÷�����ÿһ��item��ֻ��һ���ı���
 * �������£� 
 * 1���ҵ�listview
 * 2��׼������data��data ������String ������߼��ϣ���֮�����Ǵ����String��
 * 3���½�һ��������ArrayAdapter��һ�㴫��3������new ArrayAdapter(Context context, android.R.layout.simple_list_item_1, data)
 * 4����listviewװ����������listview.setAdapter(myArrayAdapter);
 * 
 * @author LinTeX9527
 */
public class ListViewTestActivity extends BaseActivity {

	private ListView myListView = null;
	private String[] data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ��ӹ���
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
					// ��Ȼ���ⲿ����Ч��ǡ�������View���� android.R.layout.simple_list_item_checked���ʹ����Ǹ�TextView��
					// �����Զ���ؼ����п��ܲ��� TextView��
					Toast.makeText(getBaseContext(), "������ı��� : " + tempTextView.getText().toString(), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "�����ˣ�ԭ����� View ���ǳ���item��View", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		/**
		 * Ϊʲôѡ�е���ȴû�д����¼��أ�
		 * �ƺ������������û���ã���Ϊ���õ�������� setOnItemClickListener()
		 * 2017-09-11 21:45
		 */
//		myListView.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				Toast.makeText(ListViewTestActivity.this, "ѡ����" + data[position], Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				Toast.makeText(ListViewTestActivity.this, "ѡ����nobody", Toast.LENGTH_SHORT).show();
//			}
//		});
	}
	
	
	protected void initData(){
		data = new String[]{"л����",
							"������",
							"���»�",
							"�⾩"};
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(ListViewTestActivity.this);
	}
	
	
	
}
