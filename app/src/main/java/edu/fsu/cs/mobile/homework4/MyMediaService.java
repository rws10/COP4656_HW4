package edu.fsu.cs.mobile.homework4;

import android.app.Service;
import android.content.Intent;
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
    }
}
