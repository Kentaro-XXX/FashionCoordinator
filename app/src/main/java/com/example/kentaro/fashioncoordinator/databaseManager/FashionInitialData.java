package com.example.kentaro.fashioncoordinator.databaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kentaro on 2015/08/16.
 */
public class FashionInitialData {
    // Default tops path and ID data.
    private static final Map<String, String> DEFAULT_TOPS_DATA;
        static  {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("FashionCoordinator/tops/PinkShirts.JPG",       "T_Pink1");
            map.put("FashionCoordinator/tops/ShirtsBlack.JPG",      "T_Black1");
            map.put("FashionCoordinator/tops/ShirtsKahki.JPG",      "T_Kahki1");
            map.put("FashionCoordinator/tops/ShirtsNatural.JPG",    "T_Natural1");
            map.put("FashionCoordinator/tops/ShirtsNavy.JPG",       "T_Navy1");
            map.put("FashionCoordinator/tops/ShirtsOrange.JPG",     "T_Orange1");
            map.put("FashionCoordinator/tops/ShirtsYellow.JPG",     "T_Yellow1");

            DEFAULT_TOPS_DATA = Collections.unmodifiableMap(map);
        };

    // Default bottoms path and ID data.
    private static final Map<String, String> DEFAULT_BOTTOMS_DATA;
        static {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("FashionCoordinator/bottoms/PantsBeige.JPG",    "B_Beige1");
            map.put("FashionCoordinator/bottoms/PantsBlack.JPG",    "B_Black1");
            map.put("FashionCoordinator/bottoms/PantsGray.JPG",     "B_Gray1");
            map.put("FashionCoordinator/bottoms/PantsGray2.JPG",    "B_Gray2");
            map.put("FashionCoordinator/bottoms/PantsGreen2.JPG",   "B_Green1");
            map.put("FashionCoordinator/bottoms/PantsNavy.JPG",     "B_Navy1");
            map.put("FashionCoordinator/bottoms/PantsWine.JPG",     "B_Wine1");

            DEFAULT_BOTTOMS_DATA = Collections.unmodifiableMap(map);
        }
    final String LOG_TAG = "FashionInitialData_LOG";

    private Context context;

    public FashionInitialData(Context con){
        context = con;
    }

    public void setFashionInitialData(){
        String externalStorageDir = Environment.getExternalStorageDirectory().getPath();

        /* Initiate Database. */
        SQLiteDatabase db;
        // FashionSQLiteOpenHelper Class
        FashionSQLiteOpenHelper hlpr = new FashionSQLiteOpenHelper(context);

        // Get writable database
        db = hlpr.getWritableDatabase();

        // Set initial data.
        for (String path : DEFAULT_TOPS_DATA.keySet()){
            String absolutePath = externalStorageDir + "/" + path;
            hlpr.setTopsImage(db, absolutePath, DEFAULT_TOPS_DATA.get(path));

            Log.i(LOG_TAG, "id: " + DEFAULT_TOPS_DATA.get(path));
            File imgFile = new File(absolutePath);
            if(imgFile.exists()){
                Log.i(LOG_TAG, "OK! File Exist! (" + absolutePath +")");
            }else{
                Log.e(LOG_TAG, "Error! File Not Exist! (" + absolutePath +")");
            }
        }
        for (String path : DEFAULT_BOTTOMS_DATA.keySet()){
            String absolutePath = externalStorageDir + "/" + path;
            hlpr.setTopsImage(db, absolutePath, DEFAULT_BOTTOMS_DATA.get(path));

            Log.i(LOG_TAG, "id: " + DEFAULT_BOTTOMS_DATA.get(path));
            File imgFile = new File(absolutePath);
            if(imgFile.exists()){
                Log.i(LOG_TAG, "OK! File Exist! (" + absolutePath +")");
            }else{
                Log.e(LOG_TAG, "Error! File Not Exist! (" + absolutePath +")");
            }
        }
    }
}
