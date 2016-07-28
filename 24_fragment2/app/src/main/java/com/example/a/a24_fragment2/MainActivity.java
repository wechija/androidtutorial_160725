package com.example.a.a24_fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fr = fm.findFragmentById(R.id.frame);

        switch(v.getId()){
            case R.id.btnAdd:
                if(fr == null){
                    CountFragment cf = new CountFragment();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.frame,cf,"counter");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case R.id.btnRemove:
                if(fr != null){
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.remove(fr);
                    ft.commit();
                }
                break;
            case R.id.btnReplace:
                if(fr != null){
                    FragmentTransaction ft = fm.beginTransaction();
                    if(fr.getTag().equals("counter")){
                        TextFragment tf = new TextFragment();
                        ft.replace(R.id.frame,tf,"text");
                    }else{
                        CountFragment cf = new CountFragment();
                        ft.replace(R.id.frame,cf,"counter");
                    }
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case R.id.btnHide:
                if(fr != null){
                    FragmentTransaction ft = fm.beginTransaction();
                    if(fr.isHidden()){
                        ft.show(fr);
                    }else{
                        ft.hide(fr);
                    }
                    ft.commit();
                }
                break;
        }
    }
}
