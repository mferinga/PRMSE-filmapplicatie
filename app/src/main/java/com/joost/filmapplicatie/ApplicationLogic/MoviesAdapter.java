package com.joost.filmapplicatie.ApplicationLogic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joost.filmapplicatie.Domain.Movie;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.R;
import com.squareup.picasso.Picasso;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private MovieList movieList;
    private LayoutInflater inflater;
    private MovieListener movieListener;
    private Context context;

    public MoviesAdapter(Context context, MovieList movieList) {
        this.context = context;
        this.movieListener = (MovieListener) context;
        this.inflater = LayoutInflater.from(context);
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("MoviesAdapter", "Test 700: onCreate");
        View itemView = inflater.inflate(R.layout.movies_recyclerview_item, parent, false);

        return new MovieViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie currentMovie = movieList.getMovieByPosition(position);

        holder.movieName.setText(currentMovie.getTitle());
        holder.movieDate.setText(currentMovie.getDate());
        holder.movieRating.setText(currentMovie.getRating() + "");

        // Image async ophalen met Picasso en deze aan de ImageView toekennen
        String url = currentMovie.getImageLink();
        Picasso.get().load(url).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return this.movieList.getMovieListSize();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView movieName;
        public TextView movieDate;
        public ImageView movieImage;
        public TextView movieRating;

        public MoviesAdapter adapter;

        public MovieViewHolder(@NonNull View itemView, MoviesAdapter adapter) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movie_name);
            movieDate = itemView.findViewById(R.id.movie_date);
            movieImage= itemView.findViewById(R.id.movie_image);
            movieRating= itemView.findViewById(R.id.movie_rating);

            this.adapter = adapter;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            Log.i("MoviesAdapter", "Test 400: Clicked on a Movie!");

            int position = getLayoutPosition();
            Log.i("MoviesAdapter", "Test 400: Clicked on Movie at position: " + position);

            Movie movie = movieList.getMovieByPosition(position);
            Log.i("MoviesAdapter", "Test 400: Movie gepakt: " + position);

            movieListener.showMoviesDetailPage(movie);


        }
    }
}
