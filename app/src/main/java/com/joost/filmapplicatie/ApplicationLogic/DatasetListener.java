package com.joost.filmapplicatie.ApplicationLogic;

import com.joost.filmapplicatie.Domain.Actor;
import com.joost.filmapplicatie.Domain.Movie;

public interface DatasetListener {

    public void addMovie(Movie movie);

    public void addMovieBySearch(Movie movie);

    public void addActor(Actor actor);

    public void datasetUpdated();

    public int getMovieID();

    public String getSearchKeyword();

    public void setGenresIDs(int[] genresIDs);

}
