# WorkPortal
## Overview
WorkPortal is a Management System designed for managing Users and Managers.

It offers essential features such as registration, login, user profile display, access to exclusive Managerial Code of Conduct pages, and logout functionality.
Managers have the option to view the Managerial Code of Conduct in either English or French languages.

## Prerequisites
- Java 17 or later.
- Git.
- IntelliJ IDEA.
- MySQL, and MySQL Workbench.

## Setup
1. Clone this repository.
2. Open the `IntelliJ IDE`.
3. Select `Open`.
4. Navigate to the location of the cloned repository cloned.
5. Create a `schema` (e.g., work_portal) on the MySQL Workbench.
6. Update application.properties in src/main/resources/:
   - Line 5: Set `spring.datasource.url=jdbc:mysql://localhost:3306/name-of-your-schema`.
   - Line 6: Set `spring.datasource.username=name-of-your-mysql-username`.
   - Line 7: Set `spring.datasource.username=name-of-your-mysql-password`.

## Instructions
1. Right-click the WorkPortalApplication class file (Location: src/main/java/com/example/WorkPortal/WorkPortalApplication.java)
2. If there issues with running the application:
   - Click the `Maven icon` on the right
   - Navigate to `WorkPortal/Lifecycle`
   - Click `Clean`, then `Install`
   - Drop all tables in the schema and repeat Step 1.
3. Enter `localhost:9001/` in your browser after seeing the message `Started WorkPortalApplication` in the `console`.
4. Explore the application.

## Tech Stack
- Programming: Java
- Frameworks/Tools: Spring Boot, JUnit, Git, HTML, CSS, MySQL.
