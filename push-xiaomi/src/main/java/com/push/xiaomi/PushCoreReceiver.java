package com.push.xiaomi;

import android.content.Context;
import android.util.Log;

import com.push.core.BaseCoreReceiver;
import com.push.core.PushCore;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;


/**
 * Created by My on 2017/6/8.
 */

public class PushCoreReceiver extends BaseCoreReceiver {

    private String TAG = "PushCoreReceiver";

    @Override
    public void init(Context context) {
        if (PushCore.DEBUG){
            LoggerInterface newLogger = new LoggerInterface() {
                @Override
                public void setTag(String tag) {
                    // ignore
                }

                @Override
                public void log(String content, Throwable t) {
                    Log.d(TAG, content, t);
                }

                @Override
                public void log(String content) {
                    Log.d(TAG, content);
                }
            };
            Logger.setLogger(context, newLogger);
        }
        String appId = PushCore.getMetaData(context, "MIUI_APPID");
        String appKey = PushCore.getMetaData(context, "MIUI_APPKEY");
        MiPushClient.registerPush(context, appId, appKey);
        PushCore.printLog("初始化小米推送 MIUI_APPID:" + appId + " - MIUI_APPKEY:" + appKey);
    }

    @Override
    public void setAlias(Context context, String alias) {
        MiPushClient.setAlias(context, alias, null);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        MiPushClient.unsetAlias(context, alias, null);
    }

    @Override
    public void stopPush(Context context) {
        MiPushClient.unregisterPush(context);
    }

}
