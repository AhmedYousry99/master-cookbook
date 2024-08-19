package com.senseicoder.mastercookbook.initial.presenter;

public interface InitialActivityPresenter {

    public void loginUsingGoogleEmail(String tokenId);

    public void loginUsingFacebookEmail();

    public void loginUsingGuest();

    public void requestUserGoogleAccount();
}
