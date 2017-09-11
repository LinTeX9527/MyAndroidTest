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
 * ActionBar 就是应用程序界面上方显示有 Settings 按钮的那一部分
 * 
 * 在 AndroidManifest.xml 中对应的 Activity 下面添加如下的一行配置代码就可以删除ActionBar
 * android:theme="@android:style/Theme.Holo.NoActionBar"
 * 
 * 也可以代码中动态隐藏ActionBar
 * ActionBar actionBar = getActionBar();
 * actionBar.hide();
 * 
 * 
 * @author LinTeX9527
 *
 */
public class AutoCompleteTextViewTestActivity extends BaseActivity {

	// 第一个例子，AutoCompleteTextView 的补全列表名字是变化的，通过按钮手动增加名字或者减少名字
	// 补全列表库，模拟用户输入记录，或者模拟公司员工目录
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
	// 当前 AutoCompleteTextView 加载的补全列表，动态变化的
	List<String> nameList = new ArrayList<String>();
	// 索引，记录当前处理的是补全列表库中的哪个位置
	int index = 0;
	AutoCompleteTextView autoComEditTextViewUserName = null;
	ArrayAdapter<String> userNameAdapter = null;
	Button btnAddNames = null;
	Button btnRemoveNames = null;
	
	// 第二个例子，AutoCompleteTextView 的补全列表库是一开始就加载好的。
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

        // 添加管理
        String description = getResources().getString(R.string.test_autocomplete);
        ActivityCollector.modifyDescription(this, description);
        
        // 第二个例子，一次性填充所有的水果名字
        editTextFruit = (AutoCompleteTextView) findViewById(R.id.editFruits);
        fruitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fruitNames);
        editTextFruit.setAdapter(fruitAdapter);
        editTextFruit.setThreshold(0); // 指定用户需要输入最少的字符便提示可以自动补全的词  
        
        // 按钮按一次填充一次
        autoComEditTextViewUserName = (AutoCompleteTextView) findViewById(R.id.autoTextView);
        userNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameList);
        autoComEditTextViewUserName.setAdapter(userNameAdapter);
        autoComEditTextViewUserName.setThreshold(1);
        btnAddNames = (Button) findViewById(R.id.btnAddNames);
        btnAddNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index < nameLibs.length){
					// 最简洁的方法是直接向适配器中添加元素
					userNameAdapter.add(nameLibs[index]); // 向适配器中添加元素

					Toast.makeText(AutoCompleteTextViewTestActivity.this, "添加了" + nameLibs[index], Toast.LENGTH_SHORT).show();
					index ++;
				} else {
					Toast.makeText(AutoCompleteTextViewTestActivity.this, "添加完毕", Toast.LENGTH_SHORT).show();
				}
			}
		});

        
        btnRemoveNames = (Button) findViewById(R.id.btnRemoveNames);
        btnRemoveNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index <= 0) {
					// 自动补全词库所有元素都删除了
					Toast.makeText(AutoCompleteTextViewTestActivity.this, "词库为空!", Toast.LENGTH_SHORT).show();
				} else {
					index --;
					if (userNameAdapter.getItem(index).equals(nameLibs[index])){
						userNameAdapter.remove(nameLibs[index]);
						Toast.makeText(AutoCompleteTextViewTestActivity.this, "删除了" + nameLibs[index], Toast.LENGTH_SHORT).show();
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
     * 填充 XML 布局资源
     * 资源的定义就是菜单项的定义
     * 
     * 菜单项最后不要用图标，除非是图标的用意很明确，否则显示文字也挺好的。
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_action_bar, menu);
        return true;
    }

    /**
     * 选中ActionBar中的菜单项，将会触发下面的选中事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(getBaseContext(), "设置", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        if (id == R.id.action_find){
        	Toast.makeText(getBaseContext(), "查找", Toast.LENGTH_SHORT).show();
        	return true;
        }
        
        if (id == R.id.action_new){
        	// 启动一个新的 Activity
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
