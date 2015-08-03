package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hackaccess.c4q.nyc.educationapp.firebase.Like;
import hackaccess.c4q.nyc.educationapp.firebase.UserInfo;
import hackaccess.c4q.nyc.educationapp.profile.BackCallback;

public class FirebaseHelper extends Firebase {

    private static Context sContext;
    private static final String URL = "https://edusearch.firebaseio.com/";
    private static FirebaseHelper INSTANCE;
    private static String userID = null;
    private static UserInfo userInfo;
    //private static ArrayList<HashMap<String, String>> userLikes = new ArrayList<>();

    private static ArrayList<Program> mUserFavorites = new ArrayList<>();

    private BackCallback mCallback;



    public String getUserID() {
        return userID;
    }

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

    public void setCallback(BackCallback mCallback) {
        this.mCallback = mCallback;
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

                if (mCallback != null) {
                    mCallback.goback();
                }

            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(sContext, "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isLoggedIn() {
        return (userID != null);
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
        if(isLoggedIn()) {
            userInfo = new UserInfo();
            Firebase userRef = INSTANCE.child(Constants.FIREBASE_KEY_USERS).child(userID).child(Constants.FIREBASE_KEY_FIRST_NAME);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userInfo.setFirstName((String) dataSnapshot.getValue());

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//            Firebase userRef2 = INSTANCE.child(Constants.FIREBASE_KEY_USERS).child(userID).child(Constants.FIREBASE_KEY_LAST_NAME);
//            userRef2.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    userInfo.setLastName((String) dataSnapshot.getValue());
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });

        }

        return userInfo;
    }


//    public boolean addFavorite(String programId, String zipcode){
//
//        if(userID != null){
//
//            Like like = new Like(programId, zipcode);
//
//            updateUserFavorites();
//
//            userLikes.add(like.getHashMap());
//            Firebase userRef = INSTANCE.child(Constants.FIREBASE_KEY_USERS).child(userID).child(Constants.FIREBASE_KEY_FAVORITES);
//            userRef.setValue(userLikes);
//
//
//
//            Toast.makeText(sContext, "Added!", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return false;
//    }

    public boolean addFavorite(Program program){

        if(userID != null){

            updateUserFavorites();

            mUserFavorites.add(program);

            Firebase userRef = INSTANCE.child(Constants.FIREBASE_KEY_USERS).child(userID).child(Constants.FIREBASE_KEY_FAVORITES);
            userRef.setValue(mUserFavorites);



            Toast.makeText(sContext, "Added!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }





    public ArrayList<Like> getOfficeLikes(String programID, String zipcode){
        return null;
    }



    public boolean updateUserFavorites() {


        if (userID != null) {
            Firebase firebase = new Firebase(Constants.FIREBASE_URL);
            firebase.child(Constants.FIREBASE_KEY_USERS).child(userID).child(Constants.FIREBASE_KEY_FAVORITES).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        mUserFavorites = new ArrayList<Program>();
                        Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                        for (DataSnapshot dataSnapshot1 : iterable) {
                            Program program = dataSnapshot1.getValue(Program.class);
                            mUserFavorites.add(program);
                        }
                        //Toast.makeText(sContext, "Updated!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }





//        
//        if(userID != null) {
//            Query likesRef = INSTANCE.child("users").equalTo(userID).equalTo("likes");
//            likesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    userLikes = (ArrayList<HashMap<String, String>>) dataSnapshot.getValue();
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });
//
//            return true;
//        }

        return false;

    }
    public List<Program> getUserFavorites() {
        return mUserFavorites;
    }
}
