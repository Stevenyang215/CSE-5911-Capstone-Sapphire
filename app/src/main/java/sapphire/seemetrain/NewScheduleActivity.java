package sapphire.seemetrain;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;



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

    public void addVideo(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addVideoFragment add_Video_Fragment = new addVideoFragment();
        fragmentTransaction.replace(R.id.fragment_container, add_Video_Fragment);
        fragmentTransaction.commit();

    }

}
