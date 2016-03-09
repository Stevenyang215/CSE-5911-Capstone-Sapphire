package sapphire.seemetrain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * File Created by Joseph on 3/2/16.
 */
public class PlaylistFragment extends Fragment {

    private Button savePlaylist;
    private Button selectNextVideo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.playlist_fragment_layout, container, false);
        savePlaylist = (Button) view.findViewById(R.id.save_playlist);
        selectNextVideo = (Button) view.findViewById(R.id.next_video_select);

        savePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selectNextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
}

