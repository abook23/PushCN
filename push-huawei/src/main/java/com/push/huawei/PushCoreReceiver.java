package com.push.huawei;

import android.content.Context;
import android.util.Log;

import com.huawei.android.pushagent.api.PushManager;
import com.push.core.BaseCoreReceiver;

/**
 * Created by My on 2017/6/8.
 */

public class PushCoreReceiver extends BaseCoreReceiver{

    private String TAG = "HUAWEI";
    @Override
    public void init(Context context) {
        PushManager.enableReceiveNormalMsg(context,true);
    }

    @Override
    public void setAlias(Context context,String alias) {
        Log.d(TAG,"华为推送,不支持");
    }

    @Override
    public void unsetAlias(Context context,String alias) {
        Log.d(TAG,"华为推送,不支持");
    }

    @Override
    public void stopPush(Context context) {
        PushManager.enableReceiveNormalMsg(context,false);
    }

}
