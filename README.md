# 🚗 Parking Manager API

REST API for managing parking lots with registration of establishments, vehicles, and control of vehicle entry and exit.

## 🔧 Technologies Used

* Java 17
* Spring Data JPA
* Spring Security (JWT)
* PostgreSQL
* Gradle
* JUnit 5 (testing)
* Swagger

## ✅ Features

* **Establishments**

  - [x] Create, list, update, and delete
  - [x] Manage parking spots for motorcycles and cars

* **Vehicles**

  - [x] Create, list, update, and delete
  - [x] Register brand, model, color, license plate, and type (car or motorcycle)

* **Movements**

  - [x] Register vehicle entry and exit
  - [x] Validate availability of spots based on vehicle type

* **Reports (coming soon)**

  - [x] Total number of entries and exits
  - [x] Entries and exits per hour

* **Authentication**

  - [ ] Login with JWT
  - [ ] Secured endpoints with token-based authentication
 
* **Documentation**
  - [x] Swagger API Documentation

## ▶️ How to Run

```bash
git clone https://github.com/your-username/parking-manager-api.git
cd parking-manager-api
./gradlew spring-boot:run
```

Access:

* API: `http://localhost:8080/api`
* Documentation (Swagger): `http://localhost:8080/v1/api/swagger-ui/index.html` 
