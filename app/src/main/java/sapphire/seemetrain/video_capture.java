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
                Snackbar.make(view, R.string.recordInstructions, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        record_button = (Button) findViewById(R.id.record_button);

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

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:", Toast.LENGTH_LONG).show();
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





