package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class RecyclerView extends AppCompatActivity {
    TextView contacts;
    Button back;
    DatabaseControl control;
    Intent myIntent = new Intent(this, MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        contacts = findViewById(R.id.contacts);
        control = new DatabaseControl(this);
        back = findViewById(R.id.back);

        control.open();
        String names = control.getAllSongsArray().toString();
        contacts.setText(names);
        control.close();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    } // end of on create
} // end