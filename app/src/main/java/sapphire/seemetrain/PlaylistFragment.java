package sapphire.seemetrain;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * File Created by Joseph on 3/2/16.
 */
public class PlaylistFragment extends Fragment {

    private Button savePlaylist;
    private Button addVideo;
    private LinearLayout mLayout;
    public static final int PICK_FROM_GALLERY = 1;
    private int videoNum = 0;
    Playlist playlist = new Playlist();
    private static SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.playlist_fragment_layout, container, false);
        savePlaylist = (Button) view.findViewById(R.id.save_playlist);
        addVideo = (Button) view.findViewById(R.id.next_video_select);

        sharedPrefs = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        addVideo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });

        savePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                mLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
                int count = mLayout.getChildCount();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                playlist.writeToPreferences(videoNum, view, editor, context);
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) return;

        if (requestCode == PICK_FROM_GALLERY) {

            //Add a new video to the UI list:
            videoNum++;
            Uri mVideoURI = data.getData();
            VideoPath nextVid = new VideoPath("abc" + videoNum, mVideoURI);
            View view = getView();
            playlist.addVideo(nextVid);
            mLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

            RelativeLayout nextVideoContainer = new RelativeLayout(this.getActivity());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            TextView textView = new TextView(this.getActivity());
            textView.setText("Video #" + videoNum);
            textView.setLayoutParams(params);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.RIGHT_OF, textView.getId());
            EditText editText = new EditText(this.getActivity());
            editText.setText("Command Name");
            editText.setTag("command" + videoNum);
            editText.setLayoutParams(params);

            nextVideoContainer.addView(textView);
            nextVideoContainer.addView(editText);

            mLayout.addView(nextVideoContainer);
        }

    }
}

