package com.example.lists;

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lists.adapters.MusicListAdapter;
import com.example.lists.classes.Music;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {
    ListView listView;
    ArrayList<Music> music;
    MusicListAdapter musicListAdapter;
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
                        String author = result.getData().getStringExtra("data3");

                        music.add(new Music(title, date, author));

                        musicListAdapter.notifyDataSetChanged();
                    } else if (result.getResultCode() == OBJECT_EDIT) {
                        assert result.getData() != null;
                        for (Music music_item : music) {
                            if (music_item.getId() == Integer.parseInt(result.getData().getStringExtra("obj_id"))) {
                                music_item.setId(Integer.parseInt(result.getData().getStringExtra("obj_id")));
                                music_item.setTitle(result.getData().getStringExtra("data1"));
                                music_item.setDate(Integer.parseInt(result.getData().getStringExtra("data2")));
                                music_item.setAuthor(result.getData().getStringExtra("data3"));
                            }
                        }

                        musicListAdapter.notifyDataSetChanged();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        init();

        listView = findViewById(R.id.listViewMusic);
        musicListAdapter = new MusicListAdapter(this, music);
        listView.setAdapter(musicListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Music music_item : music) {
                    if (id == music_item.getId()) {
                        intent = new Intent(MusicList.this, AddItemActivity.class);
                        intent.putExtra("data1", music_item.getTitle());
                        intent.putExtra("data2", music_item.getDate());
                        intent.putExtra("data3", music_item.getAuthor());
                        intent.putExtra("action", "edit");
                        intent.putExtra("obj_type", "music");
                        intent.putExtra("obj_id", music_item.getId());
                        ActivityResult.launch(intent);
                    }
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (Music music_item : music) {
                    if (id == music_item.getId()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MusicList.this);
                        builder.setTitle("Notice");
                        builder.setMessage("Delete music?");

                        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == DialogInterface.BUTTON_POSITIVE) {
                                    music.remove(music_item);
                                    musicListAdapter.notifyDataSetChanged();
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
        music = new ArrayList<>();
        music.add(new Music("See You Again", 2015, "Wiz Khalifa, Charlie Puth"));
        music.add(new Music("Just Another Day", 2015,
                "Dr. Dre, The Game, Asia Bryant"));
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
            intent = new Intent(MusicList.this, AddItemActivity.class);
            intent.putExtra("action", "add");
            ActivityResult.launch(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}