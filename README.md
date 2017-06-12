# PushCN

```java
    rom推送 > 默认推送 
    
    compile project(':push-core')   //必须集成
    compile project(':push-xiaomi') //可选
    compile project(':push-huawei') //可选
    compile project(':push-flyme')  //可选
    compile project(':push-jiguang')//默认推送 //可以,但必须集成其他推送 push-xiaomi > push-flyme > push-huawei
```