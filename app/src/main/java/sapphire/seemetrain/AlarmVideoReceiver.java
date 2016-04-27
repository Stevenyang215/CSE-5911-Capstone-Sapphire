package sapphire.seemetrain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;

/**
 * File Created by Joseph
 */
public class AlarmVideoReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        final SMTApplication global = (SMTApplication) context.getApplicationContext();
        int endHour = global.getHour();
        int endMinute = global.getMinute();
        Calendar calendar = Calendar.getInstance();

        Calendar endtime = Calendar.getInstance();
        endtime.set(Calendar.HOUR_OF_DAY, endHour);
        endtime.set(Calendar.MINUTE, endMinute);

        //Check if the end time has passed
        if ((calendar.compareTo(endtime)) <= 0) {
            Intent service = new Intent(context, AlarmVideoService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            service.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            service.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);

            //Use this to invoke the video view as a service
            //startWakefulService(context, service);
        } else {
            //Cancel the alarm
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmMgr.cancel(alarmIntent);
        }
    }
}
