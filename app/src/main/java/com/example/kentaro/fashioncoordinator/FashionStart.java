package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionStart extends Activity {

    @Override
    public void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.fashion_start);

        Button buttonScan = (Button) findViewById(R.id.button_scan);
        Button buttonHistory = (Button) findViewById(R.id.button_history);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 遷移先の画面を指定する
                Intent intentScan = new Intent(getApplication(), FashionScan.class);

                // Scan画面に遷移する
                startActivity(intentScan);
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 遷移先の画面を指定する
                Intent intentHistory = new Intent(getApplication(), FashionHistory.class);

                // Scan画面に遷移する
                startActivity(intentHistory);
            }
        });


    }
}
