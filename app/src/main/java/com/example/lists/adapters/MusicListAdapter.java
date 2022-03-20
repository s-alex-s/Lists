package com.example.lists.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lists.R;
import com.example.lists.classes.Music;

import java.util.ArrayList;

public class MusicListAdapter extends ArrayAdapter<Music> {
    Context context;
    ArrayList<Music> music;

    public MusicListAdapter(Context context, ArrayList<Music> music) {
        super(context, R.layout.item_music, music);

        this.context = context;
        this.music = music;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music music = this.music.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        @SuppressLint({"ViewHolder", "InflateParams"}) View v =
                inflater.inflate(R.layout.item_music, null, false);

        TextView tvTitle = v.findViewById(R.id.music_title);
        TextView tvDate = v.findViewById(R.id.music_date);
        TextView tvAuthor = v.findViewById(R.id.music_author);

        tvTitle.setText(music.getTitle());
        tvDate.setText(music.getDate() + "");
        tvAuthor.setText(music.getAuthor());

        v.setTag(music.getId());

        return v;
    }
}
