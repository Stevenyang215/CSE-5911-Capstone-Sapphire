package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * File Created by Joseph
 */
public class Playlist {



    private String name = "New Playlist";
    private ArrayList<VideoPath> playbackQueue = new ArrayList<>();
    private Integer index = 0;

    public String getName() {
        return name;
    }

    public Playlist(){

    }

    public Playlist(String playlistName, VideoPath video) {
        name = playlistName;
        playbackQueue.add(0, video);
    }

    public VideoPath getNextVideo(){
        VideoPath video = playbackQueue.get(index);
        if (!(index == playbackQueue.size() - 1)){
            index++;
        } else {
            index = 0;
        }
        return video;
    }

    public void addVideo(VideoPath video) {
        playbackQueue.add(video);
    }

    public void writeToPreferences(int numberToWrite, View view, SharedPreferences.Editor editor, Context context) {

        editor.putInt("currentVid", 1);
        editor.putInt("lastVid", numberToWrite);

        for (int i=1; i<(numberToWrite + 1); i++){
            //Name
            EditText nextName = (EditText) view.findViewWithTag("command" + i);
            String key = "video" + i + "name";
            String name = nextName.getText().toString();
            editor.putString(key, name);

            //Path
            key = "video" + i + "path";
            VideoPath video = getNextVideo();
            String path = video.getLocalPath().toString();
            editor.putString(key, path);

            //Count
            key = "video" + i + "count";
            editor.putInt(key, 0);
        }
        editor.commit();
    }
}

