package com.example.a.p03_gps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2016-07-27.
 */
public class TestSqlLiteOpenHelper extends SQLiteOpenHelper {

    public TestSqlLiteOpenHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE gps (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "lat TEXT, lgt TEXT, inserttime TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
