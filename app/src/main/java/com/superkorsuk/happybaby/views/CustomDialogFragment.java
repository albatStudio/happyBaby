package com.superkorsuk.happybaby.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 1001078 on 2016. 9. 21..
 */

public class CustomDialogFragment extends DialogFragment {

    String title = "Happy Baby";
    String message = "This is the Message.";
    String okButtonTitle = "OK";
    String cancelButtonTitle = "Cancel";
    CustomDialogListener listener;

    public interface CustomDialogListener {
        public void onDialogOKClick(DialogFragment dialogFragment);
        public void onDialogCancelClick(DialogFragment dialogFragment);
    }

    public CustomDialogFragment() {
    }

    public CustomDialogFragment(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public CustomDialogFragment(String title, String message, String okButtonTitle, String cancelButtonTitle) {
        this.title = title;
        this.message = message;
        this.okButtonTitle = okButtonTitle;
        this.cancelButtonTitle = cancelButtonTitle;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(okButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogOKClick(CustomDialogFragment.this);
            }
        }).setNegativeButton(cancelButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogCancelClick(CustomDialogFragment.this);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CustomDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    // https://developer.android.com/guide/topics/ui/dialogs.html
}
