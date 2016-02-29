package sapphire.seemetrain;

import android.app.Application;

/**
 * File Created by Joseph
 */
public class SMTApplication extends Application {

    private String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String s) {
        pathName = s;
    }
}