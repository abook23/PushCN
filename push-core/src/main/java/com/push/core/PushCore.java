package com.push.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.push.core.util.RomUtil;

import java.text.NumberFormat;

public class PushCore {

    public static String TAG = "PushCore";

    public static boolean DEBUG = true;

    private final static String RECEIVER_ROM_DEFAULT = "com.push.core.rom.jiguang";
    private final static String RECEIVER_ROM_MIUI = "com.push.core.rom.miui";
    private final static String RECEIVER_ROM_FLYME = "com.push.core.rom.flyme";
    private final static String RECEIVER_ROM_EMUI = "com.push.core.rom.emui";

    public static final int INIT = 0x01;
    public static final int SET_ALIAS = 0x02;
    public static final int STOP_PUSH = 0x03;
    public static final int UN_ALIAS = 0x04;

    private static final String INTEGRATION_PUSH_MIUI = "INTEGRATION_PUSH_MIUI";
    private static final String INTEGRATION_PUSH_EMUI = "INTEGRATION_PUSH_EMUI";
    private static final String INTEGRATION_PUSH_FLYME = "INTEGRATION_PUSH_FLYME";
    private static final String INTEGRATION_PUSH_JGUANG = "INTEGRATION_PUSH_JGUANG";


    public static boolean isJoinMiui, isJoiEmui, isJoinFlyme, isJoinDefault;
    private static Context sContext;
    private static String action;

    /**
     * 初始化
     * 优先选择 开发者集成的 rom 推送 ,否则使用 默认推送
     *
     * @param context
     */
    public static void init(Context context) {
        init(context, false);
    }

    public static void init(Context context, boolean debug) {
        sContext = context.getApplicationContext();
        DEBUG = debug;
        try {
            integrationRomPush(sContext);//系统 开发者集成了那些 推送
            if (DEBUG)
                Log.d(TAG, RomUtil.getHandSetInfo());
            sendInitBroadcast();//
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写在 相应 的 mainfest --- meta-data
     *
     * @param context c
     * @throws PackageManager.NameNotFoundException
     */

    private static void integrationRomPush(Context context) throws PackageManager.NameNotFoundException {
        ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        Bundle bundle = appInfo.metaData;
        isJoinMiui = bundle.getBoolean(INTEGRATION_PUSH_MIUI, false);
        isJoiEmui = bundle.getBoolean(INTEGRATION_PUSH_EMUI, false);
        isJoinFlyme = bundle.getBoolean(INTEGRATION_PUSH_FLYME, false);
        isJoinDefault = bundle.getBoolean(INTEGRATION_PUSH_JGUANG, false);
        StringBuffer sb = new StringBuffer();
        sb.append("PUSH集成:");
        sb.append(" 小米推送").append(isJoinMiui);
        sb.append(" 华为推送").append(isJoiEmui);
        sb.append(" 魅族推送").append(isJoinFlyme);
        sb.append(" 极光推送").append(isJoinDefault);
        printLog(sb.toString());
        if (!isJoiEmui && !isJoinMiui && !isJoinFlyme && !isJoinDefault)
            throw new NullPointerException("至少集成一个推送");
    }

    /**
     * 绑定别名
     * 华为推送不支持
     *
     * @param alias
     */
    public static void setAlias(String alias) {
        Log.d(TAG, "setAlias:" + alias);
        Log.e(TAG, "华为推送不支持 setAlias" + alias);
        sendSetAliasBroadcast(alias);
    }

    /**
     * 解除别名绑定
     *
     * @param alias
     */
    public static void unsetAlias(String alias) {
        sendUnsetAliasBroadcast(alias);
    }

    /**
     * 停止服务
     */
    public static void stopPush() {
        sendStopPushBroadcast();
    }


    private static void sendInitBroadcast() {
        PushCore.printLog("正在选择最优推送.....");
        Message message = new Message();
        message.setType(INIT);
        PushCoreReceiverManager.sendBroadcast(sContext, getRomAction(), message);
    }

    private static void sendSetAliasBroadcast(String alias) {
        Message message = new Message();
        message.setType(SET_ALIAS);
        message.setAlias(alias);
        PushCoreReceiverManager.sendBroadcast(sContext, getRomAction(), message);
    }

    private static void sendUnsetAliasBroadcast(String alias) {
        Message message = new Message();
        message.setType(UN_ALIAS);
        message.setAlias(alias);
        PushCoreReceiverManager.sendBroadcast(sContext, getRomAction(), message);
    }

    private static void sendStopPushBroadcast() {
        Message message = new Message();
        message.setType(STOP_PUSH);
        PushCoreReceiverManager.sendBroadcast(sContext, getRomAction(), message);
    }

    private static String getRomAction() {
        if (action != null)
            return action;
        switch (RomUtil.rom()) {
            case MIUI:
                action = isJoinMiui ? RECEIVER_ROM_MIUI : RECEIVER_ROM_DEFAULT;
                break;
            case EMUI:
                action = isJoiEmui ? RECEIVER_ROM_EMUI : RECEIVER_ROM_DEFAULT;
                break;
            case FLYME:
                action = isJoinFlyme ? RECEIVER_ROM_FLYME : RECEIVER_ROM_DEFAULT;
                break;
            default:
                if (isJoinDefault) action = RECEIVER_ROM_DEFAULT;
                else if (isJoinMiui) action = RECEIVER_ROM_DEFAULT;
                else if (isJoinFlyme) action = RECEIVER_ROM_DEFAULT;
                else if (isJoiEmui) action = RECEIVER_ROM_DEFAULT;
                break;
        }
        //action = RECEIVER_ROM_DEFAULT;
        return action;
    }

    public static String getMetaData(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object data = appInfo.metaData.get(key);
            if (data != null)
                return data.toString();
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    // 方法一：NumberFormat
    public static String formatFloat(float d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }

    public static void printLog(String content) {
        if (DEBUG) {
            Log.d(TAG, content);
        }
    }

}
