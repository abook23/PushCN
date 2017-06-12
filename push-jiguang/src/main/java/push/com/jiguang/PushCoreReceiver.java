package push.com.jiguang;

import android.content.Context;
import android.content.Intent;

import com.push.core.BaseCoreReceiver;
import com.push.core.BuildConfig;
import com.push.core.PushCore;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by My on 2017/6/8.
 */

public class PushCoreReceiver extends BaseCoreReceiver {

    @Override
    public void init(Context context) {
        JPushInterface.init(context);
        if (PushCore.DEBUG){
            String JPUSH_APPKEY = PushCore.getMetaData(context,"JPUSH_APPKEY");
            PushCore.printLog("初始化极光推送 JPUSH_APPKEY:" + JPUSH_APPKEY);
        }
    }

    @Override
    public void setAlias(Context context,String alias) {
        JPushInterface.setAlias(context, alias, null);
    }

    @Override
    public void unsetAlias(Context context,String alias) {

    }

    @Override
    public void stopPush(Context context) {
        JPushInterface.stopPush(context);
    }

}
