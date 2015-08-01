package hackaccess.c4q.nyc.educationapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NavDrawer extends ActionBarActivity {

    @Bind(R.id.navList) ListView mDrawerList;
    @Bind(R.id.user_info) ImageView userInfo;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        ButterKnife.bind(this);
        addDrawerItems();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        fTransaction.replace(R.id.content_frame, new DirectoryActivity()).commit();

    }

    private void addDrawerItems() {
        String[] ndArray = {"Home", "Button Press Counter"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ndArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = null;
                switch (position) {
                    case 0: {
                        fragment = new DirectoryActivity();
                        break;
                    }
                    case 1: {
                        fragment = new DirectoryActivity();
                        break;
                    }
                    default:
                        break;
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fTransaction = fragmentManager.beginTransaction();
                fTransaction.replace(R.id.content_frame, fragment).commit();

                drawerLayout.closeDrawers();
            }
        });
    }
}
