package sapphire.seemetrain;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.VideoView;

import java.util.Calendar;
import java.util.SortedMap;

/**
 * File Created by Joseph
 */
public class AlarmVideo extends Activity {

    private VideoView videoView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (){
//            //TODO if current time is past the specified time, cancel alarm
//        }

        String videoPath =  ((SMTApplication) this.getApplication()).getPathName();
        setContentView(R.layout.content_alarm_play);
        videoView.setVideoPath(videoPath);
    }
}
