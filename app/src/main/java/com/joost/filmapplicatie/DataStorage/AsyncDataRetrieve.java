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

public class AsyncDataRetrieve extends AsyncTask<Void, Void, String> {

    private DatasetListener listener;

    public AsyncDataRetrieve(DatasetListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return NetworkUtils.getMovieList();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            Log.d("AsyncDataRetrieve", "Test 800: Data");
            JSONObject jsonObject = new JSONObject(s);
            Log.d("AsyncDataRetrieve", "Test 801: JSONObject created");

            JSONArray itemsArray = jsonObject.getJSONArray("results");

//            Log.d("AsyncDataRetrieve", "Test 805:" + itemsArray);

            Log.d("AsyncDataRetrieve", "Test 806: Data");

            int i = 0;

            int movieID = 0;
            String movieName = null;
            String movieReleaseDate = null;
            String movieImageLink = null;
            double movieRating = 0;
            String movieDescription = null;
            List<Integer> genreList = null;

            JSONArray genresJSONArray = null;


            while (i < itemsArray.length()) {

                Log.d("AsyncDataRetrieve", "Test 802: i = " + i);

                JSONObject movie = itemsArray.getJSONObject(i);

                try {

                    movieID = movie.getInt("id");
                    movieName = movie.getString("title");
                    movieReleaseDate = movie.getString("release_date");
                    movieImageLink = "https://image.tmdb.org/t/p/w500/" + movie.getString("backdrop_path");
                    movieRating = movie.getDouble("vote_average");
                    movieDescription = movie.getString("overview");

                    //get the movie genres
                    genresJSONArray = movie.getJSONArray("genre_ids");

                    genreList = new ArrayList<>();
                    for(int j = 0; j < genresJSONArray.length(); j++){
                        genreList.add(genresJSONArray.getInt(j));
                    }

                    int[] movieGenre = new int[genreList.size()];

                    for (int k = 0; k < genreList.size(); k++) {
                        movieGenre[k] = genreList.get(k);
                    }


//                    movieImageLink = "https://hips.hearstapps.com/nl.h-cdn.co/quote/images/quote/nieuws/record!-nog-nooit-zijn-er-zoveel-europese-unicorns-bijgekomen-in-een-half-jaar-216479/4227636-2-dut-NL/Record!-Nog-nooit-zijn-er-zoveel-Europese-Unicorns-bijgekomen-in-een-half-jaar.png";


                    Log.i("AsyncDataRetrieve", "Test 803: movieName = "  + movieName);
                    Log.i("AsyncDataRetrieve", "Test 804: movieReleaseDate = "  + movieReleaseDate);
                    Log.i("AsyncDataRetrieve", "Test 805: movieImageLink = "  + movieImageLink);
                    Log.i("AsyncDataRetrieve", "Test 806: movieRating = "  + movieRating);
                    Log.i("AsyncDataRetrieve", "Test 807: movieDescription = "  + movieDescription);
                    Log.i("AsyncDataRetrieve", "Test 808: movieDescription = "  + movieGenre[0] + ", " + movieGenre[1] + ", " + movieGenre[2]);
                    Log.i("AsyncDataRetrieve", "Test 809: ------------");

                    listener.addMovie(new Movie(movieID, movieName, movieReleaseDate, movieImageLink, movieRating, movieDescription));

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
