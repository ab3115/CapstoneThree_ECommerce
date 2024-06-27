package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart shoppingCart = new ShoppingCart();

        String sql = "SELECT p.*, quantity " +
                " FROM products AS p " +
                " JOIN shopping_cart AS s " +
                "   ON p.product_id = s.product_id " +
                " WHERE user_id = ?";

        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1, userId);

            try(ResultSet row = preparedStatement.executeQuery();)
            {
                while(row.next())
                {
                    Product product = MySqlProductDao.mapRow(row);
                    int quantity = row.getInt("quantity");
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(quantity);
                    shoppingCart.add(shoppingCartItem);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }




    @Override
    public ShoppingCartItem add(int userId, ShoppingCartItem shoppingCartItem) {
        return null;
    }

    @Override
    public void update(int userId, ShoppingCartItem shoppingCartItem) {

    }

    @Override
    public void delete(int userId) {

    }
}
