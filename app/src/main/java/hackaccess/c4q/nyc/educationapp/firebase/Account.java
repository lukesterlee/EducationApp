package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class Account {

    HashMap<String, String> account;

    public Account(String email, String password) {
        account.put("email", email);
        account.put("password", password);
    }

    public String getEmail() {

        return account.get("email");
    }

    public void setPassword(String password) {
        account.put("password", password);
    }

    public boolean checkPassword(String password){
        return account.get("password").equals(password);
    }

    public HashMap<String, String> getAccountHash(){
        return account;
    }


}
