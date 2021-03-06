package sapphire.seemetrain;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * File Created by Joseph
 */
public class LoginActivity extends Activity {

    private Button login_button;
    private EditText nameField;
    private EditText passField;
    private SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account_screen);

        login_button = (Button) findViewById(R.id.login_button);
        nameField = (EditText) findViewById(R.id.nameText);
        passField = (EditText) findViewById(R.id.passText);
        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void authAccount(View view) throws ClassNotFoundException {
        String pass = passField.getText().toString();

        //Validate
        if (AccountValidator.checkPass(pass, getApplicationContext())) {
            Toast.makeText(LoginActivity.this, "Password Correct", Toast.LENGTH_LONG).show();

            Bundle extras = getIntent().getExtras();
            String classname = extras.getString("class");
            Class<?> nextClass = Class.forName(classname);
            Intent intent = new Intent(this, nextClass);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
        }

    }
}
