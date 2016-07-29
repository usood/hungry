package in.of10.hungry;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import in.of10.hungry.restaurants.OrderService;
import in.of10.hungry.restaurants.Restaurant;
import in.of10.hungry.service.RestaurantService;

public class MenuActivity extends AppCompatActivity {

    ListView mList;
    TextView mCart;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent();
        int restaurantId = i.getIntExtra("restaurant_id", -1);

        Restaurant restaurant = RestaurantService.getInstance().getRestaurantById(restaurantId);
        setTitle(restaurant.getName());

        OrderService.getInstance().startNewOrder(restaurantId);

        mCart = (TextView) findViewById(R.id.cart);
        mCart.setText("0 items in cart");

        mList = (ListView) findViewById(R.id.listView);

        menuAdapter = new MenuAdapter(this, restaurantId);
        mList.setAdapter(menuAdapter);
        menuAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                int totalItems = OrderService.getInstance().getItemsInOrder();
                mCart.setText(totalItems + " items in cart");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }
}
