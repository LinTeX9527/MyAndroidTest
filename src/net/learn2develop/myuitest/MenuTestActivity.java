/**
 * 
 */
package net.learn2develop.myuitest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Gallery.LayoutParams;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * <p>菜单项测试</p>
 * <p>这里是手动创建菜单项，也可以通过XML文件创建菜单项，参见 TestPageActivity.java</p>
 * 
 * <p>既可以创建 选项菜单OptionsMenu，也可以创建上下文菜单ContextMenu </p>
 * <p>创建 OptionMenu 必须要实现两个方法：1、 onCreateOptionsMenu() 2、 onOptionsItemSelected(MenuItem)</p>
 * 
 * <p>创建ContextMenu 必须要实现两个方法： 1、onCreateContextMenu(ContextMenu, View, ContextMenuInfo) 2、 onContextItemSelected(MenuItem)
 * 另外最好是给对应的 View 注册  registerForContextMenu()</p>
 * 
 * @author LinTeX9527
 *
 */
@SuppressWarnings("deprecation")
public class MenuTestActivity extends Activity implements ViewFactory{

	// 给各个菜单item 设定唯一的ID，当然这些ID是可以自定义的，必须不能重复
	private final static int MENU_ITEM_ID_1 = 0xAA000001;
	private final static int MENU_ITEM_ID_2 = 0xAA000002;
	private final static int MENU_ITEM_ID_3 = 0xAA000003;
	private final static int MENU_ITEM_ID_4 = 0xAA000004;
	private final static int MENU_ITEM_ID_5 = 0xAA000005;
	private final static int MENU_ITEM_ID_6 = 0xAA000006;
	
	// 为btnImageSwitch生成的上下文菜单MenuItem的 item ID
	private final static int CONTEXT_MENU_ITEM_ID_01 = 0xAB000001;	// 上一张
	private final static int CONTEXT_MENU_ITEM_ID_02 = 0xAB000002;	// 下一张
	private final static int CONTEXT_MENU_ITEM_ID_06 = 0xAB000006;	// 随机
	
	// 为btnText生成的上下文菜单MenuItem的itemID
	private final static int CONTEXT_MENU_ITEM_ID_03 = 0xAB000003;	// 改变颜色
	private final static int CONTEXT_MENU_ITEM_ID_04 = 0xAB000004;	// 改变大小
	
	// 这个是公共的上下文菜单项item ID
	private final static int CONTEXT_MENU_ITEM_ID_05 = 0xAB000005;	// 公共菜单
	
	private ImageSwitcher imageSlide = null;
	private Button btnImageSwitch = null;
	private Button btnText = null;
	private TextView tvText = null;
	
	int[] imageIDs = {
			R.drawable.m01,
			R.drawable.m02,
			R.drawable.m03,
			R.drawable.m04,
			R.drawable.m05,
			R.drawable.m06
	};
	// 当前显示的图片的索引
	static int index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_test);
		
		
		imageSlide = (ImageSwitcher) findViewById(R.id.imageslide);
		imageSlide.setFactory(this);
		
		// 左侧滑入右侧滑出比淡入淡出效果更好。
		// slide_in_left/slide_out_right is better than fade_in/fade_out
		imageSlide.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		imageSlide.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
		
		index = 0;
		btnImageSwitch = (Button) findViewById(R.id.btnImageSwitch);
		registerForContextMenu(btnImageSwitch);
		
		// 要让一个 View具有上下文菜单，则必须设置下面的 setOnCreateContextMenuListener() 方法.
		btnImageSwitch.setOnCreateContextMenuListener(this);
		
		
		tvText = (TextView) findViewById(R.id.tvText);
		
		btnText = (Button) findViewById(R.id.btnText);
		registerForContextMenu(btnText);
		 
		// 使得按钮btnText能够生成上下文菜单
		btnText.setOnCreateContextMenuListener(this);
	}

	
	
	/**
	 * <p>为某个View v 填充上下文菜单 ContextMenu menu</p>
	 * 
	 * <p>这个例子中，为按钮btnImageSwitch 创建了3+1个菜单项；为按钮btnText创建了2+1个菜单选项。
	 * 	  所有的菜单选项都通过 id 来互相区分，即便共用一个上下文菜单创建方法、共用一个上下文菜单响应方法，都不会互相干扰。</p>
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		// 按钮 btnImageSwitch 相关的菜单选项
		if (v.getId() == btnImageSwitch.getId()){
			
			Toast.makeText(getBaseContext(), "这个是为btnImageSwitch生成的上下文", Toast.LENGTH_SHORT).show();
			
			MenuItem item01 = menu.add(0, CONTEXT_MENU_ITEM_ID_01, 0, "上一张");
			{
				item01.setIcon(R.drawable.m01);
				item01.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item02 = menu.add(0, CONTEXT_MENU_ITEM_ID_02, 0, "下一张");
			{
				item02.setIcon(R.drawable.m02);
				item02.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item06 = menu.add(0, CONTEXT_MENU_ITEM_ID_06, 0, "随机");
			{
				item06.setIcon(R.drawable.m02);
				item06.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
		} else if (v.getId() == btnText.getId()){
			// 按钮 btnText 相关的菜单选项
			
			Toast.makeText(getBaseContext(), "这个是为btnText生成的上下文", Toast.LENGTH_SHORT).show();
			
			MenuItem item03 = menu.add(0, CONTEXT_MENU_ITEM_ID_03, 0, "改变颜色");
			{
				item03.setIcon(R.drawable.m03);
				item03.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}
			MenuItem item04 = menu.add(0, CONTEXT_MENU_ITEM_ID_04, 0, "改变大小");
			{
				item04.setIcon(R.drawable.m04);
				item04.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}			
		}
		
		MenuItem item05 = menu.add(0, CONTEXT_MENU_ITEM_ID_05, 0, "公共菜单项");
		{
			item05.setIcon(R.drawable.m05);
			item05.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		
	}




	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		// 图片相关的两个上下文菜单项
		case CONTEXT_MENU_ITEM_ID_01:
			if (index == 0){
				index = imageIDs.length - 1;
			}
			imageSlide.setImageResource(imageIDs[index]);
			index --;
			break;
			
		case CONTEXT_MENU_ITEM_ID_02:
			if (index == imageIDs.length - 1){
				index = 0;
			}
			imageSlide.setImageResource(imageIDs[index]);
			index ++;
			break;

		// 文字相关的上下文菜单项
		case CONTEXT_MENU_ITEM_ID_03:
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			tvText.setBackgroundColor(Color.rgb(red, green, blue));
			break;
			
		case CONTEXT_MENU_ITEM_ID_04:
			// 设置文字大小在 16~26之间
			int size = (int) (16 + (Math.random() * 10));
			tvText.setTextSize(size);
			break;			

		case CONTEXT_MENU_ITEM_ID_05:
			Toast.makeText(getBaseContext(), "选中公共菜单项", Toast.LENGTH_SHORT).show();
			break;
			
		case CONTEXT_MENU_ITEM_ID_06:
			index = (int)(Math.random() * (imageIDs.length));
			imageSlide.setImageResource(imageIDs[index]);
			Toast.makeText(getBaseContext(), "随机一张图片:" + index, Toast.LENGTH_SHORT).show();
			break;			
			
		default:
			Toast.makeText(getBaseContext(), "未选中", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}




	/**
	 * <p>创建菜单项</p>
	 * groupID 和 order 感觉没有一点用啊，怎么设置都没有变化。
	 * <p>各个菜单的响应事件在  onOptionsItemSelected() 中自定义 </p>
	 * 
	 * <p>setEnable(false) 可见，灰色的，不能触发单击事件</p>
	 * 
	 * <p>setVisable(false) 不可见，虽说能够通过 shortcut 触发事件，但是我测试没有成功。即便在AndroidManifest.xml 文件中
	 * 给这个 Activity 添加了android:windowSoftInputMode="stateAlwaysVisible" 属性，依然不能通过键盘触发事件，为什么？</p>
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int groupId = 5;
		int order = Menu.CATEGORY_SECONDARY;
		//order = Menu.CATEGORY_CONTAINER;
		MenuItem item01 = menu.add(groupId, MENU_ITEM_ID_1, order, "m01");
		item01.setEnabled(false); // 可见，但是单击不能触发事件
		item01.setTitle("妹子1"); // 这个设置会覆盖上面的
		item01.setIcon(imageIDs[0]);
		
		MenuItem item02 = menu.add(groupId, MENU_ITEM_ID_2, order, "m02");
		item02.setEnabled(true);
		item02.setTitle("妹子2"); // 这个设置会覆盖上面的
		item02.setIcon(imageIDs[1]);
		
		groupId = 1;
		MenuItem item03 = menu.add(groupId, MENU_ITEM_ID_3, order, "m03");
		item03.setEnabled(false);
		item03.setTitle("妹子3"); // 这个设置会覆盖上面的
		item03.setIcon(imageIDs[2]);
		
		MenuItem item04 = menu.add(groupId, MENU_ITEM_ID_4, order, "m04");
		item04.setEnabled(true);
		item04.setTitle("妹子4"); // 这个设置会覆盖上面的
		item04.setIcon(imageIDs[3]);
		item04.setShortcut('1', 'a');
		item04.setVisible(false); // 不可见，但是可以通过 shortcut 触发事件

		groupId = 2;
		//order = Menu.CATEGORY_ALTERNATIVE;
		MenuItem item05 = menu.add(groupId, MENU_ITEM_ID_5, order, "m05");
		item05.setEnabled(true);
		item05.setTitle("妹子5"); // 这个设置会覆盖上面的
		item05.setIcon(imageIDs[4]);
		item05.setCheckable(false);
		
		MenuItem item06 = menu.add(groupId, MENU_ITEM_ID_6, order, "m06");
		item06.setEnabled(true);
		item06.setTitle("妹子6"); // 这个设置会覆盖上面的
		item06.setIcon(imageIDs[5]);
		item06.setCheckable(false);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case MENU_ITEM_ID_1:
			imageSlide.setImageResource(imageIDs[0]);
			break;
		case MENU_ITEM_ID_2:
			imageSlide.setImageResource(imageIDs[1]);
			break;
		case MENU_ITEM_ID_3:
			imageSlide.setImageResource(imageIDs[2]);
			break;
		case MENU_ITEM_ID_4:
			imageSlide.setImageResource(imageIDs[3]);
			break;
		case MENU_ITEM_ID_5:
			imageSlide.setImageResource(imageIDs[4]);
			break;
		case MENU_ITEM_ID_6:
			imageSlide.setImageResource(imageIDs[5]);
			break;

		default:
			Toast.makeText(getBaseContext(), "没有选中菜单项", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return true;
	}


	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF00FF00);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return imageView;
	}
	
}
