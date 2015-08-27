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
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kentaro.fashioncoordinator.R;

/**
 * Created by 0000131258 on 2015/08/11.
 */
public class WeatherManager extends AsyncTask<String, Void, String> {
    //public Activity owner;
    private String ReceiveStr;
    String api_url = "http://api.openweathermap.org/data/2.5/weather?q=Tokyo,jp";
    private Activity owner;
    public WeatherManager(Activity activity) {

        // 呼び出し元のアクティビティを変数へセット
        this.owner = activity;
    }

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
    String temperature = "";
    String humidity = "";
    String icon = "";

    @Override
    protected void onPostExecute(String result) {

        //String weather = "";
        Log.d("weatherLOG", result);
        try {
            JSONObject json = new JSONObject(result);
            temperature = json.getJSONObject("main").getString("temp");
            humidity = json.getJSONObject("main").getString("humidity");
            weather = json.getJSONArray("weather").getJSONObject(0).getString("main");
            icon = json.getJSONArray("weather").getJSONObject(0).getString("icon");


        }catch (JSONException e) {
            Log.d("weatherLOG", "exception");
            e.printStackTrace();
        }
        Log.d("weatherLOG", weather);
        Log.d("weatherLOG", temperature);
        Log.d("weatherLOG", humidity);
        Log.d("weatherLOG", icon);

        double temp = Double.parseDouble(temperature);
        double int_temp = temp - 273 ;
        Log.d("weatherLOG", String.valueOf(temp));
        String icon_url = "http://openweathermap.org/img/w/"+icon+".png";
        TextView mytextview = (TextView)this.owner.findViewById(R.id.textView4);
        mytextview.setText("temperature = " + String.valueOf(int_temp) +"\n" +
                            "humidity = " + humidity + "\n" +
                            "weather = " +weather);

        WebView  myWebView = (WebView)this.owner.findViewById(R.id.webView2);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(icon_url);
        myWebView.zoomIn();
        myWebView.zoomIn();
        myWebView.zoomIn();
    }

    public WeatherData GetWeather() {
        WeatherData data= new WeatherData();

        data.temperature = temperature;
        data.weather = weather;
        data.humidity = humidity;
    	data.icon = icon;
        return data;
    }

}