package group2.shah.mili.zuhlke.matt.emoviecon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Main Activity started first after the splash screen
 * Contains large image buttons defined onclick actions
 * to start activity intents
 *
 * @author Matt Zuhlke
 * @author Mili Shah
 * @version 1.0
 */
public class MainActivity extends EMovieConActivity {

    /**
     * Initializing context in class to use in internal OnClickListeners
     * for sharedpreferences and gui updates
     */
    public Context context = this;

    /**
     * Override of create function to set activity layout to menu and
     * layout with customizing the imagebutton onclick events
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EMovieConActivity.activityId = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ImageButton playing_imgbtn = findViewById(R.id.playing_now_imgbtn);
        ImageButton upcoming_imgbtn = findViewById(R.id.upcoming_imgbtn);
        ImageButton aboutus_imgbtn = findViewById(R.id.about_us_imgbtn);
        ImageButton refresh_imgbtn = findViewById(R.id.refresh_list_imgbtn);

        playing_imgbtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlayingNowActivity.class));
            }
        });
        upcoming_imgbtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UpcomingActivity.class));
            }
        });
        aboutus_imgbtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });
        refresh_imgbtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Updating lists from TheMovieDB!", Toast.LENGTH_LONG).show();
                TheMovieDBWrapper.updateLists(context.getApplicationContext());
                Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
