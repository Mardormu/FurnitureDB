-- ===========================
-- Таблица Client
-- ===========================
CREATE TABLE Client (
  client_id NUMBER(10) NOT NULL,
  name NVARCHAR2(100),
  email NVARCHAR2(100),
  phone NVARCHAR2(20),
  address NVARCHAR2(255),
  PRIMARY KEY(client_id)
);

-- ===========================
-- Таблица User
-- ===========================
CREATE TABLE SystemUser (
  user_id NUMBER(10) NOT NULL,
  name NVARCHAR2(100),
  email NVARCHAR2(100),
  phone NVARCHAR2(20),
  PRIMARY KEY(user_id)
);


-- ===========================
-- Таблица Product
-- ===========================
CREATE TABLE Product (
  product_id NUMBER(10) NOT NULL,
  price NUMBER(10,2),
  quantity NUMBER(10),
  PRIMARY KEY(product_id)
);

-- ===========================
-- Таблица OrderTable
-- ===========================
CREATE TABLE OrderTable (
  order_id NUMBER(10) NOT NULL,
  client_id NUMBER(10),
  order_date DATE,
  PRIMARY KEY(order_id)
);

-- Връзка Order → Client
ALTER TABLE OrderTable
  ADD CONSTRAINT fk_order_client
  FOREIGN KEY(client_id)
  REFERENCES Client(client_id);

-- ===========================
-- Таблица Payment (1:1 с Order)
-- ===========================
CREATE TABLE Payment (
  payment_id NUMBER(10) NOT NULL,
  order_id NUMBER(10),
  bank NVARCHAR2(100),
  method NVARCHAR2(50),
  PRIMARY KEY(payment_id)
);

-- Връзка Payment → Order
ALTER TABLE Payment
  ADD CONSTRAINT fk_payment_order
  FOREIGN KEY(order_id)
  REFERENCES OrderTable(order_id);

-- ===========================
-- Таблица Warehouse
-- ===========================
CREATE TABLE Warehouse (
  warehouse_id NUMBER(10) NOT NULL,
  location NVARCHAR2(100),
  capacity NUMBER(10),
  PRIMARY KEY(warehouse_id)
);

-- ===========================
-- Таблица Supplier
-- ===========================
CREATE TABLE Supplier (
  supplier_id NUMBER(10) NOT NULL,
  region NVARCHAR2(100),
  salary NUMBER(10,2),
  PRIMARY KEY(supplier_id)
);

-- ===========================
-- Таблица Furniture
-- ===========================
CREATE TABLE Furniture (
  furniture_id NUMBER(10) NOT NULL,
  color NVARCHAR2(50),
  furniture_size NVARCHAR2(50),    -- променено от size
  material NVARCHAR2(50),
  furniture_type NVARCHAR2(50),
  weight NUMBER(10,2),
  furniture_name NVARCHAR2(100),
  PRIMARY KEY(furniture_id)
);


ALTER TABLE Supplier_Furniture
  ADD CONSTRAINT fk_sf_supplier
  FOREIGN KEY(supplier_id)
  REFERENCES Supplier(supplier_id);

ALTER TABLE Supplier_Furniture
  ADD CONSTRAINT fk_sf_furniture
  FOREIGN KEY(furniture_id)
  REFERENCES Furniture(furniture_id);

-- ===========================
-- Таблица Delivery
-- ===========================
CREATE TABLE Delivery (
  delivery_id NUMBER(10) NOT NULL,
  client_id NUMBER(10),
  supplier_id NUMBER(10),
  delivery_address NVARCHAR2(255),
  delivery_date DATE,
  PRIMARY KEY(delivery_id)
);

-- Връзки Delivery → Client и Supplier
ALTER TABLE Delivery
  ADD CONSTRAINT fk_delivery_client
  FOREIGN KEY(client_id)
  REFERENCES Client(client_id);

ALTER TABLE Delivery
  ADD CONSTRAINT fk_delivery_supplier
  FOREIGN KEY(supplier_id)
  REFERENCES Supplier(supplier_id);

-- ===========================
-- Таблица Production
-- ===========================
CREATE TABLE Production (
  production_id NUMBER(10) NOT NULL,
  furniture_id NUMBER(10),
  quantity NUMBER(10),
  start_date DATE,
  end_date DATE,
  PRIMARY KEY(production_id)
);

-- Връзка Production → Furniture
ALTER TABLE Production
  ADD CONSTRAINT fk_production_furniture
  FOREIGN KEY(furniture_id)
  REFERENCES Furniture(furniture_id);

-- ===========================
-- Междинна таблица Order_Product (M:N)
-- ===========================
CREATE TABLE Order_Product (
  order_id NUMBER(10),
  product_id NUMBER(10),
  PRIMARY KEY(order_id, product_id)
);

ALTER TABLE Order_Product
  ADD CONSTRAINT fk_op_order
  FOREIGN KEY(order_id)
  REFERENCES OrderTable(order_id);

ALTER TABLE Order_Product
  ADD CONSTRAINT fk_op_product
  FOREIGN KEY(product_id)
  REFERENCES Product(product_id);

-- ===========================
-- Междинна таблица Warehouse_Furniture (M:N)
-- ===========================
CREATE TABLE Warehouse_Furniture (
  warehouse_id NUMBER(10),
  furniture_id NUMBER(10),
  PRIMARY KEY(warehouse_id, furniture_id)
);

ALTER TABLE Warehouse_Furniture
  ADD CONSTRAINT fk_wf_warehouse
  FOREIGN KEY(warehouse_id)
  REFERENCES Warehouse(warehouse_id);

ALTER TABLE Warehouse_Furniture
  ADD CONSTRAINT fk_wf_furniture
  FOREIGN KEY(furniture_id)
  REFERENCES Furniture(furniture_id);

-- ===========================
-- Междинна таблица Supplier_Furniture (M:N)
-- ===========================
CREATE TABLE Supplier_Furniture (
  supplier_id NUMBER(10),
  furniture_id NUMBER(10),
  PRIMARY KEY(supplier_id, furniture_id)
);

ALTER TABLE Supplier_Furniture
  ADD CONSTRAINT fk_sf_supplier
  FOREIGN KEY(supplier_id)
  REFERENCES Supplier(supplier_id);

ALTER TABLE Supplier_Furniture
  ADD CONSTRAINT fk_sf_furniture
  FOREIGN KEY(furniture_id)
  REFERENCES Furniture(furniture_id);
  
  
  INSERT ALL
INTO SystemUser (user_id, name, email, phone) VALUES (2, 'Teodor Slavov', 'teodor.slavov@example.com', '0888000002')
INTO SystemUser (user_id, name, email, phone) VALUES (3, 'Adrian Petrov', 'adrian.petrov@example.com', '0888000003')
INTO SystemUser (user_id, name, email, phone) VALUES (4, 'Stanislav Konstantinov', 'stanislav.kon@example.com', '0888000004')
INTO SystemUser (user_id, name, email, phone) VALUES (5, 'Martina Stilianova', 'martina.stilianova@example.com', '0888000005')
INTO SystemUser (user_id, name, email, phone) VALUES (6, 'Ivan Kolev', 'ivan.kolev@example.com', '0888000006')
INTO SystemUser (user_id, name, email, phone) VALUES (7, 'Maria Petrova', 'maria.petrova@example.com', '0888000007')
INTO SystemUser (user_id, name, email, phone) VALUES (8, 'Georgi Ivanov', 'georgi.ivanov@example.com', '0888000008')
INTO SystemUser (user_id, name, email, phone) VALUES (9, 'Elena Dimitrova', 'elena.dimitrova@example.com', '0888000009')
INTO SystemUser (user_id, name, email, phone) VALUES (10, 'Dimitar Stoyanov', 'dimitar.stoyanov@example.com', '0888000010')
INTO SystemUser (user_id, name, email, phone) VALUES (11, 'Petya Nikolova', 'petya.nikolova@example.com', '0888000011')
SELECT 1 FROM DUAL;

INSERT ALL
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (2, 'Blue', 'Large', 'Metal', 'Table', 25.5, 'Dining Table')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (3, 'Green', 'Small', 'Wood', 'Chair', 10.0, 'Office Chair')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (4, 'Black', 'Medium', 'Plastic', 'Shelf', 15.0, 'Bookshelf')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (5, 'White', 'Large', 'Wood', 'Wardrobe', 50.0, 'Bedroom Wardrobe')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (6, 'Brown', 'Medium', 'Wood', 'Desk', 20.0, 'Study Desk')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (7, 'Gray', 'Small', 'Metal', 'Chair', 12.0, 'Visitor Chair')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (8, 'Red', 'Medium', 'Plastic', 'Shelf', 14.0, 'Kitchen Shelf')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (9, 'Yellow', 'Large', 'Wood', 'Table', 30.0, 'Conference Table')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (10, 'Purple', 'Small', 'Wood', 'Chair', 9.5, 'Kids Chair')
INTO Furniture (furniture_id, color, furniture_size, material, furniture_type, weight, furniture_name) VALUES (11, 'Orange', 'Medium', 'Metal', 'Rack', 18.0, 'Storage Rack')
SELECT 1 FROM DUAL;

INSERT ALL
INTO Client (client_id, name, email, phone, address) VALUES (2, 'Anna Ivanova', 'anna.ivanova@example.com', '0888010002', 'ул. Пирин 5, София')
INTO Client (client_id, name, email, phone, address) VALUES (3, 'Boris Petrov', 'boris.petrov@example.com', '0888010003', 'ул. Витоша 12, София')
INTO Client (client_id, name, email, phone, address) VALUES (4, 'Viktor Dimitrov', 'viktor.dimitrov@example.com', '0888010004', 'ул. Русе 3, Русе')
INTO Client (client_id, name, email, phone, address) VALUES (5, 'Elena Georgieva', 'elena.georgieva@example.com', '0888010005', 'ул. Левски 20, Пловдив')
INTO Client (client_id, name, email, phone, address) VALUES (6, 'Stefan Stoyanov', 'stefan.stoyanov@example.com', '0888010006', 'ул. Черни Връх 7, София')
INTO Client (client_id, name, email, phone, address) VALUES (7, 'Maria Nikolova', 'maria.nikolova@example.com', '0888010007', 'ул. Раковски 15, София')
INTO Client (client_id, name, email, phone, address) VALUES (8, 'Georgi Ivanov', 'georgi.ivanov@example.com', '0888010008', 'ул. Черно море 2, Варна')
INTO Client (client_id, name, email, phone, address) VALUES (9, 'Petya Petrova', 'petya.petrova@example.com', '0888010009', 'ул. Стара планина 9, Плевен')
INTO Client (client_id, name, email, phone, address) VALUES (10, 'Dimitar Kolev', 'dimitar.kolev@example.com', '0888010010', 'ул. Орфей 1, Пловдив')
INTO Client (client_id, name, email, phone, address) VALUES (11, 'Teodora Vasileva', 'teodora.vasileva@example.com', '0888010011', 'ул. Марица 8, Пловдив')
SELECT 1 FROM DUAL;


INSERT ALL
INTO OrderTable (order_id, client_id, order_date) VALUES (2, 2, TO_DATE('2026-02-01','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (3, 3, TO_DATE('2026-02-02','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (4, 4, TO_DATE('2026-02-03','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (5, 5, TO_DATE('2026-02-04','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (6, 6, TO_DATE('2026-02-05','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (7, 7, TO_DATE('2026-02-06','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (8, 8, TO_DATE('2026-02-07','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (9, 9, TO_DATE('2026-02-08','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (10, 10, TO_DATE('2026-02-09','YYYY-MM-DD'))
INTO OrderTable (order_id, client_id, order_date) VALUES (11, 11, TO_DATE('2026-02-10','YYYY-MM-DD'))
SELECT 1 FROM DUAL;


INSERT ALL
INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (1, 2, 10, TO_DATE('2026-01-01','YYYY-MM-DD'), TO_DATE('2026-01-10','YYYY-MM-DD'))
INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (2, 3, 20, TO_DATE('2026-01-05','YYYY-MM-DD'), TO_DATE('2026-01-15','YYYY-MM-DD'))
INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (3, 4, 15, TO_DATE('2026-01-07','YYYY-MM-DD'), TO_DATE('2026-01-17','YYYY-MM-DD'))
INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (4, 5, 5, TO_DATE('2026-01-10','YYYY-MM-DD'), TO_DATE('2026-01-20','YYYY-MM-DD'))
INTO Production (production_id, furniture_id, quantity, start_date, end_date) VALUES (5, 6, 12, TO_DATE('2026-01-12','YYYY-MM-DD'), TO_DATE('2026-01-22','YYYY-MM-DD'))
SELECT 1 FROM DUAL;


INSERT ALL
INTO Warehouse (warehouse_id, location, capacity) VALUES (1, 'Sofia North', 100)
INTO Warehouse (warehouse_id, location, capacity) VALUES (2, 'Plovdiv Central', 150)
INTO Warehouse (warehouse_id, location, capacity) VALUES (3, 'Varna East', 80)
SELECT 1 FROM DUAL;


INSERT ALL
INTO Product (product_id, price, quantity) VALUES (1, 50.0, 100)
INTO Product (product_id, price, quantity) VALUES (2, 120.0, 50)
INTO Product (product_id, price, quantity) VALUES (3, 80.0, 70)
INTO Product (product_id, price, quantity) VALUES (4, 200.0, 30)
INTO Product (product_id, price, quantity) VALUES (5, 15.0, 150)
INTO Product (product_id, price, quantity) VALUES (6, 40.0, 120)
INTO Product (product_id, price, quantity) VALUES (7, 60.0, 90)
INTO Product (product_id, price, quantity) VALUES (8, 75.0, 80)
INTO Product (product_id, price, quantity) VALUES (9, 100.0, 60)
INTO Product (product_id, price, quantity) VALUES (10, 250.0, 20)
SELECT 1 FROM DUAL;


INSERT ALL
INTO Supplier (supplier_id, region, salary) VALUES (1, 'Sofia', 3000)
INTO Supplier (supplier_id, region, salary) VALUES (2, 'Plovdiv', 2800)
INTO Supplier (supplier_id, region, salary) VALUES (3, 'Varna', 2900)
INTO Supplier (supplier_id, region, salary) VALUES (4, 'Burgas', 2700)
INTO Supplier (supplier_id, region, salary) VALUES (5, 'Ruse', 2600)
SELECT 1 FROM DUAL;


INSERT ALL
INTO Supplier (supplier_id, region, salary) VALUES (1, 'Sofia', 3000)
INTO Supplier (supplier_id, region, salary) VALUES (2, 'Plovdiv', 2800)
INTO Supplier (supplier_id, region, salary) VALUES (3, 'Varna', 2900)
INTO Supplier (supplier_id, region, salary) VALUES (4, 'Burgas', 2700)
INTO Supplier (supplier_id, region, salary) VALUES (5, 'Ruse', 2600)
SELECT 1 FROM DUAL;


INSERT ALL
INTO Payment (payment_id, order_id, bank, method) VALUES (1, 2, 'DSK Bank', 'Credit Card')
INTO Payment (payment_id, order_id, bank, method) VALUES (2, 3, 'UniCredit', 'Cash')
INTO Payment (payment_id, order_id, bank, method) VALUES (3, 4, 'Raiffeisen', 'Transfer')
INTO Payment (payment_id, order_id, bank, method) VALUES (4, 5, 'PostBank', 'Credit Card')
INTO Payment (payment_id, order_id, bank, method) VALUES (5, 6, 'DSK Bank', 'Transfer')
INTO Payment (payment_id, order_id, bank, method) VALUES (6, 7, 'UniCredit', 'Cash')
INTO Payment (payment_id, order_id, bank, method) VALUES (7, 8, 'Raiffeisen', 'Credit Card')
INTO Payment (payment_id, order_id, bank, method) VALUES (8, 9, 'PostBank', 'Transfer')
INTO Payment (payment_id, order_id, bank, method) VALUES (9, 10, 'DSK Bank', 'Credit Card')
INTO Payment (payment_id, order_id, bank, method) VALUES (10, 11, 'UniCredit', 'Cash')
SELECT 1 FROM DUAL;

INSERT ALL
INTO Order_Product (order_id, product_id) VALUES (2,1)
INTO Order_Product (order_id, product_id) VALUES (2,2)
INTO Order_Product (order_id, product_id) VALUES (3,3)
INTO Order_Product (order_id, product_id) VALUES (3,4)
INTO Order_Product (order_id, product_id) VALUES (4,5)
INTO Order_Product (order_id, product_id) VALUES (4,6)
INTO Order_Product (order_id, product_id) VALUES (5,7)
INTO Order_Product (order_id, product_id) VALUES (5,8)
INTO Order_Product (order_id, product_id) VALUES (6,9)
INTO Order_Product (order_id, product_id) VALUES (7,10)
SELECT 1 FROM DUAL;

INSERT ALL
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (1,2)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (1,5)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (2,3)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (2,6)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (3,4)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (3,7)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (4,8)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (5,9)
INTO Supplier_Furniture (supplier_id, furniture_id) VALUES (5,10)
SELECT 1 FROM DUAL;

INSERT ALL
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (1,2)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (1,3)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (2,4)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (2,5)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (3,6)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (3,7)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (1,8)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (2,9)
INTO Warehouse_Furniture (warehouse_id, furniture_id) VALUES (3,10)
SELECT 1 FROM DUAL;


DELETE FROM Product
WHERE product_id = 1;

SELECT * 
FROM Product
WHERE price > 50;


DELETE FROM Payment
WHERE payment_id = 1;

SELECT * 
FROM Payment
WHERE method = 'Credit Card';


DELETE FROM Delivery
WHERE delivery_id = 1;

SELECT * 
FROM Delivery
WHERE delivery_date > TO_DATE('2026-02-05','YYYY-MM-DD');


DELETE FROM Supplier
WHERE supplier_id = 1;

SELECT * 
FROM Supplier
WHERE region = 'Sofia';

)
DELETE FROM Supplier_Furniture
WHERE supplier_id = 1 AND furniture_id = 2;

SELECT * 
FROM Supplier_Furniture
WHERE supplier_id = 1;


DELETE FROM Warehouse_Furniture
WHERE warehouse_id = 2 AND furniture_id = 3;

SELECT * 
FROM Warehouse_Furniture
WHERE warehouse_id = 2;


DELETE FROM Order_Product
WHERE order_id = 3 AND product_id = 5;

SELECT * 
FROM Order_Product
WHERE order_id = 3;


DELETE FROM Production
WHERE production_id = 1;

SELECT * 
FROM Production
WHERE quantity > 20;


DELETE FROM Warehouse
WHERE warehouse_id = 1;

SELECT * 
FROM Warehouse
WHERE capacity > 50;
