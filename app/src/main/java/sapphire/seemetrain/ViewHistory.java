package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import org.w3c.dom.Text;

import java.lang.String;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class ViewHistory extends AppCompatActivity {

    private String historyPref = "MyPrefs";
    private static SharedPreferences histPref;
    private LinearLayout history_linear_layout;
    private TextView title;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        histPref = getSharedPreferences(historyPref, Context.MODE_PRIVATE);
        history_linear_layout = (LinearLayout) findViewById(R.id.HistView);
        title = (TextView) findViewById(R.id.Title);
        clear = (Button) findViewById(R.id.clear_history);
        //TextView lplayed = (TextView) findViewById(R.id.lastplayed);
        TextView st = (TextView) findViewById(R.id.starttime);
        TextView intval = (TextView) findViewById(R.id.interval);
        //TextView endtime = (TextView) findViewById(R.id.endtime);

        final SMTApplication global = (SMTApplication) getApplication();
        int inthour=global.getHour();
        String ampm="AM";
        if(inthour>12){
            inthour=inthour-12;
            ampm="PM";
        }
        String hour= Integer.toString(inthour);
        String minuteS = Integer.toString(global.getMinute());
        String tottime=hour+":"+minuteS+" "+ampm;
        intval.setText(Integer.toString(global.getInterval()));
        st.setText(tottime);



        Map<String,?> history_record = histPref.getAll();

        Set keys = history_record.keySet();


       /* for(Iterator i = keys.iterator();i.hasNext();)
        {
            String key = (String) i.next();
            String value = (String) history_record.get(key);
            String content = key + " in " + value;
            TextView newView = new TextView(this);
            newView.setText(content);
            history_linear_layout.addView(newView);
        }*/
        if (history_record.containsKey("video1name")) {
            int lastvideo = (Integer) history_record.get("lastVid");
            String maxValVideo = "None";
            String contentForHistory;
            String Video_name = "video1name";
            int max_count = (Integer) history_record.get("video1count");
            String Video_count;
            for (int i = 1; i <= lastvideo; i++) {
                Video_name = "video" + i + "name";
                Video_count = "video" + i + "count";
                int vidcnt = 0;
                if (history_record.containsKey(Video_name)) {
                    Video_name = (String) history_record.get(Video_name);

                }
                if (history_record.containsKey(Video_count)) {
                    vidcnt = (Integer) history_record.get(Video_count);

                }
                if (vidcnt >= max_count) {
                    max_count = vidcnt;
                    maxValVideo = Video_name;
                }
            }
            contentForHistory = maxValVideo + " is played maximum number of times (" + max_count + " times)";
            TextView newView = new TextView(this);
            newView.setText(contentForHistory);
            history_linear_layout.addView(newView);
        } else {
            //lplayed.setText("No History Yet. Create a playlist to start");
            Toast.makeText(ViewHistory.this, "No History Yet.Create a playlist to start", Toast.LENGTH_LONG).show();
        }

    }

    public void clearHistory(View view){
        final Context context = this;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Are you sure?");
        alertDialogBuilder
                //.setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        TextView st = (TextView) findViewById(R.id.starttime);
                        TextView intval = (TextView) findViewById(R.id.interval);
                        intval.setText("0");
                        st.setText("0");
                        final SMTApplication global = (SMTApplication) getApplication();
                        global.alarmoff();
                        SharedPreferences pref = getSharedPreferences(historyPref,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        int i = 1;
                        String key = "video" + i + "count";
                        while(pref.contains(key)){
                            editor.putInt(key,0);
                            i++;
                            key = "video" + i + "count";
                        }
                        editor.commit();
                        ViewHistory.this.recreate();
                        //ViewHistory.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}