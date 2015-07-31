package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 0000100038 on 2015/07/29.
 */
public class FashionSelect extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_select);

        // ボタン定義
        Button buttonOK = (Button)findViewById(R.id.button_select_ok);
        Button buttonNext = (Button)findViewById(R.id.button_select_next);
        Button buttonPrev = (Button)findViewById(R.id.button_select_prev);
        Button buttonHome = (Button)findViewById(R.id.button_select_home);

        // OKボタンが押されたときの処理
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save画面への遷移を指定
                Intent intentSave = new Intent(getApplication(), FashionSave.class);

                // save画面へ遷移
                startActivity(intentSave);
            }
        });

        // Nextボタンが押されたときの処理
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 次の服を表示する
            }
        });

        // Prevボタンが押されたときの処理
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 前の服を表示する
            }
        });

        // Homeボタンが押されたときの処理
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start画面への遷移を指定
                Intent intentStart = new Intent(getApplication(), FashionStart.class);

                // save画面へ遷移
                startActivity(intentStart);
            }
        });


    }
}
