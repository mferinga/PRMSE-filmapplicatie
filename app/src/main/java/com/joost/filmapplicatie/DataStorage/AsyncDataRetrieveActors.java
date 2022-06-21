package com.joost.filmapplicatie.DataStorage;

import android.os.AsyncTask;
import android.util.Log;

import com.joost.filmapplicatie.ApplicationLogic.DatasetListener;
import com.joost.filmapplicatie.Domain.Actor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncDataRetrieveActors extends AsyncTask<Void, Void, String> {

    private DatasetListener listener;

    public AsyncDataRetrieveActors(DatasetListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return NetworkUtils.getActorsWithMovieID(listener.getMovieID());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            Log.d("AsyncDataRetrieve", "Test 800: Data");
            JSONObject jsonObject = new JSONObject(s);
            Log.d("AsyncDataRetrieve", "Test 801: JSONObject created");

            JSONArray itemsArray = jsonObject.getJSONArray("cast");



//            Log.d("AsyncDataRetrieve", "Test 805:" + itemsArray);

            Log.d("AsyncDataRetrieve", "Test 806: Data");



            int i = 0;

            int actorID = 0;
            String actorName = null;
            String actorImageLink = null;
            String actorCharacter = null;


            while (i < 15) {

                Log.d("AsyncDataRetrieveActors", "Test 1102: i = " + i);

                JSONObject actor = itemsArray.getJSONObject(i);

                try {


                    actorID =  actor.getInt("id");
                    actorName =  actor.getString("original_name");
                    actorImageLink = "https://image.tmdb.org/t/p/w500/" + actor.getString("profile_path");
                    actorCharacter = actor.getString("character");

                    Log.i("AsyncDataRetrieveActors", "Test 1105: Actor id = " + actorID);
                    Log.i("AsyncDataRetrieveActors", "Test 1106: Actor name = " + actorName);
                    Log.i("AsyncDataRetrieveActors", "Test 1107: Actor Imagelink = " + actorImageLink);
                    Log.i("AsyncDataRetrieveActors", "Test 1108: Actor Character = " + actorCharacter);

                    listener.addActor(new Actor(actorID, actorName, actorImageLink, actorCharacter));

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
