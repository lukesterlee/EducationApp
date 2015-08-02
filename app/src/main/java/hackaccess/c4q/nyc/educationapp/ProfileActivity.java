package hackaccess.c4q.nyc.educationapp;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sufeizhao on 8/1/15.
 */
public class ProfileActivity extends ExpandableListActivity {

    private ImageView profilePic;
    private ExpandableListView eListView;
    private TextView name;
    private Button logout, changeEmail;
    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = (ImageView) findViewById(R.id.profile_pic);
        name = (TextView) findViewById(R.id.name);

        changeEmail = (Button) findViewById(R.id.change_email);
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
                final EditText emailEditText = new EditText(ProfileActivity.this);
                dialogBuilder.setTitle("Change Email Address")
                        .setMessage("Enter new email address: ")
                        .setView(emailEditText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String email = emailEditText.getText().toString();
                                //TODO add to db
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

        eListView = (ExpandableListView) findViewById(android.R.id.list);
        setGroupParents();
        setChildData();
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(parentItems, childItems);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        eListView.setAdapter(adapter);
        eListView.setOnChildClickListener(this);
    }

    public void setGroupParents() {
        parentItems.add("Favorites");
        parentItems.add("Chat History");
    }

    public void setChildData() {

        // Favorites
        ArrayList<String> child = new ArrayList<String>();
        child.add("Core");
        child.add("Games");
        childItems.add(child);

        // Chat History
        child = new ArrayList<String>();
        child.add("Apache");
        child.add("Applet");
        child.add("AspectJ");
        child.add("Beans");
        child.add("Crypto");
        childItems.add(child);
    }
}