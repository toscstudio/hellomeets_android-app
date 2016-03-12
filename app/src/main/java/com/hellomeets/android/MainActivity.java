package com.hellomeets.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.hellomeets.android.categories.BaseCategories;
import com.hellomeets.android.categories.CategoryAdapter;
import com.hellomeets.android.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {

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
        navList.setAdapter(new CategoryAdapter(this, BaseCategories.getTopCategories()));

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


    }
}
