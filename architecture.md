# Project Architecture

## 1. Overview
The Railway Reservation System is a Java desktop application built using Swing and MySQL.

## 2. Technology Stack
- Frontend: Java Swing
- Backend: Java
- Database: MySQL
- Connectivity: JDBC
- Build Tool: Maven
- IDE: Visual Studio Code

## 3. Architecture Diagram

Passenger
   |
   v
Java Swing Dashboard
   |
   v
Java Application Logic
   |
   v
JDBC Database Connection
   |
   v
MySQL Database

## 4. Main Modules
- Train Management: Display train details and available seats.
- Ticket Booking: Book a selected train for a journey date.
- PNR Generation: Generate a unique booking reference.
- My Tickets: Display reservation details.
- Ticket Cancellation: Cancel eligible confirmed reservations.
- Database Connection: Connect Java to MySQL.

## 5. Database Tables
- passengers: Stores passenger details.
- trains: Stores train numbers, names, routes, timings, seat capacity, and fares.
- reservations: Stores PNR, passenger ID, train ID, journey date, seat number, and reservation status.

## 6. Booking Flow
1. Passenger selects a train.
2. Passenger enters the journey date.
3. Application validates the input.
4. Application checks seat availability.
5. Application saves the reservation in MySQL.
6. Application displays the generated PNR.

## 7. Important Design Decision
The application opens directly to the dashboard. A login page is not included in the current design.
