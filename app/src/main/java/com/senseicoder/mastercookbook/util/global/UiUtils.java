package com.senseicoder.mastercookbook.util.global;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.senseicoder.mastercookbook.R;

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

    public static void preventSoftKeyboardFromPushingUpFragment(Activity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    public static NavHostFragment getNavHostFragment(FragmentActivity fragmentActivity) {
        return (NavHostFragment) fragmentActivity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_home);
    }

    public static NavController getNavController(FragmentActivity fragmentActivity){
        NavHostFragment navHostFragment = (NavHostFragment) fragmentActivity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_home);
        return navHostFragment.getNavController();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
