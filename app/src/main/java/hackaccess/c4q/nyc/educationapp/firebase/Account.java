package hackaccess.c4q.nyc.educationapp.firebase;

import java.util.HashMap;

public class Account {

    HashMap<String, String> account;

    public Account(String email, String password) {
        account = new HashMap<>();
        account.put("email", email);
        account.put("password", password);
    }

    public String getEmail() {

        return account.get("email");
    }



    public void setPassword(String password) {
        account.put("password", password);
    }

    public String getPassword(){
        return account.get("password");
    }

    public boolean checkPassword(String password){
        return account.get("password").equals(password);
    }

    public HashMap<String, String> getAccountHash(){
        return account;
    }


}