package net.learn2develop.myuitest;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * ImageSwitcher ��ʵ������ ImageView��ֻ���������л�ͼƬʱ����ָ���л��Ķ�̬Ч������������ѡ�����FADE_IN, FADE_OUT��
 * ��ͬ��ʹ�÷�����ָ��ͼƬ��Դ�� setImageResource(resourceID)
 * 
 * ���ﻹ��ʹ�� Gallery ��սʿ���е�ͼƬ���� ImageSwitcher ����ʾѡ�е�ͼƬ�Ĵ�ͼ������ ImageSwitcher �л�ͼƬʱ�Ķ�̬Ч�������š�
 * 
 * ������Դ�� res/layout/activity_imageswitcher_test.xml
 * ������Դ�� res/values/attrs.xml
 * 
 * @author LinTeX9527
 * 2017-09-12 11��08
 * 
 */
@SuppressWarnings("deprecation")
public class ImageSwitcherTestActivity extends Activity implements ViewFactory{

	// Ҫ���ص�ͼƬ��Դ
	Integer[] imageIDs = {
			R.drawable.m01,
			R.drawable.m02,
			R.drawable.m03,
			R.drawable.m04,
			R.drawable.m05,
			R.drawable.m06
	};
	
	private ImageSwitcher imageSwitcher;
	//static int oldposition = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageswitcher_test);
		
		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher01);
		//imageSwitcher.setImageResource(imageIDs[oldposition]);
		
		// ������趨 Factory������Ҫ�ֶ��������view���μ� setFactory() ����˵����
		imageSwitcher.setFactory(this);
		
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery01);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {

			/**
			 * ��Gallery��ѡ��ĳ��ͼƬʱ������ ImageSwither���л���Ӧ��ͼƬ��
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				imageSwitcher.setImageResource(imageIDs[position]);
				
//				if (position != oldposition){
//					imageSwitcher.setImageResource(imageIDs[position]);
//					oldposition = position;
//				}
			}
		});
		
	}

	
	/**
	 * ����� ViewFactory �ķ���
	 */
	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return imageView;
	}
	
	
	/**
	 * �Զ��������������ͼƬ��Դ�󶨵� Gallery �ϡ�
	 * @author LinTeX9527
	 *
	 */
	public class ImageAdapter extends BaseAdapter{
		
		private Context context;
		private int itemBackground;
		
		public ImageAdapter(Context c){
			context = c;
			
			// ��������
			TypedArray a = obtainStyledAttributes(R.styleable.GalleryTestAttr);
			itemBackground = a.getResourceId(R.styleable.GalleryTestAttr_android_galleryItemBackground, 0);
		}
		

		@Override
		public int getCount() {
			return imageIDs.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = null;
			
			if(convertView == null){
				imageView = new ImageView(context);
				imageView.setImageResource(imageIDs[position]);
				
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
				imageView.setBackgroundResource(itemBackground);
				
			} else {
				imageView = (ImageView) convertView;
			}
			
			return imageView;
		}
	} // end of ImageAdapter

}
