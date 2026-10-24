# Project Memory

## Project Name
Railway Reservation System

## Project Overview
A Java desktop application for viewing trains, booking tickets, generating PNR numbers, viewing reservations, and cancelling tickets.

## Technology Stack
- Programming Language: Java
- User Interface: Java Swing
- Database: MySQL 8.4
- Database Connectivity: JDBC
- Build Tool: Maven
- IDE: Visual Studio Code
- Main Class: com.railway.App

## Project Location
D:\RailwayReservationSystem\railway-reservation-system

## Important Files
- src/main/java/com/railway/App.java - Main application code
- pom.xml - Maven configuration
- prd.md - Product requirements
- architecture.md - Project architecture
- rules.md - Coding rules
- design.md - UI/UX direction
- tasks.md - Tasks and progress
- memory.md - Project context

## Database
Database name: railway_reservation

Tables:
- passengers
- trains
- reservations

## Sample Trains
- 12123 Deccan Express: Pune to Mumbai, fare Rs. 250.
- 12124 Deccan Express: Mumbai to Pune, fare Rs. 250.
- 11029 Koyna Express: Mumbai to Pune, fare Rs. 300.

## Build and Run
Compile:
mvn clean compile

Run:
mvn exec:java "-Dexec.mainClass=com.railway.App" "-Dexec.classpathScope=runtime"

## Database Password
The application uses the RAILWAY_DB_PASSWORD environment variable.
Never store the actual password in source code or documentation.

## UI Preferences
- Blue-and-white dashboard.
- Simple and beginner-friendly layout.
- No login page.
- Main actions: Refresh Trains, Book Ticket, My Tickets, Cancel Ticket.

## Important Development Notes
- Inspect the current App.java before modifying the application.
- Keep database table and column names consistent with SQL queries.
- Verify database operations before marking features complete.
- Passenger ownership must be handled safely for ticket viewing and cancellation.
- Update tasks.md when features are implemented and tested.
- Give step-by-step instructions when making changes.

## Known Pending Work
- Verify passenger association for each booking.
- Test seat availability and journey date validation.
- Test ticket cancellation and My Tickets.
- Run mvn clean compile after code changes.
