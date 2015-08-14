package com.example.kentaro.fashioncoordinator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * Created by 0000100038 on 2015/07/29.
 */
public class FashionSelect extends Activity {
    private PopupWindow mPopupWindow;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_select);

        // ボタン定義
        final Button buttonOK = (Button) findViewById(R.id.button_select_ok);
        Button buttonNext = (Button) findViewById(R.id.button_select_next);
        Button buttonPrev = (Button) findViewById(R.id.button_select_prev);
        Button buttonHome = (Button) findViewById(R.id.button_select_home);

        // OKボタンが押されたときの処理
        buttonOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // popupのレイアウト設定
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.fashion_popup, null);

                // popupのレイアウト設定
                final PopupWindow popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                // タップ時の他のViewでキャッチされないための設定
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                // 表示サイズの設定
                float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 330, getResources().getDisplayMetrics());
                popupWindow.setWindowLayoutMode((int) width, 200);
                popupWindow.setWidth((int) width);
                popupWindow.setHeight(200);


                // popupのOKボタンの設定
                Button btnPopupOK = (Button) popupView.findViewById(R.id.button_popup);
                btnPopupOK.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // 選択した情報をSaveする

                        // Start(Home)画面に遷移する
                        Intent intentStart = new Intent(getApplication(), FashionStart.class);
                        startActivity(intentStart);

                    }
                });

                // 画面中央に表示
                //popupWindow.showAtLocation(findViewById(R.id.button_popup), Gravity.CENTER, 0, 0);
                popupWindow.showAsDropDown(buttonOK, -130, -300);

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
