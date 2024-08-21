package com.senseicoder.mastercookbook.util.global;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.senseicoder.mastercookbook.R;

import java.util.ArrayList;

public class UiUtils {

    public static void applyAppBarInsetsOnView(View view){
        ViewCompat.setOnApplyWindowInsetsListener(view, (view1, insets) -> {
            int statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            // Adjust the top margin of the toolbar
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view1.getLayoutParams();
            params.topMargin = statusBarHeight;
            view1.setLayoutParams(params);
            return insets;
        });
    }

}
