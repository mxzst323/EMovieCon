package group2.shah.mili.zuhlke.matt.emoviecon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Settings activity that extends EMovieConActivity to add menu functionality
 * Adds Setting for page query count. to limit how many results are available
 * WIP - More can be added
 *
 * @author Matt Zuhlke
 * @author Mili Shah
 * @version 1.0
 */
public class SettingsActivity extends EMovieConActivity {

    public Context context = this;

    /**
     * Override onCreate to set a spinner and load the value into the SharedPreferences
     * This is used in TheMovieDBWrapper to know how many pages to query set onSelectedListener
     * accordingly.
     *
     * @param savedInstanceState Old data saved from a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EMovieConActivity.activityId = R.layout.activity_settings;
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        final SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences("EMovieConPrefs", 0);
        final SharedPreferences.Editor editor = sharedPref.edit();
        Spinner spin = findViewById(R.id.max_page_number);
        int maxPages = sharedPref.getInt("saved_max_pages_key", 3);

        final ArrayList<String> pages = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                pages.add(Integer.toString(i) + " (Recommended)");
            } else {
                pages.add(Integer.toString(i));
            }
        }
        pages.add("Maximum (Not Recommended)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pages);
        spin.setAdapter(adapter);
        spin.setSelection(maxPages - 1); // position is one off because arrays start with the zero position
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pageNum = pages.get(position);
                String[] splitStr = pageNum.split(Pattern.quote("("));
                if (null != splitStr[0]) {
                    try {
                        if (splitStr[0].contains("Max"))
                            editor.putInt("saved_max_pages_key", 11);
                        else
                            editor.putInt("saved_max_pages_key", Integer.parseInt(splitStr[0]));
                    } catch (NumberFormatException nxe) {
                        Log.e("EMovieCon", "Exception: Could not parse maxpage using default" + nxe.getMessage());
                        editor.putInt("saved_max_pages_key", 3);
                    } finally {
                        editor.apply();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(context, "Using Default Value!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
