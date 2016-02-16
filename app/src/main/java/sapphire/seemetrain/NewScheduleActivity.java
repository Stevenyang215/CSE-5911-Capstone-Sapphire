package sapphire.seemetrain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import org.w3c.dom.Text;
import android.app.Fragment;

public class NewScheduleActivity extends AppCompatActivity {

    private TextView title;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);
        title = (TextView) findViewById(R.id.new_schedule);
        add = (Button) findViewById(R.id.add);
    }

    private void add_video(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addVideoFragment add_Video_Fragment = new addVideoFragment();
        fragmentTransaction.add(R.id.fragment_container,new Fragment());
        fragmentTransaction.commit();


    }

}
