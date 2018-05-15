package com.yash.ocr;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //String data = getIntent().getStringExtra("DATA");
        String[] data = getIntent().getStringArrayExtra("DATA");

        TextView txt = findViewById(R.id.dataTxt);
        //txt.setText(data);
        txt.setMovementMethod(new ScrollingMovementMethod());
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.finish();
            }
        });


        setInformation(data);
    }

    private void setInformation(String[] data) {
        try {
            String bankName = data[0];
            String cardNumber = "";
            String txnNumber = "";
            String amount = "";

            for (int i = 0; i < data.length; i++) {
                if (data[i].toLowerCase().indexOf("txn") != -1) {
                    txnNumber = data[i];
                    txnNumber = txnNumber.replace("TXN NO", "").trim();
                    txnNumber = txnNumber.replace("TXN", "").trim();
                    txnNumber = txnNumber.replace(":", "");
                    txnNumber = txnNumber.replace("RESP", "").trim();
                    txnNumber = txnNumber.replace("NO", "").trim();
                    txnNumber = txnNumber.replace("NO", "").trim();
                    txnNumber = txnNumber.replace("000", "").trim();
                }

                if (data[i].toLowerCase().indexOf("card") != -1) {
                    cardNumber = data[i];
                   /* cardNumber = cardNumber.replace(":", "");
                    cardNumber = cardNumber.replace("CARD NO", "").trim();*/
                    cardNumber = cardNumber.replaceAll("[^0-9*]+", "");

                }
                if (data[i].toLowerCase().indexOf("unt") != -1) {
                    amount = data[i];
                /*    amount = amount.replace("AMOUNT", "").trim();
                    amount = amount.replace("AMT", "").trim();
                    amount = amount.replace(":", "");*/
                    amount = amount.replaceAll("[^0-9.]+", "");
                }
            }

            ((TextView) findViewById(R.id.address)).setText(bankName);
            ((TextView) findViewById(R.id.card_no)).setText(cardNumber);
            ((TextView) findViewById(R.id.txn_no)).setText(txnNumber);
            ((TextView) findViewById(R.id.amount)).setText(amount);
        } catch (Throwable throwable) {
            Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
        }


    }
}
