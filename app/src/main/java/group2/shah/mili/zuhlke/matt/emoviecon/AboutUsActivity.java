package group2.shah.mili.zuhlke.matt.emoviecon;

import android.os.Bundle;

/**
 * EMovieCon activity used to start the about us activity
 *
 * @author Mili Shah
 * @author Matt Zuhlke
 * @version 1.0
 */
public class AboutUsActivity extends EMovieConActivity {

    /**
     * Overridden method to start the activity "About Us" from
     * the layout. Extends EMovieConActivity for menu items
     *
     * @param savedInstanceState Saved old data from last run
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EMovieConActivity.activityId = R.layout.activity_about_us;
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about_us);
    }

}
