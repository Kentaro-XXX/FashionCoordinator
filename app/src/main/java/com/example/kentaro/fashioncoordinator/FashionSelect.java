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

        // インテントを取得
        Intent intent = getIntent();
        // インテントに保存されたデータを取得
        String data = intent.getStringExtra("keyword");
        //表示してみる
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        // Initialize candidateIndex
        candidateIndex = 0;
        candidateIndexMax = 4;  //TODO

        // ボタン定義
        final BootstrapButton buttonOK = (BootstrapButton) findViewById(R.id.button_select_ok);
        BootstrapButton buttonNext = (BootstrapButton) findViewById(R.id.button_select_next);
        BootstrapButton buttonPrev = (BootstrapButton) findViewById(R.id.button_select_prev);
        BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail) findViewById(R.id.button_select_home);

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
                next();
            }
        });

        // Prevボタンが押されたときの処理
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 前の服を表示する
                prev();
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
        // getFashionCoordinate(candidateIndex); コーディネートを出力するAPIと置換する。
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
        // getFashionCoordinate(candidateIndex); コーディネートを出力するAPIと置換する。
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
        // getFashionCoordinate(candidateIndex); コーディネートを出力するAPIと置換する。
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
