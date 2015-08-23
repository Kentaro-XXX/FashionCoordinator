package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionStart extends Activity {

    @Override
    public void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.fashion_start);

//        Button buttonScan = (Button) findViewById(R.id.button_start_scan);
        BootstrapButton buttonScan = (BootstrapButton) findViewById(R.id.button_start_scan);
        BootstrapButton buttonHistory = (BootstrapButton) findViewById(R.id.button_start_history);
        BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail)findViewById(R.id.button_start_home);

        // Scan�{�^���������ꂽ�Ƃ��̏���
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // �J�ڐ�̉�ʂ��w�肷��
                Intent intentScan = new Intent(getApplication(), FashionScan.class);

                // Scan��ʂɑJ�ڂ���
                startActivity(intentScan);
            }
        });

        // History�{�^���������ꂽ�Ƃ��̏���
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // �J�ڐ�̉�ʂ��w�肷��
                Intent intentHistory = new Intent(getApplication(), FashionHistory.class);

                // History��ʂɑJ�ڂ���
                startActivity(intentHistory);
            }
        });

        // Home�{�^���������ꂽ�Ƃ��̏���
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // �������Ȃ�
            }
        });

    }
}
