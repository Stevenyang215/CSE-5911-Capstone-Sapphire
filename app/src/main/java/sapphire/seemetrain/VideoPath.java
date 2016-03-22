package sapphire.seemetrain;

import android.net.Uri;

/**
 * File Created by Joseph
 */
public class VideoPath {

    private String command;
    private Uri localPath;
    private String accPass;

    public VideoPath(String name, Uri path) {
        command = name;
        localPath = path;
    }

}
