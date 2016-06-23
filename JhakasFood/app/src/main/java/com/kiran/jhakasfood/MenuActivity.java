package com.kiran.jhakasfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    ListView mList;
    TextView mCart;
    MenuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();

        long restaurantId = intent.getLongExtra("restaurant_id", -1);

        Restaurant restaurant = JhakasService.getInstance().getRestaurantById(restaurantId);
        setTitle(restaurant.name);

        mCart = (TextView) findViewById(R.id.cart);
        mCart.setText("0 Items in Cart");

        mList = (ListView) findViewById(R.id.listView);

        mAdapter = new MenuAdapter(this, restaurantId);
        mList.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }
}
