package com.kodonho.android.musicplayer.domain;

import android.net.Uri;
import android.provider.MediaStore;

public class Music {
    public String id;

    public String title;
    public String artist;
    public String albumart_id;
    public long duration;

    public Uri music_uri;
    public Uri albumart_uri;

    public void setMusicURI(){
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        music_uri = Uri.withAppendedPath(contentUri, id);
    }
    public void setAlbumArt(){
        //Uri contentUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String contentUri = "content://media/external/audio/albumart/";
        albumart_uri = Uri.parse(contentUri + albumart_id);
    }
}
