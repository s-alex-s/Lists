package com.example.lists;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lists.adapters.MoviesListAdapter;
import com.example.lists.classes.Movies;

import java.util.ArrayList;

public class MoviesList extends AppCompatActivity {
    ListView listView;
    ArrayList<Movies> movies;
    MoviesListAdapter moviesListAdapter;
    Intent intent;
    public final int OBJECT_ADD = 3;
    public final int OBJECT_EDIT = 4;

    ActivityResultLauncher<Intent> ActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<androidx.activity.result.ActivityResult>() {
                @Override
                public void onActivityResult(androidx.activity.result.ActivityResult result) {
                    if (result.getResultCode() == OBJECT_ADD) {
                        assert result.getData() != null;
                        String title = result.getData().getStringExtra("data1");
                        int date = Integer.parseInt(result.getData().getStringExtra("data2"));
                        String duration = result.getData().getStringExtra("data3");

                        movies.add(new Movies(title, date, duration));

                        moviesListAdapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == OBJECT_EDIT) {
                        assert result.getData() != null;
                        for (Movies movie : movies) {
                            if (movie.getId() == Integer.parseInt(result.getData().getStringExtra("obj_id"))) {
                                movie.setId(Integer.parseInt(result.getData().getStringExtra("obj_id")));
                                movie.setTitle(result.getData().getStringExtra("data1"));
                                movie.setDate(Integer.parseInt(result.getData().getStringExtra("data2")));
                                movie.setDuration(result.getData().getStringExtra("data3"));
                            }
                        }

                        moviesListAdapter.notifyDataSetChanged();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        init();

        moviesListAdapter = new MoviesListAdapter(this, movies);
        listView = findViewById(R.id.listViewMovies);
        listView.setAdapter(moviesListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Movies movie : movies) {
                    if (movie.getId() == id) {
                        intent = new Intent(MoviesList.this, AddItemActivity.class);
                        intent.putExtra("data1", movie.getTitle());
                        intent.putExtra("data2", movie.getDate());
                        intent.putExtra("data3", movie.getDuration());
                        intent.putExtra("action", "edit");
                        intent.putExtra("obj_type", "movie");
                        intent.putExtra("obj_id", movie.getId());
                        ActivityResult.launch(intent);
                    }
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Movies movie : movies) {
                    if (movie.getId() == id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MoviesList.this);
                        builder.setTitle("Notice");
                        builder.setMessage("Delete movie?");

                        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == DialogInterface.BUTTON_POSITIVE) {
                                    movies.remove(movie);
                                    moviesListAdapter.notifyDataSetChanged();
                                }
                            }
                        };

                        builder.setPositiveButton("Yes", listener);
                        builder.setNegativeButton("No", listener);

                        builder.create().show();
                    }
                }

                return true;
            }
        });
    }

    public void init() {
        movies = new ArrayList<>();
        movies.add(new Movies("Пираты Карибского моря: Проклятие Чёрной жемчужины", 2003,
                "2h 17m"));
        movies.add(new Movies("Форсаж 6", 2013, "130m"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item) {
            intent = new Intent(MoviesList.this, AddItemActivity.class);
            intent.putExtra("action", "add");
            ActivityResult.launch(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}