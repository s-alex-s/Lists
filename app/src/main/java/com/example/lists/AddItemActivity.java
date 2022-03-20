package com.example.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lists.classes.Games;

public class AddItemActivity extends AppCompatActivity {
    Intent intent;
    TextView data1;
    TextView data2;
    TextView data3;
    Bundle arguments;
    public final int OBJECT_ADD = 3;
    public final int OBJECT_EDIT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        data1 = findViewById(R.id.Data1);
        data2 = findViewById(R.id.Data2);
        data3 = findViewById(R.id.Data3);

        arguments = getIntent().getExtras();

        if (arguments.get("action").toString().equals("edit")) {
            data1.setText(arguments.get("data1").toString());
            data2.setText(arguments.get("data2").toString());
            data3.setText(arguments.get("data3").toString());
        }
    }

    public void clicked(View v) {
        String sdata1 = data1.getText().toString();
        String sdata2 = data2.getText().toString();
        String sdata3 = data3.getText().toString();
        boolean is_ok = true;

        if (sdata1.isEmpty()) {
            data1.setError("Empty input");
            is_ok = false;
        }
        if (sdata2.isEmpty()) {
            data2.setError("Empty input");
            is_ok = false;
        }
        if (sdata3.isEmpty()) {
            data3.setError("Empty input");
            is_ok = false;
        }

        try {
            Integer.parseInt(sdata2);
        } catch (NumberFormatException n) {
            is_ok = false;
            data2.setError("Incorrect input");
        }

        if (is_ok) {
            intent = new Intent();

            if (arguments.get("action").toString().equals("add")) {
                intent.putExtra("data1", sdata1);
                intent.putExtra("data2", sdata2);
                intent.putExtra("data3", sdata3);
                setResult(OBJECT_ADD, intent);
            } else if (arguments.get("action").toString().equals("edit")) {
                intent.putExtra("data1", sdata1);
                intent.putExtra("data2", sdata2);
                intent.putExtra("data3", sdata3);
                intent.putExtra("obj_id", arguments.get("obj_id").toString());
                setResult(OBJECT_EDIT, intent);
            }
            finish();
        }
    }
}