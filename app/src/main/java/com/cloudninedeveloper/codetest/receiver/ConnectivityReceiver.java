package com.cloudninedeveloper.codetest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.cloudninedeveloper.codetest.utils.Utils;

/**
 * @Author myothiha
 * Created 16/01/2023 at 12:45 PM.
 **/

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        //check connection is available or not
        if (wifi.isConnected() || mobile.isConnected()) Utils.isOnline.postValue(true);
        else Utils.isOnline.postValue(false);
    }
}


