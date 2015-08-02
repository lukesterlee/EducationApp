package hackaccess.c4q.nyc.educationapp.profile;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import hackaccess.c4q.nyc.educationapp.FirebaseHelper;

import hackaccess.c4q.nyc.educationapp.ProfileActivity;
import hackaccess.c4q.nyc.educationapp.R;
import hackaccess.c4q.nyc.educationapp.SettingsActivity;
import hackaccess.c4q.nyc.educationapp.chat.ChatRoomActivity;

public class CreateProfileActivity extends ActionBarActivity implements ActionBar.TabListener, BackCallback {

    private SlidingTabLayout mSlidingTabLayout;

    private ViewPager mViewPager;
    private ProfilePagerAdapter mAdapter;
    private FirebaseHelper mHelper;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        mHelper = FirebaseHelper.getInstance(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Account Info");

        mHelper = FirebaseHelper.getInstance(getApplicationContext());
        mHelper.setCallback(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ProfilePagerAdapter(getSupportFragmentManager());
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

    }

    public class ProfilePagerAdapter extends FragmentStatePagerAdapter {

        public ProfilePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new SignupFragment();
                    break;
                case 1:
                    fragment = new LoginFragment();
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
                    title = "Sign Up";
                    break;
                case 1:
                    title = "Login";
                    break;
            }
            return title;
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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
    public void goback() {
        onBackPressed();
    }
}
