#  GoGreen Billing & Inventory Management System

**GoGreen** is a Java Swing-based desktop application designed for **Billing and inventory management** for small businesses. It allows employees to manage products, generate bills, update inventory, and print receipts seamlessly.  

---

##  Features

- **Employee Login**:  
- **Product Management**: View all products, descriptions, prices, and stock quantity.  
- **Billing System**:  
  - Generate payment receipts 
  - Add multiple items with quantities  
  - Calculate total amount automatically  
  - Print receipts  
- **Inventory Updates**: Product quantities automatically updated after billing.  
- **User-Friendly UI**: Easy-to-use interface with buttons, forms, and tables.  
- **Logout & Home Navigation**: Quick access to home screen or logout.  

---

##  Technologies Used

- **Language**: Java  
- **GUI**: Java Swing  
- **Database**: MySQL  
- **JDBC**: MySQL Connector/J for database communication  

---

##  Steps to run the Application:
1) Clone the repository.
2) Open the project in IntelliJ IDEA or Eclipse.
3) Add your MySQL database credentials in **db_connection/MySQLConnection.java**.
4) Ensure your database has a table **itemlist** with the following columns:
<table>
  <tr>
    <th>Field</th>
    <th>Type</th>
  </tr>
  <tr>
    <td>id</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Productname</td>
    <td>varchar(255)</td>
  </tr>
  <tr>
    <td>Description</td>
    <td>text</td>
  </tr>
  <tr>
    <td>Quantity</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Price</td>
    <td>int</td>
  </tr>
</table>

  Also another table named **orderedlist** with following columns:
  <table>
  <tr>
    <th>Field</th>
    <th>Type</th>
  </tr>
  <tr>
    <td>id</td>
    <td>int</td>
  </tr>
  <tr>
    <td>PaymentDate</td>
    <td>varchar(255)</td>
  </tr>
  <tr>
    <td>ProductName</td>
    <td>varchar(255)</td>
  </tr>
  <tr>
    <td>Price</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Quantity</td>
    <td>int</td>
  </tr>
  <tr>
    <td>OrderId</td>
    <td>varchar(255)</td>
  </tr>
  <tr>
    <td>TotalPrice</td>
    <td>int</td>
  </tr>
</table>

5) Run the MainScreen.java class to start the application. (note : currently cred are hardcoded as its just a demonstration)
Hardcoded credentials=> Admin-> username: Admin, password: Admin3187     and for Employee-> username: Emp1,  Password: Emp1admin

---

## 🛠️ Database Connection

Database connectivity is handled in the `db_connection` package.  
> **Important:** This file is **ignored in Git** (`.gitignore`) to protect sensitive credentials.  

Here’s the **template with placeholders** for your own credentials:

```java
package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    
    private static String ConnURL = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
    private static String username = "YOUR_DB_USERNAME";
    private static String password = "YOUR_DB_PASSWORD";
    
    private static String forNameClassVar = "com.mysql.cj.jdbc.Driver";
    
    public static Connection DBConn() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(ConnURL, username, password);
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }
}

```
---
Structure: 
GoGreen/
├── emp/                
├── admin/               
├── db_connection/       
├── .gitignore           
└── README.md
