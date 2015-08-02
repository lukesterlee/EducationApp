package hackaccess.c4q.nyc.educationapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


/**
 * Created by sufeizhao on 8/1/15.
 */
public class ProgramActivity extends FragmentActivity implements ActionBar.TabListener {

    private Program mProgram;
    private ViewPager mViewPager;
    private ProgramPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);


        Intent intent = getIntent();
        if (intent != null) {
            mProgram = intent.getParcelableExtra(Constants.EXTRA_PROGRAM);
        }



//        ActionBar actionBar = getActionBar();
//        actionBar.setN

        //actionBar.setHomeButtonEnabled(false);

        // Specify that tabs should be displayed in the action bar.
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);







        //mProgram =  getIntent().getParcelableExtra();


        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ProgramPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);


        //Program program = intent.getParcelableExtra("program");

        // Add 3 tabs, specifying the tab's text and TabListener
//        for (int i = 0; i < 3; i++) {
//
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText("Tab " + (i + 1))
//                            .setTabListener(this));
//        }


    }

    public class ProgramPagerAdapter extends FragmentPagerAdapter {

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
}
