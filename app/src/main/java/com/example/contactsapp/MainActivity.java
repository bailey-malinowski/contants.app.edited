package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText song;
    Spinner spinner;
    EditText album;
    Button add;
    Button get;
    DatabaseControl control;
    TextView result;
    Button delete;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        song = findViewById(R.id.songname);
        spinner = findViewById(R.id.spinner);
        album = findViewById(R.id.albumname);
        control = new DatabaseControl(this);
        add = findViewById(R.id.addButton);
        get = findViewById(R.id.getbutton);
        result = findViewById(R.id.resultView);
        delete = findViewById(R.id.delete);
        next = findViewById(R.id.next);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String song2 = song.getText().toString();
                    String artist = ((TextView) spinner.getSelectedView()).getText().toString();
                    String album2 = album.getText().toString();
                    control.open();
                   boolean itWorked = control.insert(song2, artist, album2);
                    if(itWorked) {
                    Toast.makeText(getApplicationContext(), "Added" + song + " " + artist + " " + album, Toast.LENGTH_SHORT).show();
                 } else {
                    Toast.makeText(getApplicationContext(), "FAILED " + song + " " + artist + " " + album, Toast.LENGTH_SHORT). show();
                   }
                     onResume();
                     control.close();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String song2 = song.getText().toString();
                control.open();
                control.delete(song2);
                control.close();
                onResume();
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.open();
                String artist = control.getArtist(song.getText().toString());
                control.close();
                result.setText(artist);
                control.open();
                String album = control.getAlbum(song.getText().toString());
                control.close();
                result.setText(album);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MainActivity.this, RecyclerView.class);
                startActivity(k);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}