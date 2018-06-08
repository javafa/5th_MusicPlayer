package com.kodonho.android.musicplayer.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MusicLoader {
    private static List<Music> musicList = null;
    // 음원 가져오기
    public static List<Music> getMusic(Context context){
        if(musicList == null)
            musicList = new ArrayList<>();
        if(musicList.size() < 1){
            ContentResolver resolver = context.getContentResolver();
            // 1. 데이터 주소
            Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            // 2. 가져올 컬럼 정의
            String projections[] = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION
            };
            // 3. 쿼리 실행
            Cursor cursor = resolver.query(
                musicUri,
                projections,
                null,
                null,
                MediaStore.Audio.Media.TITLE+" asc");
            // 4. 반복문을 돌면서 list에 담는다
            if(cursor != null){
                while(cursor.moveToNext()){
                    Music music = new Music();
                    music.id = getColumnString(cursor,projections[0]);
                    music.title = getColumnString(cursor,projections[1]);
                    music.artist = getColumnString(cursor,projections[2]);
                    music.albumart_id = getColumnString(cursor,projections[3]);
                    music.duration = getColumnLong(cursor,projections[4]);
                    music.setMusicURI();
                    music.setAlbumArt();
                    musicList.add(music);
                }
            }
        }
        return musicList;
    }
    private static String getColumnString(Cursor cursor, String column){
        int index = cursor.getColumnIndex(column);
        return cursor.getString(index);
    }
    private static long getColumnLong(Cursor cursor, String column){
        int index = cursor.getColumnIndex(column);
        return cursor.getLong(index);
    }
    private static int getColumnInt(Cursor cursor, String column){
        int index = cursor.getColumnIndex(column);
        return cursor.getInt(index);
    }
}
