package com.senseicoder.mastercookbook.util.dialogs;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.senseicoder.mastercookbook.R;

public class CircularProgressIndicatorDialog {

    private final Activity activity;
    private AlertDialog alertDialog;

    public CircularProgressIndicatorDialog(Activity activity)
    {
        this.activity = activity;
    }

    public void startProgressBar()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialog_progress_bar,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        alertDialog.show();
    }

    public void dismissProgressBar()
    {
        if(alertDialog != null)
            alertDialog.dismiss();
    }

}
