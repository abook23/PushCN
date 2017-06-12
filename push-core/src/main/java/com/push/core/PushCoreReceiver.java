package com.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class PushCoreReceiver extends BroadcastReceiver implements PushInterface {

    public final static String ON_REGISTER = "com.push.core.onRegister";
    public final static String ON_UN_REGISTER = "com.push.core.onUnRegister";
    public final static String ON_RESUME = "com.push.core.onResume";
    public final static String ON_PAUSED = "com.push.core.onPaused";
    public final static String ON_MESSAGE = "com.push.core.onMessage";
    public final static String ON_NOTIFY_MESSAGE = "com.push.core.onNotifyMessage";
    public final static String ON_MESSAGE_CLICKED = "com.push.core.onMessageClicked";
    public final static String ON_CUSTOM_MESSAGE = "com.push.core.onCustomMessage";
    public final static String ON_ALIAS = "com.push.core.onAlias";
    public final static String ON_UN_ALIAS = "com.push.core.onUnAlias";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Message message = intent.getParcelableExtra("message");
        switch (action) {
            case ON_REGISTER:
                onRegister(context, message.getMessageID());
                break;
            case ON_UN_REGISTER:
                onUnRegister(context);
                break;
            case ON_RESUME:
                onResume(context);
                break;
            case ON_PAUSED:
                onPaused(context);
                break;
            case ON_MESSAGE:
                onMessage(context, message);
                break;
            case ON_MESSAGE_CLICKED:
                onMessageClicked(context, message);
                break;
            case ON_CUSTOM_MESSAGE:
                onCustomMessage(context, message);
                break;
            case ON_ALIAS:
                onAlias(context, message.getAlias());
                break;
            case ON_UN_ALIAS:
                onUnAlias(context, message.getAlias());
                break;
        }
    }

}
