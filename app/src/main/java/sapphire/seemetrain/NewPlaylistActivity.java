package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * File Created by Joseph on 3/1/16.
 */
public class NewPlaylistActivity extends AppCompatActivity {

    private TextView title;
    private SharedPreferences histPref;
    private String historyPref = "hist_pref";




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playlist);
        title = (TextView) findViewById(R.id.new_playlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Playlist");
        toolbar.setTitleTextColor(Color.WHITE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaylistFragment playlistFragmentMaker = new PlaylistFragment();
        fragmentTransaction.replace(R.id.fragment_container, playlistFragmentMaker);
        fragmentTransaction.commit();

        //update the history view sharedPreference
        histPref = getSharedPreferences(historyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = histPref.edit();

        String content = "Create a new playlist for training";
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String localTime = date.format(currentLocalTime);
        editor.putString(content, localTime);
    }
}
