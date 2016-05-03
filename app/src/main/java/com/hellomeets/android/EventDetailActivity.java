package com.hellomeets.android;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hellomeets.android.fragment.upcoming.eventsDetail.EventsFragmentAdapter;
import com.hellomeets.android.fragmentnavigator.FragmentNavigator;
import com.hellomeets.android.widgets.TabLayout;

public class EventDetailActivity extends AppCompatActivity {

    private FragmentNavigator mNavigator;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Events Detail");

        mNavigator = new FragmentNavigator(getSupportFragmentManager(), new EventsFragmentAdapter(), R.id.childContainer);
        mNavigator.setDefaultPosition(0);
        mNavigator.onCreate(savedInstanceState);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setOnTabItemClickListener(new TabLayout.OnTabItemClickListener() {
            @Override
            public void onTabItemClick(int position, View view) {
                setCurrentTab(position);
            }
        });

        setCurrentTab(mNavigator.getCurrentPosition());

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    public void setCurrentTab(int position) {
        mNavigator.showFragment(position);
        tabLayout.select(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (hasFocus) {
                startPostponedEnterTransition();
            }
        }
    }


}
