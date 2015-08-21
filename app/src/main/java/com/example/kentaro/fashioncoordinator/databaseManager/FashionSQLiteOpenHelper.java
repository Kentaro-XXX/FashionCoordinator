package com.example.kentaro.fashioncoordinator.databaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 0000139099 on 2015/07/16.
 */
public class FashionSQLiteOpenHelper extends SQLiteOpenHelper {
    static final String LOG_TAG = "FashionSQLiteOpenHelper";

    static final String DB = "sqlite_fashion.db";
    static final int DB_VERSION = 1;

    // Table name
    static final String TOPS_IMAGE_TABLE_NAME =
            "topsImageTable";
    static final String BOTTOMS_IMAGE_TABLE_NAME =
            "bottomsImageTable";
    static final String HISTORY_TABLE_NAME =
            "historyTable";

    // Create tables
    // image table column is
    // "_id"        : INTEGER,
    // "_imgData"   : TEXT
    static final String CREATE_TOPS_IMAGE_TABLE =
            "create table " + TOPS_IMAGE_TABLE_NAME    + " ( _id integer primary key autoincrement, _imgData text );";
    static final String CREATE_BOTTOMS_IMAGE_TABLE =
            "create table " + BOTTOMS_IMAGE_TABLE_NAME + " ( _id integer primary key autoincrement, _imgData text );";

    // history table column is
    // "_date"      : TEXT,
    // "_topsId"    : TEXT,
    // "_bottomsId" : TEXT
    // "_weatherIconName" : TEXT
    // "_temperature"     : TEXT
    // "_humidity"        : TEXT
    static final String CREATE_HISTORY_TABLE =
            "create table " + HISTORY_TABLE_NAME + " (_date text, _topsId integer, _bottomsId integer, " +
                    "_weatherIconName text, _temperature text, _humidity text );";

    // Drop tables
    static final String DROP_TOPS_IMAGE_TABLE =
            "drop table createTopsImageTable";
    static final String DROP_BOTTOMS_IMAGE_TABLE =
            "drop table createBottomsImageTable";
    static final String DROP_HISTORY_TABLE =
            "drop table createHistoryTable";


    public FashionSQLiteOpenHelper(Context c){
        super(c, DB, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        // execute tables creation.
        db.execSQL(CREATE_TOPS_IMAGE_TABLE);
        db.execSQL(CREATE_BOTTOMS_IMAGE_TABLE);
        db.execSQL(CREATE_HISTORY_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_TOPS_IMAGE_TABLE);
        db.execSQL(DROP_BOTTOMS_IMAGE_TABLE);
        db.execSQL(DROP_HISTORY_TABLE);
        onCreate(db);
    }

    ///////////////////////////////////////////////
    ///                 Getter                   //
    ///////////////////////////////////////////////
    private int getTopsImageIdByPath(SQLiteDatabase db, String path){
        final String SELECT_ID_FROM_TOPS_IMAGE_TABLE =
                "select _id from " + TOPS_IMAGE_TABLE_NAME + " where _imgData = \"" + path + "\"";

        Log.d(LOG_TAG, SELECT_ID_FROM_TOPS_IMAGE_TABLE);

        // Get tops data id by path
        Cursor mCursor = db.rawQuery(SELECT_ID_FROM_TOPS_IMAGE_TABLE, null);

        int id = -1;
        if(mCursor.moveToFirst()){
            // Get tops data id from cursor variable.
            id = mCursor.getInt(mCursor.getColumnIndex("_id"));
        }
        return id;
    }
    private int getBottomsImageIdByPath(SQLiteDatabase db, String path){
        final String SELECT_ID_FROM_BOTTOMS_IMAGE_TABLE =
                "select _id from " + BOTTOMS_IMAGE_TABLE_NAME + " where _imgData = \"" + path + "\"";

        // Get bottoms data id by path
        Cursor mCursor = db.rawQuery(SELECT_ID_FROM_BOTTOMS_IMAGE_TABLE, null);

        int id = -1;
        if(mCursor.moveToFirst()){
            // Get bottoms data id from cursor variable.
            id = mCursor.getInt(mCursor.getColumnIndex("_id"));
        }
        return id;
    }

    public String getTopsImagePathById(SQLiteDatabase db, int id){
        final String SELECT_IMAGE_PATH_FROM_TOPS_IMAGE_TABLE =
                "select _imgData from " + TOPS_IMAGE_TABLE_NAME + " where _id = \"" + Integer.toString(id) + "\"";

        // Get tops data path by id
        Cursor mCursor = db.rawQuery(SELECT_IMAGE_PATH_FROM_TOPS_IMAGE_TABLE, null);

        String path = null;
        if(mCursor.moveToFirst()){
            // Get tops data path from cursor variable.
            path = mCursor.getString(mCursor.getColumnIndex("_imgData"));
        }
        return path;
    }
    public String getBottomsImagePathById(SQLiteDatabase db, int id){
        final String SELECT_IMAGE_PATH_FROM_BOTTOMS_IMAGE_TABLE =
                "select _imgData from " + BOTTOMS_IMAGE_TABLE_NAME + " where _id = \"" + Integer.toString(id) + "\"";

        // Get bottoms data path by id
        Cursor mCursor = db.rawQuery(SELECT_IMAGE_PATH_FROM_BOTTOMS_IMAGE_TABLE, null);

        String path = null;
        if(mCursor.moveToFirst()){
            // Get bottoms data path from cursor variable.
            path = mCursor.getString(mCursor.getColumnIndex("_imgData"));
        }
        return path;
    }
    public FashionWeatherHistoryData getFashionWeatherHistoryData(SQLiteDatabase db, String date){
        final String SELECT_IMAGE_IDS_FROM_HISTORY_TABLE =
                "select _topsId, _bottomsId from " + HISTORY_TABLE_NAME + " where _date = \"" + date + "\"";

        // Get fashion IDs by date
        Cursor mCursor = db.rawQuery(SELECT_IMAGE_IDS_FROM_HISTORY_TABLE, null);

        if(mCursor.moveToFirst()){
            Log.d(LOG_TAG, "[getFashionWeatherHistoryData] History Data exists.");

            FashionWeatherHistoryData mFashionWeatherHistoryData = new FashionWeatherHistoryData();
            mFashionWeatherHistoryData.date = date;

            // Get tops ID
            int topsId = mCursor.getInt(mCursor.getColumnIndex("_topsId"));
            // Get tops image data path
            mFashionWeatherHistoryData.topsDataPath = getTopsImagePathById(db, topsId);

            // Get bottoms ID
            int bottomsId = mCursor.getInt(mCursor.getColumnIndex("_bottomsId"));
            // Get bottoms image data path
            mFashionWeatherHistoryData.bottomsDataPath = getBottomsImagePathById(db, bottomsId);


            // Get weather icon name
            String weatherIconName = mCursor.getString(mCursor.getColumnIndex("_weatherIconName"));
            mFashionWeatherHistoryData.weatherIconName = weatherIconName;

            // Get temperature
            String temperature = mCursor.getString(mCursor.getColumnIndex("_temperature"));
            mFashionWeatherHistoryData.temperature = temperature;

            // Get humidity
            String humidity = mCursor.getString(mCursor.getColumnIndex("_humidity"));
            mFashionWeatherHistoryData.humidity = humidity;

            return mFashionWeatherHistoryData;
        }else{
            Log.d(LOG_TAG, "[getFashionWeatherHistoryData] No History Data!");
            return null;
        }

    }

    ///////////////////////////////////////////////
    ///                 Setter                   //
    ///////////////////////////////////////////////
    public boolean setTopsImagePath(SQLiteDatabase db, String path) {

        // Check if input path is already existed or not.
        int existingId = getTopsImageIdByPath(db, path);
        if(existingId == -1) {
            Log.d(LOG_TAG, "[setTopsImagePath] Path is NOT existed.");
            ContentValues values = new ContentValues();
            values.put("_imgData", path);
            db.insert(TOPS_IMAGE_TABLE_NAME, null, values);
        }else{
            Log.d(LOG_TAG, "[setTopsImagePath] Path is ALREADY existed.");
        }

        return true;
    }
    public boolean setBottomsImagePath(SQLiteDatabase db, String path) {

        // Check if input path is already existed or not.
        int existingId = getBottomsImageIdByPath(db, path);
        if(existingId == -1) {
            Log.d(LOG_TAG, "[setBottomsImagePath] Path is NOT existed.");
            ContentValues values = new ContentValues();
            values.put("_imgData", path);
            db.insert(BOTTOMS_IMAGE_TABLE_NAME, null, values);
        }else{
            Log.d(LOG_TAG, "[setBottomsImagePath] Path is ALREADY existed.");
        }

        return true;
    }
    public boolean setFashionWeatherHistoryData(SQLiteDatabase db, FashionWeatherHistoryData history){

        boolean rtn;

        // Check if input history data is already existed or not.
        if(getFashionWeatherHistoryData(db, history.date) == null){
            Log.d(LOG_TAG, "[setFashionWeatherHistoryData] History Data is ALREADY existed.");
            rtn = false;
        }else {
            ContentValues values = new ContentValues();
            // Put date data
            values.put("_date", history.date);

            // Check if path info is valid or not
            int topsId = getTopsImageIdByPath(db, history.topsDataPath);
            int bottomsId = getBottomsImageIdByPath(db, history.bottomsDataPath);
            if (topsId == -1 || bottomsId == -1) {
                // error : Path info is invalid
                return false;
            }
            values.put("_topsId", topsId);
            values.put("_bottomsId", bottomsId);

            // Handle weather data from here
            values.put("_weatherIconName", history.weatherIconName);
            values.put("_temperature", history.temperature);
            values.put("_humidity", history.humidity);

            // Insert history data into history table
            db.insert(HISTORY_TABLE_NAME, null, values);

            rtn = true;
        }

        return rtn;
    }
}
