package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
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

    //NFC��
    private NfcAdapter mNfcAdapter;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_scan);

        BootstrapButton buttonOK = (BootstrapButton)findViewById(R.id.button_scan_ok);
        BootstrapCircleThumbnail buttonHome = (BootstrapCircleThumbnail)findViewById(R.id.button_scan_home);

        //OK
        buttonOK.setOnClickListener(this);

        // Home�{�^���������ꂽ�Ƃ��̏���
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);
                // Start��ʂɑJ�ڂ���
                startActivity(intentStart);

            }
        });


        /**�摜��\������**/
        //�錾
     /*   ImageView imageView1 = new ImageView(this);//(ImageView)findViewById(R.drawable.image0);
        //�摜�̃A�T�C��
        imageView1.setImageResource(R.drawable.image2);
        //�T�C�Y�ύX
        imageView1.setScaleType(ImageView.ScaleType.CENTER);
         //�\������
        setContentView(imageView1);
	*/
    }

    @Override
    public void onClick(View v){
        Intent intentScandisplay = new Intent(getApplication(), FashionScanDisplay.class);

        str_key = Environment.getExternalStorageDirectory().getPath() + "/FashionCoordinator/tops/ShirtsNatural.JPG";   //temp

        //　インテントに値をセット
        intentScandisplay.putExtra("keyword", str_key);
        // Select画面に遷移する
        startActivity(intentScandisplay);
        //String FashionPath = GetFashionPath(str.substring(1));
        Log.d(LOG_TAG, "ScanLastFashionPath : " + str_key);
    }
    //�摜�\��
    // @Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //}	

    @Override
    protected void onResume(){
        super.onResume();

        //����������������
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        //��NFC�̋@�\����
        //NFC�@�\�Ȃ��@��
        if(mNfcAdapter == null){
            Toast.makeText(getApplicationContext(), "no Nfc feature", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //NFC�ʐMOFF���[�h
        if(!mNfcAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(), "off Nfc feature", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        //��NFC�̋@�\����

        //NFC���������Ƃ��ɔ���������
        //PendingIntent���^�C�~���O�i�C�x���g�����j���w�肵��Intent�𔭐�������
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()), 0);

        //�^�C�~���O�́A�^�O�������Ƃ���B
        IntentFilter[] intentFilter = new IntentFilter[]{
                new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        };

        //��������^�O�̎�ނ��w��B
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
        //���������������܂�
    }

    @Override
    public void onPause(){
        super.onPause();

        //����������������
        //�A�v�����\������ĂȂ����́ANFC�ɔ������Ȃ��Ă������悤�ɂ���
        //  mNfcAdapter.disableForegroundDispatch(this);
        //���������������܂�
    }

    //NFC���^�b�`������̏���
    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        //����������������
        String action = intent.getAction();
        if(TextUtils.isEmpty(action)){
            return;
        }

        if(!action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)){
            return;
        }

        //�����I�ƕ\�����Ă݂�
        Toast.makeText(getApplicationContext(), "Get", Toast.LENGTH_SHORT).show();
        //���������������܂�

        //NFC��ID���擾�Bbyte�z��B
        byte[] rawId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String id = "nothing";

        //String�ɕϊ����ĕ\�������Ă݂�
        id = bytesToString(rawId);
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();


        //��NFC�̒��g���擾����
        //NdefMessage��Parcelable�^�Ŏ擾�BNdefMessage������ł���������p�^�[��������B
        Parcelable[] rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if(rawMessage != null){
            //Parcelable�^����NdefMessage�^�ɓ��꒼���B
            NdefMessage[] msgs = new NdefMessage[rawMessage.length];
            String str = "";

            for(int i=0; i<rawMessage.length; i++){
                msgs[i] = (NdefMessage)rawMessage[i];
                //NdefMessage��NdefRecord�Ƀo�����B
                for(NdefRecord record : msgs[i].getRecords()){
                    //�f�[�^�{�̂�Payload�������o���B�o�C�g�z��B
                    byte[] payload = record.getPayload();
                    for(byte data : payload){
                        //���̒l�������Ă�ꍇ������̂�"& 0xff"������
                        str += String.format("%c", data & 0xff);
                    }
                }
                String FashionPath = GetFashionPath(str.substring(1));
                //String FashionPath = GetFashionPath("enw");
                str_key = FashionPath;
                //str_key = Environment.getExternalStorageDirectory().getPath() + "/FashionCoordinator/tops/ShirtsNatural.JPG";

                //ここで画像を表示する
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                // Set initial data.
                Log.i(LOG_TAG, "tops_tag: " + str);
                Log.i(LOG_TAG, "tops_path1: " + str_key);

            }
            Intent intentScandisplay = new Intent(getApplication(), FashionScanDisplay.class);
            //　インテントに値をセット
            intentScandisplay.putExtra("keyword", str_key);
            // Select画面に遷移する
            startActivity(intentScandisplay);
            //String FashionPath = GetFashionPath(str.substring(1));
            Log.d(LOG_TAG, "ScanLastFashionPath : " + str_key);

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
        //�����܂ł��܂��Ȃ�

        String FashionPath = hlpr.getTopsImagePathById(db, str);//test
        return FashionPath;


    }

    //byte�z���String�ɂ��ĕԂ�
    public String bytesToString(byte[] bytes){
        StringBuilder buffer = new StringBuilder();
        boolean isFirst = true;

        for(byte b : bytes){
            if(isFirst){
                isFirst = false;
            } else {
                buffer.append("-");
            }
            //���̏ꍇ������̂Łu& 0xff�v������B
            buffer.append(Integer.toString(b & 0xff));
        }
        return buffer.toString();
    }



}
