package sapphire.seemetrain;

import android.content.Context;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.database.Cursor;
import android.content.Context;

import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by zijiangyang on 2/15/16.
 */
public class NewScheduleFragment extends Fragment {

    private static final int SELECT_VIDEO = 1;

    private TimePicker timePicker;
    private SeekBar seekBar;
    private TextView seekBarOut;
    static String time;
    public static int interval;

    private String selectedVideoPath;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){

        final View view = inflater.inflate(R.layout.add_video_fragment_layout, container, false);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBarOut = (TextView) view.findViewById(R.id.seekBarOut);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressToChange = 1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressToChange = progress + 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interval = progressToChange*15;
                String newInterval = Integer.toString(interval) + " mins";
                seekBarOut.setText(newInterval);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveSchedule(view);

                Snackbar.make(view, "Schedule Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){

        Context context = getContext();
        final SMTApplication global = (SMTApplication) getActivity().getApplication();
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_VIDEO) {
                global.setPath(data.getData());
                selectedVideoPath = getPath(data.getData());

                try {
                        if (selectedVideoPath == null) {
                                Log.e("addVideoAvtivity", "selected video path = null!");
                                getActivity().finish();

                            Toast.makeText(context, "Null", Toast.LENGTH_LONG).show();

                        } else {
                                Toast.makeText(context, selectedVideoPath, Toast.LENGTH_LONG).show();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("Error ::", e.toString());
                }
            }
        }
    }

    public void saveSchedule(View v) {

        int time_hour = timePicker.getCurrentHour();
        int time_minute = timePicker.getCurrentMinute();
        time = Integer.toString(time_hour);
        time = time + Integer.toString(time_minute);



        final SMTApplication global = (SMTApplication) getActivity().getApplication();
        global.setInterval(interval);
        global.setHour(time_hour);
        global.setMinute(time_minute);
        global.setCount(0);
    }

    private String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if(cursor!=null){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
        }
        else return null;
    }
}

