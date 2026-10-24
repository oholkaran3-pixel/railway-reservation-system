# Product Requirements Document (PRD)

## Project Name
Railway Reservation System

## Objective
Develop a Java desktop application that allows passengers to view trains, book tickets, generate PNR numbers, view their tickets, and cancel reservations.

## Technology Stack
- Java
- Java Swing
- MySQL
- JDBC
- Maven
- VS Code

## Core Features
1. Display available trains.
2. Show train numbers, names, routes, departure times, arrival times, available seats, and fares.
3. Allow passengers to select a train and journey date.
4. Book tickets and generate a unique PNR.
5. Display booked tickets.
6. Allow eligible tickets to be cancelled.
7. Store passenger, train, and reservation information in MySQL.
8. Display meaningful validation and database error messages.

## User Interface
- Blue-and-white theme.
- Simple Java Swing dashboard.
- Easy-to-use buttons and tables.
- No login page, as requested.

## Database
Database name: railway_reservation

Tables:
- passengers
- trains
- reservations

## Security and Reliability
- Use PreparedStatement for database queries.
- Keep database passwords outside source code.
- Validate journey dates and user inputs.
- Use transactions for ticket booking.
- Ensure ticket cancellation cannot affect another passenger's reservation.

## Success Criteria
- Application compiles and runs.
- Train information loads from MySQL.
- Valid bookings generate PNR numbers.
- Seat capacity is respected.
- Ticket viewing and cancellation work correctly.
