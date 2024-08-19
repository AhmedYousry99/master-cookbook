package com.senseicoder.mastercookbook.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.main.ui.home.presenter.HomePresenter;
import com.senseicoder.mastercookbook.main.ui.home.presenter.HomePresenterImpl;
import com.senseicoder.mastercookbook.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;

public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter presenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        applyInsetsOnHostFragment(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenterImpl(this, DataRepositoryImpl.getInstance(
                FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir())
        ));
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