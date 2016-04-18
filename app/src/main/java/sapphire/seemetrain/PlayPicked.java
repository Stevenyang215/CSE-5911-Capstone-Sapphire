package sapphire.seemetrain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
    private SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        button = (Button) findViewById(R.id.button);
        videoView = (VideoView) findViewById(R.id.video_view);
        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

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
            String path = mVideoURI.toString();
            videoView.setVideoPath(path);
            videoView.start();
            TextView text = (TextView) findViewById(R.id.outText);
            text.setText(mVideoURI.toString());

            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("video1path", path);
            editor.commit();

        }

    }
}