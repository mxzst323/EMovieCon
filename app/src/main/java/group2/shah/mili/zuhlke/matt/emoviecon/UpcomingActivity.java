package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class UpcomingActivity extends EMovieConActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EMovieConActivity.activityId = R.layout.activity_upcoming;
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_upcoming);

        List<EMovieCon> ucMovies = TheMovieDBWrapper.getUpcomingMovies();

        if (ucMovies.size() > 0) {
            MovieRecyclerAdapter rvAdapter = new MovieRecyclerAdapter(ucMovies);

            RecyclerView rv = findViewById(R.id.upcoming_rv);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(rvAdapter);
        }
    }
}
