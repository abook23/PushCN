package com.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by My on 2017/6/8.
 */

public abstract class BaseCoreReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Message message = PushCoreReceiverManager.parsePushData(intent);
        switch (message.getType()) {
            case PushCore.INIT:
                init(context);
                break;
            case PushCore.SET_ALIAS:
                setAlias(context, message.getAlias());
                break;
            case PushCore.UN_ALIAS:
                unsetAlias(context, message.getAlias());
                break;
            case PushCore.STOP_PUSH:
                stopPush(context);
                break;
        }
    }

    public abstract void init(Context context);

    public abstract void setAlias(Context context, String alias);

    public abstract void unsetAlias(Context context, String alias);

    public abstract void stopPush(Context context);
}
