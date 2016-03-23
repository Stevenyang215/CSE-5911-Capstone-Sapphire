package sapphire.seemetrain;

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
}
