package hackaccess.c4q.nyc.educationapp.chat;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import java.util.Random;

import hackaccess.c4q.nyc.educationapp.Constants;
import hackaccess.c4q.nyc.educationapp.ProfileActivity;
import hackaccess.c4q.nyc.educationapp.Program;
import hackaccess.c4q.nyc.educationapp.R;
import hackaccess.c4q.nyc.educationapp.SettingsActivity;
import hackaccess.c4q.nyc.educationapp.program.DetailsFragment;
import hackaccess.c4q.nyc.educationapp.program.HoursFragment;

public class ChatRoomActivity extends AppCompatActivity implements ActionBar.TabListener {

    private static final String FIREBASE_URL = "https://edusearch.firebaseio.com/";
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        ProgramPagerAdapter mAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setDistributeEvenly(true);

        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        mSlidingTabLayout.setViewPager(mViewPager);

        setTitle("Chatrooms");
    }

    // TABS
    public class ProgramPagerAdapter extends FragmentStatePagerAdapter {

        public ProgramPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new ChatFragment("chat");
                    break;
                case 1:
                    fragment = new ChatFragment("chat2");
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position) {
                case 0:
                    title = "Chatroom1";
                    break;
                case 1:
                    title = "Chatroom2";
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

