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
 * ImageSwitcher 其实类似于 ImageView，只不过它在切换图片时可以指定切换的动态效果，例如这里选择的是FADE_IN, FADE_OUT。
 * 相同的使用方法，指定图片资源： setImageResource(resourceID)
 * 
 * 这里还是使用 Gallery 来战士所有的图片，用 ImageSwitcher 来显示选中的图片的大图，加上 ImageSwitcher 切换图片时的动态效果，酷炫。
 * 
 * 布局资源： res/layout/activity_imageswitcher_test.xml
 * 属性资源： res/values/attrs.xml
 * 
 * @author LinTeX9527
 * 2017-09-12 11：08
 * 
 */
@SuppressWarnings("deprecation")
public class ImageSwitcherTestActivity extends Activity implements ViewFactory{

	// 要加载的图片资源
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
		
		// 如果不设定 Factory，就需要手动添加两个view，参见 setFactory() 方法说明。
		imageSwitcher.setFactory(this);
		
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery01);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {

			/**
			 * 在Gallery中选中某个图片时，将在 ImageSwither中切换对应的图片。
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
	 * 这个是 ViewFactory 的方法
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
	 * 自定义的适配器，把图片资源绑定到 Gallery 上。
	 * @author LinTeX9527
	 *
	 */
	public class ImageAdapter extends BaseAdapter{
		
		private Context context;
		private int itemBackground;
		
		public ImageAdapter(Context c){
			context = c;
			
			// 设置属性
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
