package in.of10.hungry.restaurants;

import java.util.HashMap;

/**
 * Created by upaharsood on 30/06/16.
 */
public class Order {
    private int orderId;
    private int restaurantId;
    private HashMap<Integer, Integer> dishesInOrder;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public HashMap<Integer, Integer> getDishesInOrder() {
        return dishesInOrder;
    }

    public void setDishesInOrder(HashMap<Integer, Integer> dishesInOrder) {
        this.dishesInOrder = dishesInOrder;
    }
}
