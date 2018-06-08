package com.kodonho.android.musicplayer.domain;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class Player {
    private static MediaPlayer mediaPlayer = null;

    private static final int STOP = 0;
    private static final int PLAY = 1;
    private static final int PAUSE = 2;
    private static final int EMPTY = 99;

    private static int status = EMPTY;

    public static void set(Context context, Uri uri){
        if(status != EMPTY){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context , uri);
        status = STOP;
    }

    public static void play(){
        if(mediaPlayer != null) {
            mediaPlayer.start();
            status = PLAY;
        }
    }

    public static void pause(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            status = PAUSE;
        }
    }
}
