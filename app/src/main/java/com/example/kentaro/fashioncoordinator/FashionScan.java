package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionScan extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_scan);

        // �{�^���̒�`
        Button buttonOK = (Button)findViewById(R.id.button_scan_ok);
        Button buttonHome = (Button)findViewById(R.id.button_scan_home);

        // OK�{�^���������ꂽ�Ƃ��̏���
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelect = new Intent(getApplication(), FashionSelect.class);
                // Select��ʂɑJ�ڂ���
                startActivity(intentSelect);

            }
        });

        // Home�{�^���������ꂽ�Ƃ��̏���
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);
                // Start��ʂɑJ�ڂ���
                startActivity(intentStart);

            }
        });

    }

}
