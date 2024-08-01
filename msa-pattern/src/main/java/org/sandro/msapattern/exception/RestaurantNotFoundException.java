package org.sandro.msapattern.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(long restaurantId) {
        super("Restaurant not found with id " + restaurantId);
    }
}