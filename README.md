# Restaurant Management System

I am currently in the process of developing a Cafe Management System that will simplify the order creation process for cafes and provide customers with a seamless experience. This ongoing project includes user roles for Admins and Customers, efficient category and product management, automated billing, and the ability to download and print bills.

**Admin Features:**

* Category and Product Management: Admins can manage categories, products, and their details to keep the menu up-to-date.
* User Management: Admins have control over user accounts, granting access and managing roles.
* Order Management: Admins can oversee and update orders for efficient cafe operations.

**Customer Features:**

* Order Creation: Customers can create orders by selecting items from the menu.
* Generated Bills: Upon order creation, the system generates a bill that can be downloaded and printed for reference.

**Technologies**

* Backend: JAVA JDK 11 , Spring Boot, Spring Data JPA, MySQL , Java Database Connectivity (JDBC) , Spring Security, REST API.
* Frontend: Angular, Bootstrap styling or Tailwind CSS.
* Authentication: JWT (JSON Web Tokens) for securing API endpoints.

**Project Progress**

* This project is currently under development, with ongoing work to create the following features:
* A user-friendly landing page for customers to create orders.
* A role-based system with two user categories: Admin and Customer.
* Order processing capabilities for both users.
* Automatic bill generation upon order creation.

**Installation**

1. Clone the repository to your local machine.
2. Create a MySQL database for the project and configure the database connection in the 'application.properties' file.
3. Build the project using Maven.
4. Build the project using Maven.
The API should now be running at 'http://localhost:8090.'

You can interact with the API's using tools like Postman or by integrating it into your frontend application.

**User API Endpoints and inputs**

* Signup new User: **POST /user/signup**
<br />   {"name":"", "contactNumber": "", "email":"", "password":""}
* Signin User: **POST /user/signin**
 <br />  {"email": "", "password": "" }
* Get all User: **GET /user/get**
* Update the status of the User: **POST /user/update**
  <br /> {"id":"", "status":""}
* Change Password: **POST /user/changePassword**
  <br /> {"oldPassword":"", "newPassword":""}
* Forgot password: **POST /user/forgotPassword**
  <br /> {"email":""} 

**Category API Endpoints and inputs** **_(Make sure you logged in as an admin)_**

* Add new category: **POST /category/add**
  <br />  { "categoryname": "" }
* Get all categories: **GET /category/get**
* Update the category name: **POST /category/update**
  <br /> {"id":"", "categoryname":""}

**Product API Endpoints and inputs** **_(Make sure you logged in as an admin)_**

* Add new product: **POST /product/add**
  <br />  {"categoryid":"", "productname":"", "description":"", "price":"" } **_category if a foreign key_**
* Get all product: **GET /product/get**
* Update Product **POST /product/update**
  <br />  {"categoryid":"", "productname":"", "description":"", "price":"", "pid":""} 


**Bill API Endpoints and inputs**

<br /><br />
![Desktop - 2](https://github.com/Bilal025/RestaurantManagementSystem/assets/95700674/994e61ea-6415-482a-a0aa-fff1807735fb)


<h1>Thank you</h1>


