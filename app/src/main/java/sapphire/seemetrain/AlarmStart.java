package sapphire.seemetrain;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * File Created by Joseph
 */
public class AlarmStart extends Activity {

    AlarmManager alarmMgr;
    PendingIntent alarmIntent;
    String hourS;
    String minuteS;
    String interval;
    Uri path;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_start);

        final SMTApplication global = (SMTApplication) getApplication();
        path = global.getPath();
        hourS = Integer.toString(global.getHour());
        minuteS = Integer.toString(global.getMinute());
        interval = Integer.toString(global.getInterval());

        Context context = this.getApplicationContext();
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmVideoReceiver.class);
        intent.setData(path);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    }

    public void arming_alarm(View v) {

        final SMTApplication global = (SMTApplication) getApplication();
        // Set the alarm to start in 1 minute
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        //Toast.makeText(context, "Current Time" + currentHour + currentMinute, Toast.LENGTH_LONG).show();
        currentMinute++;

        calendar.set(Calendar.HOUR_OF_DAY, currentHour);
        calendar.set(Calendar.MINUTE, currentMinute);

// setRepeating() lets you specify a precise custom interval
        Context context = this.getApplicationContext();
        Toast.makeText(context, "Stop Time: " + hourS + ":" + minuteS + ".\nInterval: "
                + interval + "minutes.\n" + path.toString(), Toast.LENGTH_LONG).show();

        //global.getMinute()
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * global.getMinute(), alarmIntent);

    }

    public void cancel_alarm(View v){

        alarmMgr.cancel(alarmIntent);

        Context context = this.getApplicationContext();
        Toast.makeText(context, "Canceled Alarm", Toast.LENGTH_LONG).show();

    }

}
