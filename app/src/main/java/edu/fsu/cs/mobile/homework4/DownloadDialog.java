package edu.fsu.cs.mobile.homework4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class DownloadDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText editText = new EditText((getActivity()));
        editText.setId(R.id.songUrl);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Download");
        //https://stackoverflow.com/questions/18799216/how-to-make-a-edittext-box-in-a-dialog
        builder.setView(editText);


        builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dialogView = getDialog();
                //https://stackoverflow.com/questions/27037381/gettext-from-a-edittext-in-a-dialogfragment
                EditText editText = (EditText) dialogView.findViewById(R.id.songUrl); //
                //String url = editText.getText().toString();
String url = "https://files.freemusicarchive.org/music%2FMusic_for_Video%2FPodington_Bear %2FUplifting%2FPodington_Bear_-_Arise.mp3";
                MainActivity act = (MainActivity) getActivity();
                act.downloadSong(url);
            }
        });
        return builder.create();
    }
}