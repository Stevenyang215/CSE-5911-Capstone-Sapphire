package sapphire.seemetrain;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.provider.MediaStore;
import android.net.Uri;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class VideoCaptureActivity extends AppCompatActivity {

    public static final int REQUEST_VIDEO_CAPTURE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

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

                File mediaFile = getOutputMediaFile(2);

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
                Toast.makeText(this, "Video Saved", Toast.LENGTH_LONG).show();
                //Uri uri = data.getData();
                //galleryAddPic(uri);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /** Create a File for saving a video */
    private static File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("SeeMeTrain", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_VIDEO){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "SMT"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    /** Add a video to device "Gallery" */
    //Doesn't work for all API for some reason, so this is unused for now
    private void galleryAddPic(Uri mCurrentPhotoPath) {
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, mCurrentPhotoPath));
    }



}





