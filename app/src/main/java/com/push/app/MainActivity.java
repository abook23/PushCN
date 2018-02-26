package com.push.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.push.core.PushCore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button register = (Button) findViewById(R.id.register);
//        String url = "ws://192.168.1.109:8081/echo";
//        WebSocketPush.getInstance().init(url).setListener(new WebSocketPushListener() {
//            @Override
//            public void onMessage(String text) {
//
//            }
//        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 PushCore.init(mContext.getApplicationContext(),true);
//                WebSocketPush.getInstance().send("时间"+new Date().getTime());
            }
        });
    }
}
