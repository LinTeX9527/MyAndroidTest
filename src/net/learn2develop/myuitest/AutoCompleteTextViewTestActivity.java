package net.learn2develop.myuitest;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * ActionBar ����Ӧ�ó�������Ϸ���ʾ�� Settings ��ť����һ����
 * 
 * �� AndroidManifest.xml �ж�Ӧ�� Activity ����������µ�һ�����ô���Ϳ���ɾ��ActionBar
 * android:theme="@android:style/Theme.Holo.NoActionBar"
 * 
 * Ҳ���Դ����ж�̬����ActionBar
 * ActionBar actionBar = getActionBar();
 * actionBar.hide();
 * 
 * 
 * @author LinTeX9527
 *
 */
public class AutoCompleteTextViewTestActivity extends BaseActivity {

	// ��һ�����ӣ�AutoCompleteTextView �Ĳ�ȫ�б������Ǳ仯�ģ�ͨ����ť�ֶ��������ֻ��߼�������
	// ��ȫ�б�⣬ģ���û������¼������ģ�⹫˾Ա��Ŀ¼
	String[] nameLibs = {"Aaron",
						"Abby",
						"Adam",
						"Baker",
						"Betty",
						"Bob",
						"Carl",
						"Cole",
						"Daisy",
						"Dale",
						"Dean",
						"Dodge",
						"Edward",
						"Eve",
						"Frank",
						"Franklin",
						"George",
						"Gill",
						"Hamlet",
						"Hill",
						"James",
						"Jane",
						"Jordan",
						"Kathy",
						"Lincoln",
						"Linda",
						"Lucia",
						"Mac",
						"Maria",
						"Norman",
						"Orlando",
						"Pete",
						"Peter",
						"Pike",
						"Robert",
						"Robin",
						"Roger",
						"Sam",
						"Shaw",
						"Sidney",
						"Taylor",
						"Toby",
						"Tommy",
						"Victoria",
						"Victor",
						"White",
						"William",
						"Xavier",
						"York",
						"Zoe"};
	// ��ǰ AutoCompleteTextView ���صĲ�ȫ�б���̬�仯��
	List<String> nameList = new ArrayList<String>();
	// ��������¼��ǰ������ǲ�ȫ�б���е��ĸ�λ��
	int index = 0;
	AutoCompleteTextView autoComEditTextViewUserName = null;
	ArrayAdapter<String> userNameAdapter = null;
	Button btnAddNames = null;
	Button btnRemoveNames = null;
	
	// �ڶ������ӣ�AutoCompleteTextView �Ĳ�ȫ�б����һ��ʼ�ͼ��غõġ�
	String[] fruitNames = {"Almond",
							"Apple", 
							"Apricot", 
							"Bagasse",
							"Banana",
							"Bennet",
							"Berry", 
							"Black brin",
							"Bush fruit",
							"Cherry",
							"Core",
							"Custard apple",
							"Damson",
							"Date",
							"Durian",
							"Fig",
							"Filbert",
							"Flat peach",
							"Ginkgo",
							"Gooseberry",
							"Grape",
							"Haw",
							"Hickory",
							"Juicy peach",
							"Kernel fruit",
							"Kiwifruit",
							"Lemon",
							"Lichee",
							"Longan",
							"Loquat",
							"Lotus nut",
							"Mandarin",
							"Mango",
							"Marc",
							"Melon",
							"Nectarine",
							"Newton pippin",
							"Nucleus",
							"Olive",
							"Orange",
							"Papaya",
							"Peach",
							"Peanut",
							"Pear",
							"Phoenix eye nut",
							"Plum",
							"Quarenden",
							"Rambutan",
							"Segment",
							"Sorgo",
							"Strawberry",
							"Sweet acorn",
							"Tangerine",
							"Tangor",
							"Teazle fruit",
							"Vermillion orange",
							"Walnut",
							"Water Caltrop",
							"Wild peach"};
	AutoCompleteTextView editTextFruit = null;
	ArrayAdapter<String> fruitAdapter = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocompletetextview_test);
        
        ActionBar actionBar = getActionBar();
//        actionBar.hide();
        actionBar.show();

        // ��ӹ���
        String description = getResources().getString(R.string.test_autocomplete);
        ActivityCollector.modifyDescription(this, description);
        
        // �ڶ������ӣ�һ����������е�ˮ������
        editTextFruit = (AutoCompleteTextView) findViewById(R.id.editFruits);
        fruitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fruitNames);
        editTextFruit.setAdapter(fruitAdapter);
        editTextFruit.setThreshold(0); // ָ���û���Ҫ�������ٵ��ַ�����ʾ�����Զ���ȫ�Ĵ�  
        
        // ��ť��һ�����һ��
        autoComEditTextViewUserName = (AutoCompleteTextView) findViewById(R.id.autoTextView);
        userNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameList);
        autoComEditTextViewUserName.setAdapter(userNameAdapter);
        autoComEditTextViewUserName.setThreshold(1);
        btnAddNames = (Button) findViewById(R.id.btnAddNames);
        btnAddNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index < nameLibs.length){
					// ����ķ�����ֱ���������������Ԫ��
					userNameAdapter.add(nameLibs[index]); // �������������Ԫ��

					Toast.makeText(AutoCompleteTextViewTestActivity.this, "�����" + nameLibs[index], Toast.LENGTH_SHORT).show();
					index ++;
				} else {
					Toast.makeText(AutoCompleteTextViewTestActivity.this, "������", Toast.LENGTH_SHORT).show();
				}
			}
		});

        
        btnRemoveNames = (Button) findViewById(R.id.btnRemoveNames);
        btnRemoveNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index <= 0) {
					// �Զ���ȫ�ʿ�����Ԫ�ض�ɾ����
					Toast.makeText(AutoCompleteTextViewTestActivity.this, "�ʿ�Ϊ��!", Toast.LENGTH_SHORT).show();
				} else {
					index --;
					if (userNameAdapter.getItem(index).equals(nameLibs[index])){
						userNameAdapter.remove(nameLibs[index]);
						Toast.makeText(AutoCompleteTextViewTestActivity.this, "ɾ����" + nameLibs[index], Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
        
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActivityCollector.finishAll();
			}
		});
    }


    /**
     * ��� XML ������Դ
     * ��Դ�Ķ�����ǲ˵���Ķ���
     * 
     * �˵������Ҫ��ͼ�꣬������ͼ����������ȷ��������ʾ����Ҳͦ�õġ�
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_action_bar, menu);
        return true;
    }

    /**
     * ѡ��ActionBar�еĲ˵�����ᴥ�������ѡ���¼�
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(getBaseContext(), "����", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        if (id == R.id.action_find){
        	Toast.makeText(getBaseContext(), "����", Toast.LENGTH_SHORT).show();
        	return true;
        }
        
        if (id == R.id.action_new){
        	// ����һ���µ� Activity
        	Intent intent = new Intent(AutoCompleteTextViewTestActivity.this, ImageTestActivity.class);
        	startActivity(intent);
        	return true;
        }
        if (id == R.id.action_back){
        	startActivity(new Intent("com.lintex9527.network.AsyncTaskTestActivity"));
        }
        
        return super.onOptionsItemSelected(item);
    }


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_CENTER:
			Toast.makeText(getBaseContext(), "canter was clicked", Toast.LENGTH_SHORT).show();
			break;
			
		case KeyEvent.KEYCODE_DPAD_LEFT:
			Toast.makeText(getBaseContext(), "left arrow was clicked", Toast.LENGTH_SHORT).show();
			break;

		case KeyEvent.KEYCODE_DPAD_RIGHT:
			Toast.makeText(getBaseContext(), "right arrow was clicked", Toast.LENGTH_SHORT).show();
			break;

		case KeyEvent.KEYCODE_DPAD_UP:
			Toast.makeText(getBaseContext(), "up arrow was clicked", Toast.LENGTH_SHORT).show();
			break;

		case KeyEvent.KEYCODE_DPAD_DOWN:
			Toast.makeText(getBaseContext(), "down arrow was clicked", Toast.LENGTH_SHORT).show();
			break;

			
		default:
			break;
		}
		return false;
	}
    
    
}
