package com.senseicoder.mastercookbook.util.global;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Random;

public class RandomUtils {

    public static String getRandomLowercaseLetter() {
        Random random = new Random();
        return String.valueOf((char) (random.nextInt(26) + 'a'));
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be a null
        return (netInfo != null && netInfo.isConnected());
    }
}
