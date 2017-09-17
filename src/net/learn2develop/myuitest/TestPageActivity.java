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
 * ��������ҵ�HomePage��������ѡ����Ե�Ԫ
 * ����ҳ�棬���в�����ļ��ϣ������б��е�ĳһ��Ϳ���ֱ����ת����Ӧ�Ĳ�����
 * 
 * @author LinTeX9527
 */
public class TestPageActivity extends Activity {

	public final static String ITEM_DESCRIPTION = "DESCRIPTION";
	public final static String ITEM_ACTION = "ACTION";
	
	private ListView lvAllItem = null;
	/**
	 * ��� data ��ʾ���еĲ�����Ŀ
	 * ���е�ÿһ��HashMap ��ʾһ��������Ŀ����Ϣ��
	 * 1��String description �Բ�����Ŀ������
	 * 2��String action ������ĿActivity ��action
	 * 
	 */
	private List<HashMap<String, Object>> data = null;
	// ���ÿһ����������뵽����б��е����
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
				
				// ��һ���汾����������ķ���
//				TextView lbl = (TextView)view.findViewById(R.id.tvDesc);
//				startTestItem(lbl.getText().toString());

				// �ڶ����汾����������ķ���
				startTestItem(position);
			}
		});
	}
	
	protected void initData(){
	
		data = new ArrayList<HashMap<String, Object>>();
		
		/**
		 * ���û������ index=0����ô���Կ���ÿ�λص����Բ˵�ҳʱ����Ŷ������ˣ�˵��ÿ�ζ���Activity�� onCreate()����Ӧ��Ӧ��
		 * Ҳ������onDestroy()�¼�����Ҫ����Activity�������ڣ�������TestPageActivity֮ǰͨ�� Bundle ����˵��
		 * �ڻص� TestPageActivity�лָ��˵��� data��
		 * 
		 * ��Ȼ���������index��������ʱ���������⣬���Ǿ��Բ�������
		 */
		index = 0;
		
		// �������ԣ����治��ʹ��ͬһ�� map ������ӣ�����ÿһ�������Ӧһ��map����
		// ������ AutoCompleteTextView
		addTestItem(R.string.test_autocomplete, "net.learn2develop.myuitest.AutoCompleteTextViewTestActivity");
		
		// ������ ImageTest
		addTestItem(R.string.test_image, "net.learn2develop.myuitest.ImageTestActivity");

		// ������ ListView
		addTestItem(R.string.test_listview, "net.learn2develop.myuitest.ListViewTestActivity");
		
		// ������ SimpleAdapter
		addTestItem(R.string.test_simpleadapter, "net.learn2develop.myuitest.SimpleAdapterTestActivity");
		
		// ������ AsyncTask
		addTestItem(R.string.test_asynctask, "com.lintex9527.network.AsyncTaskTestActivity");
		
		// ������ Gallery
		addTestItem(R.string.test_gallery, "net.learn2develop.myuitest.GalleryTestActivity");
		
		// ������ ImageSwitcher
		addTestItem(R.string.test_imageswitcher, "net.learn2develop.myuitest.ImageSwitcherTestActivity");
		
		// ������ �˵�
		addTestItem(R.string.test_menu, "net.learn2develop.myuitest.MenuTestActivity");
		
		// ������ GridView
		addTestItem(R.string.test_gridview, "net.learn2develop.myuitest.GridViewTestActivity");
		
		
		// ������ Volley
		addTestItem(R.string.test_volley, "com.lintex9527.network.VolleyTestActivity");
		
		// ������ ¼����
		addTestItem(R.string.test_audiorecorder, "com.lintex9527.recorderplayer.MyRecorderTestActivity");
		
		
		// �ٷ����̣�ʹ�� MediaRecord ¼����Ƶ
		addTestItem(R.string.test_mediarecord, "com.lintex9527.recorderplayer.AudioRecordTestActivity");
		
	}
	
	protected void addTestItem(int idDescription, String action){
		HashMap<String, Object> mapx = new HashMap<String, Object>();
		// ���б�����ʾÿһ������item���������ı���ǰ�������ţ����磺
		// 1�� ����ListView
		// 2�� ����ImageView
		mapx.put(ITEM_DESCRIPTION, "" + (++index) +  "�� " +getResources().getString(idDescription));
		mapx.put(ITEM_ACTION, action);
		data.add(mapx);
	}
	

	/**
	 * <p>����������description����������������� </p>
	 * 
	 * ��һ���汾�в��ã�ÿ����������������ı�����Ψһ�ģ���ͨ�����������ListView�� onItemClickListener() ��ȷ������������һ��
	 * ������Ҫ���б��м�����ţ������������ı�ǰ��������������ַ��������㡣�Ľ��汾��ͨ��������������
	 * @param description
	 * @since v0.1
	 * @deprecated
	 * 
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
	
	/**
	 * ������������Ž����������������
	 * @param itemindex
	 */
	protected void startTestItem(int itemindex){
		if (itemindex >= 0 && itemindex < data.size()){
			startActivity(new Intent(data.get(itemindex).get(ITEM_ACTION).toString()));
		}
	}

	/**
	 * ���ActionBar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_action_bar, menu);
		return true;
	}

	/**
	 * �Զ���ActionBar�и�����ť����Ӧ�¼�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_new:
			Toast.makeText(TestPageActivity.this, "��ʱ���½�", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_back:
			Toast.makeText(TestPageActivity.this, "��ʱ���ú���", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_find:
			Toast.makeText(TestPageActivity.this, "����ʲô�أ�", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_settings:
			Toast.makeText(TestPageActivity.this, "��ʱ����Ҫ����ѡ��", Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
		return true;
	}
}
