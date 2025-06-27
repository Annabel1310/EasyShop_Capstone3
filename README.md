# 🛒 EasyShop Shopping Cart API
EasyShop is a robust, full-featured Java Spring Boot-powered RESTful API that brings the convenience and efficiency of modern online shopping right into your development environment. Tailored for e-commerce platforms, this API empowers users to seamlessly manage their shopping experience—from browsing products to calculating their final checkout total with precision.
Whether you're building a small boutique storefront or scaling to a high-traffic retail platform, EasyShop provides the foundational components needed to support a secure, user-specific shopping cart system. Backed by a MySQL database and tested thoroughly using Postman, this project serves as both a practical template and a hands-on learning tool for developers exploring Spring Boot, REST architecture, and databa

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

## ## ✨ Features

### 👤 User-Based Cart Management
- Each cart is tied to a specific logged-in user using Spring Security's `Principal`.
- Only the authenticated user can access and modify their own cart.

### 🛒 Add Products to Cart
- Users can add products to their cart using `POST /cart/products/{productId}`.
- If the product is already in the cart, the quantity is automatically increased.

### ✏️ Update Product Quantity
- Users can update the quantity of a product in their cart using `PUT /cart/products/{productId}`.
- The updated quantity is saved directly in the database.

### 🧹 Clear the Entire Cart
- `DELETE /cart` removes all products from the current user's cart.
- Helpful for simulating a "checkout" or "cancel order" action.

### 📦 Product Data Integration
- Product details (name, price, etc.) are automatically pulled into each `ShoppingCartItem` via `ProductDao`.
- `getProduct()` provides direct access to full product info per item in the cart.

### 💵 Total Calculation with Discounts
- The `ShoppingCart.getTotal()` method calculates a subtotal using:
  - Product price × quantity
  - Applied discount percentage (if any)
- Uses `BigDecimal` for precision in currency calculations.

### 🔍 Product Search (Bonus Feature via Product Controller)
- Supports filtering products using query parameters like:
  - `category`
  - `priceMin` / `priceMax`
  - `color`
  - `keyword`

### 🔐 Secure REST Endpoints
- Uses Spring Security to protect all endpoints.
- Each user can only access and manipulate their own cart.

### 🧪 API Tested via Postman
- Each endpoint was tested using Postman with real-time JSON inputs and responses.
- Screenshots included in this repo show working Postman calls.

### 💽 Relational Database Design
- MySQL database with foreign key constraints to enforce referential integrity:
  - `shopping_cart.user_id → users.user_id`
  - `shopping_cart.product_id → products.product_id`


---
### 🔗 API Endpoints
| Method | URL                   | Description             |
| ------ | --------------------- | ----------------------- |
| GET    | `/cart`               | Get current user's cart |
| POST   | `/cart/products/{id}` | Add product to cart     |
| PUT    | `/cart/products/{id}` | Update product quantity |
| DELETE | `/cart`               | Clear cart              |



## 💡 Interesting Code: Cart Total Calculation

This method in the `ShoppingCart` model uses Java Streams and BigDecimal for precise total computation:
![Screenshot 2025-06-26 190304](https://github.com/user-attachments/assets/521b6cc3-8ed2-4cf0-8140-3876051aa246)

### ✅ Reasons:

Uses BigDecimal for accurate money calculations

Clean, modern code with Java Streams

Applies discount logic per item

Follows OOP by separating responsibilities



