package com.kodonho.android.musicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.kodonho.android.musicplayer.domain.Player;

import static com.kodonho.android.musicplayer.domain.Player.pause;

public class PlayerService extends Service {
    public static final String STOP = "com.kodonho.android.musicplayer.STOP";
    public static final String PLAY = "com.kodonho.android.musicplayer.PLAY";
    public static final String PAUSE = "com.kodonho.android.musicplayer.PAUSE";

    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        switch(action){
            case PLAY :
                play();
                break;
            case PAUSE:
                pause();
                break;
            case STOP:
                stop();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }



    public static final int NOTI_FLAG = 999;
    public void play(){
        showNotification();
        Player.play();
    }

    public void pause(){
        Player.pause();
    }

    private void stop() {
        Player.stop();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(NOTI_FLAG);
    }

    public void showNotification(){
        // 알림 매니저
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channel_id = "noti_channel";
        // 26 이상부터는 알람 채널을 생성해서 해당 채널을 통해 알람을 서비스 해야 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림 채널 생성
            CharSequence name = "채널이름";
            String description = "채널설명";
            int importance = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel mChannel = new NotificationChannel(channel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(mChannel);
        }

        // 알림 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id);
        builder
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("음악제목")
            .setContentText("가수이름")
            .setChannelId(channel_id);
        // 펜딩인테트( 나중에 실행할 기능을 담아서 버튼에 전달 )
        Intent stopIntent = new Intent(getApplicationContext(), PlayerService.class);
        stopIntent.setAction(STOP);
        PendingIntent pedingStopIntent = PendingIntent.getService(getApplicationContext(), 1, stopIntent, 0);
        // 알림 전체에 인텐트 달기
        builder.setContentIntent(pedingStopIntent);
        // 버튼 달기
        NotificationCompat.Action pauseAction = makeAction(android.R.drawable.ic_media_pause, "Pause", PAUSE);
        builder.addAction(pauseAction);

        // 알림 보이기
        manager.notify(NOTI_FLAG, builder.build());
    }

    public NotificationCompat.Action makeAction(int resid, String text, String action){
        Intent intent = new Intent(getApplicationContext(), PlayerService.class);
        intent.setAction(action);
        PendingIntent pIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
        return new NotificationCompat.Action.Builder(resid, text, pIntent).build();
    }
}
