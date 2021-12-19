CREATE DATABASE IF NOT EXISTS salesDB;

USE salesDB;

CREATE TABLE IF NOT EXISTS sellers
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    document VARCHAR(8)   NOT NULL,
    name     VARCHAR(255) NOT NULL,
    phone    VARCHAR(255),
    status   BIT DEFAULT 1,
    userName VARCHAR(8)
);

CREATE TABLE IF NOT EXISTS clients
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    document VARCHAR(8)   NOT NULL,
    name     VARCHAR(255) NOT NULL,
    address  VARCHAR(244),
    status   BIT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS products
(
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(255) NOT NULL,
    price  DOUBLE,
    stock  INTEGER,
    status BIT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS orders
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    clientId  INTEGER,
    sellerId  INTEGER,
    FOREIGN KEY (clientId) REFERENCES clients (id),
    FOREIGN KEY (sellerId) REFERENCES sellers (id),
    orderCode VARCHAR(244),
    orderDate DATE,
    total     DOUBLE,
    status    BIT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS orders_details
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    orderId   INTEGER,
    productId INTEGER,
    FOREIGN KEY (orderId) REFERENCES orders (id),
    FOREIGN KEY (productId) REFERENCES products (id),
    quantity  INTEGER,
    price     DOUBLE
);

USE salesdb;


SELECT*
FROM sellers;

INSERT INTO sellers(id, `document`, `name`, `phone`, `status`, `userName`)
VALUES (null, '76870404', 'Ramon Valdez', '974632122', true, '76870404');

SELECT*
FROM clients;

INSERT INTO clients(id, `document`, `name`, `address`, `status`)
VALUES (null, '22870404', 'Ana Mena', 'Jr. Chinchon 123', true);

INSERT INTO clients(id, `document`, `name`, `address`, `status`)
VALUES (null, '65870404', 'Sofia Terrones', 'Jr. Alfonso Ugarte 1222', true);

SELECT*FROM products;
SELECT*FROM clients;
SELECT*FROM sellers;
SELECT*FROM orders;
SELECT*FROM orders_details;
SELECT MAX(orderCode) FROM orders;

UPDATE products SET stock =? WHERE id=?;


SELECT c.name,o.orderCode,o.orderDate,CAST(o.total AS DECIMAL(6,2)) AS total FROM orders o
    JOIN clients c
    ON o.clientId=c.id
WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE()) AND MONTH(o.orderDate) = MONTH(CURRENT_DATE());


SELECT p.name, SUM(od.quantity) AS quantity,CAST(SUM(od.price*od.quantity) AS DECIMAL(6,2)) AS price FROM orders_details od
JOIN products p
ON od.productId = p.id
JOIN orders o
ON od.orderId = o.id
WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE()) AND MONTH(o.orderDate) = MONTH(CURRENT_DATE())
GROUP BY od.productId ORDER BY quantity DESC;


SELECT * FROM orders_details;

SELECT
       (@row_num := @row_num + 1) AS id,
       p.name,
       SUM(od.quantity) AS quantity,
       CAST(SUM(od.price*od.quantity) AS DECIMAL(6,2)) AS price
FROM orders_details od
    JOIN products p
        ON p.id = od.productId
    JOIN orders o
        ON od.orderId = o.id
        CROSS JOIN (select @row_num := 0) r
        WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE()) AND MONTH(o.orderDate) = MONTH(CURRENT_DATE())
GROUP BY od.productId;


SELECT * FROM orders_details;
SELECT * FROM orders;
SELECT SUM(quantity) FROM orders_details WHERE orderId!=1 GROUP BY productId;
SELECT*FROM products;
SELECT quantity FROM orders_details WHERE productId = 3;

SELECT SUM(quantity) FROM orders_details GROUP BY productId;

DELETE FROM orders WHERE id=1;

SELECT c.name,orderDate,orderCode,total FROM orders o JOIN clients c
ON o.clientId=c.id WHERE o.id=2;

SELECT
       (@row_num := @row_num + 1) AS id,
       p.name,
       od.quantity,
       od.price,
       CAST(od.price*od.quantity AS DECIMAL(6,2)) AS total
FROM orders_details od
    LEFT JOIN products p
        ON p.id = od.productId
        CROSS JOIN (select @row_num := 0) r
        WHERE od.orderId = 3;

SELECT
       (@row_num := @row_num + 1) AS id,
       p.name,
       od.quantity,
       od.price,
       CAST(od.price*od.quantity AS DECIMAL(6,2)) AS total
FROM orders_details od
    LEFT JOIN products p
        ON p.id = od.productId
        CROSS JOIN (select @row_num := 0) r
        WHERE od.orderId = 9

SELECT*FROM orders_details WHERE orderId=9