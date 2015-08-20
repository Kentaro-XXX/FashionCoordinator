package com.example.kentaro.fashioncoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 0000100038 on 2015/07/22.
 */
public class FashionHistory extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fashion_history);

        // ƒ{ƒ^ƒ“’è‹`
        Button buttonScan = (Button)findViewById(R.id.button_history_back);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(getApplication(), FashionStart.class);

                startActivity(intentStart);

            }
        });
    }


}
