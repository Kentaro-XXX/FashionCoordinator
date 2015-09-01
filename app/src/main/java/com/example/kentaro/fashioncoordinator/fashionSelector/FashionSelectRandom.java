package com.example.kentaro.fashioncoordinator.fashionSelector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kentaro.fashioncoordinator.databaseManager.FashionSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kentaro on 2015/09/01.
 */
public class FashionSelectRandom implements FashionSelector {
//    private static final int FASHION_PATTERN_NUM = 5;
    private int fashionPatternNum;

    private SQLiteDatabase db;
    private FashionSQLiteOpenHelper hlpr;
    private List<String> bottomsIdList;

    static final String LOG_TAG = "FashionSelector_LOG";

    public FashionSelectRandom(Context con){
        fashionPatternNum = FASHION_PATTERN_NUM;
        startFashionSelectByRandom(con);
    }

    public FashionSelectRandom(Context con, int num){
        fashionPatternNum = num;
        startFashionSelectByRandom(con);
    }

    private void startFashionSelectByRandom(Context con) {
        /* Initiate Database. */
        hlpr = new FashionSQLiteOpenHelper(con);    // FashionSQLiteOpenHelper Class
        db = hlpr.getWritableDatabase();            // Get writable database

        String bottomsIds[];
        bottomsIds = hlpr.getBottomsIds(db);

        // Copy the string column to list.
        bottomsIdList = new ArrayList<String>();
        for (int i = 0; i < bottomsIds.length; i++) {
            bottomsIdList.add(bottomsIds[i]);
        }
        if(fashionPatternNum > bottomsIds.length){
            fashionPatternNum = bottomsIds.length;
        }
        // Shuffle the list.
        Collections.shuffle(bottomsIdList);
    }

    /* Get the number of fashion pattern */
    public int getPatternNumber(){
        return fashionPatternNum;
    }

    /* Get bottoms path based on the input index */
    public String getBottomsPath(int index){
        /* Check the index */
        if(index >= fashionPatternNum){
            Log.e(LOG_TAG, "getBottomsPath: index is too large!");
            return null;
        }

        String bottomsId = bottomsIdList.get(index);
        return hlpr.getBottomsImagePathById(db, bottomsId);
    }

    /* Get bottoms path based on the input index */
    public String getTopsPath(int index){
        /* Check the index */
        if(index >= fashionPatternNum){
            Log.e(LOG_TAG, "getTopsPath: index is too large!");
            return null;
        }

        Log.e(LOG_TAG, "getTopsPath: This function is not implemented yet!");

        return null;
    }

}
