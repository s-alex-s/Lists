package com.example.lists.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lists.R;
import com.example.lists.classes.Games;

import java.util.ArrayList;
import java.util.List;

public class GamesListAdapter extends ArrayAdapter<Games> {
    Context context;
    ArrayList<Games> games;

    public GamesListAdapter(Context context, ArrayList<Games> games) {
        super(context, R.layout.item_games, games);

        this.context = context;
        this.games = games;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Games game = this.games.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        @SuppressLint({"ViewHolder", "InflateParams"}) View v =
                inflater.inflate(R.layout.item_games, null, false);

        TextView tvTitle = v.findViewById(R.id.game_title);
        TextView tvDate = v.findViewById(R.id.game_date);
        TextView tvGenre = v.findViewById(R.id.game_genre);

        tvTitle.setText(game.getTitle());
        tvDate.setText(game.getRelease_date() + "");
        tvGenre.setText(game.getGenre());

        v.setTag(game.getId());

        return v;
    }
}
