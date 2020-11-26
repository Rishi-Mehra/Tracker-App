package com.rm.myapp.services;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.rm.myapp.R;
import com.rm.myapp.ui.fragments.SendLocationFragment;
import com.sac.speech.GoogleVoiceTypingDisabledException;
import com.sac.speech.Speech;
import com.sac.speech.SpeechDelegate;
import com.sac.speech.SpeechRecognitionNotAvailable;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MyService extends Service implements SpeechDelegate, Speech.stopDueToDelay {

  public static SpeechDelegate delegate;

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    //TODO do something useful
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        ((AudioManager) Objects.requireNonNull(
          getSystemService(Context.AUDIO_SERVICE))).setStreamMute(AudioManager.STREAM_SYSTEM, true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    Speech.init(this);
    delegate = this;
    Speech.getInstance().setListener(this);

    if (Speech.getInstance().isListening()) {
      Speech.getInstance().stopListening();
      muteBeepSoundOfRecorder();
    } else {
      System.setProperty("rx.unsafe-disable", "True");
      RxPermissions.getInstance(this).request(Manifest.permission.RECORD_AUDIO).subscribe(granted -> {
        if (granted) { // Always true pre-M
          try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(null, this);
          } catch (SpeechRecognitionNotAvailable exc) {
            //showSpeechNotSupportedDialog();

          } catch (GoogleVoiceTypingDisabledException exc) {
            //showEnableGoogleVoiceTyping();
          }
        } else {
          Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG).show();
        }
      });
      muteBeepSoundOfRecorder();
    }
    return Service.START_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent) {
    //TODO for communication return IBinder implementation
    return null;
  }

  @Override
  public void onStartOfSpeech() {
  }

  @Override
  public void onSpeechRmsChanged(float value) {

  }

  @Override
  public void onSpeechPartialResults(List<String> results) {
    for (String partial : results) {
      Log.d("Result", partial+"");
    }
  }

  @Override
  public void onSpeechResult(String result) {
    Log.d("Result", result+"");
    System.out.println("++++++result  "+result);
    if (!TextUtils.isEmpty(result)) {
      Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
      if (result.equalsIgnoreCase("send location")){
        GPSTracker gps = new GPSTracker(MyService.this);
        if(gps.canGetLocation()) {

          double latitude = gps.getLatitude();
          double longitude = gps.getLongitude();
          SendLocationFragment sendLocationFragment = new SendLocationFragment();
          sendLocationFragment.sendLocation(MyService.this,String.valueOf(latitude),String.valueOf(longitude));
           } else {
          // Can't get location.
          // GPS or network is not enabled.
          // Ask user to enable GPS/network in settings.
          gps.showSettingsAlert();
        }
      }
    }
  }

  @Override
  public void onSpecifiedCommandPronounced(String event) {
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        ((AudioManager) Objects.requireNonNull(
          getSystemService(Context.AUDIO_SERVICE))).setStreamMute(AudioManager.STREAM_SYSTEM, true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (Speech.getInstance().isListening()) {
      muteBeepSoundOfRecorder();
      Speech.getInstance().stopListening();
    } else {
      RxPermissions.getInstance(this).request(Manifest.permission.RECORD_AUDIO).subscribe(granted -> {
        if (granted) { // Always true pre-M
          try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(null, this);
          } catch (SpeechRecognitionNotAvailable exc) {
            //showSpeechNotSupportedDialog();

          } catch (GoogleVoiceTypingDisabledException exc) {
            //showEnableGoogleVoiceTyping();
          }
        } else {
          Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG).show();
        }
      });
      muteBeepSoundOfRecorder();
    }
  }

  /**
   * Function to remove the beep sound of voice recognizer.
   */
  private void muteBeepSoundOfRecorder() {
    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    if (amanager != null) {
      amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
      amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
      amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
      amanager.setStreamMute(AudioManager.STREAM_RING, true);
      amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
    }
  }

  @Override
  public void onTaskRemoved(Intent rootIntent) {
    //Restarting the service if it is removed.
    PendingIntent service =
      PendingIntent.getService(getApplicationContext(), new Random().nextInt(),
        new Intent(getApplicationContext(), MyService.class), PendingIntent.FLAG_ONE_SHOT);

    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    assert alarmManager != null;
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, service);
    super.onTaskRemoved(rootIntent);
  }
}