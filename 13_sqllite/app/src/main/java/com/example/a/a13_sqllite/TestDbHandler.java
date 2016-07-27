package com.example.a.a13_sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by a on 2016-07-27.
 */
public class TestDbHandler {
    TestSqlLiteOpenHelper helper;

    public TestDbHandler(Context context){
        helper = new TestSqlLiteOpenHelper(context,"people",null,1);

    }

    public  void insert(String name, int age, String address){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("address",address);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("student",null,values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student","name=?",new String[]{name});
    }

    public void update(String name, int newAge){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age",newAge);
        db.update("student",values,"name = ?",new String[]{name});
    }

    public void selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("student",null,null,null,null,null,null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));
            Log.d("sqllite::","name:"+name+"::age::"+age+"::address::"+address);
        }

    }
}
