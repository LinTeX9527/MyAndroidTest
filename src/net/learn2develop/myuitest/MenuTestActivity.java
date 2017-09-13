/**
 * 
 */
package net.learn2develop.myuitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * 菜单项测试
 * @author LinTeX9527
 *
 */
@SuppressWarnings("deprecation")
public class MenuTestActivity extends Activity implements ViewFactory {

	// 给各个菜单item 设定唯一的ID，当然这些ID是可以自定义的，必须不能重复
	private final static int MENU_ITEM_ID_1 = 0xAA00001;
	private final static int MENU_ITEM_ID_2 = 0xAA00002;
	private final static int MENU_ITEM_ID_3 = 0xAA00003;
	private final static int MENU_ITEM_ID_4 = 0xAA00004;
	private final static int MENU_ITEM_ID_5 = 0xAA00005;
	private final static int MENU_ITEM_ID_6 = 0xAA00006;
	
	
	private ImageSwitcher imageSlide = null;
	
	int[] imageIDs = {
			R.drawable.m01,
			R.drawable.m02,
			R.drawable.m03,
			R.drawable.m04,
			R.drawable.m05,
			R.drawable.m06
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_test);
		
		
		imageSlide = findViewById(R.id.imageslide);
		imageSlide.setFactory(this);
		
		// 左侧滑入右侧滑出比淡入淡出效果更好。
		// slide_in_left/slide_out_right is better than fade_in/fade_out
		imageSlide.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		imageSlide.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
		
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
