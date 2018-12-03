package group2.shah.mili.zuhlke.matt.emoviecon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * EMovieConActivity Class to start every activity that extends this class with the header and
 * navigation menu
 *
 * @author Matt Zuhlke
 * @version 1.0
 */
@SuppressLint("Registered")
public class EMovieConActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int activityId;

    /**
     * Override the onCreate method in AppCompatActivity to add the drawer layout for the navigation
     * menu. Sets the toolbar instead of using the theme.
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activityId);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Captures the back button to close the drawer back to frame
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Handles an item selection from the UI
     *
     * @param item Item click on by user from the UI
     * @return true after the drawer is closed
     * false is swollowed
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_playing_now) {
            startActivity(new Intent(this, PlayingNowActivity.class));
        } else if (id == R.id.nav_upcoming) {
            startActivity(new Intent(this, UpcomingActivity.class));
        } else if (id == R.id.nav_refresh_lists) {
            Toast.makeText(this, "Updating lists from TheMovieDB!", Toast.LENGTH_LONG).show();
            TheMovieDBWrapper.updateLists(this.getApplicationContext());
            Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
