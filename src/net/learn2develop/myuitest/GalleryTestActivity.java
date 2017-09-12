/**
 * 
 */
package net.learn2develop.myuitest;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Gallery 的用法
 * 主要是绑定一个适配器，通过适配器关联数据--图片，这里Gallery本身创建一个ImageView来显示图片。
 * 然后绑定事件监听器，单击事件触发时，让另外一个ImageView显示一个更大的图片。
 * 
 * 布局文件 activity_gallery_test.xml
 * 属性文件 res/values/attrs.xml
 * 
 * @author LinTeX9527
 *
 */
@SuppressWarnings("deprecation")
public class GalleryTestActivity extends Activity {

	// 要加载的图片资源
	private Integer[] imageIDs = {
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
		setContentView(R.layout.activity_gallery_test);
		
		Gallery gallery = (Gallery) findViewById(R.id.gallerybar);
		gallery.setAdapter(new ImageAdapter(this)); // 绑定自定义的适配器,适配器本身不会响应单击事件，适配器只会把图片数据和视图绑定在一起
		
		// 设置单击响应事件，得到 position，知道图片资源是哪一个，从而在另外一个 ImageView 中显示对应的图片
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getBaseContext(), "点击的图片是" + (position + 1), Toast.LENGTH_SHORT).show();
				
				// 最重要的是得到这个 positioin 参数
				ImageView imageView = (ImageView) findViewById(R.id.imageBig);
				imageView.setImageResource(imageIDs[position]);
			}
		});
	}
	
	
	// 自定义的适配器
	public class ImageAdapter extends BaseAdapter{

		Context context;
		
		int itemBackground;
		
		public ImageAdapter(Context c){
			context = c;
			
			// 设置属性
			TypedArray a = obtainStyledAttributes(R.styleable.GalleryTestAttr);
			itemBackground = a.getResourceId(R.styleable.GalleryTestAttr_android_galleryItemBackground, 0);
			
			a.recycle();
		}

		
		// 返回图片总数量
		@Override
		public int getCount() {
			return imageIDs.length;
		}

		
		// 返回当前的item
		@Override
		public Object getItem(int position) {
			return position;
		}

		
		// 返回当前item的ID
		public long getItemId(int position) {
			return position;
		}

		
		// returns the ImageView view
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//这里的ImageView 是 Gallery本身应该具有的，用来显示图片
			// 适配器的重要就是把图片资源映射到这个 ImageView 中。
			ImageView imageView;
			
			if (convertView == null){
				imageView = new ImageView(context);
				imageView.setImageResource(imageIDs[position]);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
			} else {
				imageView = (ImageView)convertView;
			}

			imageView.setBackgroundResource(itemBackground);
			return imageView;
		} // end of getView
		
	} // end of class ImageAdapter
	
}
