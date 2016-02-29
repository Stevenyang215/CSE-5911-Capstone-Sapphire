package sapphire.seemetrain;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;

//TODO Need unit test cases

public class HomeScreen extends Activity {

    private Button record_button;
    private Button new_schedule;
    private Button play_button;
    //TODO Create standard button format/look

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        record_button = (Button) findViewById(R.id.record_button);
        new_schedule = (Button) findViewById(R.id.new_schedule);



        
    }

    public void record_video(View view){
        Intent intent = new Intent(this,video_capture.class);
        startActivity(intent);
    }

    public void new_schedule_set_up(View view){
        Intent intent = new Intent(this,NewScheduleActivity.class);
        startActivity(intent);
    }

    public void play_video(View view){
        //Do nothing for now
        //Intent intent = new Intent(this,video_repository.class); //TODO add video repo layout
        //startActivity(intent);
    }

    public void begin_alarm(View v){
        Intent intent = new Intent(this, AlarmStart.class);
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
