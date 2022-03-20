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
import com.example.lists.classes.Movies;

import java.util.ArrayList;

public class MoviesListAdapter extends ArrayAdapter<Movies> {
    Context context;
    ArrayList<Movies> movies;

    public MoviesListAdapter(Context context, ArrayList<Movies> movies) {
        super(context, R.layout.item_movies, movies);

        this.context = context;
        this.movies = movies;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movies movies = this.movies.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.item_movies, null, false);

        TextView tvTitle = v.findViewById(R.id.movies_title);
        TextView tvDate = v.findViewById(R.id.movies_date);
        TextView tvDuration = v.findViewById(R.id.movies_duration);

        tvTitle.setText(movies.getTitle());
        tvDate.setText(movies.getDate() + "");
        tvDuration.setText(movies.getDuration());

        v.setTag(movies.getId());

        return v;
    }
}
