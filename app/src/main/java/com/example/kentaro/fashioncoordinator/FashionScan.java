package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.nfc.*;
import android.os.Parcelable;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.ViewGroup;



import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.util.Log;

import com.example.kentaro.fashioncoordinator.databaseManager.FashionInitialData;
import com.example.kentaro.fashioncoordinator.databaseManager.FashionSQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionScan extends Activity implements View.OnClickListener {
//public class FashionScan extends Activity {

    final String LOG_TAG = "FashionScan_LOG";
    private String str_key;

    //NFC★
    private NfcAdapter mNfcAdapter;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_scan);

        // ボタンの定義
//        Button buttonOK = (Button)findViewById(R.id.button_scan_ok);
//        Button buttonHome = (Button)findViewById(R.id.button_scan_home);
        BootstrapButton buttonOK = (BootstrapButton)findViewById(R.id.button_scan_ok);
        BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail)findViewById(R.id.button_scan_home);

        // OKボタンが押されたときの処理
/*        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSelect = new Intent(getApplication(), FashionSelect.class);
                // Select画面に遷移する
                startActivity(intentSelect);

            }
        });
		*/
        buttonOK.setOnClickListener(this);

        // Homeボタンが押されたときの処理
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);
                // Start画面に遷移する
                startActivity(intentStart);

            }
        });


        /**画像を表示する**/
        //宣言
     /*   ImageView imageView1 = new ImageView(this);//(ImageView)findViewById(R.drawable.image0);
        //画像のアサイン
        imageView1.setImageResource(R.drawable.image2);
        //サイズ変更
        imageView1.setScaleType(ImageView.ScaleType.CENTER);
         //表示する
        setContentView(imageView1);
	*/
    }

    @Override
    public void onClick(View v){
        Intent intentSelect = new Intent(getApplication(), FashionSelect.class);
        //　インテントに値をセット
        intentSelect.putExtra("keyword", str_key);
        // Select画面に遷移する
        startActivity(intentSelect);


    }
    //画像表示
    // @Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //}	

    @Override
    protected void onResume(){
        super.onResume();

        //▼▼▼▼ここから
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        //▼NFCの機能判定
        //NFC機能なし機種
        if(mNfcAdapter == null){
            Toast.makeText(getApplicationContext(), "no Nfc feature", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //NFC通信OFFモード
        if(!mNfcAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(), "off Nfc feature", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        //▲NFCの機能判定

        //NFCを見つけたときに反応させる
        //PendingIntent→タイミング（イベント発生）を指定してIntentを発生させる
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()), 0);

        //タイミングは、タグ発見時とする。
        IntentFilter[] intentFilter = new IntentFilter[]{
                new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        };

        //反応するタグの種類を指定。
        String[][] techList = new String[][]{
                {
                        android.nfc.tech.NfcA.class.getName(),
                        android.nfc.tech.NfcB.class.getName(),
                        android.nfc.tech.IsoDep.class.getName(),
                        android.nfc.tech.MifareClassic.class.getName(),
                        android.nfc.tech.MifareUltralight.class.getName(),
                        android.nfc.tech.NdefFormatable.class.getName(),
                        android.nfc.tech.NfcV.class.getName(),
                        android.nfc.tech.NfcF.class.getName(),
                }
        };
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, techList);
        //▲▲▲▲▲ここまで
    }

    @Override
    public void onPause(){
        super.onPause();

        //▼▼▼▼ここから
        //アプリが表示されてない時は、NFCに反応しなくてもいいようにする
        //  mNfcAdapter.disableForegroundDispatch(this);
        //▲▲▲▲▲ここまで
    }

    //NFCをタッチした後の処理
    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        //▼▼▼▼ここから
        String action = intent.getAction();
        if(TextUtils.isEmpty(action)){
            return;
        }

        if(!action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)){
            return;
        }

        //成功！と表示してみる
        Toast.makeText(getApplicationContext(), "Get", Toast.LENGTH_SHORT).show();
        //▲▲▲▲▲ここまで

        //NFCのIDを取得。byte配列。
        byte[] rawId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String id = "nothing";

        //Stringに変換して表示させてみる
        id = bytesToString(rawId);
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();


        //▼NFCの中身を取得する
        //NdefMessageをParcelable型で取得。NdefMessageが並列でいくつかあるパターンがある。
        Parcelable[] rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if(rawMessage != null){
            //Parcelable型からNdefMessage型に入れ直す。
            NdefMessage[] msgs = new NdefMessage[rawMessage.length];
            String str = "";

            for(int i=0; i<rawMessage.length; i++){
                msgs[i] = (NdefMessage)rawMessage[i];
                //NdefMessageをNdefRecordにバラす。
                for(NdefRecord record : msgs[i].getRecords()){
                    //データ本体のPayload部を取り出す。バイト配列。
                    byte[] payload = record.getPayload();
                    for(byte data : payload){
                        //負の値が入ってる場合があるので"& 0xff"をつける
                        str += String.format("%c", data & 0xff);
                    }
                }
                String FashionPath = GetFashionPath(str.substring(1));
                str_key = FashionPath;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                // Set initial data.
                Log.i(LOG_TAG, "tops_tag: " + str);
                Log.i(LOG_TAG, "tops_path1: " + str_key);



            }
        }
        else{
            Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
        }



    }


    private String GetFashionPath(String str){
        /* Initiate Database. */
        SQLiteDatabase db;
        // FashionSQLiteOpenHelper Class
        FashionSQLiteOpenHelper hlpr = new FashionSQLiteOpenHelper(getApplicationContext());

        // Get writable database
        db = hlpr.getWritableDatabase();
        //ここまでおまじない

        String FashionPath = hlpr.getTopsImagePathById(db, str);//test
        return FashionPath;


    }

    //byte配列をStringにして返す
    public String bytesToString(byte[] bytes){
        StringBuilder buffer = new StringBuilder();
        boolean isFirst = true;

        for(byte b : bytes){
            if(isFirst){
                isFirst = false;
            } else {
                buffer.append("-");
            }
            //負の場合があるので「& 0xff」をつける。
            buffer.append(Integer.toString(b & 0xff));
        }
        return buffer.toString();
    }



}
