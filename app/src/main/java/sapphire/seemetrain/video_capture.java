package sapphire.seemetrain;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.provider.MediaStore;
import android.net.Uri;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.Calendar;


public class video_capture extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private Button record_button;
    private Button play_button;
    //TODO Add stop/pause buttons to layout
    private Button stop_button;
    private Button pause_button;

    private Button save_button;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_capture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        record_button = (Button) findViewById(R.id.record_button);
        play_button = (Button) findViewById(R.id.play_button);
        //stop_button = (Button) findViewById(R.id.stop_button);
        //pause_button = (Button) findViewById(R.id.pause_button);

        save_button = (Button) findViewById(R.id.save_button);
        videoView = (VideoView) findViewById(R.id.videoView);

        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create a unique file name
                String path_name = generateVideoName();
                File mediaFile = new File(getFilesDir() + path_name);

                Intent captureVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                captureVideo.putExtra(MediaStore.EXTRA_DURATION_LIMIT,60);

                //Save Video to unique file path
                Uri videoUri = Uri.fromFile(mediaFile);
                captureVideo.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);

                if(captureVideo.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(captureVideo,REQUEST_VIDEO_CAPTURE);
                }
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

//        stop_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                videoView.stopPlayback();
//            }
//        });
//
//        pause_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Check canPause() ?
//                videoView.pause();
//            }
//        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private String generateVideoName(){

        //Use current time as a unique file name
        Calendar right_now = Calendar.getInstance();
        String vid_name = right_now.toString();
        vid_name = "/" + vid_name + "_SeeMeTrain";

//        Toast.makeText(this, "Video named " + vid_name,
//                Toast.LENGTH_LONG).show();

        return vid_name;
    }



}





