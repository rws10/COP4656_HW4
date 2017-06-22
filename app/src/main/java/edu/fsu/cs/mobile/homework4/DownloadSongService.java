package edu.fsu.cs.mobile.homework4;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DownloadSongService extends IntentService {
    private static final String ACTION_DOWNLOAD = "edu.fsu.cs.mobile.homework4.action.DOWNLOAD";
    public static final String EXTRA_URL = "edu.fsu.cs.mobile.homework4.extra.URL";
    private DownloadManager downloadManager;
    private long mydownloadReference;
    private BroadcastReceiver receiverDownloadComplete;
    private BroadcastReceiver receiverNotificationClicked;

    public DownloadSongService() {
        super("DownloadSongService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(ACTION_DOWNLOAD)) {
                // prepare the notification builder
                final String url = intent.getStringExtra(EXTRA_URL);
                //PendingIntent pIntent = (PendingIntent) intent.getParcelableExtra("pending");
                String songName;
                // TODO Handle download
                //https://101apps.co.za/articles/using-the-downloadmanager-to-manage-your-downloads.html
                Uri songUri = Uri.parse(url);
                //songName = getSongName(songUri);
                downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(songUri)
                        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                                DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("Demo")
                        .setDescription("Something useful. No, really.")
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "test.mp4");
                request.setDestinationInExternalFilesDir(DownloadSongService.this, Environment.DIRECTORY_DOWNLOADS, "song");
                mydownloadReference = downloadManager.enqueue(request);
                //request.setDescription(songName);

                IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

                receiverDownloadComplete = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                        if(mydownloadReference == reference){
                            DownloadManager.Query query = new DownloadManager.Query();
                            query.setFilterById(reference);
                            Cursor cursor = downloadManager.query(query);
                        }
                    }
                };

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cdalbumsmall)
                        .setContentTitle("Downloading: Arise")
                        .setContentText("Podington Bear - Arise");

                Intent resultIntent = new Intent(this, MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(MainActivity.class);



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
