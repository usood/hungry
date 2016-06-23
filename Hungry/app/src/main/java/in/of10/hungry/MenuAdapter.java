package in.of10.hungry;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.of10.hungry.restaurants.Dish;
import in.of10.hungry.service.DishService;

/**
 * Created by upaharsood on 22/06/16.
 */
public class MenuAdapter extends BaseAdapter {

    DishService dishService;
    Context mContext;
    long mRestaurantId;

    public MenuAdapter(Context context, Long restaurantId){
        mContext = context;
        dishService = DishService.getInstance();
        mRestaurantId = restaurantId;
    }

    @Override
    public int getCount() {
        return dishService.getNumberOfDishes(mRestaurantId);
    }

    @Override
    public Object getItem(int i) {
        return dishService.getDish(mRestaurantId, i);
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
        Dish dish = (Dish)getItem(i);

        TextView name = (TextView) v.findViewById(R.id.dish_name);
        TextView description = (TextView) v.findViewById(R.id.description);

        name.setText(dish.getName());
        description.setText(dish.getDescription());
        if (dish.isSelected()) {
            v.setBackgroundColor(Color.GRAY);
        }

        return view;
    }
}
