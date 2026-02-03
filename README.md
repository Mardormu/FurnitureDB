Project Overview

Database: Oracle XE 21c
Database Tool: Oracle SQL Developer
Design Type: Relational database (tables, primary keys, foreign keys)
Java Application: Java Swing (Desktop UI)
Data Access: JDBC with DAO pattern
IDE: IntelliJ IDEA

Purpose: Manage furniture data and delivery operations

Database Structure
Tables

Client – Stores customer information

SystemUser – Stores system users (employees/admins)

Supplier – Stores supplier and delivery personnel data

Furniture – Stores furniture characteristics

Delivery – Connects clients, suppliers, and delivery details

Database Schema (Oracle SQL)
Client
CREATE TABLE Client (
    client_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    email VARCHAR2(100),
    phone VARCHAR2(20),
    address VARCHAR2(200)
);

SystemUser
CREATE TABLE SystemUser (
    user_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    email VARCHAR2(100),
    phone VARCHAR2(20)
);

Supplier
CREATE TABLE Supplier (
    supplier_id NUMBER PRIMARY KEY,
    region VARCHAR2(100),
    salary NUMBER
);

Furniture
CREATE TABLE Furniture (
    furniture_id NUMBER PRIMARY KEY,
    furniture_name VARCHAR2(100),
    furniture_type VARCHAR2(100),
    material VARCHAR2(100),
    color VARCHAR2(50),
    furniture_size VARCHAR2(50),
    weight NUMBER
);

Delivery
CREATE TABLE Delivery (
    delivery_id NUMBER PRIMARY KEY,
    client_id NUMBER,
    supplier_id NUMBER,
    delivery_address VARCHAR2(200),
    delivery_date DATE,
    CONSTRAINT fk_client
        FOREIGN KEY (client_id) REFERENCES Client(client_id),
    CONSTRAINT fk_supplier
        FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
);

Relationships

Client → Delivery (One-to-Many)

Supplier → Delivery (One-to-Many)

Foreign keys ensure referential integrity between related tables.

Java Application Overview

The Java application is built using Java Swing and follows the DAO (Data Access Object) pattern.

DAO Classes
ClientDAO
UserDAO
SupplierDAO
FurnitureDAO
DeliveryDAO

Each DAO provides:

add
getAll
delete

(update where applicable)
JOIN Queries (Reports)
The application uses SQL JOIN queries to display combined data.

Example: Delivery Report
SELECT d.delivery_id,
       c.name AS client_name,
       s.region AS supplier_region,
       d.delivery_address,
       d.delivery_date
FROM Delivery d
JOIN Client c ON d.client_id = c.client_id
JOIN Supplier s ON d.supplier_id = s.supplier_id;


This query combines data from:

Delivery
Client
Supplier
User Interface (Swing Panels)

Each table has a dedicated Swing panel that allows:

Viewing all records (JTable)
Adding new records
Deleting selected records
Refreshing table data
Panels include:
Client Panel
User Panel
Supplier Panel
Furniture Panel
Delivery Panel
Maven Configuration (pom.xml)

The project uses Maven for dependency management.

Required Dependency
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <version>21.9.0.0</version>
</dependency>
