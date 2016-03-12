package com.hellomeets.android.categories;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by championswimmer on 12/3/16.
 */
public abstract class BaseCategories {
    public static final String TAG = "BaseCategories";

    public String getTitle() {
        return title;
    }

    String title;

    abstract int catTitleResId();

    public BaseCategories(Context c) {
        title = c.getString(catTitleResId());
    }

    private static ArrayList<BaseCategories> topCategories;

    public static void setTopCategories(Context ctx) {
        Log.d(TAG, "setTopCategories: ");
        topCategories = new ArrayList<>();

        topCategories.add(new HelloHackers(ctx));
        topCategories.add(new HelloCoders(ctx));
    }

    public static ArrayList<BaseCategories> getTopCategories() {
        return topCategories;
    }

}
