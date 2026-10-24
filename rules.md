# Coding Rules

## Java
1. Use meaningful class, method, and variable names.
2. Follow Java naming conventions.
3. Keep methods small and focused.
4. Handle exceptions properly.
5. Keep the existing blue-and-white dashboard design.

## Database
6. Use PreparedStatement for SQL queries containing user input.
7. Use try-with-resources to close database connections and statements.
8. Never hard-code database passwords.
9. Read the database password from the RAILWAY_DB_PASSWORD environment variable.
10. Use transactions for ticket booking.
11. Validate data before inserting it into MySQL.
12. Check ticket ownership before cancellation.

## Project Structure
13. Keep Java source files in src/main/java/com/railway/.
14. Keep the main class named App.
15. Do not change database table or column names without updating the related code.

## Testing
16. Compile after code changes using:
    mvn clean compile
17. Test train listing, booking, PNR generation, and cancellation.
18. Verify database changes after each important operation.
19. Do not mark a feature complete until it has been tested.

## Security
20. Never store database passwords in source code or documentation.
21. Do not use raw SQL concatenation with user-provided values.
