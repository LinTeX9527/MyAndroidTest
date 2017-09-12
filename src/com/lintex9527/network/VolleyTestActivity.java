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
 * Volley ���������
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
	 * ����Volley����ͼƬ
	 * 
	 */
	public void loadImageVolley(){
		
		String imageurl = "http://mm.chinasareview.com/wp-content/uploads/2017a/07/03/01.jpg";
		// �½�һ���������
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
		// ��һ������ imageView ��������ʾͼƬ��
		// �ڶ�������������ͼƬ��Ĭ��ֵ����������У���ʱ�����ͼƬm01��ʾ�ڿؼ�imageView��
		// ����������������ͼƬ����ʱ������ֵ�������粻ͨ���򷵻����ͼƬm02
		ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.m01, R.drawable.m02);
		imageLoader.get(imageurl, listener);
	
	}
}
