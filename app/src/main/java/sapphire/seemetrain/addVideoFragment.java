package sapphire.seemetrain;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Button;
import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.database.Cursor;
import android.content.Context;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by zijiangyang on 2/15/16.
 */
public class addVideoFragment extends Fragment {

        private static final int SELECT_VIDEO = 1;

        private Button saveTime;
        private TimePicker timePicker;
        Calendar calendar;
        private TextView txtView;

        private String selectedVideoPath;



        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){

                View view = inflater.inflate(R.layout.add_video_fragment_layout, container, false);
                saveTime = (Button) view.findViewById(R.id.save_time);
                timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                txtView = (TextView)view.findViewById(R.id.video_path);

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

        public void selectVideo(){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,SELECT_VIDEO);
        }

        public void onActivityResult(int requestCode,int resultCode, Intent data){
                if(requestCode == Activity.RESULT_OK) {
                        if (requestCode == SELECT_VIDEO) {
                                selectedVideoPath = getPath(data.getData());
                                try {
                                        if (selectedVideoPath == null) {
                                                Log.e("addVideoAvtivity", "selected video path = null!");
                                                getActivity().finish();
                                        } else {
                                                txtView.setText(selectedVideoPath);
                                        }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e("Error ::", e.toString());
                                }
                        }
                }
                getActivity().finish();
        }

        public String getPath(Uri uri){
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

