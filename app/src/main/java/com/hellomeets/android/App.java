package com.hellomeets.android;

import android.app.Application;

import com.hellomeets.android.categories.BaseCategories;

/**
 * Created by championswimmer on 12/3/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BaseCategories.setTopCategories(getApplicationContext());
    }
}
