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
import android.widget.VideoView;


public class video_capture extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private Button record_button;
    private Button play_button;
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
        videoView = (VideoView) findViewById(R.id.videoView);

        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureVideo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Uri video = data.getData();
            videoView.setVideoURI(video);
        }
    }



}





