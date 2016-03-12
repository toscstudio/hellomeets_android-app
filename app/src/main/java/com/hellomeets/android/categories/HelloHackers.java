package com.hellomeets.android.categories;

import android.content.Context;

import com.hellomeets.android.R;

/**
 * Created by championswimmer on 12/3/16.
 */
public class HelloHackers extends BaseCategories{

    public HelloHackers(Context c) {
        super(c);
    }

    @Override
    int catTitleResId() {
        return R.string.category_hellohackers;
    }
}
