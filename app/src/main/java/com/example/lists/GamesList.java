package com.example.lists;

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

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lists.adapters.GamesListAdapter;
import com.example.lists.classes.Games;

import java.util.ArrayList;

public class GamesList extends AppCompatActivity {
    ArrayList<Games> games;
    ListView listView;
    GamesListAdapter gamesListAdapter;
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
                        String genre = result.getData().getStringExtra("data3");

                        games.add(new Games(title, date, genre));

                        gamesListAdapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == OBJECT_EDIT) {
                        assert result.getData() != null;
                        for (Games game : games) {
                            if (game.getId() == Integer.parseInt(result.getData().getStringExtra("obj_id"))) {
                                game.setId(Integer.parseInt(result.getData().getStringExtra("obj_id")));
                                game.setTitle(result.getData().getStringExtra("data1"));
                                game.setRelease_date(Integer.parseInt(result.getData().getStringExtra("data2")));
                                game.setGenre(result.getData().getStringExtra("data3"));
                            }
                        }

                        gamesListAdapter.notifyDataSetChanged();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        init();

        listView = findViewById(R.id.listViewGames);
        gamesListAdapter = new GamesListAdapter(this, games);
        listView.setAdapter(gamesListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Games game : games) {
                    if (game.getId() == id) {
                        intent = new Intent(GamesList.this, AddItemActivity.class);
                        intent.putExtra("data1", game.getTitle());
                        intent.putExtra("data2", game.getRelease_date());
                        intent.putExtra("data3", game.getGenre());
                        intent.putExtra("action", "edit");
                        intent.putExtra("obj_type", "game");
                        intent.putExtra("obj_id", game.getId());
                        ActivityResult.launch(intent);
                    }
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Games game : games) {
                    if (game.getId() == id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GamesList.this);
                        builder.setTitle("Notice");
                        builder.setMessage("Delete game?");

                        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == DialogInterface.BUTTON_POSITIVE) {
                                    games.remove(game);
                                    gamesListAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_item_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item) {
            intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("action", "add");
            ActivityResult.launch(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void init() {
        games = new ArrayList<>();
        games.add(new Games("Grand Theft Auto IV", 2008, "Action, Adventure"));
        games.add(new Games("Just Cause 2", 2010, "Action, Adventure"));
        games.add(new Games("Metro Exodus", 2019, "Action"));
        games.add(new Games("L.A Noire", 2011, "Adventure, Strategy"));
        games.add(new Games("Red Dead Redemption 2", 2019, "Action, Adventure"));
        games.add(new Games("Dying Light 2", 2022, "Action, Adventure, RPG"));
    }
}