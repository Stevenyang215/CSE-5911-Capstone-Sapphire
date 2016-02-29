package sapphire.seemetrain;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * File Created by Joseph
 */
public class PlayPicked extends Activity{
    private VideoView videoView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        videoView = (VideoView) findViewById(R.id.video_view);
        //Toast.makeText(PlayPicked.this, getIntent().getData().toString(), Toast.LENGTH_SHORT).show();

        MediaController controller = new MediaController(PlayPicked.this);
        videoView.setMediaController(controller);

        videoView.setVideoURI(getIntent().getData());
        videoView.requestFocus();
        videoView.start();
        //videoView.setVideoPath("/storage/emulated/0/DCIM/Camera/VID_20160229_040448.mp4");
    }
}
