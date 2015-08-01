package hackaccess.c4q.nyc.educationapp;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;

import hackaccess.c4q.nyc.educationapp.firebase.Like;
import hackaccess.c4q.nyc.educationapp.firebase.Account;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class FbHelper {


    public static Firebase saveAccount(Account account, Firebase ref){


        HashMap<String,String> accountHash = account.getAccountHash();
        Firebase accountRef = ref.child("users").push();
        accountRef.setValue(accountHash);

        return accountRef;

    }

    public void addLike(long officeId, long userId){

    }

    public ArrayList<Like> getOfficeLikes(long officeID){
        return null;
    }

    public ArrayList<Like> getUserLikes(Firebase account){
        return null;
    }

}
