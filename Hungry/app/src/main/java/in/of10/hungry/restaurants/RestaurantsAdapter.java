package in.of10.hungry.restaurants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.of10.hungry.R;
import in.of10.hungry.service.RestaurantService;

/**
 * Created by upaharsood on 18/06/16.
 */
public class RestaurantsAdapter extends BaseAdapter {

    private RestaurantService restaurantService;
    private List<Restaurant> mRestaurants;
    private Context mContext;

    /**
     * Constructor
     * @param c
     */
    public RestaurantsAdapter(Context c){
        mContext = c;
        restaurantService = RestaurantService.getInstance();
        mRestaurants = new ArrayList<Restaurant>();
        restaurantService.getListOfRestaurants(new RestaurantCallback() {
            @Override
            public void receiveRestaurants(List<Restaurant> restaurants) {
                mRestaurants = restaurants;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    /**
     * Get Count of Restaurants
     */
    public int getCount() {
        return mRestaurants.size();
    }

    /**
     * Get Item
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return mRestaurants.get(i);
    }

    /**
     * Get Item Id
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        Restaurant restaurant = (Restaurant)getItem(i);
        return restaurant.getId();
    }

    /**
     *
     * @param i
     * @param reusableView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View reusableView, ViewGroup viewGroup) {
        View v = null;
        // Get an empty view
        if(reusableView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            v = layoutInflater.inflate(R.layout.restaurant_item, viewGroup, false);
        }else{
            v = reusableView;
        }
        // Get the data
        Restaurant res = (Restaurant) getItem(i);

        //Get UI Elements and Connect them
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView description = (TextView) v.findViewById(R.id.description);
        ImageView photo = (ImageView) v.findViewById(R.id.photo);

        Picasso.with(mContext)
                .load(res.getPhoto())
                .centerCrop()
                .resize(200, 200)
                .into(photo);
        //update them
        name.setText(res.getName());
        description.setText(res.getDescription());


        return v;
    }
}
