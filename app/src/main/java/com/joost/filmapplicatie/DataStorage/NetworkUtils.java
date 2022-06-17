package com.joost.filmapplicatie.DataStorage;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String MOVIE_BASE_URL =  "https://api.themoviedb.org/3/movie/popular?api_key=12370ac49bb17ff087470862c5fde9ce&language=en-US";
    private static final String MOVIE_ACTOR_URL_PART1 =  "https://api.themoviedb.org/3/movie/";
    private static final String MOVIE_ACTOR_URL_PART2 =  "/credits?api_key=12370ac49bb17ff087470862c5fde9ce";
    private static final String MOVIE_TRAILER_URL_PART1 = "https://api.themoviedb.org/3/movie/";
    private static final String MOVIE_TRAILER_URL_PART2 = "/videos?api_key=12370ac49bb17ff087470862c5fde9ce";
    private static final String MOVIE_SEARCH_URL_PART1 = "https://api.themoviedb.org/3/search/movie?api_key=12370ac49bb17ff087470862c5fde9ce&query=";
    private static final String MOVIE_SEARCH_URL_PART2 = "&page=1";
    private static final String MOVIE_DURATION_URL_PART1 = "https://api.themoviedb.org/3/movie/";
    private static final String MOVIE_DURATION_URL_PART2 = "?api_key=12370ac49bb17ff087470862c5fde9ce";
    private static final String MOVIE_GENRE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=12370ac49bb17ff087470862c5fde9ce&language=en-US&page=1";



    static String getMovieList(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_BASE_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            movieJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 900: API called!");
        Log.d(LOG_TAG, movieJSONString);
        return movieJSONString;
    }

    static String getActorsWithMovieID(int movieID){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String actorJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_ACTOR_URL_PART1 + String.valueOf(movieID) + MOVIE_ACTOR_URL_PART2).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            actorJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 901: Actors requested!");
        Log.d(LOG_TAG, actorJSONString);
        return actorJSONString;
    }

    static String getTrailerWithMovieID(int movieID){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String trailerJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_TRAILER_URL_PART1 + String.valueOf(movieID) + MOVIE_TRAILER_URL_PART2).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            trailerJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 902: Trailer requested!");
        Log.d(LOG_TAG, trailerJSONString);
        return trailerJSONString;
    }

    static String getMoviesByKeyword(String keyword){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_SEARCH_URL_PART1 + keyword + MOVIE_SEARCH_URL_PART2).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            movieJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 903: Searching movies by keyword!");
        Log.d(LOG_TAG, movieJSONString);
        return movieJSONString;
    }

    static String getDurationWithMovieID(int movieID){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String durationJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_DURATION_URL_PART1 + String.valueOf(movieID) + MOVIE_DURATION_URL_PART2).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            durationJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 904: Duration requested!");
        Log.d(LOG_TAG, durationJSONString);
        return durationJSONString;
    }

    static String getGenres(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String genreJSONString = null;

        try{

            Uri builtURI = Uri.parse(MOVIE_GENRE_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());

            //Getting connection with the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Creating a reader and a writing for scanning through the API code
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            //writing the lines from the API to the StringBuilder
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            //checks if the builder isn't empty else return null
            if(builder.length() == 0){
                return null;
            }

            //storing the builder.toString() into the mealJSONString attribute
            genreJSONString = builder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //returns the mealJSONString attribute which contains all the information from the API

        Log.d("NetworkUtils", "Test 905: Genres requested!");
        Log.d(LOG_TAG, genreJSONString);
        return genreJSONString;
    }

}