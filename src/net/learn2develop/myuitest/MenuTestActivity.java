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
 * <p>�˵������</p>
 * <p>�������ֶ������˵��Ҳ����ͨ��XML�ļ������˵���μ� TestPageActivity.java</p>
 * 
 * <p>�ȿ��Դ��� ѡ��˵�OptionsMenu��Ҳ���Դ��������Ĳ˵�ContextMenu </p>
 * <p>���� OptionMenu ����Ҫʵ������������1�� onCreateOptionsMenu() 2�� onOptionsItemSelected(MenuItem)</p>
 * 
 * <p>����ContextMenu ����Ҫʵ������������ 1��onCreateContextMenu(ContextMenu, View, ContextMenuInfo) 2�� onContextItemSelected(MenuItem)
 * ��������Ǹ���Ӧ�� View ע��  registerForContextMenu()</p>
 * 
 * @author LinTeX9527
 *
 */
@SuppressWarnings("deprecation")
public class MenuTestActivity extends Activity implements ViewFactory{

	// �������˵�item �趨Ψһ��ID����Ȼ��ЩID�ǿ����Զ���ģ����벻���ظ�
	private final static int MENU_ITEM_ID_1 = 0xAA000001;
	private final static int MENU_ITEM_ID_2 = 0xAA000002;
	private final static int MENU_ITEM_ID_3 = 0xAA000003;
	private final static int MENU_ITEM_ID_4 = 0xAA000004;
	private final static int MENU_ITEM_ID_5 = 0xAA000005;
	private final static int MENU_ITEM_ID_6 = 0xAA000006;
	
	// ΪbtnImageSwitch���ɵ������Ĳ˵�MenuItem�� item ID
	private final static int CONTEXT_MENU_ITEM_ID_01 = 0xAB000001;	// ��һ��
	private final static int CONTEXT_MENU_ITEM_ID_02 = 0xAB000002;	// ��һ��
	private final static int CONTEXT_MENU_ITEM_ID_06 = 0xAB000006;	// ���
	
	// ΪbtnText���ɵ������Ĳ˵�MenuItem��itemID
	private final static int CONTEXT_MENU_ITEM_ID_03 = 0xAB000003;	// �ı���ɫ
	private final static int CONTEXT_MENU_ITEM_ID_04 = 0xAB000004;	// �ı��С
	
	// ����ǹ����������Ĳ˵���item ID
	private final static int CONTEXT_MENU_ITEM_ID_05 = 0xAB000005;	// �����˵�
	
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
	// ��ǰ��ʾ��ͼƬ������
	static int index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_test);
		
		
		imageSlide = (ImageSwitcher) findViewById(R.id.imageslide);
		imageSlide.setFactory(this);
		
		// ��໬���Ҳ໬���ȵ��뵭��Ч�����á�
		// slide_in_left/slide_out_right is better than fade_in/fade_out
		imageSlide.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		imageSlide.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
		
		index = 0;
		btnImageSwitch = (Button) findViewById(R.id.btnImageSwitch);
		registerForContextMenu(btnImageSwitch);
		
		// Ҫ��һ�� View���������Ĳ˵����������������� setOnCreateContextMenuListener() ����.
		btnImageSwitch.setOnCreateContextMenuListener(this);
		
		
		tvText = (TextView) findViewById(R.id.tvText);
		
		btnText = (Button) findViewById(R.id.btnText);
		registerForContextMenu(btnText);
		 
		// ʹ�ð�ťbtnText�ܹ����������Ĳ˵�
		btnText.setOnCreateContextMenuListener(this);
	}

	
	
	/**
	 * <p>Ϊĳ��View v ��������Ĳ˵� ContextMenu menu</p>
	 * 
	 * <p>��������У�Ϊ��ťbtnImageSwitch ������3+1���˵��Ϊ��ťbtnText������2+1���˵�ѡ�
	 * 	  ���еĲ˵�ѡ�ͨ�� id ���������֣����㹲��һ�������Ĳ˵���������������һ�������Ĳ˵���Ӧ�����������ụ����š�</p>
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		// ��ť btnImageSwitch ��صĲ˵�ѡ��
		if (v.getId() == btnImageSwitch.getId()){
			
			Toast.makeText(getBaseContext(), "�����ΪbtnImageSwitch���ɵ�������", Toast.LENGTH_SHORT).show();
			
			MenuItem item01 = menu.add(0, CONTEXT_MENU_ITEM_ID_01, 0, "��һ��");
			{
				item01.setIcon(R.drawable.m01);
				item01.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item02 = menu.add(0, CONTEXT_MENU_ITEM_ID_02, 0, "��һ��");
			{
				item02.setIcon(R.drawable.m02);
				item02.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item06 = menu.add(0, CONTEXT_MENU_ITEM_ID_06, 0, "���");
			{
				item06.setIcon(R.drawable.m02);
				item06.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
		} else if (v.getId() == btnText.getId()){
			// ��ť btnText ��صĲ˵�ѡ��
			
			Toast.makeText(getBaseContext(), "�����ΪbtnText���ɵ�������", Toast.LENGTH_SHORT).show();
			
			MenuItem item03 = menu.add(0, CONTEXT_MENU_ITEM_ID_03, 0, "�ı���ɫ");
			{
				item03.setIcon(R.drawable.m03);
				item03.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}
			MenuItem item04 = menu.add(0, CONTEXT_MENU_ITEM_ID_04, 0, "�ı��С");
			{
				item04.setIcon(R.drawable.m04);
				item04.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}			
		}
		
		MenuItem item05 = menu.add(0, CONTEXT_MENU_ITEM_ID_05, 0, "�����˵���");
		{
			item05.setIcon(R.drawable.m05);
			item05.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		
	}




	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		// ͼƬ��ص����������Ĳ˵���
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

		// ������ص������Ĳ˵���
		case CONTEXT_MENU_ITEM_ID_03:
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			tvText.setBackgroundColor(Color.rgb(red, green, blue));
			break;
			
		case CONTEXT_MENU_ITEM_ID_04:
			// �������ִ�С�� 16~26֮��
			int size = (int) (16 + (Math.random() * 10));
			tvText.setTextSize(size);
			break;			

		case CONTEXT_MENU_ITEM_ID_05:
			Toast.makeText(getBaseContext(), "ѡ�й����˵���", Toast.LENGTH_SHORT).show();
			break;
			
		case CONTEXT_MENU_ITEM_ID_06:
			index = (int)(Math.random() * (imageIDs.length));
			imageSlide.setImageResource(imageIDs[index]);
			Toast.makeText(getBaseContext(), "���һ��ͼƬ:" + index, Toast.LENGTH_SHORT).show();
			break;			
			
		default:
			Toast.makeText(getBaseContext(), "δѡ��", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}




	/**
	 * <p>�����˵���</p>
	 * groupID �� order �о�û��һ���ð�����ô���ö�û�б仯��
	 * <p>�����˵�����Ӧ�¼���  onOptionsItemSelected() ���Զ��� </p>
	 * 
	 * <p>setEnable(false) �ɼ�����ɫ�ģ����ܴ��������¼�</p>
	 * 
	 * <p>setVisable(false) ���ɼ�����˵�ܹ�ͨ�� shortcut �����¼��������Ҳ���û�гɹ���������AndroidManifest.xml �ļ���
	 * ����� Activity �����android:windowSoftInputMode="stateAlwaysVisible" ���ԣ���Ȼ����ͨ�����̴����¼���Ϊʲô��</p>
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int groupId = 5;
		int order = Menu.CATEGORY_SECONDARY;
		//order = Menu.CATEGORY_CONTAINER;
		MenuItem item01 = menu.add(groupId, MENU_ITEM_ID_1, order, "m01");
		item01.setEnabled(false); // �ɼ������ǵ������ܴ����¼�
		item01.setTitle("����1"); // ������ûḲ�������
		item01.setIcon(imageIDs[0]);
		
		MenuItem item02 = menu.add(groupId, MENU_ITEM_ID_2, order, "m02");
		item02.setEnabled(true);
		item02.setTitle("����2"); // ������ûḲ�������
		item02.setIcon(imageIDs[1]);
		
		groupId = 1;
		MenuItem item03 = menu.add(groupId, MENU_ITEM_ID_3, order, "m03");
		item03.setEnabled(false);
		item03.setTitle("����3"); // ������ûḲ�������
		item03.setIcon(imageIDs[2]);
		
		MenuItem item04 = menu.add(groupId, MENU_ITEM_ID_4, order, "m04");
		item04.setEnabled(true);
		item04.setTitle("����4"); // ������ûḲ�������
		item04.setIcon(imageIDs[3]);
		item04.setShortcut('1', 'a');
		item04.setVisible(false); // ���ɼ������ǿ���ͨ�� shortcut �����¼�

		groupId = 2;
		//order = Menu.CATEGORY_ALTERNATIVE;
		MenuItem item05 = menu.add(groupId, MENU_ITEM_ID_5, order, "m05");
		item05.setEnabled(true);
		item05.setTitle("����5"); // ������ûḲ�������
		item05.setIcon(imageIDs[4]);
		item05.setCheckable(false);
		
		MenuItem item06 = menu.add(groupId, MENU_ITEM_ID_6, order, "m06");
		item06.setEnabled(true);
		item06.setTitle("����6"); // ������ûḲ�������
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
			Toast.makeText(getBaseContext(), "û��ѡ�в˵���", Toast.LENGTH_SHORT).show();
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
