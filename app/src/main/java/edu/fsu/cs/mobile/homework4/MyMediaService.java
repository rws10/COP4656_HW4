package edu.fsu.cs.mobile.homework4;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

public class MyMediaService extends Service {

    public MyMediaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Might need to modify depending on Service implementation
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void loadPlayer(String fileuri) {
        // TODO prepare MediaPlayer with fileuri
        // possibly activate the play button here.
        Uri songUri = Uri.parse(fileuri);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setDataSource(getApplicationContext(), songUri);
        //mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
