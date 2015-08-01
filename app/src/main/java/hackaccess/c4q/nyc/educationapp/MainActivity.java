package hackaccess.c4q.nyc.educationapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;


public class MainActivity extends ActionBarActivity {

    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://edusearch.firebaseio.com/");
    }


}
