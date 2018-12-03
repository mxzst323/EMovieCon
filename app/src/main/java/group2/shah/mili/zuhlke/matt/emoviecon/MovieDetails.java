package group2.shah.mili.zuhlke.matt.emoviecon;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Movie Details Activity started after selecting a movie from the recycler view
 *
 * @author Matt Zuhlke
 * @author Mili Shah
 * @version 1.0
 */
public class MovieDetails extends Activity {

    /**
     * Override Create Sets the movie id from the extra
     * variable sent from previous activity and list to search on
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        int movieID = getIntent().getIntExtra("MOVIE_ID", 0);
        int movieCount = getIntent().getIntExtra("LIST_COUNT", 0);

        getMovieFromList(movieID, movieCount);
    }

    /**
     * Searches the movies based on the movie id and moviecount for List
     * position. This splits it in half so searching is faster since this
     * activity is used for both now playing and upcoming
     *
     * @param movieID    id From TheMovieDB to query and get data on
     * @param movieCount List position to get the movie and cross examine with the movieID
     */
    private void getMovieFromList(int movieID, int movieCount) {
        List<EMovieCon> npMovies = TheMovieDBWrapper.getNowPlayingMovies();
        List<EMovieCon> ucMovies = TheMovieDBWrapper.getUpcomingMovies();

        EMovieCon npMovie = new EMovieCon();
        EMovieCon ucMovie = new EMovieCon();
        if (npMovies.size() >= movieCount)
            npMovie = npMovies.get(movieCount);
        if (ucMovies.size() >= movieCount)
            ucMovie = ucMovies.get(movieCount);

        if (npMovie.id == movieID) {
            setMovieInformation(npMovie);
        } else if (ucMovie.id == movieID) {
            setMovieInformation(ucMovie);
        } else {
            Log.e("EMovieCon", "Could not find matching movie!!!!");
        }
    }

    /**
     * Sets the movie title, overview,extra information text, poster,
     * builds related videos as links and grabs backdrops
     *
     * @param movie EMovieCon this activity is displaying
     */
    public void setMovieInformation(EMovieCon movie) {
        Resources resources = getResources();
        TextView title = findViewById(R.id.movie_details_title);
        TextView descr = findViewById(R.id.movie_details_description);
        TextView extraInfo = findViewById(R.id.movie_details_extra_info);
        ImageView poster = findViewById(R.id.movie_details_poster);
        TextView youtubeVideos = findViewById(R.id.movie_details_videos);
        View root = youtubeVideos.getRootView();

        title.setText(movie.title.trim());
        descr.setText(movie.descr.trim());
        String extraStr = resources.getString(R.string.movie_release) + " " + movie.releaseDate +
                String.format(Locale.getDefault(), "%n") +
                resources.getString(R.string.movie_pop) + " " + movie.popularity +
                String.format(Locale.getDefault(), "%n") +
                resources.getString(R.string.movie_voting) + " " + movie.voteAverage;
        extraInfo.setText(extraStr);

        //YouTube Video links
        youtubeVideos.setMovementMethod(LinkMovementMethod.getInstance());
        youtubeVideos.append(String.format(Locale.getDefault(), "%n"));
        ArrayList<ArrayList<String>> videos = TheMovieDBWrapper.getYouTubeLinks(movie.id);
        if (null != videos) {
            SpannableStringBuilder youtubeStr = new SpannableStringBuilder(
                    resources.getText(R.string.related_youtube));
            for (ArrayList<String> vid : videos) {
                final String name = vid.get(0);
                final String url = vid.get(1);
                youtubeStr.append(String.format(Locale.getDefault(), "%n\u2022"));
                youtubeStr.append(name);
                youtubeStr.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        //Not opening app since most phones open youtube automatically on "youtube.com" in the URI
                    }
                }, youtubeStr.length() - name.length(), youtubeStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            youtubeVideos.setText(youtubeStr, TextView.BufferType.SPANNABLE);
        }

        //Poster
        final URL photoURL = movie.photoId;
        poster.setImageBitmap(TheMovieDBWrapper.getMoviePostBitMap(photoURL));

        //Backdrops
        ArrayList<Bitmap> backdrop = TheMovieDBWrapper.getBackdrops(movie.id);
        if (null != backdrop && backdrop.size() > 0) {
            BitmapDrawable ob = new BitmapDrawable(resources, backdrop.get(0));
            root.setBackground(ob);
        } else {
            root.setBackgroundResource(R.drawable.popcon);
        }

    }
}
