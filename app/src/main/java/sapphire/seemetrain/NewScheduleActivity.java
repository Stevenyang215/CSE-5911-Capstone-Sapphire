package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class NewScheduleActivity extends AppCompatActivity {

    private TextView title;
    private SharedPreferences histPref;
    private String historyPref = "hist_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);
        title = (TextView) findViewById(R.id.new_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Schedule");
        toolbar.setTitleTextColor(Color.WHITE);
        //add = (Button) findViewById(R.id.add);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewScheduleFragment newScheduleFrament = new NewScheduleFragment();
        fragmentTransaction.replace(R.id.fragment_container, newScheduleFrament);
        fragmentTransaction.commit();


        //update the history view sharedPreference
        histPref = getSharedPreferences(historyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = histPref.edit();

        String content = "Create a new schdule for training";
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String localTime = date.format(currentLocalTime);
        editor.putString(content,localTime);
        final SMTApplication global = (SMTApplication) getApplication();
        global.setAlarm();
        boolean alarm=global.getAlarmStat();
        //Toast.makeText(NewScheduleActivity.this, "Alarm"+alarm, Toast.LENGTH_LONG).show();

    }

    private void saveSchedule2(View view) {

        TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        TextView seekBarOut = (TextView) view.findViewById(R.id.seekBarOut);

        int time_hour = timePicker.getCurrentHour();
        int time_minute = timePicker.getCurrentMinute();
        String time = Integer.toString(time_hour);
        time = time + Integer.toString(time_minute);



        final SMTApplication global = (SMTApplication) getApplication();
        global.setHour(time_hour);
        global.setMinute(time_minute);
        global.setCount(0);

    }


}
