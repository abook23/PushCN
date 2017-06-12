/*
 * Copyright (C) Huawei Technologies Co., Ltd. 2016. All rights reserved.
 * See LICENSE.txt for this sample's licensing information.
 */

package com.push.huawei;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.android.pushagent.api.PushEventReceiver;
import com.push.core.Message;
import com.push.core.PushCoreReceiverManager;
import com.push.core.PushCoreReceiver;

/*
 * 接收Push所有消息的广播接收器
 */
public class PushHuaweiReceiver extends PushEventReceiver {

    private static final String TAG = "PushHuaweiReceiver";

    private static final int RECEIVE_TOKEN_MSG = 0x01;
    private static final int RECEIVE_PUSH_MSG = 0x02;
    private static final int RECEIVE_NOTIFY_CLICK_MSG = 0x03;
    private static final int RECEIVE_TAG_LBS_MSG = 0x04;

    /*
         * 显示Push消息
         */
    public void showPushMessage(Context context, int type, String msg) {

        switch (type) {
            case RECEIVE_PUSH_MSG:
                PushCoreReceiverManager.sendBroadcast(context, PushCoreReceiver.ON_CUSTOM_MESSAGE, new Message(null, 0, msg, null));
                break;
            case RECEIVE_TOKEN_MSG:
                //PushCoreReceiverManager.sendBroadcast(context, PushCoreReceiver.ON_TOKEN_MESSAGE, new Message(null, 0, msg, null));
                break;
            case RECEIVE_NOTIFY_CLICK_MSG:
                PushCoreReceiverManager.sendBroadcast(context, PushCoreReceiver.ON_NOTIFY_MESSAGE, new Message(null, 0, msg, null));
                break;
            case RECEIVE_TAG_LBS_MSG:
               // PushBroadcastManager.sendBroadcast(context, PushReceiver.ON_CUSTOM_MESSAGE, new Message(null, 0, msg, null));
                break;
        }
    }

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        String belongId = extras.getString("belongId");
        String content = "获取token和belongId成功，token = " + token + ",belongId = " + belongId;
        Log.d(TAG, content);
        showPushMessage(context, RECEIVE_TOKEN_MSG, content);
    }


    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        try {
            String content = "收到一条Push消息： " + new String(msg, "UTF-8");
            Log.d(TAG, content);
            showPushMessage(context, RECEIVE_PUSH_MSG, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
            String content = "收到通知附加消息： " + extras.getString(BOUND_KEY.pushMsgKey);
            Log.d(TAG, content);
            showPushMessage(context, RECEIVE_NOTIFY_CLICK_MSG, content);
        } else if (Event.PLUGINRSP.equals(event)) {
            final int TYPE_LBS = 1;
            final int TYPE_TAG = 2;
            int reportType = extras.getInt(BOUND_KEY.PLUGINREPORTTYPE, -1);
            boolean isSuccess = extras.getBoolean(BOUND_KEY.PLUGINREPORTRESULT, false);
            String message = "";
            if (TYPE_LBS == reportType) {
                message = "LBS report result :";
            } else if (TYPE_TAG == reportType) {
                message = "TAG report result :";
            }
            Log.d(TAG, message + isSuccess);
            showPushMessage(context, RECEIVE_TAG_LBS_MSG, message + isSuccess);
        }
        super.onEvent(context, event, extras);
    }
}
