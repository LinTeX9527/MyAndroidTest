package com.lintex9527.recorderplayer;

import java.io.IOException;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 官方示例代码，参见网页：
 * https://developer.android.com/guide/topics/media/mediarecorder.html
 * 
 * 最好在使用 Android 8下编译运行。
 * @author LinTeX9527
 */
@TargetApi(23) public class AudioRecordTestActivity extends Activity {
	
	private static final String LOG_TAG = "AudioRecordTestActivity";
	
	private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
	private static String mFileName = null;
	
	private RecordButton mRecordButton = null;
	private MediaRecorder mRecorder = null;
	
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;
	
	
	private static final String BTN_TEXT_REC_START = "REC -- start";
	private static final String BTN_TEXT_REC_STOP  = "REC -- stop";
	private static final String BTN_TEXT_PLY_START = "PLAY -- start";
	private static final String BTN_TEXT_PLY_STOP  = "PLAY -- stop";
	
	// Requesting permission to RECORD_AUDIO
	private boolean permissionToRecordAccepted = false;
	private String[] permissions = {Manifest.permission.RECORD_AUDIO};
	
	
	/**
	 * 代码中动态申请权限，让用户选择接收还是不接受，在这个回调函数这里进行鉴别。
	 * 这里申请了 Manifest.permission.RECORD_AUDIO 权限，如果用户不同意授权，则直接退出这个APP。
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
		switch(requestCode){
		case REQUEST_RECORD_AUDIO_PERMISSION:
			permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
			break;
		}
		if (!permissionToRecordAccepted){
			finish();
		}
	}// end of onRequestPermissionsResult()
	
	
	// 用户选择启动录音还是停止录音。
	private void onRecord(boolean start){
		if (start){
			startRecording();
		} else {
			stopRecording();
		}
	}
	
	// 用户选择开始播放录音还是停止播放。
	private void onPlay(boolean start){
		if (start){
			startPlaying();
		} else {
			stopPlaying();
		}
	}
	
	/**
	 * 播放录音
	 * 1、必须使用 MediaPlayer 实例化对象；
	 * 2、设置播放的数据源；
	 * 3、准备播放 prepare() 是必须的。
	 */
	private void startPlaying(){
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.setLooping(true);	// 为了实验，设置为循环播放
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "play prepare() failed.");
			e.printStackTrace();
		}
	} // end of startPlaying().
	
	
	/**
	 * 停止播放录音，也需要立即释放资源，使得 MediaPlayer　实例化对象变成 null。
	 */
	private void stopPlaying(){
		mPlayer.pause();
		mPlayer.release();
		mPlayer = null;
	} // end of stopPlaying()
	
	
	/**
	 * 开始录音
	 * 1、实例化 MediaRecorder　对象；
	 * 2、设置音频源，一般情况是 MIC；
	 * 3、设置音频数据输出格式；
	 * 4、设置输出文件的路径；
	 * 5、设置音频编码格式；
	 * 6、准备录音 mRecorder.prepare() 是必须的；
	 * 7、开始录音 mRecorder.start()。
	 */
	private void startRecording(){
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "record prepare() failed!");
			e.printStackTrace();
		}
		
		mRecorder.start();
	}
	
	
	/**
	 * 停止录音
	 * 1、停止录音 stop()，注意停止之后要想再次录音就必须要重新完整的配置一次 mRecorder，
	 * 例如上面的 startRecording() 过程。
	 * 2、停止录音之后必须立即释放对象占用的资源，mRecorder.release() 是必须的。
	 */
	private void stopRecording(){
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}
	
	
	/**
	 * 自定义按钮，继承自 Button，主要是自定义了按钮单击响应的事件，值得借鉴。
	 * @author LinTeX9527
	 */
	class RecordButton extends Button{
		boolean mStartRecording = true;
		
		// 自定义按钮单击响应事件，通过状态控制是否录音。
		OnClickListener clicker = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording){
					setText(BTN_TEXT_REC_STOP);
				} else {
					setText(BTN_TEXT_REC_START);
				}
				mStartRecording = !mStartRecording;
				
			}
		};

		// 构造函数中设置了按钮单击响应事件。
		public RecordButton(Context context) {
			super(context);
			setText(BTN_TEXT_REC_START);
			setOnClickListener(clicker);
		}
	} // end of RecordButton.
	
	
	class PlayButton extends Button{
		boolean mStartPlaying = true;
		
		OnClickListener clicker = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying){
					setText(BTN_TEXT_PLY_STOP);
				} else {
					setText(BTN_TEXT_PLY_START);
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public PlayButton(Context context) {
			super(context);
			setText(BTN_TEXT_PLY_START);
			setOnClickListener(clicker);
		}
	}


	/**
	 * 构造函数中初始化资源：
	 * 1、录音文件暂存文件名字 mFileName；
	 * 2、请求权限，申请  Manifest.permission.RECORD_AUDIO；
	 * 3、设定布局
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Record to the external cache directory for visibility
		mFileName = getExternalCacheDir().getAbsolutePath();
		mFileName += "/audiorecordtest.3gp";
		
		requestPermissions(permissions, REQUEST_RECORD_AUDIO_PERMISSION);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		mRecordButton = new RecordButton(this);
		mRecordButton.setTextSize((float) 24.0);
		ll.addView(mRecordButton, 
				new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				0));
		
		mPlayButton = new PlayButton(this);
		mPlayButton.setTextSize((float) 24.0);
		ll.addView(mPlayButton, 
				new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 
						ViewGroup.LayoutParams.WRAP_CONTENT, 
						0));
		setContentView(ll);
	}


	/**
	 * 当前 Activity 停止的时候做的工作
	 */
	@Override
	protected void onStop() {
		super.onStop();
		
		if (mRecorder != null){
			mRecorder.release();
			mRecorder = null;
		}
		
		if (mPlayer != null){
			mPlayer.release();
			mPlayer = null;
		}
	}
	
}
