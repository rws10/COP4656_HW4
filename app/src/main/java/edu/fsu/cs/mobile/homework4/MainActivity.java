package edu.fsu.cs.mobile.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO setup UI

        // TODO Do we need to handle any Extras?
    }

    // onClick method for Play button
    public void play(View v) {
        // TODO play MediaPlayer
    }

    // onclick method for Pause button
    public void pause(View v) {
        // TODO pause MediaPlayer
    }

    // onClick method for Stop button
    public void stop(View v) {
        // TODO stop MediaPlayer
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
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_URL", url);

        Intent intent = new Intent(MainActivity.this, DownloadSongService.class);
        intent.setAction("ACTION_DOWNLOAD");
        intent.putExtras(bundle);
        startActivity(intent);
    }


    }
