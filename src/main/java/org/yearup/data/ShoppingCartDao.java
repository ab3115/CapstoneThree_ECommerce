package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCartItem add(int userId, ShoppingCartItem shoppingCartItem);

    void update(int userId, ShoppingCartItem shoppingCartItem);

    void delete(int userId);
}
