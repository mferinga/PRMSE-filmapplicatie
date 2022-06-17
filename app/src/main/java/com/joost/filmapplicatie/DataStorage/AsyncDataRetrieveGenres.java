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

public class AsyncDataRetrieveGenres extends AsyncTask<Void, Void, String> {

    private DatasetListener listener;

    public AsyncDataRetrieveGenres(DatasetListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return NetworkUtils.getGenres();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            JSONObject jsonObject = new JSONObject(s);

            JSONArray itemsArray = jsonObject.getJSONArray("results");

            Log.i("ADRG", "Test 2500: Genres in AsyncDataRetrieve");
            Log.i("ADRG", "Test 2501: " + itemsArray);


            int i = 0;

            JSONArray genresJSONArray = null;
            List<Integer> genresList = null;



            while (i < itemsArray.length()) {

                Log.d("ADRG", "Test 2503: i = " + i);

                JSONObject movie = itemsArray.getJSONObject(i);

                try {

                    genresJSONArray = movie.getJSONArray("genre_ids");
                    Log.d("ADRG", "Test 2503: " + genresJSONArray);

                    if (genresJSONArray != null) {
                        genresList = new ArrayList<>();
                        for (int j = 0; j < genresJSONArray.length(); j++) {
                            genresList.add(genresJSONArray.getInt(j));
                        }
                    }

                    int[] genresArray = new int[genresList.size()];

                    for (int k = 0; k < genresList.size(); k++) {
                        genresArray[k] = genresList.get(k);
                    }

//                    Log.d("ADRG", "Test 2504: " + genresArray.toString());

                    listener.setGenresIDs(genresArray);


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
