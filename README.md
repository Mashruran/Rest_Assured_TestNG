# DAILY-FINANCE-ASSIGNMENT
## 📘 Project Overview
### This project automates API testing for the Daily Finance Application using Rest Assured. The automation suite covers user registration, admin login, user management, and item operations (CRUD). All tests follow the Page Object Model (POM) architecture and include both positive and negative test scenarios.
## 🧩 Topic Name
### Rest Assured
## 🌐 Application URL
### 🔗 https://dailyfinance.roadtocareer.net/
## 🧪 Features Automated
### Register a New User
- Create a new user
- Create user with only mandatory fields
- Create existing user (negative test)
### Login by Admin
- Admin login with valid credentials
- Admin login with invalid credentials (negative test)
- Fetch user list
- Search user by ID
- Search user with invalid ID (negative test)
- Update user info (e.g. first name, phone number)
### Login by Any User
- User login
- Get item list
- Add a new item
- Edit an existing item
- Delete an item
## ⚙️ Tech Stack
- Language: Java
- Framework: TestNG
- Library: Rest Assured
- Build Tool: Gradle
- Reporting: Allure Report
- Design Pattern: Page Object Model (POM)

## 🧾 Postman Collection
👉 [Postman Collection Link](https://documenter.getpostman.com/view/47948566/2sBXVcnDrw)

## 🧰 Test Cases
👉 [Test Case Documentation Link](https://docs.google.com/spreadsheets/d/1NchoUeJwxNLqPWkrRxaWuZRWbwXB5R2V/edit?gid=1561792322#gid=1561792322)
## 📊 Allure Report
<img width="1079" height="802" alt="image" src="https://github.com/user-attachments/assets/a4f7b768-f91b-46d7-8950-bdb2f7266cb9" />
<img width="1070" height="732" alt="image" src="https://github.com/user-attachments/assets/c7de6300-67f5-4c10-a914-cdd351d732b7" />

## 🚀 How to Run Tests
- ```https://github.com/Mashruran/Rest_Assured_TestNG```
- ```gradle clean test```
- ```allure serve allure-results```






