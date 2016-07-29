package in.of10.hungry;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.of10.hungry.dishes.Dish;
import in.of10.hungry.dishes.DishCallback;
import in.of10.hungry.restaurants.OrderService;
import in.of10.hungry.service.DishService;

/**
 * Created by upaharsood on 22/06/16.
 */
public class MenuAdapter extends BaseAdapter {

    DishService dishService;
    Context mContext;
    int mRestaurantId;
    List<Dish> dishList;

    public MenuAdapter(Context context, int restaurantId){
        mContext = context;
        dishService = DishService.getInstance();
        dishService.getListOfDishes(restaurantId, new DishCallback() {
            @Override
            public void receiveDishes(List<Dish> dishes) {
                dishList = dishes;
            }
        });
        mRestaurantId = restaurantId;
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int i) {
        return dishList.get(i);
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
        final int dishId = dish.getId();
        final OrderService orderService = OrderService.getInstance();
        int quantity = orderService.getQuantity(dishId);

        TextView name = (TextView) v.findViewById(R.id.dish_name);
        TextView description = (TextView) v.findViewById(R.id.description);

        TextView quantityView = (TextView) v.findViewById(R.id.quantity);

        ImageView imageView = (ImageView) v.findViewById(R.id.photo);

        Button minusBtn = (Button) v.findViewById(R.id.decrease);
        Button plusBtn = (Button) v.findViewById(R.id.increase);

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderService.updateQuantity(dishId, -1);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderService.updateQuantity(dishId, 1);
            }
        });


        name.setText(dish.getName());
        description.setText(dish.getDescription());
        quantityView.setText("" + quantity);
        Picasso.with(mContext)
                .load(dish.getPhoto())
                .into(imageView);
        if (dish.isSelected()) {
            v.setBackgroundColor(Color.GRAY);
        }

        return view;
    }
}
