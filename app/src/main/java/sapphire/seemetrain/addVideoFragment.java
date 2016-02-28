package sapphire.seemetrain;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by zijiangyang on 2/15/16.
 */
public class addVideoFragment extends Fragment {

        private Button saveTime;
        private TimePicker timePicker;
        Calendar calendar;
        String time;

        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){

            final View view = inflater.inflate(R.layout.add_video_fragment_layout, container, false);
            saveTime = (Button) view.findViewById(R.id.save_time);
//            Toast.makeText(context, "Saved Time: \n" +
//                    time, Toast.LENGTH_LONG).show();


            saveTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                int time_hour = timePicker.getHour();
                int time_minute = timePicker.getMinute();

                time = Integer.toString(time_hour);
                time = time + Integer.toString(time_minute);

                Context context = getContext();
                Toast.makeText(context, "Saved Time: \n" +
                        time, Toast.LENGTH_LONG).show();


            }
        });

                return view;
        }



        public void time_save(View view) {
                timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                int time_hour = timePicker.getHour();
                int time_minute = timePicker.getMinute();

                calendar = Calendar.getInstance();
                int current_hour = calendar.get(Calendar.HOUR_OF_DAY);
                int current_minute = calendar.get(Calendar.MINUTE);

        }


}

