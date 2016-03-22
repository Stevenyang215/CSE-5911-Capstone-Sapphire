package sapphire.seemetrain;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * File Created by Joseph
 */
public class PlayPicked extends Activity {
    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private Button button;
    public static final int PICK_FROM_GALLERY = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        button = (Button) findViewById(R.id.button);
        videoView = (VideoView) findViewById(R.id.video_view);
        //Toast.makeText(PlayPicked.this, getIntent().getData().toString(), Toast.LENGTH_SHORT).show();


        //videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");
        //videoView.start();
        //videoView.setVideoURI(getIntent().getData());
        //videoView.requestFocus();
        //videoView.start();


        //videoView.setVideoPath("/storage/emulated/0/DCIM/Camera/VID_20160229_040448.mp4");
        //videoView.start();
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == PICK_FROM_GALLERY) {
            Uri mVideoURI = data.getData();
            videoView.setVideoPath(mVideoURI.toString());
            videoView.start();
            TextView text = (TextView) findViewById(R.id.outText);
            text.setText(mVideoURI.toString());
        }

    }
}