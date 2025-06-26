package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.Map;

@Component
public class MySqlShoppingCart implements ShoppingCartDao
{
    private final JdbcTemplate jdbcTemplate;
    private final ProductDao productDao;

    @Autowired
    public MySqlShoppingCart(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getCart(int userId)
    {
        String sql = "SELECT product_id, quantity FROM shopping_cart WHERE user_id = ?";

        ShoppingCart cart = new ShoppingCart();

        jdbcTemplate.query(sql, (rs) -> {
            int productId = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");

            Product product = productDao.getById(productId);

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(quantity);

            cart.add(item);
        }, userId);

        return cart;
    }

    @Override
    public void addToCart(int userId, int productId)
    {
        String checkSql = "SELECT COUNT(*) FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId, productId);

        if (count != null && count > 0) {
            String updateSql = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(updateSql, userId, productId);
        } else {
            String insertSql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
            jdbcTemplate.update(insertSql, userId, productId);
        }
    }

    @Override
    public void updateCartItem(int userId, int productId, int quantity)
    {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, quantity, userId, productId);
    }

    @Override
    public void clearCart(int userId)
    {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}
