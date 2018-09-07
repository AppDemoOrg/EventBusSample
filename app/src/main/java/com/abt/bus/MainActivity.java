package com.abt.bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);        
        setContentView(R.layout.activity_main);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectAction(ConnectEvent event) {
        LogHelper.d(TAG, "onConnectAction, status="+event.getStatus());
        if (event.getStatus()) {
            subscribeTopic();
            publish();
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
