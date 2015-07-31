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

        // �{�^����`
        Button buttonOK = (Button)findViewById(R.id.button_select_ok);
        Button buttonNext = (Button)findViewById(R.id.button_select_next);
        Button buttonPrev = (Button)findViewById(R.id.button_select_prev);
        Button buttonHome = (Button)findViewById(R.id.button_select_home);

        // OK�{�^���������ꂽ�Ƃ��̏���
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save��ʂւ̑J�ڂ��w��
                Intent intentSave = new Intent(getApplication(), FashionSave.class);

                // save��ʂ֑J��
                startActivity(intentSave);
            }
        });

        // Next�{�^���������ꂽ�Ƃ��̏���
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ���̕���\������
            }
        });

        // Prev�{�^���������ꂽ�Ƃ��̏���
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // �O�̕���\������
            }
        });

        // Home�{�^���������ꂽ�Ƃ��̏���
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start��ʂւ̑J�ڂ��w��
                Intent intentStart = new Intent(getApplication(), FashionStart.class);

                // save��ʂ֑J��
                startActivity(intentStart);
            }
        });


    }
}
