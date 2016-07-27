package com.example.a.a19_br2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = 0;
            if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
                level = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
            }else if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            }else if(intent.getAction().equals("abcdefg")){
                level = -1;
            }

            Toast.makeText(context, "batt:"+level, Toast.LENGTH_SHORT).show();
        }
    };

    public void onBntClick(View v){
        Intent intent = new Intent("abcdefg");
        sendBroadcast(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction("abcdefg");
        registerReceiver(receiver,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
