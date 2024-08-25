package com.senseicoder.mastercookbook.features.main.presenter;


import com.senseicoder.mastercookbook.features.main.view.MainView;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepository;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

public class MainPresenterImpl implements MainPresenter{

    final DataRepository dataRepository;
    final AuthenticationRepository authRepository;
    final MainView mainView;

    public MainPresenterImpl(DataRepository dataRepository, AuthenticationRepository authRepository, MainView mainView) {
        this.dataRepository = dataRepository;
        this.mainView = mainView;
        this.authRepository = authRepository;
    }

    @Override
    public boolean isUserGuest() {
        return authRepository.isUserGuest();
    }

    @Override
    public void signOut() {
        authRepository.signOut();
    }
}
