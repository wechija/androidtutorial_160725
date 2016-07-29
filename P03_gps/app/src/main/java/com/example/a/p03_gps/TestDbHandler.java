package com.example.a.p03_gps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a on 2016-07-27.
 */
public class TestDbHandler {
    TestSqlLiteOpenHelper helper;

    public TestDbHandler(Context context){
        helper = new TestSqlLiteOpenHelper(context, "gps", null, 1);
    }

    public void insert(String lat, String lgt, String inserttime){
        ContentValues values = new ContentValues();
        values.put("lat", lat);
        values.put("lgt", lgt);
        values.put("inserttime", inserttime);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("gps", null, values);
    }

    public void delete(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("gps","1=1",null);
    }



    public List<Map<String,String>> selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("gps", null, null, null, null, null, null);
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        while(c.moveToNext()){
            String lat = c.getString(c.getColumnIndex("lat"));
            String lgt = c.getString(c.getColumnIndex("lgt"));
            String inserttime = c.getString(c.getColumnIndex("inserttime"));
            Map<String,String> data = new HashMap<String,String>();
            data.put("lat",lat);
            data.put("lgt",lgt);
            data.put("inserttime",inserttime);
            result.add(data);
            Log.d("sqlite", "lat:"+lat+"lgt:"+lgt+"inserttime:"+inserttime);
        }
        return result;
    }
}
