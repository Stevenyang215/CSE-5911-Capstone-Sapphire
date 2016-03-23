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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ViewHistory extends AppCompatActivity {

    private String historyPref = "hist_pref";
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
        TextView lplayed = (TextView) findViewById(R.id.lastplayed);
        TextView st = (TextView) findViewById(R.id.starttime);
        TextView intval = (TextView) findViewById(R.id.interval);
        //TextView endtime = (TextView) findViewById(R.id.endtime);

        final SMTApplication global = (SMTApplication) getApplication();
        String hour= Integer.toString(global.getHour());
        String minuteS = Integer.toString(global.getMinute());
        String tottime=hour+"::"+minuteS;
        Uri path = global.getPath();
        intval.setText(Integer.toString(global.getInterval()));
        st.setText(tottime);
        lplayed.setText(path.toString());
        //intval.setText("15");
        //st.setText("2::30");
        //lplayed.setText("Sit");



        Map<String,?> history_record = histPref.getAll();

        Set keys = history_record.keySet();

        for(Iterator i = keys.iterator();i.hasNext();)
        {
            String key = (String) i.next();
            String value = (String) history_record.get(key);
            String content = key + " in " + value;

            TextView newView = new TextView(this);
            newView.setText(content);

            history_linear_layout.addView(newView);

        }



    }

}
