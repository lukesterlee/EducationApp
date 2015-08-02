package hackaccess.c4q.nyc.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateProfileActivity extends Activity {

    private SharedPreferences preferences;

    Button faceBook;
    Button submit;
    EditText firstName, lastName, emailName, zipcode, password;
    //CallbackManager callbackManager;
    FirebaseHelper firebaseHelper;
    private final static String FACEBOOK_CALLBACK = "facebookError";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        setupFields();

    }

    public void setupFields() {

        faceBook = (Button) findViewById(R.id.facebookLogin);
        submit = (Button) findViewById(R.id.userSignUpButton);

        firstName = (EditText) findViewById(R.id.firstNameField);
        lastName = (EditText) findViewById(R.id.lastNameField);
        emailName = (EditText) findViewById(R.id.emailField);
        zipcode = (EditText) findViewById(R.id.zipcodeField);
        password = (EditText) findViewById(R.id.passwordField);
    }



    public void submitForm(View view) {

        if (firstName.length() == 0 || lastName.length() == 0 || emailName.length() == 0 || zipcode.length() != 5 || password.length() == 0) {

            Toast.makeText(this, " Fields are missing info", Toast.LENGTH_SHORT).show();

        } else {
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String email = emailName.getText().toString();
            String zip = zipcode.getText().toString();
            String pword = password.getText().toString();

            //TODO: Call database to create user account with this info
            firebaseHelper = FirebaseHelper.getInstance(getApplicationContext());
            firebaseHelper.createAccount(email,pword);
        }

    }

    public void useFacebook(View view){

        //TODO: Call firebase api with facebook. First two iterations were messing up.


    }



    // MENU RESOURCES
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_profile) {
//            if (isLoggedIn) {
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
//            } else {
//                Intent create = new Intent(this, CreateProfileActivity.class);
//                startActivity(create);
//            }
        }
        if (id == R.id.action_chat) {
            Intent chat = new Intent(this, ChatRoomActivity.class);
            startActivity(chat);
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}




