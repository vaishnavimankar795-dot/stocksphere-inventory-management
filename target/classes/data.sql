-- Sample data for testing
INSERT INTO companies (name) VALUES ('TechCorp Inc.');
INSERT INTO companies (name) VALUES ('Retail Solutions Ltd.');

INSERT INTO warehouses (name, company_id) VALUES ('Main Warehouse', 1);
INSERT INTO warehouses (name, company_id) VALUES ('Secondary Warehouse', 1);
INSERT INTO warehouses (name, company_id) VALUES ('Central Hub', 2);

INSERT INTO suppliers (name, contact_email) VALUES ('Electronics Supplier Co.', 'contact@electronicsupplier.com');
INSERT INTO suppliers (name, contact_email) VALUES ('Global Parts Ltd.', 'sales@globalparts.com');

INSERT INTO products (name, sku, price, threshold) VALUES ('Laptop Computer', 'LAPTOP-001', 999.99, 5);
INSERT INTO products (name, sku, price, threshold) VALUES ('Wireless Mouse', 'MOUSE-001', 29.99, 20);
INSERT INTO products (name, sku, price, threshold) VALUES ('Keyboard', 'KEYBOARD-001', 79.99, 15);

-- Link products to suppliers
INSERT INTO supplier_products (supplier_id, product_id) VALUES (1, 1);
INSERT INTO supplier_products (supplier_id, product_id) VALUES (1, 2);
INSERT INTO supplier_products (supplier_id, product_id) VALUES (2, 3);

-- Create inventory with some low stock scenarios
INSERT INTO inventories (product_id, warehouse_id, quantity) VALUES (1, 1, 3); -- Low stock
INSERT INTO inventories (product_id, warehouse_id, quantity) VALUES (2, 1, 15); -- Low stock
INSERT INTO inventories (product_id, warehouse_id, quantity) VALUES (3, 1, 25); -- Normal stock
INSERT INTO inventories (product_id, warehouse_id, quantity) VALUES (1, 2, 50); -- Normal stock
