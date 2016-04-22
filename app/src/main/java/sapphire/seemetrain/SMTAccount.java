package sapphire.seemetrain;

/**
 *
 * Account singleton
 */
public class SMTAccount {

    private static String accName = "Trainer";
    private static String accPass;
    private static boolean loggedIn = false;

    public SMTAccount(String name, String pass) {
        accName = name;
        accPass = pass;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public boolean authAccount(String pass){
        if (accPass.equals(pass)){
            return true;
        } else {
            return false;
        }
    }



}
