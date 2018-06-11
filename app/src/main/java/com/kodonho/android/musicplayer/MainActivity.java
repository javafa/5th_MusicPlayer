package com.kodonho.android.musicplayer;

import android.Manifest;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kodonho.android.musicplayer.adapter.MusicAdapter;
import com.kodonho.android.musicplayer.domain.Music;
import com.kodonho.android.musicplayer.domain.MusicLoader;

import java.util.List;

public class MainActivity extends BaseActivity {

    MusicAdapter adapter;
    RecyclerView recyclerView;

    public MainActivity() {
        super(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    @Override
    public void init() {
        // mediaScan(); HTC, 에뮬레이터와 같이 예외적인 상황에서만 사용
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        List<Music> musicList = MusicLoader.getMusic(this);
        adapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void mediaScan(){
        MediaScannerConnection.scanFile(getApplicationContext(),
            new String[]{"/sdcard"},
            null,
            new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    Log.v("File scan", "file:" + path + "was scanned seccessfully");
                }
            });
    }
}
