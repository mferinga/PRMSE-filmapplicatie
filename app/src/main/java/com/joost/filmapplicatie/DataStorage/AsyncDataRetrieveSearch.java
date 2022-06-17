package com.joost.filmapplicatie.DataStorage;


import android.os.AsyncTask;
import android.util.Log;


import com.joost.filmapplicatie.ApplicationLogic.DatasetListener;
import com.joost.filmapplicatie.Domain.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncDataRetrieveSearch extends AsyncTask<Void, Void, String> {

    private DatasetListener listener;

    public AsyncDataRetrieveSearch(DatasetListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return NetworkUtils.getMoviesByKeyword(this.listener.getSearchKeyword());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            JSONObject jsonObject = new JSONObject(s);

            JSONArray itemsArray = jsonObject.getJSONArray("results");



            int i = 0;

            int movieID = 0;
            String movieName = null;
            String movieReleaseDate = null;
            String movieImageLink = null;
            double movieRating = 0;
            String movieDescription = null;


            while (i < 10) {

                Log.d("ADRSearch", "Test 2000: i = " + i);

                JSONObject movie = itemsArray.getJSONObject(i);

                try {

                    movieID = movie.getInt("id");
                    movieName = movie.getString("title");
                    movieReleaseDate = movie.getString("release_date");
                    movieImageLink = "https://image.tmdb.org/t/p/w500/" + movie.getString("backdrop_path");
                    movieRating = movie.getDouble("vote_average");
                    movieDescription = movie.getString("overview");

                    Log.i("ADRSearch", "Test 2001: movieName = "  + movieName);
                    Log.i("ADRSearch", "Test 2002: movieReleaseDate = "  + movieReleaseDate);
                    Log.i("ADRSearch", "Test 2003: movieImageLink = "  + movieImageLink);
                    Log.i("ADRSearch", "Test 2004: movieRating = "  + movieRating);
                    Log.i("ADRSearch", "Test 2005: movieDescription = "  + movieDescription);
                    Log.i("ADRSearch", "Test 2006: ------------");

                    listener.addMovieBySearch(new Movie(movieID, movieName, movieReleaseDate, movieImageLink, movieRating, movieDescription));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }

            listener.datasetUpdated();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
