package com.kodonho.android.musicplayer.domain;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static MediaPlayer mediaPlayer = null;

    public static final int STOP = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int EMPTY = 99;

    public static int status = EMPTY;

    public static SeekbarThread seekbarThread = null;

    public static void set(Context context, Uri uri){
        if(status != EMPTY){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(seekbarThread != null){
            seekbarThread.stopThread();
        }
        seekbarThread = new SeekbarThread();
        mediaPlayer = MediaPlayer.create(context , uri);
    }

    public static void play(){
        if(mediaPlayer != null) {
            mediaPlayer.start();
            if(!seekbarThread.running)
                seekbarThread.start();
            status = PLAY;
        }
    }

    public static void pause(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            status = PAUSE;
        }
    }

    public static long getCurrent(){
        if(mediaPlayer != null)
            return mediaPlayer.getCurrentPosition();
        return 0;
    }

    public static void setCurrent(long position){
        if(mediaPlayer != null) {
            // mediaPlayer.
        }
    }

    // seekbar 스레드에서 컨트롤할 인터페이스를 담아두는 저장소
    private static SeekbarCallback seekbarCallback = null;
    public interface SeekbarCallback {
        public void setSeekbar(int position);
    }
    public static void setSeekbar(SeekbarCallback seekbar){
        Player.seekbarCallback = seekbar;
    }
    public static void removeSeekbar(){
        Player.seekbarCallback = null;
    }

    public static class SeekbarThread extends Thread {
        boolean runFlag = true;
        boolean running = false;
        public void run(){
            running = true;
            while(runFlag){
                try {
                    if(mediaPlayer == null)
                        break;
                    if(seekbarCallback != null){
                        seekbarCallback.setSeekbar(mediaPlayer.getCurrentPosition());
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            running = false;
        }
        public void stopThread(){
            runFlag = false;
        }
    }
}
