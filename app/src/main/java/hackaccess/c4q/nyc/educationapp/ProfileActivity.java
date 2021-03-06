package hackaccess.c4q.nyc.educationapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import hackaccess.c4q.nyc.educationapp.chat.ChatRoomActivity;
import hackaccess.c4q.nyc.educationapp.firebase.UserInfo;
import hackaccess.c4q.nyc.educationapp.profile.CreateProfileActivity;

public class ProfileActivity extends AppCompatActivity  {

    private ImageView profilePic, heart;
    private ListView mListView;
    private CardAdapter mAdapter;
    private TextView mTextViewName;
    private Button logout, changeEmail;
    private FirebaseHelper mHelper;
    private String mUserID;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mHelper = FirebaseHelper.getInstance(getApplicationContext());
        mUserID = mHelper.getUserID();
        mHelper.updateUserFavorites();

        profilePic = (ImageView) findViewById(R.id.profile_pic);
        heart = (ImageView) findViewById(R.id.heart);
        mTextViewName = (TextView) findViewById(R.id.name);



        Firebase firebase = mHelper.child(Constants.FIREBASE_KEY_USERS).child(mHelper.getUserID()).child(Constants.FIREBASE_KEY_FIRST_NAME);
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTextViewName.setText((String) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase = mHelper.child(Constants.FIREBASE_KEY_USERS).child(mHelper.getUserID()).child(Constants.FIREBASE_KEY_LAST_NAME);
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTextViewName.append(" " + (String) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //mUserInfo = mHelper.getUserInfo();

        //mTextViewName.setText(mUserInfo.getFirstName() + " " + mUserInfo.getLastName());

        changeEmail = (Button) findViewById(R.id.change_email);
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
                final EditText emailEditText = new EditText(ProfileActivity.this);
                final EditText passwordEditText = new EditText(ProfileActivity.this);
                dialogBuilder.setTitle(getResources().getString(R.string.change_email))
                        .setMessage(getResources().getString(R.string.enter_email))
                        .setView(emailEditText)
                        .setMessage(getResources().getString(R.string.enter_password))
                        .setView(passwordEditText)
                        .setPositiveButton(getResources().getString(R.string.save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String newEmail = emailEditText.getText().toString();
                                String password = passwordEditText.getText().toString();
//                                mHelper. TODO
//                                mHelper.changeUserEmail(oldEmail, newEmail, password);
                                Toast.makeText(ProfileActivity.this, "New Email saved", Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

        logout = (Button) findViewById(R.id.log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Constants.LOGGEDIN, false).apply();

                Intent intent = new Intent(ProfileActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

        // Expandable listview
        mListView = (ListView) findViewById(R.id.favorites_list);


        mAdapter = new CardAdapter(getApplicationContext(), mHelper.getUserFavorites());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO bring up detail view
            }
        });
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
