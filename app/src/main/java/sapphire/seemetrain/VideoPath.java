package sapphire.seemetrain;

import android.net.Uri;

/**
 * File Created by Joseph
 *
 * Handles videos as an object
 */
public class VideoPath {

    private String command;
    private Uri localPath;

    public VideoPath(String name, Uri path) {
        command = name;
        localPath = path;
    }

    public String getCommand() {
        return command;
    }

    public Uri getLocalPath() {
        return localPath;
    }

}
