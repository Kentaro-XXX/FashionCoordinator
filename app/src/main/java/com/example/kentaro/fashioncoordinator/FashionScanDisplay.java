package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

import java.io.File;

/**
 * Created by 0000140114 on 2015/08/27.
 */
public class FashionScanDisplay extends Activity implements View.OnClickListener{

    final String LOG_TAG = "FashionScanDisp_LOG";
    private String str_key;

    @Override
    public void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.fashion_scandisplay);

        // 受け取ったインテントを取得
        Intent intent = getIntent();
        // インテントに保存されたデータを取得
        String str_key = intent.getStringExtra("keyword");
        //表示してみる
        Toast.makeText(getApplicationContext(), str_key, Toast.LENGTH_SHORT).show();

        //画像をだす
        updateFashionImage(str_key);

//        Button buttonScan = (Button) findViewById(R.id.button_start_scan);
        BootstrapButton buttonOK = (BootstrapButton) findViewById(R.id.button_scandisplay_ok);
        BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail)findViewById(R.id.button_scandisplay_home);

        //OKボタンが押されたときの処理
        buttonOK.setOnClickListener(this);
        // Homeボタンが押されたときの処理
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 何もしない
            }
        });
    }

    @Override
    public void onClick(View v){
        Intent intentSelect = new Intent(getApplication(), FashionSelect.class);
        //　インテントに値をセット
        intentSelect.putExtra("keyword", str_key);
        // Select画面に遷移する
        startActivity(intentSelect);
        //String Fashionpath = GetFashionPath("enw");
        Log.d(LOG_TAG, "FashionPath : " + str_key);
    }

    // Update displayed fashion images
    private void updateFashionImage(String topsPath){
        File topsImageFile = new File(topsPath);
        Log.i(LOG_TAG, "topsImageFile: " + topsImageFile.getAbsolutePath());

        // Check file is valid.
        if(!topsImageFile.exists()){
            Log.e(LOG_TAG, "Error! File Not Exist!!");
            return;
        }else{
            Log.i(LOG_TAG, "OK! Files exist.");
        }

        // Get bitmap data from File path.
        Bitmap topsBitmap = BitmapFactory.decodeFile(topsImageFile.getAbsolutePath());

        // Get Image View parts.
        ImageView topsImageView = (ImageView)findViewById(R.id.imageScanView);

        // Put bitmap data on each image view.
        topsImageView.setImageBitmap(topsBitmap);
    }



}



