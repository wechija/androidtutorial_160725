package com.example.a.a08_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MY_THREAD){
                textView.setText("count"+msg.arg1);
                progressBar.setProgress(msg.arg1);
            }
        }
    };

    private static final  int MY_THREAD = 100;

    class MyThread extends Thread  {

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                try{
                    sleep(100);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }

                Log.d("thread","count:"+i);
                Message message = new Message();
                message.what = MY_THREAD;
                message.arg1 = i;
                handler.sendMessage(message);
            }
        }
    }

    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        MyThread th = new MyThread();
        th.start();

    }
}
