package com.example.kentaro.fashioncoordinator.databaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

/**
 * Created by Kentaro on 2015/08/16.
 */
public class FashionInitialData {
    static final String TOPS_PATH_1 = "FashionCoordinator/tops/xxx1.JPG";
    static final String TOPS_PATH_2 = "FashionCoordinator/tops/xxx2.JPG";
    static final String BOTTOMS_PATH_1 = "FashionCoordinator/bottoms/xxx1.JPG";
    static final String BOTTOMS_PATH_2 = "FashionCoordinator/bottoms/xxx2.JPG";


    static final String TOPS_ID_1 =    "T_S1";
    static final String TOPS_ID_2 =    "T_S2";
    static final String BOTTOMS_ID_1 = "B_S1";
    static final String BOTTOMS_ID_2 = "B_S2";


    final String LOG_TAG = "FashionInitialData_LOG";

    private String topsPath1;
    private String topsPath2;
    private String bottomsPath1;
    private String bottomsPath2;
    private Context context;

    public FashionInitialData(Context con){
        context = con;
    }

    public void setFashionInitialData(){
        String externalStorageDir = Environment.getExternalStorageState();

        topsPath1 = externalStorageDir + "/" + TOPS_PATH_1;
        topsPath2 = externalStorageDir + "/" + TOPS_PATH_2;
        bottomsPath1 = externalStorageDir + "/" + BOTTOMS_PATH_1;
        bottomsPath2 = externalStorageDir + "/" + BOTTOMS_PATH_2;


        /* Initiate Database. */
        SQLiteDatabase db;
        // FashionSQLiteOpenHelper Class
        FashionSQLiteOpenHelper hlpr = new FashionSQLiteOpenHelper(context);

        // Get writable database
        db = hlpr.getWritableDatabase();

        // Set initial data.
        hlpr.setTopsImage(db, topsPath1, TOPS_ID_1);
        hlpr.setTopsImage(db, topsPath2, TOPS_ID_2);
        hlpr.setBottomsImage(db, bottomsPath1, BOTTOMS_ID_1);
        hlpr.setBottomsImage(db, bottomsPath2, BOTTOMS_ID_2);

        String tops_path1 = hlpr.getTopsImagePathById(db, TOPS_ID_1);
        String tops_path2 = hlpr.getTopsImagePathById(db, TOPS_ID_2);
        String bottoms_path1 = hlpr.getBottomsImagePathById(db, BOTTOMS_ID_1);
        String bottoms_path2 = hlpr.getBottomsImagePathById(db, BOTTOMS_ID_2);

        Log.i(LOG_TAG, "tops_path1: " + tops_path1);
        Log.i(LOG_TAG, "tops_path2: " + tops_path2);
        Log.i(LOG_TAG, "bottoms_path1: " + bottoms_path1);
        Log.i(LOG_TAG, "bottoms_path2: " + bottoms_path2);


    }
}
