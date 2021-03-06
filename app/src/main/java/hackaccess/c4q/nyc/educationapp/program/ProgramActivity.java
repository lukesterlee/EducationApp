package hackaccess.c4q.nyc.educationapp.program;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import hackaccess.c4q.nyc.educationapp.FirebaseHelper;
import hackaccess.c4q.nyc.educationapp.chat.ChatRoomActivity;
import hackaccess.c4q.nyc.educationapp.Constants;
import hackaccess.c4q.nyc.educationapp.ProfileActivity;
import hackaccess.c4q.nyc.educationapp.Program;
import hackaccess.c4q.nyc.educationapp.R;
import hackaccess.c4q.nyc.educationapp.SettingsActivity;
import hackaccess.c4q.nyc.educationapp.profile.CreateProfileActivity;

public class ProgramActivity extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener {

    private static final String PRE_ENDPOINT = "https://maps.googleapis.com/maps/api/streetview?key=AIzaSyDTaAeiCfVCXJhdweubPkgIvsni3s1-9ss&size=800x400&location=";
    private Toolbar mToolbar;
    private SlidingTabLayout mSlidingTabLayout;
    private Program mProgram;
    private ViewPager mViewPager;
    private ProgramActivity.ProgramPagerAdapter mAdapter;
    private FirebaseHelper mHelper;
    private ImageView image;
    private MenuItem signMenu;
    private Button mButtonFavorite;
    private String mUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        mHelper = FirebaseHelper.getInstance(getApplicationContext());
        mUserID = mHelper.getUserID();
        mHelper.updateUserFavorites();
        Intent intent = getIntent();
        if (intent != null) {
            mProgram = intent.getParcelableExtra(Constants.EXTRA_PROGRAM);
        }

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mButtonFavorite = (Button) findViewById(R.id.button_favorite);
        mButtonFavorite.setBackgroundColor(Color.parseColor("#4BAE4F"));
        mButtonFavorite.setTextColor(Color.WHITE);
        mToolbar.setTitleTextColor(Color.WHITE);



        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setDistributeEvenly(true);


        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent);
            }



        });

        // Setting the ViewPager For the SlidingTabsLayout
        mSlidingTabLayout.setViewPager(mViewPager);


        image = (ImageView) findViewById(R.id.image);
        URL url = null;
        try {
            url = new URL(PRE_ENDPOINT + mProgram.getLatitude() + "," + mProgram.getLongitude());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Glide.with(this).load(url).centerCrop().into(image);



    }

    public class ProgramPagerAdapter extends FragmentStatePagerAdapter {

        public ProgramPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.EXTRA_PROGRAM, (Parcelable) mProgram);

            switch (position) {
                case 0:
                    fragment = new DetailsFragment();
                    break;
                case 1:
                    fragment = new HoursFragment();
                    break;
                case 2:
                    fragment = new ContactFragment();
                    break;
            }

            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position) {
                case 0:
                    title = "Details";
                    break;
                case 1:
                    title = "Hours";
                    break;
                case 2:
                    title = "Contacts";
                    break;
            }
            return title;
        }

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(signMenu != null) {
            if (mHelper.isLoggedIn()) {
                signMenu.setTitle("Sign Out");

            } else {
                signMenu.setTitle("Sign In");
            }
        }

        if (mUserID != null) {
            mButtonFavorite.setOnClickListener(this);
            Firebase firebase = new Firebase(Constants.FIREBASE_URL);
            firebase.child(Constants.FIREBASE_KEY_USERS).child(mUserID).child(Constants.FIREBASE_KEY_FAVORITES).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                        for (DataSnapshot dataSnapshot1 : iterable) {
                            Program program = dataSnapshot1.getValue(Program.class);
                            if (mProgram.getProgramId().equals(program.getProgramId())) {
                                mButtonFavorite.setText("Saved");
                                mButtonFavorite.setBackgroundColor(Color.parseColor("#e91e63"));
                                mButtonFavorite.setTextColor(Color.WHITE);
                            }
                        }



                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } else {
            mButtonFavorite.setText("Save");
            mButtonFavorite.setBackgroundColor(Color.parseColor("#4BAE4F"));
            mButtonFavorite.setTextColor(Color.WHITE);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        signMenu = menu.getItem(0);
        if (mHelper.isLoggedIn()) {
            signMenu.setTitle("Sign Out");

        } else {
            signMenu.setTitle("Sign In");

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sign_user:
                if (mHelper.isLoggedIn()) {
                    item.setTitle("Sign In");
                    mHelper.logOutUser();
                    Toast.makeText(getApplicationContext(), "Signed Out!", Toast.LENGTH_SHORT).show();

                } else {
                    Intent create = new Intent(this, CreateProfileActivity.class);
                    startActivity(create);
                }
                break;
            case R.id.action_profile:
                if (mHelper.isLoggedIn()) {
                    Intent profile = new Intent(this, ProfileActivity.class);
                    startActivity(profile);
                } else {
                    Intent create = new Intent(this, CreateProfileActivity.class);
                    startActivity(create);
                }
                break;
            case R.id.action_chat:
                Intent chat = new Intent(this, ChatRoomActivity.class);
                startActivity(chat);
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        String favorite = mButtonFavorite.getText().toString().toLowerCase();

        if (favorite.equals("save")) {
            if (mHelper.addFavorite(mProgram)) {
                mButtonFavorite.setText("Saved");
                mButtonFavorite.setBackgroundColor(Color.parseColor("#e91e63"));
                mButtonFavorite.setTextColor(Color.WHITE);
            }
        } else {


            mHelper.child("users").child(mUserID).child("likes").removeValue();
            List<Program> favorites = mHelper.getUserFavorites();
            List<Program> newFav = new ArrayList<>();
            for (Program program : favorites) {
                if (!mProgram.getProgramId().equals(program.getProgramId())) {
                    newFav.add(program);

                }
            }
            mHelper.child("users").child(mUserID).child("likes").setValue(newFav);
            mButtonFavorite.setText("Save");
            mButtonFavorite.setBackgroundColor(Color.parseColor("#4BAE4F"));
            mButtonFavorite.setTextColor(Color.WHITE);

            Toast.makeText(getApplicationContext(), "Deleted from the list!", Toast.LENGTH_SHORT).show();



        }


    }
}