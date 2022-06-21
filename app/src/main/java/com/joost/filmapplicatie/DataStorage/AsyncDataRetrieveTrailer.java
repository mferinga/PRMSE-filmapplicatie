package com.joost.filmapplicatie.DataStorage;

import android.os.AsyncTask;
import android.util.Log;

import com.joost.filmapplicatie.ApplicationLogic.DatasetListenerTrailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncDataRetrieveTrailer extends AsyncTask<Void, Void, String> {

    private DatasetListenerTrailer listener;

    public AsyncDataRetrieveTrailer(DatasetListenerTrailer listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        Log.d("ADR", "Test 1800: Check 1");

        return NetworkUtils.getTrailerWithMovieID(listener.getMovieID1());
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            Log.d("ADRTrailer", "Test 1300: Data");
            JSONObject jsonObject = new JSONObject(s);
            Log.d("ADRTrailer", "Test 1301: JSONObject created");

            JSONArray itemsArray = jsonObject.getJSONArray("results");

            Log.d("ADRTrailer", "Test 1306: Data");



//            Log.d("AsyncDataRetrieve", "Test 805:" + itemsArray);

            Log.d("AsyncDataRetrieve", "Test 806: Data");



            int i = 0;


            String trailerURL = null;



            while (i < 1) {



                JSONObject trailer = itemsArray.getJSONObject(i);

                try {


                    trailerURL =  trailer.getString("key");

                    Log.i("ADRTrailer", "Test 1405: TrailerULR = https://www.youtube.com/watch?v=" + trailerURL);


                    listener.setTrailerURL(trailerURL);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
