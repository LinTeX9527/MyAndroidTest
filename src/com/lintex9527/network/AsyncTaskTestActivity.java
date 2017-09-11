package com.lintex9527.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.learn2develop.myuitest.ActivityCollector;
import net.learn2develop.myuitest.BaseActivity;
import net.learn2develop.myuitest.R;

public class AsyncTaskTestActivity extends BaseActivity {

	private TextView tvShowWebPage = null;
	private ProgressBar progressBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask_test);
		
		
		// 添加管理
		String description = getResources().getString(R.string.test_asynctask);
		ActivityCollector.modifyDescription(this, description);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar.setProgress(0);
		tvShowWebPage = (TextView) findViewById(R.id.tvShowWebPage);
		
		// 单击按钮访问百度首页，将获取的文本信息填充到 TextView 中。
		findViewById(R.id.btnGetWebPage).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressBar.setProgress(0);
				readURL("https://www.bilibili.com");
			}
		});
	}

	/**
	 * 异步访问网络，获取网页文本信息，将结果填充到TextView中
	 * @param url
	 */
	public void readURL(String url){
		new AsyncTask<String, Float, String>(){

			
			@Override
			protected String doInBackground(String... params) {
				try {
					URL url = new URL(params[0]);
					URLConnection connection = url.openConnection();
					long total = connection.getContentLength(); // 获取读取对象的长度
					InputStream iStream = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(iStream);
					BufferedReader br = new BufferedReader(isr);
					String line;
					StringBuilder builder = new StringBuilder();
					
					while((line = br.readLine()) != null){
						Thread.sleep(200);
						builder.append(line);
						publishProgress((float)builder.toString().length() * 100 / total);
					}
					br.close();
					isr.close();
					iStream.close();
					
					return builder.toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			/**
			 * 当 doInBackground() 执行完毕时触发这个方法，把返回值传递给UI线程
			 */
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				progressBar.setProgress(100);
				tvShowWebPage.setText(result);
			}

			
			/**
			 * 每次在 doInBackground()中调用 publishProgress() 时会触发这个方法，主要用来在UI线程中显示当前进度
			 */
			@Override
			protected void onProgressUpdate(Float... values) {
				progressBar.setProgress(Math.round(values[0]));
			}

			@Override
			protected void onCancelled(String result) {
				// TODO Auto-generated method stub
				super.onCancelled(result);
			}

			@Override
			protected void onCancelled() {
				// TODO Auto-generated method stub
				super.onCancelled();
			}
			
			

			
			
		}.execute(url);
	}

}
