package com.g06.bolsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.g06.bolsa.bd.bolsabd;

public class MainActivity extends AppCompatActivity {
bolsabd Databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Databasehelper =new bolsabd(this);
    }

}