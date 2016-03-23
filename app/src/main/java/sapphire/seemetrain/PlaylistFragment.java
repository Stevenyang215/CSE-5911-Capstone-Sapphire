package sapphire.seemetrain;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * File Created by Joseph on 3/2/16.
 */
public class PlaylistFragment extends Fragment {

    private Button savePlaylist;
    private Button addVideo;
    private LinearLayout mLayout;
    public static final int PICK_FROM_GALLERY = 1;
    Playlist playlist = new Playlist();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.playlist_fragment_layout, container, false);
        savePlaylist = (Button) view.findViewById(R.id.save_playlist);
        addVideo = (Button) view.findViewById(R.id.next_video_select);

        savePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addVideo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) return;

        if (requestCode == PICK_FROM_GALLERY) {

            //getCommandDialog();

            Uri mVideoURI = data.getData();
            VideoPath nextVid = new VideoPath("command1", mVideoURI);
            View view = getView();
            playlist.addVideo(nextVid);
            mLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
            TextView textView = new TextView(this.getActivity());
            textView.setText("abc");
            mLayout.addView(textView);
        }

    }

//    public void getCommandDialog() {
//        DialogFragment newFragment = new CommandNameDialog();
//        FragmentManager frgmgr = getSupportFragmentManager();
//        newFragment.show(getChildFragmentManager(), "command");
//        newFragment.show(
//    }

}

