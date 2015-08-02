package hackaccess.c4q.nyc.educationapp.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import hackaccess.c4q.nyc.educationapp.FirebaseHelper;
import hackaccess.c4q.nyc.educationapp.R;
import hackaccess.c4q.nyc.educationapp.firebase.UserInfo;

public class SignupFragment extends Fragment{

    private SharedPreferences preferences;

    //Facebook
//    LoginButton faceBook;
//    CallbackManager callbackManager;
//    private AccessTokenTracker mFacebookAccessTokenTracker;

    //General
    Button submit;
    EditText firstName, lastName, emailName, zipcode, password;
    FirebaseHelper firebaseHelper;
    Firebase ref;
    Context applicationContext;
    
//    private ProgressDialog mAuthProgressDialog;
//    private AuthData mAuthData;
//    private Firebase.AuthStateListener mAuthStateListener;

    private final static String TAG = "facebookInfo";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setupFields(result);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(v);
            }
        });

        return result;
    }

    public void setupFields(View view) {

//        faceBook = (LoginButton) findViewById(R.id.facebookLogin);
        submit = (Button) view.findViewById(R.id.userSignUpButton);

        firstName = (EditText) view.findViewById(R.id.firstNameField);
        lastName = (EditText) view.findViewById(R.id.lastNameField);
        emailName = (EditText) view.findViewById(R.id.emailField);
        zipcode = (EditText) view.findViewById(R.id.zipcodeField);
        password = (EditText) view.findViewById(R.id.passwordField);

        applicationContext = getActivity().getApplicationContext();
        firebaseHelper = FirebaseHelper.getInstance(applicationContext);
        //ref = firebaseHelper.getRef();
    }

    public void submitForm(View view) {

        if (firstName.length() == 0 || lastName.length() == 0 || emailName.length() == 0 || zipcode.length() != 5 || password.length() == 0) {

            Toast.makeText(getActivity(), " Fields are missing info", Toast.LENGTH_SHORT).show();
            Log.d("onclick","inside of the submit button");
        } else {
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String email = emailName.getText().toString();
            String zip = zipcode.getText().toString();
            String pword = password.getText().toString();

            //Creates user account on Firebase

            UserInfo userInfo = new UserInfo(fName,lName,zip);
            firebaseHelper.createAccount(email, pword, userInfo);
            Log.d("onclick","inside of the submit button");
        }

    }

    //    public void setupFacebookLogin() {
//        callbackManager = CallbackManager.Factory.create();
//        mFacebookAccessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
//                onFacebookAccessTokenChange(currentAccessToken);
//            }
//
//        };
//
//    }


//    private void onFacebookAccessTokenChange(AccessToken token) {
//        if (token != null) {
//            mAuthProgressDialog.show();
//            ref.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
//        } else {
//            // Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout
//            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
//                ref.unauth();
//                setAuthenticatedUser(null);
//            }
//        }
//    }

//    private class AuthResultHandler implements Firebase.AuthResultHandler {
//
//        private final String provider;
//
//        public AuthResultHandler(String provider) {
//            this.provider = provider;
//        }
//
//        @Override
//        public void onAuthenticated(AuthData authData) {
//            mAuthProgressDialog.hide();
//            Log.i(TAG, provider + " auth successful");
//            setAuthenticatedUser(authData);
//        }
//
//        @Override
//        public void onAuthenticationError(FirebaseError firebaseError) {
//            mAuthProgressDialog.hide();
//            showErrorDialog(firebaseError.toString());
//        }
//    }
//
//    /**
//     * Once a user is logged in, take the mAuthData provided from Firebase and "use" it.
//     */
//    private void setAuthenticatedUser(AuthData authData) {
//        if (authData != null) {
//           String email = (String) authData.getProviderData().get("firstName");
//           Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
//        } else {
//
//        }
//        this.mAuthData = authData;
//        /* invalidate options menu to hide/show the logout button */
//        supportInvalidateOptionsMenu();
//    }
//
//    private void showErrorDialog(String message) {
//        new AlertDialog.Builder(this)
//                .setTitle("Error")
//                .setMessage(message)
//                .setPositiveButton(android.R.string.ok, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//    }


}
