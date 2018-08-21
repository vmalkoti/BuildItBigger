package com.example.jokeactivitylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView joke = findViewById(R.id.joke_text);
        String jokeText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        joke.setText(jokeText);


    }
}
