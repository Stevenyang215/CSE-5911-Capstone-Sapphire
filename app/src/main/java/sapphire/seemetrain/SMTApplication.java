package sapphire.seemetrain;

import android.app.Application;
import android.net.Uri;

/**
 * File Created by Joseph
 *
 * Singleton class used to save some app-wide information
 */
public class SMTApplication extends Application {

    private Uri path;
    private int interval;
    private int hour;
    private int minute;
    private int playcount = 0;
    private boolean alarmset=false;

    public Uri getPath() {
        return path;
    }

    public void setPath(Uri uri) {
        path = uri;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int i) {
        interval = i;
    }

    public boolean getAlarmStat(){ return alarmset; }

    public void setAlarm() {    alarmset = true;  }

    public void alarmoff(){ alarmset=false;}

    public int getHour() {
        return hour;
    }

    public void setHour(int h) {
        hour = h;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int m) {
        minute = m;
    }

    public int getCount() { return playcount; }

    public void setCount(int c) { playcount = c; }
}