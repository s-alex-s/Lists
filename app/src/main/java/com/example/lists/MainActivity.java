package com.example.lists;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemGames) {
            intent = new Intent(this, GamesList.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.itemMusic) {
            intent = new Intent(this, MusicList.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.itemMovies) {
            intent = new Intent(this, MoviesList.class);
            startActivity(intent);
        }
        
        return super.onOptionsItemSelected(item);
    }
}