package com.hellomeets.android.fragment.upcoming.eventsDetail;

import android.support.v4.app.Fragment;

import com.hellomeets.android.fragmentnavigator.FragmentNavigatorAdapter;

/**
 * Created by yogesh on 4/5/16.
 */
public class EventsFragmentAdapter implements FragmentNavigatorAdapter {

    public static final String[] TABS = {"ABOUT", "SPEAKER", "MAP"};

    @Override
    public Fragment onCreateFragment(int position) {
        switch (position){
            case 0: return AboutFragment.newInstance("", "");
            case 1: return SpeakerFragment.newInstance("", "");
            case 2: return MapFragment.newInstance("", "");
        }
        return null;
    }

    @Override
    public String getTag(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
