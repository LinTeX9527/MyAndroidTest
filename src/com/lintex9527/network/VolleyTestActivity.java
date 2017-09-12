package com.lintex9527.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.learn2develop.myuitest.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Volley 网络请求库
 * @author LinTeX9527
 */
public class VolleyTestActivity extends Activity {

	private ImageView imageView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley_test);
		
		init();
		loadImageVolley();
	}
	
	public void init(){
		imageView = (ImageView) findViewById(R.id.imageview_volley);
	}

	/**
	 * 测试Volley加载图片
	 * 
	 */
	public void loadImageVolley(){
		
		String imageurl = "http://mm.chinasareview.com/wp-content/uploads/2017a/07/03/01.jpg";
		// 新建一个请求队列
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
		ImageCache imageCache = new ImageCache() {
			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};

		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		// 第一个参数 imageView 是用来显示图片的
		// 第二个参数是请求图片的默认值，例如加载中，暂时用这个图片m01显示在控件imageView中
		// 第三个参数是请求图片出错时候的替代值，如网络不通，则返回这个图片m02
		ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.m01, R.drawable.m02);
		imageLoader.get(imageurl, listener);
	
	}
}
