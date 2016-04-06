package sapphire.seemetrain;

import android.content.Context;
import android.provider.MediaStore;
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
public class addVideoFragment extends Fragment {

    private static final int SELECT_VIDEO = 1;

    private Button saveTime;
    private Button selectVideo;
    private TimePicker timePicker;
    private SeekBar seekBar;
    private TextView seekBarOut;
    Calendar calendar;
    static String time;
    private TextView txtView;
    public static int interval;

    private String selectedVideoPath;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){

        final View view = inflater.inflate(R.layout.add_video_fragment_layout, container, false);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBarOut = (TextView) view.findViewById(R.id.seekBarOut);

//        saveTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveSchedule(v);
//            }
//        });

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

        return view;
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){

        Context context = getContext();
        final SMTApplication global = (SMTApplication) getActivity().getApplication();
        if(resultCode == Activity.RESULT_OK) {
            //Toast.makeText(context, "2", Toast.LENGTH_LONG).show();
            if (requestCode == SELECT_VIDEO) {
                //Toast.makeText(context, "3", Toast.LENGTH_LONG).show();
                global.setPath(data.getData());
                selectedVideoPath = getPath(data.getData());

                try {
                        if (selectedVideoPath == null) {
                                Log.e("addVideoAvtivity", "selected video path = null!");
                                getActivity().finish();

                            Toast.makeText(context, "Null", Toast.LENGTH_LONG).show();

                        } else {
                                Toast.makeText(context, selectedVideoPath, Toast.LENGTH_LONG).show();
                                txtView.setText(selectedVideoPath);
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
        int time_minute = timePicker.getCurrentMinute(); //TODO fix single digit minute error
        time = Integer.toString(time_hour);
        time = time + Integer.toString(time_minute);



        final SMTApplication global = (SMTApplication) getActivity().getApplication();
//        global.setPath(data.getData);
        global.setInterval(interval);
        global.setHour(time_hour);
        global.setMinute(time_minute);
        global.setCount(0);

//        Context context = getContext();
//        Toast.makeText(context, "Interval \n" + interval, Toast.LENGTH_LONG).show();


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

    public static int getInterval(){
        return interval;
    }

    public void save(View v){
        saveSchedule(v);
    }
}

