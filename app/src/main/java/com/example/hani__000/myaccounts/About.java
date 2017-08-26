package com.example.hani__000.myaccounts;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rey.material.widget.FloatingActionButton;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        FloatingActionButton cancelFBTN = (FloatingActionButton)findViewById(R.id.CancelFBTN);
        cancelFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}
