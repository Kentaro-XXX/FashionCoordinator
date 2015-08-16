package com.example.kentaro.fashioncoordinator.databaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kentaro on 2015/08/01.
 */
public class FashionSQLiteTemplate {
    public FashionSQLiteTemplate(Context context){

        SQLiteDatabase db;
        // FashionSQLiteOpenHelper Class
        FashionSQLiteOpenHelper hlpr = new FashionSQLiteOpenHelper(context);

        // Get writable database
        db = hlpr.getWritableDatabase();

        // Get tops image path by ID.
        int xxx = 100;  // temp ID
        String topsPath = hlpr.getTopsImagePathById(db, xxx);
    }
}
