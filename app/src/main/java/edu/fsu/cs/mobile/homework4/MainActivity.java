package edu.fsu.cs.mobile.homework4;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static edu.fsu.cs.mobile.homework4.DownloadSongService.EXTRA_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO setup UI
        Button playButton = (Button) findViewById(R.id.button_Play);
        Button stopButton = (Button) findViewById(R.id.button_Stop);
        Button pauseButton = (Button) findViewById(R.id.button_Pause);
        pauseButton.setVisibility(View.INVISIBLE);
        playButton.setEnabled(false);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);

        // TODO Do we need to handle any Extras?


    }

    // onClick method for Play button
    public void play(View v) {
        // TODO play MediaPlayer
        Toast.makeText(this, "Play was pressed", Toast.LENGTH_SHORT).show();
        songStarted();
    }

    // onclick method for Pause button
    public void pause(View v) {
        // TODO pause MediaPlayer
        Toast.makeText(this, "Pause was pressed", Toast.LENGTH_SHORT).show();
        songStopped();
    }

    // onClick method for Stop button
    public void stop(View v) {
        // TODO stop MediaPlayer
        Toast.makeText(this, "Stop was pressed", Toast.LENGTH_SHORT).show();
        songStopped();
        stopButtonPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download:
                DownloadDialog downloadDialog = new DownloadDialog();
                downloadDialog.show(getFragmentManager(), "download");
                // Launch a dialog that allows for a url to be entered to download a song.

                break;
            case R.id.close:
                finish();
                break;
        }
        return true;
    }

    public void downloadSong(String url) {
        // Launch the DownloadSongService intent with url as an Intenet Extra
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
       // bundle.putE("EXTRA_URL", url);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent() ,0);
        final Intent intent = new Intent(this, DownloadSongService.class);
        intent.setAction("ACTION_DOWNLOAD");
        intent.putExtra(EXTRA_URL, url);
        //intent.putExtra("pending", contentIntent);
        startService(intent);
    }

// Activate the pause button and set it to visible and deactivate the play button and set it to invisible
    private void songStarted() {
        Button pauseButton = (Button) findViewById(R.id.button_Pause);
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setEnabled(false);
        Button playButton = (Button) findViewById(R.id.button_Play);
        playButton.setVisibility(View.INVISIBLE);
        playButton.setEnabled(false);
        Button stopButton = (Button)findViewById(R.id.button_Stop);
        stopButton.setEnabled(true);
    }

    // Activate the play button and set it to visible and deactivate the pause button and set it to invisible
    private void songStopped() {
        Button pauseButton = (Button) findViewById(R.id.button_Pause);
        pauseButton.setVisibility(View.INVISIBLE);
        pauseButton.setEnabled(false);
        Button playButton = (Button) findViewById(R.id.button_Play);
        playButton.setVisibility(View.VISIBLE);
        playButton.setEnabled(true);
    }

    private void stopButtonPressed(){
        Button stopButton = (Button)findViewById(R.id.button_Stop);
        stopButton.setEnabled(false);
    }

}
