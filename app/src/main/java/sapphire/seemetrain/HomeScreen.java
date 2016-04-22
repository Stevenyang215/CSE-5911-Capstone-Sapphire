package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.content.Intent;

public class HomeScreen extends AppCompatActivity {

    private SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("SeeMeTrain");
        toolbar.setTitleTextColor(Color.WHITE);
        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(!sharedPrefs.contains("passKey")){
            Intent intent = new Intent(this,CreateAccount.class);
            startActivity(intent);
        }

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

    }

    public void About(View view){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
    public void record_video(View view){
        Intent intent = new Intent(this,VideoCaptureActivity.class);
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

    public void begin_alarm(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("class", "sapphire.seemetrain.AlarmStart");
        startActivity(intent);
    }

    public void view_history(View V){
        Intent intent = new Intent(this,ViewHistory.class);
        startActivity(intent);
    }


    /** Old activity used to play videos/for debugging: */
//    public void play_video(View view){
//        final SMTApplication global = (SMTApplication) getApplication();
//        Uri uri = global.getPath();
//        Intent intent = new Intent(this,PlayPicked.class);
//        intent.setData(uri);
//        startActivity(intent);
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
