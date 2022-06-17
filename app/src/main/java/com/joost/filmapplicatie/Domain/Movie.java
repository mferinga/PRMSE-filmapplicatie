package com.joost.filmapplicatie.Domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String date;
    private String imageLink;
    private double rating;
    private String description;
    private int duration;
    private List<String> actors;
    private String trailerLink;
    private int[] genreIDs;

    //temporary constructor for creating the lists, because at the moment you can't chose movies from the API
    public Movie(String title){
        this.title = title;
    }

    public Movie(int id,String title, String date, String imageLink, double rating, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.imageLink = imageLink;
        this.rating = rating;
        this.description = description;
    }

    public Movie(String title, String date, String imageLink, double rating, String description, int duration, List<String> actors, String trailerLink) {
        this.title = title;
        this.date = date;
        this.imageLink = imageLink;
        this.rating = rating;
        this.description = description;
        this.duration = duration;
        this.actors = actors;
        this.trailerLink = trailerLink;
    }


    public static Comparator<Movie> MovieTitleAZComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            return movie1.getTitle().compareTo(movie2.getTitle());
        }
    };

    public static Comparator<Movie> MovieTitleZAComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            int i = movie1.getTitle().compareTo(movie2.getTitle());

            if(i == 0){
                i =+ 1;
            }else {
                i =-1;
            }
            return i;
        }
    };

    public static Comparator<Movie> MovieDateNewToOldComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            int i = movie1.getDate().compareTo(movie2.getDate());
            if(i == 0){
                i =+ 1;
            }else {
                i =-1;
            }
            return i;
        }
    };

    public static Comparator<Movie> MovieDateOldToNewComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            return movie1.getDate().compareTo(movie2.getDate());
        }
    };

    public static final Comparator<Movie> MovieRatingHighToLowComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            String movie1string = Double.toString(movie1.getRating());
            String movie2string = Double.toString(movie2.getRating());

            int i = movie1string.compareTo(movie2string);

            if(i == 0){
                i =+ 1;
            }else {
                i =-1;
            }

            return i;

        }
    };

    public static final Comparator<Movie> MovieRatingLowToHighComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {

            String movie1string = Double.toString(movie1.getRating());
            String movie2string = Double.toString(movie2.getRating());

            return movie1string.compareTo(movie2string);

        }
    };

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImageLink() {
        return imageLink;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public int[] getGenreIDs() {
        return genreIDs;
    }

    public void setGenreIDs(int[] genreIDs) {
        this.genreIDs = genreIDs;
    }

    public boolean isBetween(double firstNumber, double secondNumer){
        if(this.rating > firstNumber && this.rating < secondNumer){
            return true;
        }
        return false;
    }

}
