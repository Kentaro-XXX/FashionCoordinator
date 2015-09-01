package com.example.kentaro.fashioncoordinator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.example.kentaro.fashioncoordinator.databaseManager.FashionSQLiteOpenHelper;
import com.example.kentaro.fashioncoordinator.fashionSelector.FashionSelectRandom;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 0000100038 on 2015/07/29.
 */
public class FashionSelect extends Activity {
    static final String LOG_TAG = "FahionSelect_TAG";

    private PopupWindow mPopupWindow;
    private int candidateIndex;
    private int candidateIndexMax;
    private FashionSelectRandom selectRandom;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_select);

        // Get Intent from the previous page.
        Intent intent = getIntent();
        // Get the data for "keyword".
        String topsPath = intent.getStringExtra("keyword");

        BootstrapButton buttonOK = (BootstrapButton) findViewById(R.id.button_select_ok);
        BootstrapButton buttonNext = (BootstrapButton) findViewById(R.id.button_select_next);
        BootstrapButton buttonPrev = (BootstrapButton) findViewById(R.id.button_select_prev);
        final BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail) findViewById(R.id.button_select_home);

        buttonOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.fashion_popup, null);

                // Show popup
                final PopupWindow popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                // �^�b�v���̑���View�ŃL���b�`����Ȃ����߂̐ݒ�
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                // �\���T�C�Y�̐ݒ�
               // float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 330, getResources().getDisplayMetrics());
                popupWindow.setWindowLayoutMode(700, 400);
                popupWindow.setWidth(700);
                popupWindow.setHeight(400);


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
               // popupWindow.showAsDropDown(buttonOK, -130, +100);
               popupWindow.showAsDropDown(buttonHome, 170, 700);

            }

        });



        // Next�{�^���������ꂽ�Ƃ��̏���
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ���̕���\������
                next();
            }
        });

        // Prev�{�^���������ꂽ�Ƃ��̏���
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // �O�̕���\������
                prev();
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


        // Initiate rondom selector.
        selectRandom = new FashionSelectRandom(getApplicationContext());
        candidateIndexMax = selectRandom.getPatternNumber();    // get the index max num.
        candidateIndex = 0;

        // Show the fashion coordinate candidates.
        showTopsCandidate(topsPath);
        showBottomsCandidate();
    }

    private void showTopsCandidate(String topsPath){
        File topsImageFile = new File(topsPath);
        Log.i(LOG_TAG, "topsImageFile: " + topsImageFile.getAbsolutePath());

        // Check file is valid.
        if(!topsImageFile.exists()){
            Log.e(LOG_TAG, "Error! Tops File Not Exist!!");
            return;
        }else{
            Log.i(LOG_TAG, "OK! TOps File exist.");
        }

        // Get bitmap data from File path.
        Bitmap topsBitmap = BitmapFactory.decodeFile(topsImageFile.getAbsolutePath());

        // Get Image View parts.
        ImageView topsImageView = (ImageView)findViewById(R.id.imageView_tops);

        // Put bitmap data on each image view.
        topsImageView.setImageBitmap(topsBitmap);
    }

    private void showBottomsCandidate(String bottomsPath){
        File bottomsImageFile = new File(bottomsPath);
        Log.i(LOG_TAG, "bottomsImageFile: " + bottomsImageFile.getAbsolutePath());

        // Check file is valid.
        if(!bottomsImageFile.exists()){
            Log.e(LOG_TAG, "Error! Bottoms File Not Exist!!");
            return;
        }else{
            Log.i(LOG_TAG, "OK! Bottoms File exist.");
        }

        // Get bitmap data from File path.
        Bitmap bottomsBitmap = BitmapFactory.decodeFile(bottomsImageFile.getAbsolutePath());

        // Get Image View parts.
        ImageView bottomsImageView = (ImageView)findViewById(R.id.imageView_bottoms);

        // Put bitmap data on each image view.
        bottomsImageView.setImageBitmap(bottomsBitmap);
    }

    private void showBottomsCandidate(){
        String bottomsPath = selectRandom.getBottomsPath(candidateIndex);
        File bottomsImageFile = new File(bottomsPath);
        Log.i(LOG_TAG, "bottomsImageFile: " + bottomsImageFile.getAbsolutePath());

        // Check file is valid.
        if(!bottomsImageFile.exists()){
            Log.e(LOG_TAG, "Error! Bottoms File Not Exist!!");
            return;
        }else{
            Log.i(LOG_TAG, "OK! Bottoms File exist.");
        }

        // Get bitmap data from File path.
        Bitmap bottomsBitmap = BitmapFactory.decodeFile(bottomsImageFile.getAbsolutePath());

        // Get Image View parts.
        ImageView bottomsImageView = (ImageView)findViewById(R.id.imageView_bottoms);

        // Put bitmap data on each image view.
        bottomsImageView.setImageBitmap(bottomsBitmap);
    }

    // Show next fashion coordinate candidate.
    private void next(){
        // increment candidateIndex.
        if (candidateIndex + 1 == candidateIndexMax){
            candidateIndex = 0;
        } else {
            candidateIndex++;
        }

        Log.i(LOG_TAG, "next()");
        Log.i(LOG_TAG, "candidateIndex: " + Integer.toString(candidateIndex));

        // Draw fashion Coordinate
        showBottomsCandidate();
    }

    // Show next fashion coordinate candidate.
    private void prev(){
        // Decrement candidateIndex.
        if (candidateIndex < 1){
            candidateIndex = candidateIndexMax - 1;
        } else {
            candidateIndex--;
        }

        Log.i(LOG_TAG, "prev()");
        Log.i(LOG_TAG, "candidateIndex: " + Integer.toString(candidateIndex));

        // Draw fashion Coordinate
        showBottomsCandidate();
    }
}
