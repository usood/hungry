package in.of10.hungry.service;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import in.of10.hungry.dishes.Dish;
import in.of10.hungry.restaurants.Restaurant;
import in.of10.hungry.restaurants.RestaurantCallback;

/**
 * Created by upaharsood on 23/06/16.
 */
public class RestaurantService {

    private static RestaurantService INSTANCE = new RestaurantService();

    private DishService dishService;

    private AsyncHttpClient client;

    private List<Restaurant> mRestaurants;

    private int nextId;

    private RestaurantService(){
        mRestaurants = new ArrayList<Restaurant>();
        dishService = DishService.getInstance();
        nextId = 0;
        client = new AsyncHttpClient();
    }

    /**
     *
     * @return
     */
    public static RestaurantService getInstance() {
        return INSTANCE;
    }

    public void getListOfRestaurants(final RestaurantCallback resCallback){
        client.get("http://www.jhakasfood.com/api/restaurant/list", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                List<Restaurant> restaurantList = new ArrayList<Restaurant>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject r = response.getJSONObject(i);
                        Restaurant newRestaurant = new Restaurant();
                        newRestaurant.setName(r.getString("name"));
                        newRestaurant.setPhoto(r.getString("photo"));
                        newRestaurant.setId(r.getInt("id"));
                        newRestaurant.setDescription(r.getString("about"));

                        restaurantList.add(newRestaurant);
                        resCallback.receiveRestaurants(restaurantList);
                        mRestaurants = restaurantList;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     *
     * @param id
     * @return
     */
    public Restaurant getRestaurantById(long id){
        for (Restaurant restaurant: mRestaurants) {
            if(restaurant.getId() == id){
                return restaurant;
            }
        }
        return null;
    }

    public int getNumberOfRestaurants(){
        return mRestaurants.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public Restaurant getRestaurant(int index) {
        return mRestaurants.get(index);
    }

    /**
     *
     * @param restaurantId
     * @return
     */
    public List<Dish> getDishesByRestaurantId(long restaurantId) {
        return dishService.getDishes(restaurantId);
    }

    /**
     *
     * @param name
     * @param description
     * @param photo
     * @return
     */
    public boolean addRestaurant(String name, String description, String photo){
        Restaurant r = new Restaurant();
        r.setId(nextId++);
        r.setName(name);
        r.setDescription(description);
        r.setPhoto(photo);
        mRestaurants.add(r);
        dishService.addRestaurantToDishMap(r.getId());
        return true;
    }






}
