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
 * ��Ƶ¼�ƺͲ���
 * �μ���
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
	
	// ʹ����� AudioFormat.CHANNEL_CONFIGURATION_MONO ����¼�Ƴɹ���֮ǰʹ�� default ֵ�������ܳɹ���Ϊʲô��
	@SuppressWarnings("deprecation")
	private static final int MYRECORDER_CHANNELCONFIG = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	
	private static final int MYRECORDER_AUDIOENCODING = AudioFormat.ENCODING_PCM_16BIT;
	
	// ¼���ļ��ݴ���ļ�����
	private static final String MYRECORDING_FILE_PREFIX = "myrecording";

	private static final String MYRECORDING_FILE_SUFFIX = ".pcm";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecorder_test);
		
		stateView = (TextView) findViewById(R.id.view_state);
		stateView.setText("׼����ʼ");
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnFinish = (Button) findViewById(R.id.btnFinish);
		
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		
		// ��ʼʱֻ��¼��
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		btnPlay.setEnabled(false);
		btnFinish.setEnabled(false);
		
		// ����һ���ļ������ڱ���¼������
		File fpath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/files");
		fpath.mkdirs();
		try {
			audioFile = File.createTempFile(MYRECORDING_FILE_PREFIX, MYRECORDING_FILE_SUFFIX, fpath);
			Toast.makeText(getBaseContext(), "�����ļ��ɹ�", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(getBaseContext(), "�����ļ�ʧ��", Toast.LENGTH_SHORT).show();
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
			// ֹͣ¼��
			// �� RecordTask�м�⵽�����־Ϊ false ��ֹͣ¼��
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
				
				// ��ͨ�������ָ���ļ�
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(audioFile)));
				// ���ݶ���õ���������ȡ���ʵĻ�������С
				int bufferSize = AudioRecord.getMinBufferSize(MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING);
				// ʵ���� AudioRecord
				AudioRecord myRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize);
				// ���建��������Ϊ����λ����16bit������������ short ���͵�����
				short[] buffer = new short[bufferSize];
				
				// ��ʼ¼��
				myRecord.startRecording();
				
				int pr = 0; // �洢¼�ƽ���
				// ����ѭ�������� isRecording ��ֵ���ж��Ƿ����¼��
				while(isRecording){
					// �� buffer�ж�ȡ�ֽڣ����ض�ȡ��16bit�ĸ�������Ϊ���������λ�����16bit�ģ�
					int bufferReadResult = myRecord.read(buffer, 0, buffer.length);
					// ѭ���� buffer �е���Ƶ����д�뵽 OutputStream ��
					for (int i = 0; i < bufferReadResult; i ++){
						dos.writeShort(buffer[i]);
					}
					
					// ��UI�̱߳��浱ǰ����
					publishProgress(pr);
					pr ++; // ��������ֵ
				}
				
				// ¼�ƽ���
				myRecord.stop();
				Toast.makeText(getBaseContext(), "¼���ɹ�", Toast.LENGTH_SHORT).show();
				dos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * �������淽���е��� publishProgress() ʱ���÷�����������UI�߳���ִ��
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			stateView.setText(values[0].toString());
		}

		// ¼����ʼ�ˣ�ֻ��ֹͣ
		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(false);
		}

		
		// ¼��������ֻ�ܲ���¼���Ϳ�ʼ¼��
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
			short[] buffer = new short[bufferSize/4]; // Ϊʲô����4��
			try {
				// ����������������Ƶ���ݶ��뵽 AudioTrack���У�ʵ�ֲ���
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
				
				// ʵ���� AudioTrack
				AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize, AudioTrack.MODE_STREAM);
				// ��ʼ����
				track.play();
				
				// ����AudioTrack ���ŵ�����������������Ҫһ�߶�ȡһ�߲���
				while(isPlaying && dis.available()>0){
					int i = 0;
					while(dis.available()>0 && i<buffer.length){
						buffer[i] = dis.readShort();
						i ++;
					}
					// ������д�뵽AudioTrack��
					track.write(buffer, 0, buffer.length);
				}
				
				// ���Ž���
				track.stop();
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// ��ʼ����������ֻ��ֹͣ����
		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(true);
		}
		
		// ���Ž������ܹ�¼���Ͳ���
		@Override
		protected void onPostExecute(Void result) {
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(true);
			btnFinish.setEnabled(false);
		}


	} // end of class PlayerTask
}
