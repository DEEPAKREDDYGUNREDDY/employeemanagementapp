# Employee Management System

A RESTful backend application for managing employee data with full CRUD operations and secure authentication. Built with Spring Boot 4 and MySQL for reliable data persistence.

---

##  Features

- **Employee CRUD** — Create, read, update, and delete employee records
- **Authentication** — Secure login to protect employee data
- **REST API** — Clean RESTful endpoints for all operations
- **Data Validation** — Input validation to ensure data integrity
- **Persistent Storage** — MySQL database with JPA/Hibernate ORM

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Framework** | Spring Boot 4 |
| **Language** | Java 25 |
| **ORM** | Spring Data JPA / Hibernate |
| **Database** | MySQL |
| **Utilities** | Lombok |
| **Build Tool** | Maven |

---

##  Project Structure

```
EmployeeManagementSys/
├── src/
│   └── main/
│       ├── java/com/example/
│       │   ├── controller/        # REST API endpoints
│       │   ├── model/             # Entity classes (Employee)
│       │   ├── repository/        # JPA repositories
│       │   ├── service/           # Business logic
│       │   └── config/            # Security & auth configuration
│       └── resources/
│           └── application.properties  # DB config & server settings
├── pom.xml                        # Maven dependencies
├── mvnw / mvnw.cmd                # Maven wrapper
└── README.md
```

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/login` | Authenticate user |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create new employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

---

## Getting Started

### Prerequisites

- **Java** 25+
- **MySQL** 8+
- **Maven** 3.8+

### Setup

```bash
# 1. Clone the repository
git clone https://github.com/DEEPAKREDDYGUNREDDY/EmployeeManagementSys.git
cd EmployeeManagementSys

# 2. Create the MySQL database
mysql -u root -p -e "CREATE DATABASE employee_db;"

# 3. Configure database connection
# Edit src/main/resources/application.properties:
# spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
# spring.datasource.username=root
# spring.datasource.password=yourpassword
# spring.jpa.hibernate.ddl-auto=update

# 4. Run the application
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

---

##  Sample Request

**Create Employee:**
```json
POST /api/employees
Content-Type: application/json

{
  "name": "Deepak Reddy",
  "email": "deepak@example.com",
  "department": "Engineering",
  "designation": "Software Developer",
  "salary": 75000
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Deepak Reddy",
  "email": "deepak@example.com",
  "department": "Engineering",
  "designation": "Software Developer",
  "salary": 75000
}
```

---

## 👤 Author

**Deepak Reddy Gunreddy**

- GitHub: [@DEEPAKREDDYGUNREDDY](https://github.com/DEEPAKREDDYGUNREDDY)
