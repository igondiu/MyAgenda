package com.example.myagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

public class Tache extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        Intent intent = getIntent();
        String date_retrieve = intent.getStringExtra("Click_date");
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(date_retrieve);
    }
}

