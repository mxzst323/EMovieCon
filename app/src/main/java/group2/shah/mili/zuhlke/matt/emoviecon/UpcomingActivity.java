package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UpcomingActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        MovieRecyclerAdapter rvAdapter = new MovieRecyclerAdapter(getMovies());

        RecyclerView rv = findViewById(R.id.upcoming_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(rvAdapter);
    }

    private List<EConMovie> getMovies() {
        List<EConMovie> moviesList =  new ArrayList<EConMovie>();
        EConMovie tmpMovie = new EConMovie("Upcoming title", "A drastic movie", 0);
        moviesList.add(tmpMovie);
        tmpMovie = new EConMovie("Upcoming title2", "A Super movie", 0);
        moviesList.add(tmpMovie);
        return moviesList;
    }
}
