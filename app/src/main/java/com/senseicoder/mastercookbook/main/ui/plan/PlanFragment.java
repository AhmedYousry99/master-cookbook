package com.senseicoder.mastercookbook.main.ui.plan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.util.global.UiUtils;


public class PlanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        UiUtils.applyAppBarInsetsOnView(view);
        return view;
    }


}