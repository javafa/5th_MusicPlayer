package com.kodonho.android.musicplayer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kodonho.android.musicplayer.adapter.PlayerAdapter;
import com.kodonho.android.musicplayer.domain.Music;
import com.kodonho.android.musicplayer.domain.MusicLoader;

import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private int current = 0;
    ViewPager viewPager;
    PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        viewPager = findViewById(R.id.viewPager);
        List<Music> data = MusicLoader.getMusic(this);
        adapter = new PlayerAdapter(data);
        viewPager.setAdapter(adapter);
    }
}
