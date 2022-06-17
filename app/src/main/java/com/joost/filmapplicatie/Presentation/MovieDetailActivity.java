package com.joost.filmapplicatie.Presentation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;
import com.joost.filmapplicatie.ApplicationLogic.ActorsAdapter;
import com.joost.filmapplicatie.ApplicationLogic.DatasetListener;
import com.joost.filmapplicatie.ApplicationLogic.DatasetListenerTrailer;
import com.joost.filmapplicatie.ApplicationLogic.MovieListAdapter;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieve;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieveActors;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieveDuration;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieveTrailer;
import com.joost.filmapplicatie.Domain.Actor;
import com.joost.filmapplicatie.Domain.Movie;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements DatasetListener, DatasetListenerTrailer {


    private TextView movieDate;
    private ImageView movieImage;
    private TextView movieRating;
    private TextView movieDescription;
    private Button movieTrailer;
    private TextView movieDuration;
    private Button movieAddToPersonalList;
    protected List<Actor> actors = new ArrayList<>();

    private String trailerURL = "";
    private String duration = "";

    private Movie clickedMovie;

    private RecyclerView recyclerView;
    private ActorsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity_main);

//        for (int i = 0; i < 50; i++) {
//            this.actors.add(new Actor("Actor nummer " + i));
//
//        }

        this.clickedMovie = (Movie) getIntent().getSerializableExtra(MoviesActivity.clickedMovie);
        Log.d("MovieDetailActivity", "Test 1200: Check 1");

        new AsyncDataRetrieveActors(this).execute();

        new AsyncDataRetrieveTrailer(this).execute();

        new AsyncDataRetrieveDuration(this).execute();

        Log.d("MovieDetailActivity", "Test 1201: Check 2");

        movieDate = findViewById(R.id.movie_detail_date);
        movieImage= findViewById(R.id.movie_detail_image);
        movieRating = findViewById(R.id.movie_detail_rating);
        movieDescription = findViewById(R.id.movie_detail_description);
        movieTrailer = findViewById(R.id.trailer_button);
        movieDuration = findViewById(R.id.movie_detail_duration);
        movieAddToPersonalList = findViewById(R.id.movie_add_to_personal_list_button);

        movieDate.setText(clickedMovie.getDate());
        movieRating.setText(clickedMovie.getRating() + "");
        movieDescription.setText(clickedMovie.getDescription());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);

        // Image async ophalen met Picasso en deze aan de ImageView toekennen
        String url = this.clickedMovie.getImageLink();
        Picasso.get().load(url).into(movieImage);
        Log.d("MovieDetailActivity", "Test 1202: Check 3");

        recyclerView = findViewById(R.id.movie_detail_activity_actors_recyclerview);
        Log.d("MovieDetailActivity", "Test 1203: Check 4");

        adapter = new ActorsAdapter(this, actors);
        Log.d("MovieDetailActivity", "Test 1204: Check 5");

        recyclerView.setAdapter(adapter);
        Log.d("MovieDetailActivity", "Test 1205: Check 6");


        recyclerView.setLayoutManager(horizontalLayoutManager);
        Log.d("MovieDetailActivity", "Test 1206: Check 7");

        getSupportActionBar().setTitle(clickedMovie.getTitle());

    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public void addMovieBySearch(Movie movie) {

    }

    @Override
    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    @Override
    public void datasetUpdated() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public int getMovieID() {
        return clickedMovie.getID();
    }

    @Override
    public String getSearchKeyword() {
        return null;
    }

    @Override
    public void setGenresIDs(int[] genresIDs) {

    }

    @Override
    public int getMovieID1() {
        return clickedMovie.getID();
    }

    @Override
    public void setTrailerURL(String trailerURL) {
//        this.trailerURL = "https://www.youtube.com/watch?v=" + trailerURL;
        this.trailerURL = trailerURL;
//        movieTrailer.setText(trailerURL);
        Log.i("MDA", "Test 1900: Trailer ULR = " + trailerURL);
        Log.i("MDA", "Test 1901: Trailer set!");
    }

    @Override
    public void setDuration(String runtime) {
        this.duration = runtime;
        this.movieDuration.setText("Duration: " + this.duration + " min");
    }

    public void watchYoutubeVideo(View view) {
        Log.i("MovieDetailActivity", "Test 2100: https://www.youtube.com/watch?v=" + trailerURL);
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerURL));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailerURL));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    public void addToPersonalList(View view) {

        boolean contains = false;

        for (Movie m : MovieListActivity.personalList) {
            if (m.getID() == this.clickedMovie.getID()) {
                contains = true;
            }
        }

        if (!contains) {
            MovieListActivity.personalList.add(this.clickedMovie);
        } else {
            Toast.makeText(this, "This movie is already in your personal list!", Toast.LENGTH_SHORT).show();
        }
    }

    public void giveRating(View view) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        double rating = ratingBar.getRating();

        Toast.makeText(this, " Rating for" + clickedMovie.getTitle() + " set to " + rating, Toast.LENGTH_SHORT).show();


    }
}