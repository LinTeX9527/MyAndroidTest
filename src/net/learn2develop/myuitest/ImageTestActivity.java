/**
 * 
 */
package net.learn2develop.myuitest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * @author LinTeX9527
 * 使用 ImageView 显示图片，可以设置此控件的背景色，padding，以及单击的响应事件
 *
 */
public class ImageTestActivity extends BaseActivity {

	
	ImageView imageView = null;
	static int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_test);
	
		// 添加管理
		String description = getResources().getString(R.string.test_image);
		ActivityCollector.modifyDescription(this, description);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setBackgroundColor(Color.GREEN); // ImageView 的背景只会占用自身的空间，有padding的话会显示背景色
		imageView.setPadding(33, 43, 33, 53);
		imageView.setCropToPadding(true);
		//imageView.setMaxWidth(150);
		imageView.setImageResource(R.drawable.pandorabox);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				count ++;
				if (count >= 2){
					count = 0;
				}
				if (count % 2 == 0){
					imageView.setImageResource(R.drawable.pandorabox);
				} else {
					imageView.setImageResource(R.drawable.ic_launcher);
				}
			}
		});
	}

}
