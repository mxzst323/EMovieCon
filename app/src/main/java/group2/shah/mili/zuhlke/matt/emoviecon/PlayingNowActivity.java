package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Playing now activity that extends EMovieConActivity to add menu functionality
 * Grabs movies in the playing now category in TheMovieDB
 *
 * @author Matt Zuhlke
 * @author Mili Shah
 * @version 1.0
 */
public class PlayingNowActivity extends EMovieConActivity {

    /**
     * Override the onCreate to get the list of movies and set the recycler adapter
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EMovieConActivity.activityId = R.layout.activity_playing_now;
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_playing_now);

        List<EMovieCon> npMovies = TheMovieDBWrapper.getNowPlayingMovies();

        if (npMovies.size() > 0) {
            MovieRecyclerAdapter rvAdapter = new MovieRecyclerAdapter(npMovies);

            RecyclerView rv = findViewById(R.id.now_playing_rv);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(rvAdapter);
        }
    }
}
