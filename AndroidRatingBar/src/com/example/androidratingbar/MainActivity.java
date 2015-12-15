package com.example.androidratingbar;




import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends Activity implements OnClickListener {
	/**
	 * 音量调节
	 */
	private RatingBar volume_ratingbar;
	
	private Button addButton;
	private Button subButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		volume_ratingbar = (RatingBar)findViewById(R.id.volume_ratingbar);
		subButton = (Button)findViewById(R.id.volume_sub);
		addButton = (Button)findViewById(R.id.volume_add);
		subButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
		initControl();
		
		
	}
	private static AudioManager mAudioManager = null;
	/**
	 * 用于音量控制
	 * 提示声音音量
	 */
	public static final int AUDIO_MUSIC=AudioManager.STREAM_NOTIFICATION;
	/**
	 * 当前提示音量
	 */
	int volume;
	/**
	 * 最大提示音量值为7
	 */
	int maxVolume;
	/**
	 * 初始化控制逻辑
	 */
	private void initControl() {
		
		if (mAudioManager == null) {

			mAudioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
		}
		
		volume = mAudioManager.getStreamVolume(AUDIO_MUSIC);
		maxVolume = mAudioManager.getStreamMaxVolume(AUDIO_MUSIC);
		
		volume_ratingbar.setRating(volume);
		if (volume == 7) {

			//mImageVeiwAdd.setBackgroundResource(R.mipmap.volume_dialog_add_grey);

		} else if (volume == 0) {

			//mImageVeiwSub.setBackgroundResource(R.mipmap.volume_dialog_sub_grey);
		}
	}
	
	/**
	 * 增加音量
	 */
	private synchronized void addOnClick(){
		
		int vm = mAudioManager.getStreamVolume(AUDIO_MUSIC) + 1;
		if (vm == 1) {

			//mImageVeiwSub.setBackgroundResource(R.drawable.btn_volume_sub);
		}
		if (vm <= maxVolume) {
			
			//mTextVolume.setText(vm + "");
			volume_ratingbar.setRating(vm);
			mAudioManager.setStreamVolume(AUDIO_MUSIC,vm, AudioManager.FLAG_VIBRATE);
			if (vm == maxVolume) {

				//mImageVeiwAdd.setBackgroundResource(R.mipmap.volume_dialog_add_grey);
			}

		}
	}
	/**
	 * 减小音量
	 */
	private synchronized void subOnClick(){
		

		int vm = mAudioManager.getStreamVolume(AUDIO_MUSIC) - 1;
		if (vm == maxVolume - 1) {
			//mImageVeiwAdd.setBackgroundResource(R.drawable.btn_volume_add);
		}
		if (vm >= 0) {
			//mTextVolume.setText(vm + "");
			volume_ratingbar.setRating(vm);
			mAudioManager.setStreamVolume(AUDIO_MUSIC,vm, AudioManager.FLAG_VIBRATE);
			if (vm == 0) {

				//mImageVeiwSub.setBackgroundResource(R.mipmap.volume_dialog_sub_grey);
			}

		}
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.volume_add:

			addOnClick();
			
			break;
		case R.id.volume_sub:
			
			subOnClick();
			
			break;
		default:
			break;
		}
		
	}
}
