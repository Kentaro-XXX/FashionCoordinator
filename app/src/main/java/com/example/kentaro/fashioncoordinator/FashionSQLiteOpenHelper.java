package com.example.kentaro.fashioncoordinator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 0000139099 on 2015/07/16.
 */
public class FashionSQLiteOpenHelper extends SQLiteOpenHelper {
    static final String DB = "sqlite_fashion.db";
    static final int DB_VERSION = 1;
    static final String CREATE_TABLE = "create table fashionTable ( _id integer primary key autoincrement, _picData text );";
    static final String DROP_TABLE = "drop table fashionTable";

    public FashionSQLiteOpenHelper(Context c){
        super(c, DB, null, DB_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
