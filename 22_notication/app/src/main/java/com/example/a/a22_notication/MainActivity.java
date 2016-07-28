package com.example.a.a22_notication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pIndent = PendingIntent.getActivity(
                        MainActivity.this,0,intent,0
                );
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize=4;

                Bitmap bitMap = BitmapFactory.decodeResource(getResources(),R.drawable.hydrangeas,options);
                Notification noti = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("TITLE")
                        .setContentText("TEXT")
                        .setSubText("SUBTEXT")
                        .setSmallIcon(R.drawable.hydrangeas)
                        .setLargeIcon(bitMap)
                        .setContentIntent(pIndent)
                        .setAutoCancel(true)// 자동으로 꺼질경우
                        .build();
                manager.notify(1234,noti);
            }
        }, 2000);
    }
}
