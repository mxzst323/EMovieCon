package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class PlayingNowActivity extends EMovieConActivity {

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
