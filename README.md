# 🚗 Parking Manager API

REST API for managing parking lots with registration of establishments, vehicles, and control of vehicle entry and exit.

## 🔧 Technologies Used

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security (JWT)
* PostgreSQL
* Gradle
* JUnit 5 (testing)

## ✅ Features

* **Establishments**

  * Create, list, update, and delete
  * Manage parking spots for motorcycles and cars

* **Vehicles**

  * Create, list, update, and delete
  * Register brand, model, color, license plate, and type (car or motorcycle)

* **Movements**

  * Register vehicle entry and exit
  * Validate availability of spots based on vehicle type

* **Reports (coming soon)**

  * Total number of entries and exits
  * Entries and exits per hour

* **Authentication**

  * Login with JWT
  * Secured endpoints with token-based authentication

## ▶️ How to Run

```bash
git clone https://github.com/your-username/parking-manager-api.git
cd parking-manager-api
./gradlew spring-boot:run
```

Access:

* API: `http://localhost:8080/api`
