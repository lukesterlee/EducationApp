package hackaccess.c4q.nyc.educationapp.chat;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import hackaccess.c4q.nyc.educationapp.MapActivity;
import hackaccess.c4q.nyc.educationapp.profile.CreateProfileActivity;
import hackaccess.c4q.nyc.educationapp.DirectoryActivity;
import hackaccess.c4q.nyc.educationapp.FirebaseHelper;
import hackaccess.c4q.nyc.educationapp.ProfileActivity;
import hackaccess.c4q.nyc.educationapp.R;
import hackaccess.c4q.nyc.educationapp.SettingsActivity;

public class ChatRoomActivity extends AppCompatActivity implements ActionBar.TabListener {

    private ViewPager mViewPager;
    private FirebaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mHelper = FirebaseHelper.getInstance(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Chatrooms");

        if (mHelper.isLoggedIn()) {
            mViewPager = (ViewPager) findViewById(R.id.pager);
            ProgramPagerAdapter mAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mAdapter);

            SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
            mSlidingTabLayout.setDistributeEvenly(true);

            mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.accent);
                }
            });

            // Setting the ViewPager For the SlidingTabsLayout
            mSlidingTabLayout.setViewPager(mViewPager);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(getResources().getString(R.string.must_log_in));
            dialog.setPositiveButton(getResources().getString(R.string.log_in), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(ChatRoomActivity.this, CreateProfileActivity.class);
                    startActivity(myIntent);
                }
            });
            dialog.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(ChatRoomActivity.this, DirectoryActivity.class);
                    startActivity(myIntent);
                }
            });
            dialog.show();
        }

    }

    // TABS
    public class ProgramPagerAdapter extends FragmentStatePagerAdapter {

        public ProgramPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
//            String username = mHelper.getUserInfo().getFirstName() + " " + mHelper.getUserInfo().getLastName();

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
                    title = getResources().getString(R.string.chat1);
                    break;
                case 1:
                    title = getResources().getString(R.string.chat2);
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
        switch (item.getItemId()) {
            case R.id.action_mapview:
                Intent map = new Intent(this, MapActivity.class);
                startActivity(map);
                break;
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
}

