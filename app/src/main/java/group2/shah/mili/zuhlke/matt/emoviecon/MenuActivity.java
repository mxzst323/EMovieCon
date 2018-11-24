package group2.shah.mili.zuhlke.matt.emoviecon;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.curr_movies_menu:
                startActivity(new Intent(this, PlayingNowActivity.class));
                return true;
            case R.id.upcoming_movies_menu:
                startActivity(new Intent(this, UpcomingActivity.class));
                return true;
            case R.id.about_us_menu:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
