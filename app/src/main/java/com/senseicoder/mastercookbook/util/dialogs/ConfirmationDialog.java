package com.senseicoder.mastercookbook.util.dialogs;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.util.callbacks.VoidCallback;

public class ConfirmationDialog {

    private final Activity activity;
    private AlertDialog alertDialog;
    private final VoidCallback onConfirm;
    private final VoidCallback onCancel;
    private Button confirmButton;
    private Button cancelButton;
    private TextView contentTextView;
    private String message;
    private String onConfirmText;
    private String onCancelText;

    public ConfirmationDialog(VoidCallback onConfirm, VoidCallback onCancel, Activity activity)
    {
        this.activity = activity;
        this.onConfirm = onConfirm;
        this.onCancel = onCancel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOnConfirmText() {
        return onConfirmText;
    }

    public void setOnConfirmText(String onConfirmText) {
        this.onConfirmText = onConfirmText;
    }

    public String getOnCancelText() {
        return onCancelText;
    }

    public void setOnCancelText(String onCancelText) {
        this.onCancelText = onCancelText;
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_confirmation,null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.show();


        confirmButton = view.findViewById(R.id.dialogConfirmButton);
        cancelButton = view.findViewById(R.id.dialogCancelButton);
        contentTextView = view.findViewById(R.id.dialogContentTextView);

        contentTextView.setText(message);
        confirmButton.setText(onConfirmText != null ? onConfirmText : activity.getString(R.string.dialog_on_confirm_default));
        cancelButton.setText(onCancelText != null ? onCancelText : activity.getString(R.string.dialog_on_cancel_default));

        confirmButton.setOnClickListener(v -> {
            if(onConfirm != null)
                onConfirm.run();
            dismissDialog();
        });
        cancelButton.setOnClickListener(v -> {
            if(onCancel != null)
                onCancel.run();
            dismissDialog();
        });
        alertDialog.setOnDismissListener(dialog -> {
            if(onCancel != null)
                onCancel.run();
        });


    }

    public void dismissDialog()
    {
        alertDialog.dismiss();
    }
}
