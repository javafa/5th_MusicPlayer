package com.kodonho.android.musicplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kodonho.android.musicplayer.R;
import com.kodonho.android.musicplayer.domain.Music;

import java.util.List;

public class PlayerAdapter extends PagerAdapter {
    List<Music> data;

    public PlayerAdapter(List<Music> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override // View 를 inflate 하는 함수
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 1. 뷰생성
        View view = LayoutInflater.from(container.getContext())
            .inflate(R.layout.pager_item, container, false);
        // 2. 위젯 연결
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textArtist = view.findViewById(R.id.textArtist);
        // 3. 데이터 세팅
        Music music = data.get(position);
        imageView.setImageURI(music.albumart_uri);
        textTitle.setText(music.title);
        textArtist.setText(music.artist);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
