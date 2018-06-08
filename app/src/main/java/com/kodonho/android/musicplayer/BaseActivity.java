package com.kodonho.android.musicplayer;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQ_PERM = 99;
    // 0. 사용할 권한 설정
    String permissions[];

    public BaseActivity(String[] permissions){
        this.permissions = permissions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 버전체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }else{
            init();
        }
    }

    // 1. 권한체크
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(){
        // 1.1 설정 권한을 반복문을 돌면서 모두 체크한다.
        boolean check = true;
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                check = false;
                requestPermissions(permissions,REQ_PERM);
                break;
            }
        }
        // 1.2 반복문을 통과했다면 모든 권한이 승인된 상태
        if(check)
            init();
    }

    // 2. request permission 이후 처리
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_PERM){
            // 2.1 모든 승인결과를 반복문을 돌면서 체크
            for(int grant : grantResults){
                if(grant != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "권한요청을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                }
            }
            // 2.2 for문을 통과했다면
            init();
        }
    }

    public abstract void init();
}
