package com.kiran.jhakasfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by harsh on 6/12/16.
 */

public class RestaurantsAdapter extends BaseAdapter {

    private Context mContext;

    public RestaurantsAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return JhakasService.getInstance().numRestaurants();
    }

    @Override
    public Object getItem(int i) {
        return JhakasService.getInstance().getRestaurant(i);
    }

    @Override
    public long getItemId(int i) {
        Restaurant r = (Restaurant) getItem(i);
        return r.id;
    }

    @Override
    public View getView(int i, View reusableView, ViewGroup viewGroup) {
        // Get an empty View.
        View v = null;
        if (reusableView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            v = inflater.inflate(R.layout.restaurant_item, viewGroup, false);
        } else {
            v = reusableView;
        }

        // Get the data
        Restaurant res = (Restaurant) getItem(i);

        // Get UI Elements and connect them
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView description = (TextView) v.findViewById(R.id.description);
        ImageView photo = (ImageView) v.findViewById(R.id.photo);

        Picasso.with(mContext)
                .load(res.photo)
                .centerCrop()
                .resize(200, 200)
                .into(photo);

        // Update them.
        name.setText(res.name);
        description.setText(res.description);

        return v;
    }
}
