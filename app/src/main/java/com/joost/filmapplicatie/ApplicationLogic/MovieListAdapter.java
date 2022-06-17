package com.joost.filmapplicatie.ApplicationLogic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joost.filmapplicatie.Domain.Movie;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.Presentation.MovieListActivity;
import com.joost.filmapplicatie.R;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private List<MovieList> movieListList;
    private LayoutInflater inflater;
    private MovieListListener movieListListener;
    private Context context;

    public MovieListAdapter(Context context, List<MovieList> movieListList) {
        this.context = context;
        this.movieListListener = (MovieListListener) context;
        this.inflater = LayoutInflater.from(context);
        this.movieListList = movieListList;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.movie_list_recyclerview_item, parent, false);

        return new MovieListViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {

        MovieList currentMovielist = movieListList.get(position);

        holder.movieListName.setText(currentMovielist.getTitle());


        List<String> firstFiveMovies = currentMovielist.getFirstFiveMovies();

        holder.movie1.setText(firstFiveMovies.get(0));
        holder.movie2.setText(firstFiveMovies.get(1));
        holder.movie3.setText(firstFiveMovies.get(2));
        holder.movie4.setText(firstFiveMovies.get(3));
        holder.movie5.setText(firstFiveMovies.get(4));

    }

    @Override
    public int getItemCount() {
        return movieListList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView movieListName;
        public TextView movie1;
        public TextView movie2;
        public TextView movie3;
        public TextView movie4;
        public TextView movie5;

        public MovieListAdapter adapter;

        public MovieListViewHolder(View itemView, MovieListAdapter adapter) {
            super(itemView);

            movieListName = itemView.findViewById(R.id.movie_list_name);
            movie1 = itemView.findViewById(R.id.list_movie_1);
            movie2 = itemView.findViewById(R.id.list_movie_2);
            movie3 = itemView.findViewById(R.id.list_movie_3);
            movie4 = itemView.findViewById(R.id.list_movie_4);
            movie5 = itemView.findViewById(R.id.list_movie_5);

            this.adapter = adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Log.i("MovieListAdapter", "Test 300: Clicked on a MovieList!");

            int position = getLayoutPosition();
            Log.i("MovieListAdapter", "Test 301: Clicked on MovieList at position: " + position);

            MovieList movieList = movieListList.get(position);
            Log.i("MovieListAdapter", "Test 302: Movielist gepakt");

            movieListListener.showMoviesPage(movieList);




        }
    }
}
