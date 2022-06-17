package com.joost.filmapplicatie.DataStorage;

import android.os.AsyncTask;
import android.util.Log;

import com.joost.filmapplicatie.ApplicationLogic.DatasetListener;
import com.joost.filmapplicatie.ApplicationLogic.DatasetListenerTrailer;
import com.joost.filmapplicatie.Domain.Actor;
import com.joost.filmapplicatie.Domain.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncDataRetrieveDuration extends AsyncTask<Void, Void, String> {

    private DatasetListenerTrailer listener;

    public AsyncDataRetrieveDuration(DatasetListenerTrailer listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        Log.d("ADRDuration", "Test 2205: Check 1");

        return NetworkUtils.getDurationWithMovieID(listener.getMovieID1());
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            Log.d("ADRDuration", "Test 2200: Data");
            JSONObject jsonObject = new JSONObject(s);

            Log.d("ADRDuration", "Test 2201: JSONObject created");

            String runtime = jsonObject.getString("runtime");

            Log.d("ADRDuration", "Test 2202: " + runtime);

            listener.setDuration(runtime);




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}