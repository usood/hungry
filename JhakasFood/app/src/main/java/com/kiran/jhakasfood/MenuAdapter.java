package com.kiran.jhakasfood;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by harsh on 6/12/16.
 */
public class MenuAdapter extends BaseAdapter {

    Context mContext;
    long mRestaurantId;
    JhakasService JS;

    public MenuAdapter(Context ctxt, long restaurantId) {
        mContext = ctxt;
        mRestaurantId = restaurantId;

        JS = JhakasService.getInstance();
    }

    @Override
    public int getCount() {
        return JS.numDishes(mRestaurantId);
    }

    @Override
    public Object getItem(int i) {
        return JS.getDish(mRestaurantId, i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.menu_item, viewGroup, false);

        // Get the data
        Dish d = (Dish) getItem(i);



        // Get UI Elements and connect them
        TextView name = (TextView) v.findViewById(R.id.dish_name);
        TextView description = (TextView) v.findViewById(R.id.description);

        // Update them.
        name.setText(d.name);
        description.setText(d.description);
        if (d.selected) {
            v.setBackgroundColor(Color.GRAY);
        }

        return v;
    }
}
