package sapphire.seemetrain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * File Created by Joseph
 */
public class CreateAccount extends Activity {

    private Button create_button;
    private EditText nameField;
    private EditText passField;
    private EditText confirmField;

    private static SharedPreferences sharedPrefs;
    private String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        create_button = (Button) findViewById(R.id.create_button);
        nameField = (EditText) findViewById(R.id.nameText);
        passField = (EditText) findViewById(R.id.passText);
        confirmField = (EditText) findViewById(R.id.confirmpass);
        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void createAccount(View view){
        String name = nameField.getText().toString();
        String pass = passField.getText().toString();
        String confirm=confirmField.getText().toString();
        if(confirm.equals(pass)){
            //Write to shared prefs
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("nameKey", name);
            editor.putString("passKey", pass);
            editor.commit();
            SMTAccount myAccount = new SMTAccount(name, pass);
            myAccount.setLoggedIn(true);
            Intent intent = new Intent(this,HomeScreen.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(CreateAccount.this,"Passwords do not match",Toast.LENGTH_LONG).show();
        }
    }
}

