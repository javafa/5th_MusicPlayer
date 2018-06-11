package com.kodonho.android.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kodonho.android.musicplayer.PlayerActivity;
import com.kodonho.android.musicplayer.R;
import com.kodonho.android.musicplayer.domain.Music;
import com.kodonho.android.musicplayer.domain.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {
    List<Music> musicList;
    SimpleDateFormat sdf;
    public MusicAdapter(List<Music> musicList){
        this.musicList = musicList;
        sdf = new SimpleDateFormat("mm:ss");
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Music music = musicList.get(position);
        holder.setMusic(music);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView albumart;
        TextView title,artist,duration;
        Music music;
        int position;

        public Holder(View itemView) {
            super(itemView);
            albumart = itemView.findViewById(R.id.imgAlbumart);
            title = itemView.findViewById(R.id.textTitle);
            artist = itemView.findViewById(R.id.textArtist);
            duration = itemView.findViewById(R.id.textDuration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra(PlayerActivity.POSITION, position);
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void setMusic(Music music){
            this.music = music;
            setAlbumart();
            setTitle();
            setArtist();
            setDuration();
        }
        private void setAlbumart(){
            albumart.setImageURI(music.albumart_uri);
        }
        private void setTitle(){
            title.setText(music.title);
        }
        private void setArtist(){
            artist.setText(music.artist);
        }
        private void setDuration(){
            duration.setText(sdf.format(music.duration));
        }
    }
}
