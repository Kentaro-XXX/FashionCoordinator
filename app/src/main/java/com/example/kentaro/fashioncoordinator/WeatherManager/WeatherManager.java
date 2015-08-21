package com.example.kentaro.fashioncoordinator.WeatherManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;
import java.text.ParseException;

import java.util.Random;

/**
 * Created by 0000131258 on 2015/08/11.
 */
public class WeatherManager extends AsyncTask<String, Void, String> {
    //public Activity owner;
    private String ReceiveStr;
    String api_url = "http://api.openweathermap.org/data/2.5/weather?q=Tokyo,jp";

    @Override
    protected String doInBackground(String... url) {
        try {
            HttpGet httpGet = new HttpGet(api_url);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpGet.setHeader("Connection", "Keep-Alive");

            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status != HttpStatus.SC_OK) {
                throw new Exception("");
            } else {
                ReceiveStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReceiveStr;
    }
    String weather = "";
    @Override
    protected void onPostExecute(String result) {

        //String weather = "";
        Log.d("weatherLOG", result);
        try {
            JSONObject json = new JSONObject(result);
            weather = json.getString("clouds");
            //weather = json.getJSONObject("weather").getString("description");

        }catch (JSONException e) {
            Log.d("weatherLOG", "exception");
            e.printStackTrace();
        }
        Log.d("weatherLOG", weather);
    }

    public WeatherData GetWeather() {
        WeatherData data= new WeatherData();
        data.temperature = "25";
        data.weather = "cloudy";
        data.humidity = "50";
        return data;
    }

}