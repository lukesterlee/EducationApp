package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import hackaccess.c4q.nyc.educationapp.firebase.Like;
import hackaccess.c4q.nyc.educationapp.firebase.UserInfo;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class FirebaseHelper extends Firebase {

    private static Context sContext;
    private static final String URL = "https://edusearch.firebaseio.com/";
    private static FirebaseHelper INSTANCE;
    private static String userID;
    private static UserInfo userInfo;
    private static ArrayList<Like> userLikes;


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


    public void createAccount(final String emailAddress, final String password, final UserInfo userInfo) {


        INSTANCE.createUser(emailAddress, password, new ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                Toast.makeText(sContext, "Account Created", Toast.LENGTH_SHORT).show();

                Firebase userRef = INSTANCE.child("users").child(stringObjectMap.get("uid").toString());
                userRef.setValue(userInfo);

                logInUser(emailAddress, password);

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(sContext, "Email Already in Use", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logInUser(String emailAddress, String password){

        INSTANCE.authWithPassword(emailAddress, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                userID = authData.getUid();
                getUserInfo();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(sContext, "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logOutUser(){
        userID = null;
        userInfo = null;
    }

    public void changeUserEmail(String oldEmailAddress, String newEmailAddress, String password){

        INSTANCE.changeEmail(oldEmailAddress, newEmailAddress, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(sContext,"Email Successfully Changed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(sContext,"Email Change Unsuccessful", Toast.LENGTH_SHORT).show();            }
        });
    }

    public UserInfo getUserInfo(){
        Query userRef = INSTANCE.child("users").equalTo(userID);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInfo = (UserInfo) dataSnapshot.getValue();
                Log.d("UserInfo Retrieved", userInfo.getName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return userInfo;
    }


    public void addLike(String programId, String zipcode){

        if(userID != null){

        }


    }

    public ArrayList<Like> getOfficeLikes(String programID, String zipcode){
        return null;
    }

    public ArrayList<Like> getUserLikes(){
        if(userID != null) {
            Query likesRef = INSTANCE.child("userLikes").equalTo(userID);
            likesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userLikes = (ArrayList<Like>) dataSnapshot.getValue();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            return userLikes;
        }else{
            return null;
        }

    }

}
