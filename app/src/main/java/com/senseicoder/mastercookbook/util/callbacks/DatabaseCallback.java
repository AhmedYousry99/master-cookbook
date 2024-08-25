package com.senseicoder.mastercookbook.util.callbacks;

public interface DatabaseCallback {

    public void onDatabaseSuccess();

    public void onDatabaseFailure(String message);
}
