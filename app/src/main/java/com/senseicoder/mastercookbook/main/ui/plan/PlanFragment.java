package com.senseicoder.mastercookbook.main.ui.plan;

import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.senseicoder.mastercookbook.R;


public class PlanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        applyInsetsOnHostFragment(view);
        return view;
    }

    private void applyInsetsOnHostFragment(View view){
        ViewCompat.setOnApplyWindowInsetsListener(view, new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
                int statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
                // Adjust the top margin of the toolbar
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                params.topMargin = statusBarHeight;
                view.setLayoutParams(params);
                return insets;
            }
        });
    }
}