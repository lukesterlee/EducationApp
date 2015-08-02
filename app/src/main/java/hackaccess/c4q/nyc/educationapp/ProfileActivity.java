package hackaccess.c4q.nyc.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by sufeizhao on 8/1/15.
 */
public class ProfileActivity extends Activity {

    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = (ImageView) findViewById(R.id.profile_pic);

    }
}
