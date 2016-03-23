package sapphire.seemetrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * File Created by Joseph on 3/22/16.
 */
public class LoadActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 1);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
