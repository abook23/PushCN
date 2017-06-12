package com.push.flyme;

import android.content.Context;
import android.util.Log;

import com.meizu.cloud.pushsdk.PushManager;
import com.push.core.BaseCoreReceiver;

import static com.push.core.PushCore.getMetaData;

/**
 * Created by My on 2017/6/8.
 */

public class PushCoreReceiver extends BaseCoreReceiver {
    private String appId, appKey;

    @Override
    public void init(Context context) {
        appId = getMetaData(context, "FLYME_APPID");
        appKey = getMetaData(context, "FLYME_APPKEY");
        Log.d("FLYME_PUSH", "FLYME_APPID:" + appId + " - FLYME_APPKEY:" + appKey);
        PushManager.register(context, appId, appKey);
    }

    @Override
    public void setAlias(Context context, String alias) {
        String pushId = PushManager.getPushId(context);
        PushManager.subScribeAlias(context, appId, appKey, pushId, alias);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        String pushId = PushManager.getPushId(context);
        PushManager.unSubScribeAlias(context, appId, appKey, pushId, alias);
    }

    @Override
    public void stopPush(Context context) {
        PushManager.unRegister(context, appId, appKey);
    }

}
