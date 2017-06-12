# PushCN

rom推送 > 默认推送 
    
module 的 builde.gradle 集成如下
```java
android{
    ...
    defaultConfig {
            ndk {
                //选择要添加的对应cpu类型的.so库。
                abiFilters 'armeabi'
                //, 'armeabi-v7a', 'armeabi-v8a'// 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
            }
            manifestPlaceholders = [
                    //极光推送
                    JPUSH_PKGNAME: applicationId,
                    JPUSH_APPKEY : "4b1a7b20836ddfce229bb802", //JPush上注册的包名对应的appkey.
                    JPUSH_CHANNEL: "aa", //JPush上注册的包名对应的appkey.
    
                    //小米推送
                    MIUI_APPID   : "/2882303761517584626",
                    MIUI_APPKEY  : "/5691758486626",
    
                    //华为推送.
                    HUWEI_APPID  : "100022659",
    
                    //魅族.
                    FLYME_APPID  : "5691758486626",
                    FLYME_APPKEY : "5691758486626",
            ]
        }
    ...
}

dependencies {
    ...
    compile project(':push-core')   //必须集成
    compile project(':push-xiaomi') //可选
    compile project(':push-huawei') //可选
    compile project(':push-flyme')  //可选
    compile project(':push-jiguang')//默认推送 //可以,但必须集成其他推送 push-xiaomi > push-flyme > push-huawei
    ...
}

```

AndroidManifest.xml集成如下
```java
   <!--小米推送 修改包名-->
    <permission
        android:name="你的包名.permission.MIPUSH_RECEIVE"
        android:protectionLevel="signature" />
    <uses-permission android:name="你的包名.permission.MIPUSH_RECEIVE" />

    <!--魅族推送 修改包名-->
    <uses-permission android:name="你的包名.permission.MESSAGE"/>

```