package group2.shah.mili.zuhlke.matt.emoviecon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash activity that shows the EMovieCon logo and start screen and startup activities
 * like loading lists
 *
 * @author Mili Shah
 * @author Matt Zuhlke
 * @version 1.0
 */
public class SplashActivity extends Activity {

    /**
     * Override onCreate to set a timer task for the EMovieCon Logo and updates the
     * lists from TheMovieDB api
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };
        Timer open = new Timer();
        open.schedule(task, 6000);
        Toast.makeText(this, "Reloading lists from TheMovieDB!", Toast.LENGTH_LONG).show();
        TheMovieDBWrapper.updateLists(this.getApplicationContext());
        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }
}
