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

        //Toast.makeText(AlarmVideoService.this, pathName, Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), AlarmPlay.class);
        //i.setClass(this, AlarmPlay.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setData(intent.getData());
        //i.setDataAndType(Uri.parse(pathName), "video/mp4");


        startActivity(i);


//        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String path2 = sharedPrefs.getString("video1path", "def");
//        Intent actIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(path2));
//        actIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        actIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivity(actIntent);

        AlarmVideoReceiver.completeWakefulIntent(intent);

//        final SMTApplication global = (SMTApplication) getApplication();
//        if (global.getCount() < 1) {
//            String pathName = global.getPathName();
//            setContentView(R.layout.content_alarm_play);
//            videoView.setVideoPath(pathName);
//        } else {
//            String pathName = global.getPathName();
//            setContentView(R.layout.content_alarm_play);
//            videoView.setVideoPath(pathName);
//
//            //Context context = this.getApplicationContext();
//            AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//            PendingIntent alarmIntent;
//            Intent intent = new Intent(context, AlarmVideoService.class);
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//            alarmMgr.cancel(alarmIntent);
//
//        }

    }
}
