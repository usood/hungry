package com.kiran.jhakasfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by harsh on 6/19/16.
 */
public class JhakasService {

    private static JhakasService INSTANCE = new JhakasService();

    public static JhakasService getInstance() {
        return INSTANCE;
    }

    private int nextID;
    private ArrayList<Restaurant> mRestaurants;
    private HashMap<Long, ArrayList<Dish>> mDishes;

    private JhakasService() {
        mRestaurants = new ArrayList<>();
        mDishes = new HashMap<>();
        nextID = 0;
    }

    private void loadTestData() {

    }

    public int numRestaurants() {
        return mRestaurants.size();
    }

    public Restaurant getRestaurantById(long id) {
        for (Restaurant r : mRestaurants) {
            if (r.id == id)
                return r;
        }
        return null;
    }

    public Restaurant getRestaurant(int index) {
        return mRestaurants.get(index);
    }

    public List<Dish> getDishes(long restaurantId) {
        return mDishes.get(restaurantId);
    }

    public void addRestaurant(String name, String location, String photo) {
        Restaurant r = new Restaurant();
        r.name = name;
        r.location = location;
        r.photo = photo;
        r.id = nextID;
        mRestaurants.add(r);

        mDishes.put(r.id, new ArrayList<Dish>());

        nextID++;
    }

    public void addDish(long restaurantId, String name, int price) {
        Dish d = new Dish();
        d.name = name;
        d.price = price;

        List<Dish> dishes = mDishes.get(restaurantId);
        dishes.add(d);
    }

    public int numDishes(long restaurantId) {
        return mDishes.get(restaurantId).size();
    }

    public Dish getDish(long restaurantId, int index) {
        return getDishes(restaurantId).get(index);
    }
}
