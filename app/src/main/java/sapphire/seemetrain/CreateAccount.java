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

        create_button = (Button) findViewById(R.id.create_button);
        nameField = (EditText) findViewById(R.id.nameText);
        passField = (EditText) findViewById(R.id.passText);
        confirmField = (EditText) findViewById(R.id.confirmpass);
        sharedPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void createAccount(View view){
        String name = nameField.getText().toString();
        String pass = passField.getText().toString();
        String confirm = confirmField.getText().toString();
        int result = AccountValidator.initializeAccount(name,pass,confirm,getApplicationContext());

        switch (result) {
            case 1: //Success -> go to home screen
                SMTAccount myAccount = new SMTAccount(name, pass);
                myAccount.setLoggedIn(true);
                Intent intent = new Intent(this, HomeScreen.class);
                startActivity(intent);
                break;
            case 2:
                Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(CreateAccount.this, "Account and Password are required fields", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(CreateAccount.this, "ERROR", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}

