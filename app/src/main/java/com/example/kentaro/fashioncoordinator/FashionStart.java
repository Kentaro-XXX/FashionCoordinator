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
                // ‘JˆÚæ‚Ì‰æ–Ê‚ğw’è‚·‚é
                Intent intentScan = new Intent(getApplication(), FashionScan.class);

                // Scan‰æ–Ê‚É‘JˆÚ‚·‚é
                startActivity(intentScan);
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ‘JˆÚæ‚Ì‰æ–Ê‚ğw’è‚·‚é
                Intent intentHistory = new Intent(getApplication(), FashionHistory.class);

                // Scan‰æ–Ê‚É‘JˆÚ‚·‚é
                startActivity(intentHistory);
            }
        });


    }
}
