package sapphire.seemetrain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * File Created by Joseph on 4/22/16.
 *
 * Use this class for any security/account validation concerns
 * Not much for now
 *
 */
public class AccountValidator {

    private static SharedPreferences sharedPrefs;
    private static String MyPREFERENCES = "MyPrefs";

    public AccountValidator() {

    }

    public static int initializeAccount(String name, String pass, String confirm, Context c){
        if(name.equals("") || pass.equals("")){
            return 3; //requires nonempty fields
        }
        else if(pass.equals(confirm)){


            sharedPrefs = c.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("nameKey", name);
            editor.putString("passKey", pass);
            editor.commit();

            return 1; //success
        }
        else{
            return 2; //pass mismatch
        }
    }

    public static boolean checkPass(String pass, Context c){
        sharedPrefs = c.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return (pass.equals(sharedPrefs.getString("passKey", "admin")));
    }


}
