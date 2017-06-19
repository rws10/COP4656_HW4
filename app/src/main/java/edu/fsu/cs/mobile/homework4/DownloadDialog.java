package edu.fsu.cs.mobile.homework4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

public class DownloadDialog extends DialogFragment {
    Resources res =  getResources();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String title = res.getString(R.string.download_song);
        builder.setTitle(title);

        builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity act = (MainActivity) getActivity();
                act.doPositiveClick();
            }
        });
        return builder.create();
    }
}