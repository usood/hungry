package com.kiran.jhakasfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RestaurantsActivity extends AppCompatActivity {

    RestaurantsAdapter mAdapter;

    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mAdapter = new RestaurantsAdapter(this);

        mList = (ListView) findViewById(R.id.listView);
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("app", "CLICKED " + l);

                Intent intent = new Intent(getApplicationContext(),
                        MenuActivity.class);
                intent.putExtra("restaurant_id", l);
                intent.putExtra("name", "boo");
                intent.putExtra("x", true);

                startActivity(intent);
            }
        });

        setTitle("Restaurant Application");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_restaurant) {
            JhakasService.getInstance().addRestaurant("Chilis", "Powai", "http://vignette2.wikia.nocookie.net/logopedia/images/f/f7/Chili-largelogood.jpg/revision/latest?cb=20151030224025");
            mAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
