package com.yash.ocr;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private void setInformation(String[] data)
    {
        String bankName = data[0];
        String cardNumber = "";
        String txnNumber = "";
        String amount = "";


        for(int i =0 ; i<data.length ; i++)
        {
            if(data[i].toLowerCase().indexOf("txn") !=-1)
            {
                txnNumber = data[i];
                txnNumber = txnNumber.replace("TXN NO" , "");
                txnNumber = txnNumber.replace(":" , "");
            }

            if(data[i].toLowerCase().indexOf("card") !=-1)
            {
                cardNumber = data[i];
                cardNumber = cardNumber.replace(":" , "");
                cardNumber = cardNumber.replace("CARD NO" , "").trim();
            }
            if(data[i].toLowerCase().indexOf("unt") !=-1)
            {
                amount = data[i];
                amount = amount.replace("AMOUNT" , "").trim();
            }
        }

        ((TextView)findViewById(R.id.address)).setText(bankName);
        ((TextView)findViewById(R.id.card_no)).setText(cardNumber);
        ((TextView)findViewById(R.id.txn_no)).setText(txnNumber);
        ((TextView)findViewById(R.id.amount)).setText(amount);

    }
}
