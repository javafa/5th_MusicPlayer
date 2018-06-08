package com.kodonho.android.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kodonho.android.musicplayer.R;
import com.kodonho.android.musicplayer.domain.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {
    List<Music> musicList;
    public MusicAdapter(List<Music> musicList){
        this.musicList = musicList;
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
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ImageView albumart;
        private TextView title,artist,duration;
        private ImageButton play;
        private Music music;
        public Holder(View itemView) {
            super(itemView);
            albumart = itemView.findViewById(R.id.imgAlbumart);
            title = itemView.findViewById(R.id.textTitle);
            artist = itemView.findViewById(R.id.textArtist);
            duration = itemView.findViewById(R.id.textDuration);
            play = itemView.findViewById(R.id.btnPlay);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play(v.getContext());
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
            //albumart.setImageURI(music.albumart_uri);
        }
        private void setTitle(){
            title.setText(music.title);
        }
        private void setArtist(){
            artist.setText(music.artist);
        }
        private void setDuration(){
            String m = "";
            String s = "";
            duration.setText(music.duration+"");
        }
        private void play(Context context){

        }
    }
}