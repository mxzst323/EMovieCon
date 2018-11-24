package group2.shah.mili.zuhlke.matt.emoviecon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton playing_imgbtn = findViewById(R.id.playing_now_imgbtn);
        ImageButton upcoming_imgbtn = findViewById(R.id.upcoming_imgbtn);
        ImageButton aboutus_imgbtn = findViewById(R.id.about_us_imgbtn);

        playing_imgbtn.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, PlayingNowActivity.class));
            }
        });
        upcoming_imgbtn.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, UpcomingActivity.class));
            }
        });
        aboutus_imgbtn.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });
    }
}
