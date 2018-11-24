package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlayingNowActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_now);

        MovieRecyclerAdapter rvAdapter = new MovieRecyclerAdapter(getMovies());

        RecyclerView rv = findViewById(R.id.now_playing_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(rvAdapter);
    }

    private List<EConMovie> getMovies() {
        List<EConMovie> moviesList =  new ArrayList<EConMovie>();
        EConMovie tmpMovie = new EConMovie("Pulp Fiction", "A drastic movie", 0);
        moviesList.add(tmpMovie);
        tmpMovie = new EConMovie("Breakfast Club", "A Super movie", 0);
        moviesList.add(tmpMovie);
        return moviesList;
    }
}
