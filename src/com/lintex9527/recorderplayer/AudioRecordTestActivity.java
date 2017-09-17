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
 * �ٷ�ʾ�����룬�μ���ҳ��
 * https://developer.android.com/guide/topics/media/mediarecorder.html
 * 
 * �����ʹ�� Android 8�±������С�
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
	 * �����ж�̬����Ȩ�ޣ����û�ѡ����ջ��ǲ����ܣ�������ص�����������м���
	 * ���������� Manifest.permission.RECORD_AUDIO Ȩ�ޣ�����û���ͬ����Ȩ����ֱ���˳����APP��
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
	
	
	// �û�ѡ������¼������ֹͣ¼����
	private void onRecord(boolean start){
		if (start){
			startRecording();
		} else {
			stopRecording();
		}
	}
	
	// �û�ѡ��ʼ����¼������ֹͣ���š�
	private void onPlay(boolean start){
		if (start){
			startPlaying();
		} else {
			stopPlaying();
		}
	}
	
	/**
	 * ����¼��
	 * 1������ʹ�� MediaPlayer ʵ��������
	 * 2�����ò��ŵ�����Դ��
	 * 3��׼������ prepare() �Ǳ���ġ�
	 */
	private void startPlaying(){
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.setLooping(true);	// Ϊ��ʵ�飬����Ϊѭ������
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "play prepare() failed.");
			e.printStackTrace();
		}
	} // end of startPlaying().
	
	
	/**
	 * ֹͣ����¼����Ҳ��Ҫ�����ͷ���Դ��ʹ�� MediaPlayer��ʵ���������� null��
	 */
	private void stopPlaying(){
		mPlayer.pause();
		mPlayer.release();
		mPlayer = null;
	} // end of stopPlaying()
	
	
	/**
	 * ��ʼ¼��
	 * 1��ʵ���� MediaRecorder������
	 * 2��������ƵԴ��һ������� MIC��
	 * 3��������Ƶ���������ʽ��
	 * 4����������ļ���·����
	 * 5��������Ƶ�����ʽ��
	 * 6��׼��¼�� mRecorder.prepare() �Ǳ���ģ�
	 * 7����ʼ¼�� mRecorder.start()��
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
	 * ֹͣ¼��
	 * 1��ֹͣ¼�� stop()��ע��ֹ֮ͣ��Ҫ���ٴ�¼���ͱ���Ҫ��������������һ�� mRecorder��
	 * ��������� startRecording() ���̡�
	 * 2��ֹͣ¼��֮����������ͷŶ���ռ�õ���Դ��mRecorder.release() �Ǳ���ġ�
	 */
	private void stopRecording(){
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}
	
	
	/**
	 * �Զ��尴ť���̳��� Button����Ҫ���Զ����˰�ť������Ӧ���¼���ֵ�ý����
	 * @author LinTeX9527
	 */
	class RecordButton extends Button{
		boolean mStartRecording = true;
		
		// �Զ��尴ť������Ӧ�¼���ͨ��״̬�����Ƿ�¼����
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

		// ���캯���������˰�ť������Ӧ�¼���
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
	 * ���캯���г�ʼ����Դ��
	 * 1��¼���ļ��ݴ��ļ����� mFileName��
	 * 2������Ȩ�ޣ�����  Manifest.permission.RECORD_AUDIO��
	 * 3���趨����
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
	 * ��ǰ Activity ֹͣ��ʱ�����Ĺ���
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
