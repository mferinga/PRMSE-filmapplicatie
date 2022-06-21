package com.joost.filmapplicatie.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joost.filmapplicatie.ApplicationLogic.DatasetListener;
import com.joost.filmapplicatie.ApplicationLogic.MovieListAdapter;
import com.joost.filmapplicatie.ApplicationLogic.MovieListListener;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieve;
import com.joost.filmapplicatie.DataStorage.AsyncDataRetrieveSearch;
import com.joost.filmapplicatie.Domain.Actor;
import com.joost.filmapplicatie.Domain.Movie;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.R;

import java.util.ArrayList;
import java.util.List;


public class  MovieListActivity extends AppCompatActivity implements MovieListListener, DatasetListener {

    protected List<MovieList> movieListList = new ArrayList<>();

    protected List<Movie> allMovies = new ArrayList<>();
    protected List<Movie> searchedMovies = new ArrayList<>();
    protected List<int[]> genreMoviesIDs = new ArrayList<>();
    public static List<Movie> personalList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;

    private String searchKeyword;

    public static String clickedMovieList = "com.joost.shareameal.extra.CLICKED_MOVIELIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity_main);
        getSupportActionBar().setTitle("Movie lists");

        new AsyncDataRetrieve(this).execute();

        for (MovieList ml : this.movieListList) {
            Log.i("MovieListActivity", "Test 200: " + ml.getTitle());
        }

        recyclerView = findViewById(R.id.movie_list_activity_recyclerview);
        Log.i("MovieListActivity", "Test 201: Recyclerview gekoppeld");

        adapter = new MovieListAdapter(this, movieListList);
        Log.i("MovieListActivity", "Test 202: Adapter gemaakt");

        recyclerView.setAdapter(adapter);
        Log.i("MovieListActivity", "Test 203: Adapter gekoppeld aan Recyclerview");

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Log.i("MovieListActivity", "Test 204: recyclerview layout gezet");


        Bundle extras = getIntent().getExtras();
        Log.i("Create Movie info","Test 250: " + extras);
        checkNewListCreated(extras);

        movieListList.add(new MovieList("allMovies", allMovies));
        movieListList.add(new MovieList("searchedMovies", searchedMovies));
        movieListList.add(new MovieList("personalList", personalList));
//        recoverMovieListList.addAll(movieListList);
        Log.i("Create Movie info","Test 299: " + movieListList.size());
    }

    public void checkNewListCreated(Bundle extras){
        if(extras != null){
            String movieListTitle = extras.getString("movieListTitle");

//            Log.i("CreateNewMovieList", "Test 299: " + "size is " + movieListList.size());
            MovieList movieList = new MovieList(movieListTitle);

            movieListList.add(movieList);
            Log.i("CreateNewMovieList", "Test 299: " + "size is " + movieListList.size());
            adapter.notifyItemInserted(movieListList.size());

        }
    }

    @Override
    public void showMoviesPage(MovieList movieList) {
        Log.i("MovieListActivity", "Test 210: MovieList clicked, showing movies page!");

        Intent intent = new Intent(this, MoviesActivity.class);
        Log.i("MovieListActivity", "Test 211: Intent gemaakt");

        // let op Serializable
        intent.putExtra(clickedMovieList, movieList);
        Log.i("MovieListActivity", "Test 212: Aangeklikte movielist in intent gestopt");
        startActivity(intent);

    }

    @Override
    public void addMovie(Movie movie) {
        this.allMovies.add(movie);
        Log.i("MovieListActivity", "Test 1000: nieuwe film -> " + movie.getTitle());
//        Log.i("MovieListActivity", "MovieListList size = " + this.allMovies.size() + "\tMovieSearchList size = " + this.searchedMovies.size());

    }

    @Override
    public void addMovieBySearch(Movie movie) {
        // Hiermee komen de films die je hebt gezocht in de all movielist
//        addMovie(movie);
        this.searchedMovies.add(movie);
//        Log.i("MovieListActivity", "MovieListList size = " + this.allMovies.size() + "\tMovieSearchList size = " + this.searchedMovies.size());

    }

    @Override
    public void addActor(Actor actor) {

    }

    @Override
    public void datasetUpdated() {

        Log.i("MovieListActivity", "Test 1010: aantal films -> " + this.allMovies.size());
//        this.movieListList.add(new MovieList("All movies", this.allMovies));

        if (this.movieListList.size() < 1) {
            this.movieListList.add(new MovieList(getString(R.string.all_movies_list_name), this.allMovies));
        }

        if (this.movieListList.size() < 2) {
            this.movieListList.add(new MovieList(getString(R.string.searched_movies_list_name), this.searchedMovies));
            Log.i("MovieListActivity", "Searched list added to the movieListList");
        }

        if (this.movieListList.size() < 3) {
            this.movieListList.add(new MovieList(getString(R.string.personal_list_list_name), this.personalList));
            Log.i("MovieListActivity", "Personal list added to the movieListList");
        }

        if (this.allMovies.size() == this.genreMoviesIDs.size()) {

            for (int i = 0; i < genreMoviesIDs.size(); i++) {
                this.allMovies.get(i).setGenreIDs(this.genreMoviesIDs.get(i));
                Log.i("MovieListActivity", allMovies.get(i).getTitle() + " has genres ID's: " + this.genreMoviesIDs.get(i));
            }

            logMoviesInGenres();
        }

        this.adapter.notifyDataSetChanged();
        Log.d("MovieListActivity", "Dataset updated");
        Log.i("MovieListActivity", "MovieListList size = " + this.allMovies.size() + "\tMovieSearchList size = " + this.searchedMovies.size());
    }

    @Override
    public int getMovieID() {
        return 0;
    }

    public void createNewMovieList(View view) {
        Intent intent = new Intent(this, CreateMovieList.class);
        startActivity(intent);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    @Override
    public void setGenresIDs(int[] genresIDs) {
        this.genreMoviesIDs.add(genresIDs);
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

    public void searchMovie(View view) {
        EditText searchBar = findViewById(R.id.search_input_field);

        if(!searchBar.getText().toString().equals("")){
            this.searchKeyword = searchBar.getText().toString();
            new AsyncDataRetrieveSearch(this).execute();
        } else{
            Toast.makeText(this, "Input needed to search films", Toast.LENGTH_SHORT).show();
        }
    }

    private void logMoviesInGenres() {
        int genreID = 12;

        for (Movie m : this.allMovies) {

            int[] genreIDs = m.getGenreIDs();

            for (int i : genreIDs) {

                if (i == genreID) {
                    Log.i("MovieListActivity", "Test 2750: " + m.getTitle() + " has a genre with id of " + genreID);
                }
            }
        }

    }



}
