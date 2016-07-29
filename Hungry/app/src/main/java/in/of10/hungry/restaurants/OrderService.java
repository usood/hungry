package in.of10.hungry.restaurants;

import java.util.HashMap;

/**
 * Created by upaharsood on 30/06/16.
 */
public class OrderService {

    // If there is no order, this thing is null.
    private Order currentOrder;

    private static OrderService INSTANCE;

    private OrderService() {
        INSTANCE = new OrderService();
    }

    public static OrderService getInstance(){
        return INSTANCE;
    }

    public void startNewOrder(int restaurantId){
        currentOrder = new Order();
        currentOrder.setRestaurantId(restaurantId);
        currentOrder.setDishesInOrder(new HashMap<Integer, Integer>());
    }

    public void updateQuantity(int dishId, int delta){
        if(currentOrder == null)
            throw new IllegalStateException("No order");
        Integer quantity = currentOrder.getDishesInOrder().get(dishId);
        quantity += delta;
        if (quantity < 0)
            throw new IllegalStateException("No order");
        currentOrder.getDishesInOrder().put(dishId, quantity);
    }

    public int getQuantity(int dishId){
        Integer quantity = currentOrder.getDishesInOrder().get(dishId);
        if(quantity == null) return 0;
        return quantity;
    }

    public int getItemsInOrder(){
        int ret = 0;
        for (Integer value : currentOrder.getDishesInOrder().values()) {
            ret += value;
        }
        return ret;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
}
