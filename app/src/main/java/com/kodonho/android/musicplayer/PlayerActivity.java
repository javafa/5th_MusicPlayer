package com.kodonho.android.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {

    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
    }
}
