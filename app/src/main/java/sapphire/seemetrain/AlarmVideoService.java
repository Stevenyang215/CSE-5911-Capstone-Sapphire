package sapphire.seemetrain;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.SortedMap;

/**
 * File Created by Joseph
 */
public class AlarmVideoService extends IntentService {

    private SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    public AlarmVideoService(){
        super("AlarmVideoService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        Context context = getApplicationContext();

        final SMTApplication global = (SMTApplication) getApplication();

        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int current = 0;
        current = sharedPrefs.getInt("currentVid", 1);
        String path = sharedPrefs.getString("video" + current + "path", "def");
        int count = sharedPrefs.getInt("video" + current + "count", 0);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("video" + current + "count", count + 1);
        if (sharedPrefs.getInt("lastVid", 5) < current + 1) {
            editor.putInt("currentVid", 1);
        } else {
            editor.putInt("currentVid", current + 1);
        }
        editor.commit();

        ////Intent i = new Intent(getApplicationContext(), AlarmPlay.class);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(Uri.parse(path), "video/mp4");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        startActivity(i);

        AlarmVideoReceiver.completeWakefulIntent(intent);

    }
}
