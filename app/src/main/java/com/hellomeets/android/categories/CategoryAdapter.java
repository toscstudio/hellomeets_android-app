package com.hellomeets.android.categories;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by championswimmer on 12/3/16.
 */
public class CategoryAdapter extends ArrayAdapter<BaseCategories> {



    public CategoryAdapter(Context context, ArrayList<BaseCategories> cats) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, cats);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        ((TextView)view.findViewById(android.R.id.text1)).setText(getItem(position).getTitle());
        ((TextView)view.findViewById(android.R.id.text1)).setTextColor(Color.WHITE);

        return view;
    }
}
