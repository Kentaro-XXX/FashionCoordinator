package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kentaro.fashioncoordinator.WeatherManager.WeatherData;
import com.example.kentaro.fashioncoordinator.WeatherManager.WeatherManager;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionHistory extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_history);

        Log.d("weatherLOG", "weather get start!!");
        WeatherManager task = new WeatherManager();
        task.execute("start");

        WeatherData testdata = new WeatherData();
        testdata = task.GetWeather();
        Log.d("weatherLOG", testdata.temp);
        Log.d("weatherLOG", testdata.weather);
        Log.d("weatherLOG", testdata.humidity);

        // ƒ{ƒ^ƒ“’è‹`
        Button buttonScan = (Button)findViewById(R.id.button_history_back);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);

                startActivity(intentStart);

            }
        });
    }
}
