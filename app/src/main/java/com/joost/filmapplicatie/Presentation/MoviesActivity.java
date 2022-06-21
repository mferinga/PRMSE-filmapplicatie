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

import com.joost.filmapplicatie.ApplicationLogic.MovieListener;
import com.joost.filmapplicatie.ApplicationLogic.MoviesAdapter;
import com.joost.filmapplicatie.Domain.Movie;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MovieListener {

    protected MovieList movieList;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    private String searchKeyword;

    List<Movie>filteredMovies = new ArrayList<>();
    List<Movie> recoverMovieList = new ArrayList<>();

    public static String clickedMovie = "com.joost.shareameal.extra.CLICKED_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MovieListActivity", "Test 599: MoviesActivity Start");
        setContentView(R.layout.movies_activity_main);

        Log.i("MovieListActivity", "Test 500: MoviesActivity laden...");

        this.movieList = (MovieList) getIntent().getSerializableExtra(MovieListActivity.clickedMovieList);

        recyclerView = findViewById(R.id.movies_activity_recyclerview);
        Log.i("MovieListActivity", "Test 501: Recyclerview gekoppeld");

        adapter = new MoviesAdapter(this, movieList);
        Log.i("MovieListActivity", "Test 502: Adapter gemaakt");

        recyclerView.setAdapter(adapter);
        Log.i("MovieListActivity", "Test 503: Adapter gekoppeld aan Recyclerview");

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Log.i("MovieListActivity", "Test 504: recyclerview layout gezet");

        String actionBarTitle = getResources().getString(R.string.movies_activity_title);
        getSupportActionBar().setTitle(actionBarTitle + movieList.getTitle() + ")");

        recoverMovieList.addAll(movieList.getMovieList());
    }

    @Override
    public void showMoviesDetailPage(Movie movie) {
        Log.i("MovieListActivity", "Test 510: recyclerview layout gezet");

        Intent intent = new Intent(this, MovieDetailActivity.class);
        Log.i("MovieListActivity", "Test 511: Intent gemaakt");

        // let op Serializable
        intent.putExtra(clickedMovie, movie);
        Log.i("MovieListActivity", "Test 512: Aangeklikte movie in intent gestopt");
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_page_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.sort_title_az:
                Collections.sort(movieList.getMovieList(), Movie.MovieTitleAZComparator);
                Toast.makeText(this, "Sort data on title", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.sort_title_za:
                Collections.sort(movieList.getMovieList(), Movie.MovieTitleZAComparator);
                Toast.makeText(this, "Sort data on title", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.sort_date_new_old:
                Collections.sort(movieList.getMovieList(), Movie.MovieDateNewToOldComparator);
                Toast.makeText(this, "Sort data on date", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.sort_date_old_new:
                Collections.sort(movieList.getMovieList(), Movie.MovieDateOldToNewComparator);
                Toast.makeText(this, "Sort data on date", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.sort_rating_high_low:
                Collections.sort(movieList.getMovieList(), Movie.MovieRatingHighToLowComparator);
                Toast.makeText(this, "Sort data on rating", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.sort_rating_low_high:
                Collections.sort(movieList.getMovieList(), Movie.MovieRatingLowToHighComparator);
                Toast.makeText(this, "Sort data on rating", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.filter_all_movies:
                this.movieList.getMovieList().clear();
                this.movieList.getMovieList().addAll(recoverMovieList);
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_action:
                filterMovies(28);
                Toast.makeText(this, "Filtered data on action", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_adventure:
                filterMovies(12);
                Toast.makeText(this, "Filtered data on adventure", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_animation:
                filterMovies(16);
                Toast.makeText(this, "Filtered data on animation", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_comedy:
                filterMovies(35);
                Toast.makeText(this, "Filtered data on comedy", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_crime:
                filterMovies(80);
                Toast.makeText(this, "Filtered data on crime", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_drama:
                filterMovies(18);
                Toast.makeText(this, "Filtered data on drama", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_fantasy:
                filterMovies(14);
                Toast.makeText(this, "Filtered data on fantasy", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_horror:
                filterMovies(27);
                Toast.makeText(this, "Filtered data on horror", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_mystery:
                filterMovies(9648);
                Toast.makeText(this, "Filtered data on mystery", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_romance:
                filterMovies(10749);
                Toast.makeText(this, "Filtered data on romance", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_science_fiction:
                filterMovies(878);
                Toast.makeText(this, "Filtered data on science_fiction", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_genre_thriller:
                filterMovies(53);
                Toast.makeText(this, "Filtered data on thriller", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_rating_0_2:
                Toast.makeText(this, "Filtered data on rating from 0 - 2", Toast.LENGTH_SHORT).show();
                List<Movie> toFilterZeroTwoList = new ArrayList<>();
                toFilterZeroTwoList.addAll(this.movieList.getMovieList());

                this.movieList.getMovieList().clear();

                for(Movie movie : toFilterZeroTwoList){
                    if(movie.isBetween(0.0, 2.0)){
                        this.movieList.getMovieList().add(movie);
                    }
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_rating_2_4:
                Toast.makeText(this, "Filtered data on rating from 2 - 4", Toast.LENGTH_SHORT).show();
                List<Movie> toFilterTwoFourList = new ArrayList<>();
                toFilterTwoFourList.addAll(this.movieList.getMovieList());

                this.movieList.getMovieList().clear();
                for(Movie movie : toFilterTwoFourList){
                    if(movie.isBetween(2.0, 4.0)){
                        this.movieList.getMovieList().add(movie);
                    }
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_rating_4_6:
                Toast.makeText(this, "Filtered data on rating from 4 - 6", Toast.LENGTH_SHORT).show();
                List<Movie> toFilterFourSixList = new ArrayList<>();
                toFilterFourSixList.addAll(this.movieList.getMovieList());

                this.movieList.getMovieList().clear();

                for(Movie movie : toFilterFourSixList){
                    if(movie.isBetween(4.0, 6.0)){
                        this.movieList.getMovieList().add(movie);
                    }
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_rating_6_8:
                Toast.makeText(this, "Filtered data on rating from 6 - 8", Toast.LENGTH_SHORT).show();
                List<Movie> toFilterSixEightList = new ArrayList<>();
                toFilterSixEightList.addAll(this.movieList.getMovieList());

                this.movieList.getMovieList().clear();

                for(Movie movie : toFilterSixEightList){
                    if(movie.isBetween(6.0, 8.0)){
                        this.movieList.getMovieList().add(movie);
                    }
                }

                adapter.notifyDataSetChanged();
                break;

            case R.id.filter_rating_8_10:
                Toast.makeText(this, "Filtered data on rating from 8 - 10", Toast.LENGTH_SHORT).show();
                List<Movie> toFilterEightTenList = new ArrayList<>();
                toFilterEightTenList.addAll(this.movieList.getMovieList());

                this.movieList.getMovieList().clear();

                for(Movie movie : toFilterEightTenList){
                    if(movie.isBetween(8.0, 10.0)){
                        this.movieList.getMovieList().add(movie);
                    }
                }
                adapter.notifyDataSetChanged();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

//    public String getSearchKeyword() {
//        return searchKeyword;
//    }

    public void searchMovie2(View view) {
        EditText searchBar = findViewById(R.id.search_input_field2);

        if(!searchBar.getText().toString().equals("")){
            this.searchKeyword = searchBar.getText().toString();
//            Toast.makeText(this, "Input = " + searchKeyword, Toast.LENGTH_SHORT).show();

            // Zoeken in movielist
            List<Movie> searchedMovies = new ArrayList<>();
            searchedMovies.addAll(this.movieList.getMovieList());

            this.movieList.getMovieList().clear();

            for(Movie movie : searchedMovies){
                if(containsIgnoreCase(movie.getTitle(),searchKeyword)){
                    this.movieList.getMovieList().add(movie);
                } else if(containsIgnoreCase(movie.getDescription(),searchKeyword)){
                    this.movieList.getMovieList().add(movie);
                }
            }

            // notify update change
            adapter.notifyDataSetChanged();

        } else{
            Toast.makeText(this, "Input needed to search films", Toast.LENGTH_SHORT).show();
        }
    }

    public void filterMovies(int genreId){
        filteredMovies.clear();
        for(Movie movie : movieList.getMovieList()){
            if(movie.checkGenre(genreId)){
                filteredMovies.add(movie);
            }
        }

        Log.i("MovieListActivity", "Test 530: movies: ");

        this.movieList.getMovieList().clear();

        for(Movie movie : filteredMovies){
            this.movieList.getMovieList().add(movie);
            Log.i("MovieListActivity", "Test 530: movies: " + this.movieList.getMovieList().get(0));
        }

        adapter.notifyDataSetChanged();
    }

    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

}
