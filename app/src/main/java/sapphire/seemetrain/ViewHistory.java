package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        histPref = getSharedPreferences(historyPref, Context.MODE_PRIVATE);
        history_linear_layout = (LinearLayout) findViewById(R.id.HistView);
        title = (TextView) findViewById(R.id.Title);

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
        int lastvideo= (Integer) history_record.get("lastVid");
        String maxValVideo="None";
        String contentForHistory;
        String Video_name="video1name";
        int max_count=(Integer)history_record.get("video1count");
        String Video_count;
        for(int i=1;i<=lastvideo;i++){
            Video_name="video"+i+"name";
            Video_count="video"+i+"count";
            int vidcnt=0;
            if(history_record.containsKey(Video_name)){
                Video_name = (String) history_record.get(Video_name);

            }
            if(history_record.containsKey(Video_count)){
                vidcnt= (Integer) history_record.get(Video_count);

            }
            if(vidcnt>=max_count){
                max_count=vidcnt;
                maxValVideo = Video_name;
            }
        }
        contentForHistory= maxValVideo+ " is played maximum number of times ("+max_count+" times)";
        TextView newView = new TextView(this);
        newView.setText(contentForHistory);
        history_linear_layout.addView(newView);


    }

}
