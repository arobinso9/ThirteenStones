package com.example.thirteenstones.lib;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;

import com.example.thirteenstones.R;


public class Utils
{
    /*
    Night Mode-related helper methods
     */
    public static void setNightModeOnOffFromPreferenceValue(Context context, String keyNightMode) {
        setNightModeOnOrOff(isNightModePrefOn(context, keyNightMode));
    }

    public static void setNightModeOnOrOff(boolean setToOn) {
        int onMode  =  Build.VERSION.SDK_INT < 28 ? MODE_NIGHT_YES : MODE_NIGHT_FOLLOW_SYSTEM;
        AppCompatDelegate.setDefaultNightMode(setToOn ? onMode : MODE_NIGHT_NO);
    }

    private static boolean isNightModePrefOn(Context context, String keyNightMode) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getBoolean(keyNightMode, true);
    }

    // AlertDialog
    // -----------
    /**
     * Shows an Android (nicer) equivalent to JOptionPane
     *
     * @param strTitle Title of the Dialog box
     * @param strMsg   Message (body) of the Dialog box
     */
    public static void showAlertDialog(@NonNull FragmentActivity activity,
                                       @NonNull String strTitle,
                                       @NonNull String strMsg,
                                       @Nullable DialogInterface.OnClickListener okListener,
                                       @Nullable DialogInterface.OnClickListener cancelListener) {
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(strTitle, strMsg, okListener, cancelListener);

        // Avoid duplicate dialogs
        if (activity.getSupportFragmentManager().findFragmentByTag("AlertDialog") == null) {
            fragment.show(activity.getSupportFragmentManager(), "AlertDialog");
        }
    }

    public static void showInfoDialog (FragmentActivity activity, int titleID, int msgID)
    {
        showInfoDialog (activity, activity.getString (titleID), activity.getString (msgID));
    }

    @SuppressWarnings ("WeakerAccess")
    public static void showInfoDialog (FragmentActivity activity, String strTitle, String strMsg)
    {
        showAlertDialog (activity, strTitle, strMsg);
    }

    @SuppressWarnings ("WeakerAccess")
    public static void showOkCancelDialog (FragmentActivity activity, String strTitle, String strMsg,
                                           DialogInterface.OnClickListener okListener,
                                           DialogInterface.OnClickListener cancelListener)
    {
        showAlertDialog (activity, strTitle, strMsg, okListener, cancelListener);
    }

    public static void showYesNoDialog (FragmentActivity activity, String strTitle, String strMsg,
                                        DialogInterface.OnClickListener okListener,
                                        DialogInterface.OnClickListener cancelListener)
    {
        showAlertDialog (activity, strTitle, strMsg, okListener, cancelListener);
    }

    private static void showAlertDialog (FragmentActivity activity, String strTitle, String strMsg)
    {
        showAlertDialog (activity, strTitle, strMsg, null, null);
    }
}
