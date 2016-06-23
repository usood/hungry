package in.of10.hungry.service;


import java.util.ArrayList;
import java.util.List;

import in.of10.hungry.restaurants.Dish;
import in.of10.hungry.restaurants.Restaurant;

/**
 * Created by upaharsood on 23/06/16.
 */
public class RestaurantService {

    private static RestaurantService INSTANCE = new RestaurantService();

    private DishService dishService;

    private List<Restaurant> mRestaurants;

    private int nextId;

    private RestaurantService(){
        mRestaurants = new ArrayList<Restaurant>();
        dishService = DishService.getInstance();
        nextId = 0;
    }

    /**
     *
     * @return
     */
    public static RestaurantService getInstance() {
        return INSTANCE;
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
