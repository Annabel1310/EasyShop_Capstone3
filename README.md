# 🛒 EasyShop Shopping Cart API

EasyShop is a Java Spring Boot-based RESTful API for managing a shopping cart in an e-commerce system. Users can browse products, add or update items in their cart, calculate totals, and clear their cart. The application connects to a MySQL database and uses Postman for testing endpoints.

---

## 📸 Screenshots

### 🛍️ Product Table in MySQL Workbench
Shows the structure and content of the `products` table.
![Products Table]
![Screenshot 2025-06-26 163534](https://github.com/user-attachments/assets/9c5b9362-73d5-48ac-bbb2-42dce17e55a0)

---

### 🔎 Searching Products in Postman
Filtering products by category and price using query parameters.
![Postman Search]
![Screenshot 2025-06-26 163512](https://github.com/user-attachments/assets/e54ee508-6e42-47df-9b49-da6d651ef57a)

---

### 💻 Application Running in IntelliJ
Project structure and console output after a successful Spring Boot startup.
![IntelliJ View]
![Screenshot 2025-06-26 170935](https://github.com/user-attachments/assets/f2345907-c404-4b5a-8a7e-40bb59e1e0fb)

---

## ✅ Features

- View all items in the current user's cart
- Add a product to the cart
- Update product quantity in the cart
- Clear the entire cart
- Calculate total cost with optional discount
- Secure user-based interaction using `Principal`

---

## 💡 Interesting Code: Cart Total Calculation

This method in the `ShoppingCart` model uses Java Streams and BigDecimal for precise total computation:

```java
public BigDecimal getTotal()
{
    BigDecimal total = items.values()
        .stream()
        .map(ShoppingCartItem::getLineTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return total;
}

