package sapphire.seemetrain;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

/**
 * File Created by Joseph
 */
public class AlarmPlay extends Activity implements SurfaceHolder.Callback {

    private VideoView videoView;
    private SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";
    SurfaceHolder surfaceHolder;
    MediaPlayer mp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_alarm_play); //TODO Fix playback

        videoView = (VideoView) findViewById(R.id.play_alarm);

        final SMTApplication global = (SMTApplication) getApplication();
        Context context = getApplicationContext();

        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int current = 0;
        current = sharedPrefs.getInt("currentVid", 1);
        String path = sharedPrefs.getString("video" + current + "path", "def");
        int count = sharedPrefs.getInt("video" + current + "count", 0);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("video" + current + "count", count + 1);
        if (sharedPrefs.getInt("lastVid", 5) < current + 1){
            editor.putInt("currentVid", 1);
        } else {
            editor.putInt("currentVid", current + 1);
        }
        editor.commit();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(AlarmPlay.this, "Video #" + current + " counter incremented", Toast.LENGTH_LONG).show();

        surfaceHolder = videoView.getHolder();
        surfaceHolder.addCallback(this);

        finish();
        // SharedPreferences.Editor editor = sharedPrefs.edit();
        // editor.putString("video1path", path);
        // editor.commit();

//        videoView.setVideoPath(path2);
//        videoView.start();

        final Intent intent = new Intent(this, LoadActivity.class);
//        button.setOnClickListener(new View.OnClickListener() {

//            public void onClick(View v) {
//                sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                String path2 = sharedPrefs.getString("video1path", "def");
//                videoView.setVideoPath(path2);
//                videoView.start();

//                Intent intent = new Intent();
//
//                intent.setType("video/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);

//                startActivityForResult(intent, 1);
            }
//        });

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == 1) {
//            Uri mVideoURI = data.getData();
//            String path = mVideoURI.toString();
//            TextView text = (TextView) findViewById(R.id.outText);
//            String y = path + " from button";
//            text.setText(y);

            sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String path2 = sharedPrefs.getString("video1path", "def");

            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                Uri fileUri = ContentUris.withAppendedId(MediaStore.Video.Media.INTERNAL_CONTENT_URI, 166);
                mediaPlayer.setDataSource(getApplicationContext(), fileUri);
            } catch (IOException e) {
                Log.w("asgsdg", "adsgsdgvs");
                e.printStackTrace();
            }
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();

//            videoView.setVideoPath(path2);
            //startActivity(actIntent);

           // SharedPreferences.Editor editor = sharedPrefs.edit();
           // editor.putString("video1path", path);
           // editor.commit();
//            videoView.setVideoPath(path2);
//            videoView.start();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String path2 = sharedPrefs.getString("video1path", "def");
//        videoView.setVideoPath(path2);
//        videoView.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
        ////videoView.setVideoPath(path);

        //if (global.getCount() < 1) {
            //String pathName = global.getPathName();
            //setContentView(R.layout.content_alarm_play);

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
        ////videoView.start();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.setBackgroundColor(Color.TRANSPARENT);
//            }
//        });
//    }
//}
