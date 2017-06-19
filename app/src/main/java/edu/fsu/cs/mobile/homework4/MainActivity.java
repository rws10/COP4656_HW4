package edu.fsu.cs.mobile.homework4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

}
