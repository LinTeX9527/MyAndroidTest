package com.lintex9527.recorderplayer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.learn2develop.myuitest.R;
import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 音频录制和播放
 * 参见：
 * http://blog.csdn.net/jiangliloveyou/article/details/11218555
 * 
 * @author LinTeX9527
 * 2017-09-17 10:16
 */
public class MyRecorderTestActivity extends Activity implements OnClickListener{

	private TextView stateView;
	
	private Button btnStart, btnStop, btnPlay, btnFinish;
	
	private RecorderTask recorderTask;
	
	private PlayerTask playerTask;
	
	private File audioFile;
	
	private boolean isRecording = true, isPlaying = false;
	
	private static final int MYRECORDER_FREQUENCY = 8000;
	
	// 使用这个 AudioFormat.CHANNEL_CONFIGURATION_MONO 可以录制成功，之前使用 default 值反而不能成功，为什么？
	@SuppressWarnings("deprecation")
	private static final int MYRECORDER_CHANNELCONFIG = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	
	private static final int MYRECORDER_AUDIOENCODING = AudioFormat.ENCODING_PCM_16BIT;
	
	// 录音文件暂存的文件名字
	private static final String MYRECORDING_FILE_PREFIX = "myrecording";

	private static final String MYRECORDING_FILE_SUFFIX = ".pcm";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecorder_test);
		
		stateView = (TextView) findViewById(R.id.view_state);
		stateView.setText("准备开始");
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnFinish = (Button) findViewById(R.id.btnFinish);
		
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		
		// 开始时只能录音
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		btnPlay.setEnabled(false);
		btnFinish.setEnabled(false);
		
		// 创建一个文件，用于保存录制内容
		File fpath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/files");
		fpath.mkdirs();
		try {
			audioFile = File.createTempFile(MYRECORDING_FILE_PREFIX, MYRECORDING_FILE_SUFFIX, fpath);
			Toast.makeText(getBaseContext(), "创建文件成功", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(getBaseContext(), "创建文件失败", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {

		case R.id.btnStart:
			recorderTask = new RecorderTask();
			recorderTask.execute();
			break;

		case R.id.btnStop:
			// 停止录制
			// 在 RecordTask中检测到这个标志为 false 则停止录制
			this.isRecording = false;
			break;
			
		case R.id.btnPlay:
			playerTask = new PlayerTask();
			playerTask.execute();
			break;
			
		case R.id.btnFinish:
			this.isPlaying = false;
			break;
			
		default:
			break;
		}
	}
	
	
	class RecorderTask extends AsyncTask<Void, Integer, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			isRecording = true;
			try {
				
				// 开通输出流到指定文件
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(audioFile)));
				// 根据定义好的配置来获取合适的缓冲区大小
				int bufferSize = AudioRecord.getMinBufferSize(MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING);
				// 实例化 AudioRecord
				AudioRecord myRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize);
				// 定义缓冲区，因为数据位宽是16bit，所以这里是 short 类型的数据
				short[] buffer = new short[bufferSize];
				
				// 开始录制
				myRecord.startRecording();
				
				int pr = 0; // 存储录制进度
				// 定义循环，根据 isRecording 的值来判断是否继续录制
				while(isRecording){
					// 从 buffer中读取字节，返回读取的16bit的个数（因为定义的数据位宽就是16bit的）
					int bufferReadResult = myRecord.read(buffer, 0, buffer.length);
					// 循环将 buffer 中的音频数据写入到 OutputStream 中
					for (int i = 0; i < bufferReadResult; i ++){
						dos.writeShort(buffer[i]);
					}
					
					// 向UI线程报告当前进度
					publishProgress(pr);
					pr ++; // 自增进度值
				}
				
				// 录制结束
				myRecord.stop();
				Toast.makeText(getBaseContext(), "录音成功", Toast.LENGTH_SHORT).show();
				dos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 当在上面方法中调用 publishProgress() 时，该方法触发，在UI线程中执行
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			stateView.setText(values[0].toString());
		}

		// 录音开始了，只能停止
		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(false);
		}

		
		// 录音结束，只能播放录音和开始录音
		@Override
		protected void onPostExecute(Void result) {
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(true);
			btnFinish.setEnabled(false);
		}

		
		
	} // end of class RecorderTask
	
	
	class PlayerTask extends AsyncTask<Void, Integer, Void>{

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			isPlaying = true;
			int bufferSize = AudioTrack.getMinBufferSize(MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING);
			short[] buffer = new short[bufferSize/4]; // 为什么除以4？
			try {
				// 定义输入流，将音频数据读入到 AudioTrack类中，实现播放
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
				
				// 实例化 AudioTrack
				AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize, AudioTrack.MODE_STREAM);
				// 开始播放
				track.play();
				
				// 由于AudioTrack 播放的是流，所以我们需要一边读取一边播放
				while(isPlaying && dis.available()>0){
					int i = 0;
					while(dis.available()>0 && i<buffer.length){
						buffer[i] = dis.readShort();
						i ++;
					}
					// 将数据写入到AudioTrack中
					track.write(buffer, 0, buffer.length);
				}
				
				// 播放结束
				track.stop();
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 开始播放声音，只能停止播放
		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(true);
		}
		
		// 播放结束，能够录音和播放
		@Override
		protected void onPostExecute(Void result) {
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(true);
			btnFinish.setEnabled(false);
		}


	} // end of class PlayerTask
}
