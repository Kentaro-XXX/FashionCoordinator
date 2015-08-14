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

        // �{�^����`
        final Button buttonOK = (Button) findViewById(R.id.button_select_ok);
        Button buttonNext = (Button) findViewById(R.id.button_select_next);
        Button buttonPrev = (Button) findViewById(R.id.button_select_prev);
        Button buttonHome = (Button) findViewById(R.id.button_select_home);

        // OK�{�^���������ꂽ�Ƃ��̏���
        buttonOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // popup�̃��C�A�E�g�ݒ�
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.fashion_popup, null);

                // popup�̃��C�A�E�g�ݒ�
                final PopupWindow popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                // �^�b�v���̑���View�ŃL���b�`����Ȃ����߂̐ݒ�
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                // �\���T�C�Y�̐ݒ�
                float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 330, getResources().getDisplayMetrics());
                popupWindow.setWindowLayoutMode((int) width, 200);
                popupWindow.setWidth((int) width);
                popupWindow.setHeight(200);


                // popup��OK�{�^���̐ݒ�
                Button btnPopupOK = (Button) popupView.findViewById(R.id.button_popup);
                btnPopupOK.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // �I����������Save����

                        // Start(Home)��ʂɑJ�ڂ���
                        Intent intentStart = new Intent(getApplication(), FashionStart.class);
                        startActivity(intentStart);

                    }
                });

                // ��ʒ����ɕ\��
                //popupWindow.showAtLocation(findViewById(R.id.button_popup), Gravity.CENTER, 0, 0);
                popupWindow.showAsDropDown(buttonOK, -130, -300);

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
