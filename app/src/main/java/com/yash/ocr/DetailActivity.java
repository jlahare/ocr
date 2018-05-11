package com.yash.ocr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String data = getIntent().getStringExtra("DATA");
        TextView txt = findViewById(R.id.dataTxt);
        txt.setText(data);

    }
}
