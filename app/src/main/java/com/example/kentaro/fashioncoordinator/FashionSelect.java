package com.example.kentaro.fashioncoordinator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
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

    // Only for Dummy!!
    private class DummyFashionPath {
        private final Map<Integer, String> DUMMY_TOPS_PATH;
        {
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            map.put(0, "/FashionCoordinator/tops/PinkShirts.JPG");
            map.put(1, "/FashionCoordinator/tops/ShirtsBlack.JPG");
            map.put(2, "/FashionCoordinator/tops/ShirtsKahki.JPG");
            map.put(3, "/FashionCoordinator/tops/ShirtsNatural.JPG");

            DUMMY_TOPS_PATH = Collections.unmodifiableMap(map);
        };
        private final Map<Integer, String> DUMMY_BOTTOMS_PATH;
        {
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            map.put(0, "/FashionCoordinator/bottoms/PantsBeige.JPG");
            map.put(1, "/FashionCoordinator/bottoms/PantsBlack.JPG");
            map.put(2, "/FashionCoordinator/bottoms/PantsGray.JPG");
            map.put(3, "/FashionCoordinator/bottoms/PantsGray2.JPG");

            DUMMY_BOTTOMS_PATH = Collections.unmodifiableMap(map);
        };

        public String getDummyTopsPath(int index){
            return DUMMY_TOPS_PATH.get(index);
        }
        public String getDummyBottomsPath(int index){
            return DUMMY_BOTTOMS_PATH.get(index);
        }
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_select);

        // �C���e���g���擾
        Intent intent = getIntent();
        // �C���e���g�ɕۑ����ꂽ�f�[�^���擾
        String data = intent.getStringExtra("keyword");
        //�\�����Ă݂�
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        // Initialize candidateIndex
        candidateIndex = 0;
        candidateIndexMax = 4;  //TODO

        // �{�^����`
        BootstrapButton buttonOK = (BootstrapButton) findViewById(R.id.button_select_ok);
        BootstrapButton buttonNext = (BootstrapButton) findViewById(R.id.button_select_next);
        BootstrapButton buttonPrev = (BootstrapButton) findViewById(R.id.button_select_prev);
        final BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail) findViewById(R.id.button_select_home);

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


        // Show the fashion coordinate candidates.
        showFashionCoordinateCandidate();
    }


    // Show the first fashion coordinate candidate.
    private void showFashionCoordinateCandidate(){

        // Get fashion coordinate.
        // TODO
        candidateIndex = 0;


        // TODO
        // Dummy
        // getFashionCoordinate(candidateIndex); �R�[�f�B�l�[�g���o�͂���API�ƒu������B
        DummyFashionPath dummyFashionPath = new DummyFashionPath();
        String topsPath    = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyTopsPath(candidateIndex);
        String bottomsPath = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyBottomsPath(candidateIndex);

        updateFashionImage(topsPath, bottomsPath);
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

        // Get next fashion candidate image path.
        // TODO
        // Dummy
        // getFashionCoordinate(candidateIndex); �R�[�f�B�l�[�g���o�͂���API�ƒu������B
        DummyFashionPath dummyFashionPath = new DummyFashionPath();
        String topsPath    = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyTopsPath(candidateIndex);
        String bottomsPath = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyBottomsPath(candidateIndex);

        // Draw fashion Coordinate
        updateFashionImage(topsPath, bottomsPath);
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

        // Get next fashion candidate image path.
        // TODO
        // Dummy
        // getFashionCoordinate(candidateIndex); �R�[�f�B�l�[�g���o�͂���API�ƒu������B
        DummyFashionPath dummyFashionPath = new DummyFashionPath();
        String topsPath    = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyTopsPath(candidateIndex);
        String bottomsPath = Environment.getExternalStorageDirectory().getPath() + dummyFashionPath.getDummyBottomsPath(candidateIndex);

        // Draw fashion Coordinate
        updateFashionImage(topsPath, bottomsPath);

    }

    // Update displayed fashion images
    private void updateFashionImage(String topsPath, String bottomsPath){
        File topsImageFile = new File(topsPath);
        File bottomsImageFile = new File(bottomsPath);
        Log.i(LOG_TAG, "topsImageFile: " + topsImageFile.getAbsolutePath());
        Log.i(LOG_TAG, "bottomsImageFile: " + bottomsImageFile.getAbsolutePath());

        // Check file is valid.
        if(!topsImageFile.exists() || !bottomsImageFile.exists()){
            Log.e(LOG_TAG, "Error! File Not Exist!!");
            return;
        }else{
            Log.i(LOG_TAG, "OK! Files exist.");
        }

        // Get bitmap data from File path.
        Bitmap topsBitmap = BitmapFactory.decodeFile(topsImageFile.getAbsolutePath());
        Bitmap bottomsBitmap = BitmapFactory.decodeFile(bottomsImageFile.getAbsolutePath());

        // Get Image View parts.
        ImageView topsImageView = (ImageView)findViewById(R.id.imageView_tops);
        ImageView bottomsImageView = (ImageView)findViewById(R.id.imageView_bottoms);

        // Put bitmap data on each image view.
        topsImageView.setImageBitmap(topsBitmap);
        bottomsImageView.setImageBitmap(bottomsBitmap);
    }
}
