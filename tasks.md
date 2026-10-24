# Tasks and Progress

## Project Setup
- [x] Create Java Maven project.
- [x] Configure MySQL database.
- [x] Configure JDBC dependency.
- [x] Create the Java Swing dashboard.

## Main Features
- [x] Display available trains.
- [x] Create the ticket booking workflow.
- [x] Generate PNR numbers.
- [x] Create the ticket cancellation workflow.
- [ ] Test ticket booking with MySQL.
- [ ] Test cancellation and reservation status updates.
- [ ] Verify seat availability for each journey date.
- [ ] Verify ticket ownership and passenger association.
- [ ] Test invalid journey dates.
- [ ] Improve error handling where required.

## Documentation
- [x] prd.md - Product requirements.
- [x] architecture.md - Project architecture.
- [x] rules.md - Coding rules.
- [x] design.md - UI/UX direction.
- [x] tasks.md - Tasks and progress.
- [ ] memory.md - Project context.

## Important Decisions
- [x] Keep the blue-and-white dashboard.
- [x] Do not add a login page.
- [ ] Decide how passengers are identified without login.

## Build Verification
Run this command after code changes:

    mvn clean compile

Only mark a task complete after verifying that it works.
