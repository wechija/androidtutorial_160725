package com.example.a.a02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Toast.makeText(NewActivity.this, "id:"+id , Toast.LENGTH_SHORT).show();
    }

    public void onFinishClick(View v){
        Intent intent = new Intent();
        intent.putExtra("res","qqqqqqq");
        setResult(RESULT_OK,intent);
        finish();

    }
}
