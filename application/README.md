# Rental Home Robot Database Application

## Project Overview

This project is a Java command-line application for managing a robot rental system. It uses SQLite for database storage and JDBC for database connectivity.

The application includes customer management, robot management, rental operations, and six SQL reports.

## Requirements

Before running the application, make sure you have the following installed:

- Java JDK 17 or newer
- Apache Maven
- Visual Studio Code
- Extension Pack for Java (recommended VS Code extension)

## How to Run in Visual Studio Code

### 1. Download and Open the Project

1. Download the project ZIP from GitHub and extract it.
2. Open Visual Studio Code.
3. Select **File → Open Folder**.
4. Select the extracted `Rental-Home-Robot` folder.
5. Open a terminal in VS Code using **Terminal → New Terminal**.

### 2. Verify Java and Maven

Run the following commands to confirm Java and Maven are installed:

```bash
java -version
mvn -version
```

### 3. Navigate to the Application Folder

From the project's root directory, run:

```bash
cd application
```

### 4. Compile the Application

Run:

```bash
mvn clean compile
```

Maven will download the necessary dependencies, including the SQLite JDBC driver, and compile the Java source files.

A successful compilation should display `BUILD SUCCESS`.

### 5. Run the Application

For macOS or Linux:

```bash
mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
java -cp "target/classes:$(cat classpath.txt)" App
```

For Windows PowerShell:

```powershell
mvn dependency:build-classpath "-Dmdep.outputFile=classpath.txt"
$classpath = Get-Content classpath.txt
java -cp "target/classes;$classpath" App
```

The application will launch in the terminal and display the main menu.

## Application Features

The main menu includes:

1. Manage Customer
2. Manage Robot
3. Manage Driverless Car
4. Manage Rental
5. Useful Reports
0. Exit

Follow the prompts in the terminal to navigate through the application.

## Database Information

The application uses the SQLite database located at:

`database/Rental-Home-Robot-CH4.db`

The database is included in the project, so users do not need to create it manually.

**Important:** Run the application from inside the `application` directory because the database connection uses a relative file path.

## Troubleshooting

- **Database connection error:** Confirm that the database file exists in the `database` folder and that the application is running from the `application` directory.
- **Compilation error:** Verify that Java and Maven are installed correctly.
- **Invalid input:** Follow the requested input formats, including valid existing model names and facility IDs when adding robots.