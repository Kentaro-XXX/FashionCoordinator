package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kentaro.fashioncoordinator.WeatherManager.WeatherManager;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionHistory extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_history);

        Log.d("weatherLOG","push!!");
        /*
        WeatherManager WM = new WeatherManager();
        String json_result;
        json_result = WM.weather_method();*/

        String url = "http://api.openweathermap.org/data/2.5/weather?q=Tokyo,jp";
        WeatherManager task = new WeatherManager(this);
        task.owner = this;
        task.execute(url);

        //task.parse();

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
