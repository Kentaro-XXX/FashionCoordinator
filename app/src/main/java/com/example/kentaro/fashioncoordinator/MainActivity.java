package com.example.kentaro.fashioncoordinator;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.example.kentaro.fashioncoordinator.databaseManager.FashionInitialData;
import com.example.kentaro.fashioncoordinator.databaseManager.FashionSQLiteOpenHelper;


public class MainActivity extends ActionBarActivity {
    final String LOG_TAG = "MainActivity_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentStart = new Intent(MainActivity.this, FashionStart.class);

        startActivity(intentStart);

        // Initiate database
        initiateDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //test_comment
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initiateDatabase(){
        /* Initiate Database. */
        SQLiteDatabase db;
        // FashionSQLiteOpenHelper Class
        FashionSQLiteOpenHelper hlpr = new FashionSQLiteOpenHelper(getApplicationContext());

        // Get writable database
        db = hlpr.getWritableDatabase();

        // Set initial data.
        hlpr.setTopsImage(db, FashionInitialData.TOPS_PATH_1, "xxx");
        hlpr.setTopsImage(db, FashionInitialData.TOPS_PATH_2, "xxx");
        hlpr.setBottomsImage(db, FashionInitialData.BOTTOMS_PATH_1, "xxx");
        hlpr.setBottomsImage(db, FashionInitialData.BOTTOMS_PATH_2, "xxx");

        String tops_path1 = hlpr.getBottomsImagePathById(db, "xxx");
        String tops_path2 = hlpr.getBottomsImagePathById(db, "xxx");
        String bottoms_path1 = hlpr.getBottomsImagePathById(db, "xxx");
        String bottoms_path2 = hlpr.getBottomsImagePathById(db, "xxx");

        Log.i(LOG_TAG, "tops_path1: " + tops_path1);
        Log.i(LOG_TAG, "tops_path2: " + tops_path2);
        Log.i(LOG_TAG, "bottoms_path1: " + bottoms_path1);
        Log.i(LOG_TAG, "bottoms_path2: " + bottoms_path2);
    }
}
