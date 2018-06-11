package com.kodonho.android.musicplayer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kodonho.android.musicplayer.adapter.PlayerAdapter;
import com.kodonho.android.musicplayer.domain.Music;
import com.kodonho.android.musicplayer.domain.MusicLoader;
import com.kodonho.android.musicplayer.domain.Player;

import java.text.SimpleDateFormat;
import java.util.List;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String POSITION = "position";
    private int position = 0;
    ViewPager viewPager;
    PlayerAdapter adapter;
    List<Music> data;
    Music music;

    SeekBar seekBar;
    TextView textCurrent,textDuration;
    ImageButton btnPlay;

    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sdf = new SimpleDateFormat("mm:ss");
        setContentView(R.layout.activity_player);
        setView();
        data = MusicLoader.getMusic(this);
        adapter = new PlayerAdapter(data);
        viewPager.setAdapter(adapter);

        Intent intent = getIntent();
        position = intent.getIntExtra(POSITION, 0);
        viewPager.setCurrentItem(position);
        setPlayer();
        setViewPagerListener();
    }

    private void setViewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PlayerActivity.this.position = position;
                setPlayer();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setPlayer(){
        music = data.get(position);
        setCurrent(0);
        setDuration(music.duration);
        Player.set(this, music.music_uri);
    }
    private void setCurrent(long current){
        textCurrent.setText(sdf.format(current));
    }
    private void setDuration(long duration){
        textDuration.setText(sdf.format(duration));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPlay:
                if(Player.status == Player.STOP || Player.status == Player.PAUSE) {
                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    Player.play();
                }else if(Player.status == Player.PLAY) {
                    btnPlay.setImageResource(android.R.drawable.ic_media_play);
                    Player.pause();
                }

                break;
            case R.id.btnFf:
                break;
            case R.id.btnRew:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnPrev:
                break;
        }
    }

    private void setView(){
        viewPager = findViewById(R.id.viewPager);
        seekBar = findViewById(R.id.seekBar);
        textCurrent = findViewById(R.id.textCurrent);
        textDuration = findViewById(R.id.textDuration);


        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        findViewById(R.id.btnRew).setOnClickListener(this);
        findViewById(R.id.btnFf).setOnClickListener(this);
        findViewById(R.id.btnNext).setOnClickListener(this);
        findViewById(R.id.btnPrev).setOnClickListener(this);
    }
}
