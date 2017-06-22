package edu.fsu.cs.mobile.homework4;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DownloadSongService extends IntentService {
    private static final String ACTION_DOWNLOAD = "edu.fsu.cs.mobile.homework4.action.DOWNLOAD";
    public static final String EXTRA_URL = "edu.fsu.cs.mobile.homework4.extra.URL";
    private DownloadManager downloadManager;
    private long mydownloadRefernce;
    private BroadcastReceiver reciverDownloadComplete;
    private BroadcastReceiver receiverNotificationClicked;

    public DownloadSongService() {
        super("DownloadSongService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(ACTION_DOWNLOAD)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                //PendingIntent pIntent = (PendingIntent) intent.getParcelableExtra("pending");
                String songName;
                // TODO Handle download
                //https://101apps.co.za/articles/using-the-downloadmanager-to-manage-your-downloads.html
                Uri songUri = Uri.parse(url);
                songName = getSongName(songUri);
                DownloadManager.Request request = new DownloadManager.Request(songUri);
                mydownloadRefernce = downloadManager.enqueue(request);
                request.setDescription(songName);
                request.setDestinationInExternalFilesDir(DownloadSongService.this, Environment.DIRECTORY_DOWNLOADS, "song");

                /*try {
                    pIntent.send(1);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }*/


            }
        }
    }
private String getSongName(Uri songUri){
String songName = null;
    if (songUri.getScheme().equals("audio")) {
        songName = songUri.getLastPathSegment();
    } else {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(songUri, new String[]{
                    MediaStore.Audio.AudioColumns.DISPLAY_NAME
            }, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                songName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
                Log.d(TAG, "name is " + songName);
            }
        } finally {

            if (cursor != null) {
                cursor.close();
            }
        }
    }
    return songName;
}

}
