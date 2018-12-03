package group2.shah.mili.zuhlke.matt.emoviecon;

import java.net.URL;

/**
 * EMovieCon Class to hold a movie with the basic information we plan to present
 *
 * @author Matt Zuhlke
 * @version 1.0
 */
public class EMovieCon {
    public int id;
    public String title;
    public String descr;
    public URL photoId;
    public Double voteAverage;
    public long popularity;
    public String releaseDate;

    /**
     * Class Constructor
     */
    EMovieCon() {
    }

    /**
     * Class Constructuor to take in the most basic variables
     *
     * @param title   Movie title
     * @param descr   Movie Overview
     * @param photoId Movie poster URL id pointed to http response from TheMovieDBWrapper
     * @see TheMovieDBWrapper
     */
    EMovieCon(String title, String descr, URL photoId) {
        this.title = title;
        this.descr = descr;
        this.photoId = photoId;
    }

    /**
     * Class Constructor to take in all variables
     *
     * @param id          Movie ID identified by TheMovieDB
     * @param title       Movie Title
     * @param descr       Movie Overview
     * @param photoId     Movie poster URL id pointed to http response from TheMovieDBWrapper
     * @param voteAverage Movie voting average
     * @param popularity  Movie popularity
     * @param releaseDate Movie release date as string
     * @see TheMovieDBWrapper
     */
    EMovieCon(int id, String title, String descr, URL photoId, Double voteAverage, long popularity, String releaseDate) {
        this.id = id;
        this.title = title;
        this.descr = descr;
        this.photoId = photoId;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
    }
}
