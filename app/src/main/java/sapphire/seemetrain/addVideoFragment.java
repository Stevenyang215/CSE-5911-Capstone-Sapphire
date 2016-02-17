package sapphire.seemetrain;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Button;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by zijiangyang on 2/15/16.
 */
public class addVideoFragment extends Fragment {

        private Button saveTime;
        private TimePicker timePicker;
        Calendar calendar;


        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){

                View view = inflater.inflate(R.layout.add_video_fragment_layout,container,false);
                saveTime = (Button) view.findViewById(R.id.save_time);
                timePicker = (TimePicker) view.findViewById(R.id.timePicker);


                return view;
        }


        public void time_save() {
                int time_hour = timePicker.getHour();
                int time_minute = timePicker.getMinute();

                calendar = Calendar.getInstance();
                int current_hour = calendar.get(Calendar.HOUR_OF_DAY);
                int current_minute = calendar.get(Calendar.MINUTE);

                if(time_hour==current_hour&&time_minute==current_minute){
                        //start the activity to play the video
                }

        }


}

