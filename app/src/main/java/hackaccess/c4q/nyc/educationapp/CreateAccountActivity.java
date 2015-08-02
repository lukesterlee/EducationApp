package hackaccess.c4q.nyc.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hackaccess.c4q.nyc.educationapp.chat.ChatRoomActivity;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class CreateAccountActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButton;

    private FirebaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mEditTextEmail = (EditText) findViewById(R.id.editText_email);
        mEditTextPassword = (EditText) findViewById(R.id.editText_password);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        mHelper = FirebaseHelper.getInstance(getApplicationContext());

        mHelper.createAccount(mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());

//        if () {
//            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Email Already In Use", Toast.LENGTH_SHORT).show();
//
//        }


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
