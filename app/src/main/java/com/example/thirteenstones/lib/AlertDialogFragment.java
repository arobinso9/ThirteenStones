package com.example.thirteenstones.lib;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.thirteenstones.R;

public class AlertDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";

    public static AlertDialogFragment newInstance(String title, String message,
                                                  DialogInterface.OnClickListener okListener,
                                                  DialogInterface.OnClickListener cancelListener) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        fragment.okListener = okListener;
        fragment.cancelListener = cancelListener;
        return fragment;
    }

    private DialogInterface.OnClickListener okListener;
    private DialogInterface.OnClickListener cancelListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = requireContext();
        Bundle arguments = getArguments();

        String title = "Alert";
        String message = "No Message Specified";

        if (arguments != null) {
            title = arguments.getString(ARG_TITLE, title);
            message = arguments.getString(ARG_MESSAGE, message);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(true);

        boolean positiveButtonSet = false;
        boolean negativeButtonSet = false;
        if (okListener != null) {
            builder.setPositiveButton(android.R.string.ok, okListener);
            positiveButtonSet = true;
        }

        if (cancelListener != null) {
            builder.setNegativeButton(android.R.string.cancel, cancelListener);
            negativeButtonSet = true;
        }

        // If no buttons were set at all (neither ok nor cancel listener provided),
        // add a default neutral "OK" button that just dismisses.
        if (!positiveButtonSet && !negativeButtonSet) {
            builder.setNeutralButton(android.R.string.ok, (dialog, which) -> {
                dismiss(); // Explicitly dismiss, or leave empty if auto-dismiss on click is default
            });
        }

        return builder.create();
    }
}