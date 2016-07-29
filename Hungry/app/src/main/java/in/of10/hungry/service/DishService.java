package in.of10.hungry.service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import in.of10.hungry.dishes.Dish;
import in.of10.hungry.dishes.DishCallback;

/**
 * Created by upaharsood on 23/06/16.
 */
public class DishService {

    private static DishService INSTANCE = new DishService();

    private Map<Long, List<Dish>> restaurantDishMap;

    private AsyncHttpClient client;

    public static DishService getInstance() {
        return INSTANCE;
    }
    /**
     *
     */
    private DishService(){
        restaurantDishMap = new HashMap<Long, List<Dish>>();
    }

    /**
     *
     * @param restaurantId
     * @return DishList
     */
    public List<Dish> getDishes(long restaurantId) {
        return restaurantDishMap.get(restaurantId);
    }


    public void getListOfDishes(final int restaurantId, final DishCallback dishCallback){
        client.get(String.format("http://www.jhakasfood.com/api/restaurant/list/%d/dishes",restaurantId), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                List<Dish> dishList = new ArrayList<Dish>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject r = response.getJSONObject(i);
                        Dish newDish = new Dish();
                        newDish.setName(r.getString("name"));
                        newDish.setPhoto(r.getString("photo"));
                        newDish.setId(r.getInt("id"));
                        newDish.setDescription(r.getString("about"));
                        newDish.setPrice(r.getInt("cost"));

                        dishList.add(newDish);
                        dishCallback.receiveDishes(dishList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     *
     * @param restaurantId
     * @return
     */
    public int getNumberOfDishes(long restaurantId){
        return restaurantDishMap.get(restaurantId).size();
    }

    /**
     *
     * @param restaurantId
     * @return
     */
    public boolean addRestaurantToDishMap(long restaurantId){
        restaurantDishMap.put(restaurantId, new ArrayList<Dish>());
        return true;
    }

    /**
     *
     * @param restaurantId
     * @param dishIndex
     * @return
     */
    public Dish getDish(long restaurantId, int dishIndex){
        Dish dish = restaurantDishMap.get(restaurantId).get(dishIndex);
        return dish;
    }

    public boolean addDish(long restaurantId, String name, int price){
        Dish d = new Dish();
        d.setName(name);
        d.setPrice(price);

        List<Dish> dishes = restaurantDishMap.get(restaurantId);
        dishes.add(d);
        return true;
    }
}
