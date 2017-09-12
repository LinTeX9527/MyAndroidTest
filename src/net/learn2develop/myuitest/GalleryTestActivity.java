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
 * Gallery ���÷�
 * ��Ҫ�ǰ�һ����������ͨ����������������--ͼƬ������Gallery������һ��ImageView����ʾͼƬ��
 * Ȼ����¼��������������¼�����ʱ��������һ��ImageView��ʾһ�������ͼƬ��
 * 
 * �����ļ� activity_gallery_test.xml
 * �����ļ� res/values/attrs.xml
 * 
 * @author LinTeX9527
 *
 */
@SuppressWarnings("deprecation")
public class GalleryTestActivity extends Activity {

	// Ҫ���ص�ͼƬ��Դ
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
		gallery.setAdapter(new ImageAdapter(this)); // ���Զ����������,��������������Ӧ�����¼���������ֻ���ͼƬ���ݺ���ͼ����һ��
		
		// ���õ�����Ӧ�¼����õ� position��֪��ͼƬ��Դ����һ�����Ӷ�������һ�� ImageView ����ʾ��Ӧ��ͼƬ
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getBaseContext(), "�����ͼƬ��" + (position + 1), Toast.LENGTH_SHORT).show();
				
				// ����Ҫ���ǵõ���� positioin ����
				ImageView imageView = (ImageView) findViewById(R.id.imageBig);
				imageView.setImageResource(imageIDs[position]);
			}
		});
	}
	
	
	// �Զ����������
	public class ImageAdapter extends BaseAdapter{

		Context context;
		
		int itemBackground;
		
		public ImageAdapter(Context c){
			context = c;
			
			// ��������
			TypedArray a = obtainStyledAttributes(R.styleable.GalleryTestAttr);
			itemBackground = a.getResourceId(R.styleable.GalleryTestAttr_android_galleryItemBackground, 0);
			
			a.recycle();
		}

		
		// ����ͼƬ������
		@Override
		public int getCount() {
			return imageIDs.length;
		}

		
		// ���ص�ǰ��item
		@Override
		public Object getItem(int position) {
			return position;
		}

		
		// ���ص�ǰitem��ID
		public long getItemId(int position) {
			return position;
		}

		
		// returns the ImageView view
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//�����ImageView �� Gallery����Ӧ�þ��еģ�������ʾͼƬ
			// ����������Ҫ���ǰ�ͼƬ��Դӳ�䵽��� ImageView �С�
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
