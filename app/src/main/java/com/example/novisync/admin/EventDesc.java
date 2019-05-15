package com.example.novisync.admin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDesc extends AppCompatActivity {

    TextView tv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_desc);

        tv = (TextView) findViewById(R.id.tv1);
        b = (Button) findViewById(R.id.b1);

        Intent intent = getIntent();

        tv.setText(intent.getStringExtra("call_name"));
        b.setText(intent.getStringExtra("phnumber"));
        }
    }






