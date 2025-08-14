# Practical 2 - Student Management System

**Student:** ROSHAN ARYAL  
**Course:** Intermediate Application Development Concepts  
**Assignment:** Practical 2 - Java Database Connectivity with JavaFX  


## Project Overview

This project implements a complete Student Management System using Java, JavaFX, and MySQL database connectivity. The application provides a comprehensive GUI for managing three interconnected database tables: Students, ITPs (Institutes of Technology and Polytechnics), and Applications.

## Features Implemented

### ✅ All Required Operations (Assignment Requirements a-e):

**a. Retrieve Data**
- View all students, ITPs, and applications in organized tables
- Real-time data loading from MySQL database

**b. Insert New Records**
- Add new students with validation
- Add new ITPs with enrollment data
- Add new applications linking students to ITPs

**c. Delete Records**
- Delete selected students (with foreign key cascade)
- Delete ITPs and applications
- Confirmation and error handling

**d. Update Records**
- Edit existing student information
- Update ITP details and enrollment numbers
- Modify application status and details

**e. Custom Operations**
- **Students:** GPA range search and filtering
- **ITPs:** Filter by region with enrollment sorting
- **Applications:** Filter by acceptance status
- **Reports:** Statistical analysis and data insights

### 🎨 GUI Features

- **Tabbed Interface:** Separate tabs for Students, ITPs, Applications, and Reports
- **Table Views:** Sortable columns with selection handling
- **Form Controls:** Input validation and error messages
- **Search & Filter:** Multiple filtering options per table
- **Status Bar:** Real-time feedback on operations
- **Professional Design:** Clean, intuitive user interface

### 🗄️ Database Design

- **Student Table:** sID (PK), sName, GPA, sizeHS
- **ITP Table:** itpName (PK), region, enrollment
- **Apply Table:** sID, itpName, major (Composite PK), decision
- **Relationships:** Proper foreign key constraints with cascade delete

## Project Structure

```
src/main/java/com/itpassignment/
├── MainApplication.java              # Main JavaFX application
├── DatabaseConnection.java           # Singleton database connection
├── model/
│   ├── Student.java                 # Student entity class
│   ├── ITP.java                     # ITP entity class
│   └── Apply.java                   # Application entity class
├── dao/
│   ├── StudentDAO.java              # Student data access layer
│   ├── ITPDAO.java                  # ITP data access layer
│   └── ApplyDAO.java                # Application data access layer
├── controller/
│   └── StudentController.java       # FXML controller (alternative)
└── test/
    ├── DatabaseTest.java            # Database connectivity test
    ├── StudentDAOTest.java          # DAO testing
    └── ComprehensiveSystemTest.java # Complete system test
```

## Technologies Used

- **Java 21** - Core programming language
- **JavaFX 21** - Desktop GUI framework
- **MySQL 8.x** - Database management system
- **Maven** - Build and dependency management
- **JDBC** - Database connectivity

## Setup Instructions

### 1. Database Setup
1. Start XAMPP and run MySQL
2. Open phpMyAdmin or MySQL Workbench
3. Run the provided `database_setup.sql` script
4. Verify that MyDB database is created with sample data

### 2. Application Configuration
1. Update `DatabaseConnection.java` with your MySQL credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/MyDB";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "your_password";
   ```

### 3. Running the Application

**Option 1: Using Maven**
```bash
./mvnw javafx:run
```

**Option 2: Using IDE**
- Import as Maven project
- Run `MainApplication.java` as Java application

**Option 3: Command Line**
```bash
./mvnw clean compile
./mvnw exec:java -Dexec.mainClass="com.itpassignment.MainApplication"
```

## Testing

### Run All Tests
```bash
# Database connectivity test
java com.itpassignment.test.DatabaseTest

# DAO functionality test  
java com.itpassignment.test.StudentDAOTest

# Comprehensive system test
java com.itpassignment.test.ComprehensiveSystemTest
```

### Manual Testing Checklist
- [ ] Database connection successful
- [ ] All tables load with data
- [ ] CRUD operations work for all tables
- [ ] Search and filter functions work
- [ ] Error handling for invalid inputs
- [ ] GUI is responsive and intuitive

## Design Patterns & Best Practices

### ✅ Design Patterns Used
- **Singleton Pattern:** DatabaseConnection class
- **DAO Pattern:** Separate data access objects for each entity
- **MVC Pattern:** Separation of model, view, and controller logic

### ✅ Best Practices Implemented
- **Exception Handling:** Try-catch blocks with user-friendly error messages
- **Resource Management:** Proper connection and statement cleanup
- **Input Validation:** Number format validation and null checks
- **Code Organization:** Clear package structure and naming conventions
- **Documentation:** Comprehensive JavaDoc comments

## Sample Queries Demonstrated

1. **Student GPA Analysis**
   ```sql
   SELECT * FROM Student WHERE GPA BETWEEN 3.5 AND 4.0 ORDER BY GPA DESC;
   ```

2. **Regional ITP Distribution**
   ```sql
   SELECT * FROM ITP WHERE region = 'Auckland' ORDER BY enrollment DESC;
   ```

3. **Application Success Rate**
   ```sql
   SELECT COUNT(*) as Accepted FROM Apply WHERE decision = 'Y';
   ```

4. **Complex Join Query**
   ```sql
   SELECT s.sName, i.itpName, a.major, a.decision 
   FROM Student s, ITP i, Apply a 
   WHERE s.sID = a.sID AND i.itpName = a.itpName;
   ```

## Screenshots

The application includes:
- Multi-tab interface for different data tables
- Sortable table views with selection highlighting
- Form-based data entry with validation
- Search and filter capabilities
- Real-time status updates

## Submission Contents

### 📁 Project Files
- **Source Code:** Complete Maven project with all Java files
- **Database Script:** SQL setup file with sample data
- **Documentation:** This README and code comments
- **Screenshots:** Application interface images

### 📋 Assignment Compliance
- ✅ MySQL database with three tables
- ✅ Java program to query tables
- ✅ JavaFX GUI with all required operations (a-e)
- ✅ Additional custom operation (GPA range search)
- ✅ Professional design following best practices

## Future Enhancements

Potential improvements for extended versions:
- Advanced reporting with charts and graphs
- Data export functionality (CSV, PDF)
- User authentication and role-based access
- Advanced search with multiple criteria
- Backup and restore functionality

## Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Verify XAMPP MySQL is running
   - Check credentials in DatabaseConnection.java
   - Ensure MyDB database exists

2. **JavaFX Module Issues**
   - Ensure Java 21 is installed
   - Verify JavaFX dependencies in pom.xml
   - Use Maven to handle module path

3. **Table Not Displaying Data**
   - Check database has sample data
   - Verify DAO methods are working
   - Run DatabaseTest.java for diagnosis

## Contact

**Student:** ROSHAN ARYAL  
**Email:** aryalroshann@gmail.com
**Course:** - Intermediate Application Development Concepts  
