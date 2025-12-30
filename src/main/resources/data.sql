-- Insert sample users
INSERT INTO users (username, email, phone, address, created_at, updated_at) VALUES
                                                                                ('john_doe', 'john@example.com', '1234567890', '123 Main St', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                ('jane_smith', 'jane@example.com', '0987654321', '456 Oak Ave', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                ('bob_johnson', 'bob@example.com', '5555555555', '789 Pine Rd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                ('alice_williams', 'alice@example.com', '4444444444', '321 Elm St', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                ('charlie_brown', 'charlie@example.com', '3333333333', '654 Maple Dr', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample products
INSERT INTO products (name, description, price, stock, created_at, updated_at) VALUES
                                                                                   ('Laptop', 'High-performance laptop', 999.99, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                   ('Mouse', 'Wireless mouse', 29.99, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                   ('Keyboard', 'Mechanical keyboard', 79.99, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                   ('Monitor', '27-inch 4K monitor', 399.99, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                   ('Headphones', 'Noise-canceling headphones', 199.99, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);