package sapphire.seemetrain;

public class SMTAccount {

    private String accName = "Trainer";
    //private Schedule currentSchedule = new Schedule("empty");
    private String accPass;

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

    private boolean loggedIn = false;

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
