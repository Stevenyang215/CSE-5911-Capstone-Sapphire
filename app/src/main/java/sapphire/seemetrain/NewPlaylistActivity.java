package sapphire.seemetrain;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * File Created by Joseph on 3/1/16.
 */
public class NewPlaylistActivity extends AppCompatActivity {

    private TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playlist);
        title = (TextView) findViewById(R.id.new_playlist);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaylistFragment playlistFragmentMaker = new PlaylistFragment();
        fragmentTransaction.replace(R.id.fragment_container, playlistFragmentMaker);
        fragmentTransaction.commit();


    }
}
