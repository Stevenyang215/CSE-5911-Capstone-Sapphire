package sapphire.seemetrain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * File Created by Joseph on 3/9/16.
 */
public class TestCreateAccount extends android.test.ActivityUnitTestCase<CreateAccount> {

    private CreateAccount activity;
    private String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPrefs;
    Editor ed;

    public TestCreateAccount() {
        super(CreateAccount.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                CreateAccount.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        clearSharedPrefs();
    }

    /**
     * Clears everything in the SharedPreferences
     */
    private void clearSharedPrefs() {
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().
                getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @SmallTest
    public void testLayoutExists() {
        // Verifies the button and text field exist
        assertNotNull(activity.findViewById(R.id.create_button));
        // Verifies the text of the button
        Button view = (Button) activity.findViewById(R.id.create_button);
        assertEquals("Incorrect label of the button", "Create Account", view.getText());
    }

    @SmallTest
    public void testIntentTriggerViaOnClick() {
        String nameValue = "Testing Name";
        String passValue = "TestPass";
        // Set a value into the text field
        EditText etName = (EditText) activity.findViewById(R.id.nameText);
        EditText etPass = (EditText) activity.findViewById(R.id.passText);
        etName.setText(nameValue);
        etPass.setText(passValue);
        // Verify button exists on screen
        Button btnLaunch = (Button) activity.findViewById(R.id.create_button);
        assertNotNull("Button should not be null", btnLaunch);
        // Trigger a click on the button
        btnLaunch.performClick();
        // Verify the intent was started with correct result extra
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);
//        String data = triggeredIntent.getExtras().getString("result");
//        assertEquals("Incorrect result data passed via the intent",
//                "Testing Name", data);
    }

}