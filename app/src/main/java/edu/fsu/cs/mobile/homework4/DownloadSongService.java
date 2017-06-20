package edu.fsu.cs.mobile.homework4;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;

public class DownloadSongService extends IntentService {
    private static final String ACTION_DOWNLOAD = "edu.fsu.cs.mobile.homework4.action.DOWNLOAD";
    private static final String EXTRA_URL = "edu.fsu.cs.mobile.homework4.extra.URL";
    private DownloadManager downloadManager;

    public DownloadSongService() {
        super("DownloadSongService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                // TODO Handle download
                
            }
        }
    }
}
