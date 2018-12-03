package group2.shah.mili.zuhlke.matt.emoviecon;

import java.net.URL;

public class EMovieCon {
    public int id;
    public String title;
    public String descr;
    public URL photoId;
    public Double voteAverage;
    public long popularity;
    public String releaseDate;

    EMovieCon() {
    }

    EMovieCon(String title, String descr, URL photoId) {
        this.title = title;
        this.descr = descr;
        this.photoId = photoId;
    }

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
