package com.hellomeets.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.hellomeets.android.fragment.EventListFragment;
import com.hellomeets.android.fragment.dummy.DummyContent;
import com.hellomeets.android.intro.IntroActivity;

public class MainActivity extends AppCompatActivity implements EventListFragment.OnListFragmentInteractionListener{

    public static final String PREF_INTRO_SHOWN = "intro_shown";

    DrawerLayout navDrawerLayout;
    FrameLayout contentFrame;
    FrameLayout drawerFrame;
    ActionBarDrawerToggle mDrawerToggle;
    ListView navList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean introShown = sPrefs.getBoolean(PREF_INTRO_SHOWN, false);

        if (!introShown) {
            Intent i = new Intent(this, IntroActivity.class);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
        drawerFrame = (FrameLayout) findViewById(R.id.left_drawer);
        navList = (ListView) findViewById(R.id.nav_list);

        final ArrayAdapter<String> navCategoryAdapter = new ArrayAdapter<>(this,R.layout.list_item_nav_category);
        navCategoryAdapter.addAll("Upcoming", "Past Events", "Blog");

        navList.setAdapter(navCategoryAdapter);

        mDrawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        navDrawerLayout,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                )
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        navDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navList.setItemChecked(position, true);
                navDrawerLayout.closeDrawer(drawerFrame);
                goToFragment(position);

            }
        });


    }

    private void goToFragment (int navItemPosition) {
        Fragment newFragment;
        switch (navItemPosition) {
            default:
                newFragment = EventListFragment.newInstance(1);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, newFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
