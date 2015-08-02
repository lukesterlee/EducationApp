package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hackaccess.c4q.nyc.educationapp.firebase.Like;
import hackaccess.c4q.nyc.educationapp.firebase.Account;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class FirebaseHelper extends Firebase {

    private static Context sContext;
    private static final String URL = "https://edusearch.firebaseio.com/";
    private static FirebaseHelper INSTANCE;

    public FirebaseHelper() {
        super(URL);
    }

    public static FirebaseHelper getInstance(Context context) {
        sContext = context;
        if (INSTANCE == null) {
            Firebase.setAndroidContext(context);
            INSTANCE = new FirebaseHelper();
        }
        return INSTANCE;
    }


    public void createAccount(String emailAddress, String password) {
        INSTANCE.createUser(emailAddress, password, new ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {

                Toast.makeText(sContext, "Success!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(sContext, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createUser(String email, String password){

        Account account = new Account(email, password);

        INSTANCE.child("users").push().setValue(account.getAccountHash());

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
