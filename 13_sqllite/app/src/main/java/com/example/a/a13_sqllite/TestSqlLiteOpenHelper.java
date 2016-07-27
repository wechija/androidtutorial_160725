package com.example.a.a13_sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2016-07-27.
 */
public class TestSqlLiteOpenHelper extends SQLiteOpenHelper{

    public TestSqlLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    /**
     * 설치될때 한번
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE STUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "NAME TEXT, AGE INTEGER, ADDRESS TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
