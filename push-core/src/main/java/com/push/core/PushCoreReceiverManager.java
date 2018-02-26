package com.push.core;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by My on 2017/6/9.
 */

public class PushCoreReceiverManager {

    public static final String INTENT_DATA_PUSH = "push_data";

    public static void sendBroadcast(Context context, String action,String type, Parcelable data) {
        Intent intent = new Intent(action);
        intent.putExtra("type",type);
        intent.putExtra(INTENT_DATA_PUSH, data);
        context.sendBroadcast(intent);
    }

    public static <T extends Parcelable> T parsePushData(Intent intent) {
        return intent.getParcelableExtra(INTENT_DATA_PUSH);
    }

    public static void sendBroadcast(Context context, String action,  Parcelable data) {
        Intent intent = new Intent(action);
//        intent.putExtra("type",type);
        intent.putExtra(INTENT_DATA_PUSH, data);
        context.sendBroadcast(intent);
    }
}
