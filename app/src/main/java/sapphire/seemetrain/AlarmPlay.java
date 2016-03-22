package sapphire.seemetrain;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

/**
 * File Created by Joseph
 */
public class AlarmPlay extends Activity {

    private VideoView videoView;
    private Button button;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_alarm_play); //TODO Fix playback

//        initializeVideo();
//
//
//    }
//
//    private void initializeVideo() {
        videoView = (VideoView) findViewById(R.id.play_alarm);

        final SMTApplication global = (SMTApplication) getApplication();
        Context context = getApplicationContext();

        //if (global.getCount() < 1) {
            //String pathName = global.getPathName();
            //setContentView(R.layout.content_alarm_play);
            videoView.setVideoURI(getIntent().getData());
        //} else {
            //String pathName = global.getPathName();
            //setContentView(R.layout.content_alarm_play);
            //videoView.setVideoURI(getIntent().getData());

            //Context context = this.getApplicationContext();
//            AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//            PendingIntent alarmIntent;
//            Intent intent = new Intent(context, AlarmVideoService.class);
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//            alarmMgr.cancel(alarmIntent);

//        }
        videoView.start();
        videoView.pause();
        videoView.resume();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }
}
