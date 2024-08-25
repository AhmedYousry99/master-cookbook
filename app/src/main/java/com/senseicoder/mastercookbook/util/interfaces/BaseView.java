package com.senseicoder.mastercookbook.util.interfaces;

import io.reactivex.rxjava3.core.Observable;

public interface BaseView {

    Observable<Boolean> initiateNetworkObserver();

    void handleConnection(boolean connected);

    void handleError(Throwable e);
}
