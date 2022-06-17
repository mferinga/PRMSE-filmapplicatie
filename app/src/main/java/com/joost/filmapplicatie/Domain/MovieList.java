package com.joost.filmapplicatie.Domain;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieList implements Serializable {

    private String title;
    private List<Movie> movieList;

    public MovieList (String title){
        this.title = title;
        movieList = new ArrayList<>();
    }

    public MovieList(String title, List<Movie> movieList) {
        this.title = title;
        this.movieList = movieList;
    }

    public String getTitle() {
        return title;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public Movie getMovieByPosition (int position) {
        return this.movieList.get(position);
    }

    public int getMovieListSize() {
        return this.movieList.size();
    }

    public List<String> getFirstFiveMovies() {
        List<String> results = new ArrayList<>();

        if (this.movieList.isEmpty()){
            for(int i = 0; i < 5; i++){
                results.add("");
            }
        }else if(this.movieList.size() < 5){
            for(int i = 0; i < this.movieList.size(); i++){
                try {
                    results.add(this.movieList.get(i).getTitle());
                } catch (NullPointerException e ){
                    Log.d("MoveList","Movielist had no item at this position");
                }
            }
            for(int j = this.movieList.size(); j < 5; j++){
                results.add("");
            }
        }  else {
            for (int i = 0; i < 5; i++) {
                try {
                    results.add(this.movieList.get(i).getTitle());
                } catch (NullPointerException e ){
                    Log.d("MoveList","Movielist had no item at this position");
                }
            }
        }
//        while (results.size() < 5) {
//            results.add("");
//        }

        return results;
    }

}
