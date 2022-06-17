package com.joost.filmapplicatie.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joost.filmapplicatie.R;

import java.util.ArrayList;

public class CreateMovieList extends AppCompatActivity {

    private EditText movieListTitleInput;
    private String movieListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_movie_list);
        getSupportActionBar().setTitle("Create new list");
    }

    public void getData(){
        movieListTitleInput = findViewById(R.id.movieListTitleInput);
        movieListTitle = movieListTitleInput.getText().toString().trim();
    }

    public void submitMovie(View view) {
        Intent intent = new Intent(this, MovieListActivity.class);
        getData();
        intent.putExtra("movieListTitle", movieListTitle);
        startActivity(intent);
    }
}