package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 0000100038 on 2015/07/29.
 */
public class FashionSave extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_save);

        // ボタンの定義
        Button buttonSave = (Button)findViewById(R.id.button_save_save);
        Button buttonHome = (Button)findViewById(R.id.button_save_home);

        // Saveボタンが押されたとき
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 選んだ服の情報を保存する


            }
        });

        // Homeボタンが押されたとき
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);
                // Start画面に遷移する
                startActivity(intentStart);
            }
        });

    }
}
