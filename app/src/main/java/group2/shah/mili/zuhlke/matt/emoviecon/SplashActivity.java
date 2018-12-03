package group2.shah.mili.zuhlke.matt.emoviecon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

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
