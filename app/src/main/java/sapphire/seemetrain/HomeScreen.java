package sapphire.seemetrain;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

//TODO Need unit test cases

public class HomeScreen extends Activity {

    private Button record_button;
    private Button new_schedule;
    private Button play_button;
    private Button view_history;
    private SharedPreferences sharedPrefs;
    private TextView textView;
    private String MyPREFERENCES = "MyPrefs";
    //TODO Create standard button format/look

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(!sharedPrefs.contains("passKey")){
            Intent intent = new Intent(this,CreateAccount.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        record_button = (Button) findViewById(R.id.record_button);
        new_schedule = (Button) findViewById(R.id.new_schedule);
        view_history = (Button) findViewById(R.id.history_view);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

    }

    public void About(View view){
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }
    public void record_video(View view){
        Intent intent = new Intent(this,video_capture.class);
        startActivity(intent);
    }

    public void new_schedule_set_up(View view){
        Intent intent = new Intent(this,NewScheduleActivity.class);
        startActivity(intent);
    }

    public void new_playlist_set_up(View view){
        Intent intent = new Intent(this,NewPlaylistActivity.class);
        startActivity(intent);
    }

    public void play_video(View view){
        final SMTApplication global = (SMTApplication) getApplication();
        Uri uri = global.getPath();
        Intent intent = new Intent(this,PlayPicked.class);
        intent.setData(uri);
        startActivity(intent);
    }

    public void begin_alarm(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("class", "sapphire.seemetrain.AlarmStart");
        startActivity(intent);
    }

    public void view_history(View V){
        Intent intent = new Intent(this,ViewHistory.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
