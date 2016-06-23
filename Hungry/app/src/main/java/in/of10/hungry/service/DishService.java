package in.of10.hungry.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.of10.hungry.restaurants.Dish;

/**
 * Created by upaharsood on 23/06/16.
 */
public class DishService {

    private static DishService INSTANCE = new DishService();

    private Map<Long, List<Dish>> restaurantDishMap;

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
