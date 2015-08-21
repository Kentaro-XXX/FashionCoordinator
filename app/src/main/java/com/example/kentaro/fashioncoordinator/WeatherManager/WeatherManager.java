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
    public Activity owner;
    private String ReceiveStr;

    public WeatherManager(Activity activity) {
        owner = activity;
    }

    @Override
    protected String doInBackground(String... url) {
        try {
            HttpGet httpGet = new HttpGet(url[0]);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpGet.setHeader("Connection", "Keep-Alive");

            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status != HttpStatus.SC_OK) {
                throw new Exception("");
            } else {
                ReceiveStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            wait(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
       // TextView textView2 = (TextView) owner.findViewById(R.id.textView2);
       //textView2.setText(ReceiveStr);
        String weather = "";
        Log.d("weatherLOG", ReceiveStr);
        try {
            Log.d("weatherLOG", "try");
            JSONObject json = new JSONObject(ReceiveStr);
            Log.d("weatherLOG", "middle");
            weather = json.getString("clouds");
            //weather = json.getJSONObject("weather").getString("description");

            Log.d("weatherLOG", "end");
        }catch (JSONException e) {
            Log.d("weatherLOG", "exception");
            e.printStackTrace();
        }
        Log.d("weatherLOG", weather);
    }


    public void parse(){

    }

}