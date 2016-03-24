package com.example.shaonaji.weihuai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shaona Ji on 2016/3/12.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int versioin){
        super(context,name,cursorFactory,versioin);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists users;");
        db.execSQL("create table if not exists users(" + "id integer primary key," + "user_name varchar," + "user_account varchar," + "user_pwd varchar," + "user_location varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
