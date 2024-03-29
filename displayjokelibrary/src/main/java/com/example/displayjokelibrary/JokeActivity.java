package com.example.displayjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        if(getIntent().hasExtra("joke")){
            jokeTextView.setText(getIntent().getStringExtra("joke"));
        }else{
            jokeTextView.setText("No joke, sorry!");
        }

    }
}
