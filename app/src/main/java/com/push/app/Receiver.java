package com.push.app;


import android.content.Context;
import android.util.Log;

import com.push.core.Message;
import com.push.core.PushCoreReceiver;

/**
 * Created by My on 2017/6/8.
 */

public class Receiver extends PushCoreReceiver {
    private String TAG = "PushReceiver";

    @Override
    public void onRegister(Context context, String registerID) {
        Log.d(TAG, "onRegister :" + registerID);
    }

    @Override
    public void onUnRegister(Context context) {
        Log.d(TAG, "onUnRegister :");
    }

    @Override
    public void onPaused(Context context) {
        Log.d(TAG, "onPaused :");
    }

    @Override
    public void onResume(Context context) {
        Log.d(TAG, "onResume :");
    }

    @Override
    public void onMessage(Context context, Message message) {
        Log.d(TAG, "onMessage :"+message.getMessage());
    }

    @Override
    public void onCustomMessage(Context context, Message message) {
        Log.d(TAG, "onCustomMessage :"+message.getMessage());
    }

    @Override
    public void onMessageClicked(Context context, Message message) {
        Log.d(TAG, "onMessageClicked :"+message.getMessage());
    }


    @Override
    public void onAlias(Context context, String alias) {
        Log.d(TAG, "onAlias :"+alias);
    }

    @Override
    public void onUnAlias(Context context, String alias) {
        Log.d(TAG, "onUnAlias :"+alias);
    }

}
