package com.push.core.util;

import java.io.IOException;

/**
 * Created by My on 2017/6/7.
 */

public class RomUtil {
    public static boolean DEBUG;
    private static Rom mRom = null;
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";

    private static final String KEY_FLYME_ICON = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_PUBLISHED = "ro.flyme.published";
    private static final String KEY_FLYME_FLYME = "ro.meizu.setupwizard.flyme";


    /**
     * 华为rom
     *
     * @return
     */
    private static boolean isEMUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.containsKey(KEY_EMUI_VERSION_CODE);
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 小米rom
     *
     * @return
     */

    private static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            /*String rom = "" + prop.getProperty(KEY_MIUI_VERSION_CODE, null) + prop.getProperty(KEY_MIUI_VERSION_NAME, null)+prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null);
            Log.d("Android_Rom", rom);*/
            return prop.containsKey(KEY_MIUI_VERSION_CODE)
                    || prop.containsKey(KEY_MIUI_VERSION_NAME)
                    || prop.containsKey(KEY_MIUI_REAL_BLUR)
                    || prop.containsKey(KEY_MIUI_HANDY_MODE_SF);
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 魅族rom
     *
     * @return
     */
    private static boolean isFlyme() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.containsKey(KEY_FLYME_ICON)
                    || prop.containsKey(KEY_FLYME_PUBLISHED)
                    || prop.containsKey(KEY_FLYME_FLYME);
        } catch (final IOException e) {
            return false;
        }
    }


    public static Rom rom() {
        if (mRom != null)
            return mRom;

        if (isEMUI()) {
            mRom = Rom.EMUI;
            return mRom;
        }
        if (isMIUI()) {
            mRom = Rom.MIUI;
            return mRom;
        }
        if (isFlyme()) {
            mRom = Rom.FLYME;
            return mRom;
        }

        mRom = Rom.OTHER;
        return mRom;
    }

    public static String getHandSetInfo() {
        String handSetInfo = "手机型号:" + android.os.Build.MODEL
                + "\n系统版本:" + android.os.Build.VERSION.RELEASE
                + "\n产品型号:" + android.os.Build.PRODUCT
                + "\n版本显示:" + android.os.Build.DISPLAY
               // + "\n基带版本:" + reflect()
                + "\n系统定制商:" + android.os.Build.BRAND
                + "\n设备参数:" + android.os.Build.DEVICE
                + "\n开发代号:" + android.os.Build.VERSION.CODENAME
                + "\nSDK版本号:" + android.os.Build.VERSION.SDK_INT
                + "\nCPU类型:" + android.os.Build.CPU_ABI
                + "\n硬件类型:" + android.os.Build.HARDWARE
                + "\n主机:" + android.os.Build.HOST
                + "\n生产ID:" + android.os.Build.ID
                + "\nROM制造商:" + android.os.Build.MANUFACTURER // 这行返回的是rom定制商的名称
                ;
        return handSetInfo;
    }

}