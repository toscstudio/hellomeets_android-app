package com.hellomeets.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.hellomeets.android.fragment.upcoming.UpcomingEventListFragment;
import com.hellomeets.android.utils.DummyContent;
import com.hellomeets.android.intro.IntroActivity;
import com.thefinestartist.finestwebview.FinestWebView;
import com.thefinestartist.finestwebview.FinestWebViewActivity;

public class MainActivity extends AppCompatActivity implements UpcomingEventListFragment.OnListFragmentInteractionListener{

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
                goToFragment(position);
                navDrawerLayout.closeDrawer(drawerFrame);

            }
        });


    }


    private void goToFragment (int navItemPosition) {
        Fragment newFragment;
        String tag;
        switch (navItemPosition) {
            case 2:
                new FinestWebView.Builder(getApplicationContext())
                        .statusBarColor(Color.BLACK)
                        .toolbarColor(Color.BLACK)
                        .progressBarColor(Color.WHITE)
                        .titleColor(Color.WHITE)
                        .updateTitleFromHtml(true)
                        .iconDefaultColor(Color.WHITE)
                        .iconDisabledColor(Color.DKGRAY)
                        .show("http://medium.com/@hellomeets");
                //Do not select the blog item
                navList.setItemChecked(0, true);
                break;

            default:
                navList.setItemChecked(navItemPosition, true);
                newFragment = UpcomingEventListFragment.newInstance(1);
                tag = "upcoming";
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, newFragment, tag).commit();
                break;
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
