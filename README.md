# WorkPortal
## Overview
WorkPortal is a Management System designed for management of Users and Managers.

It offers essential features such as registration, login, user profile display, access to exclusive Managerial Code of Conduct pages, and logout functionality.
Managers have the option to view the Managerial Code of Conduct in either English or French languages.

## Prerequisites
- Java 17 or later.
- Git.
- IntelliJ IDEA.
- MySQL, and MySQL Workbench.

## Setup
1. `Clone` this repository.
2. Open the `IntelliJ IDE`.
3. Select `Open` and navigate to the location of the cloned repository.
4. Create a `schema` on the MySQL Workbench.
5. Update `application.properties` in the `src/main/resources` folder:
   - Line 5: Set `spring.datasource.url=jdbc:mysql://localhost:3306/name-of-your-schema`.
   - Line 6: Set `spring.datasource.username=name-of-your-mysql-username`.
   - Line 7: Set `spring.datasource.username=name-of-your-mysql-password`.

## Instructions
1. Right-click the `WorkPortalApplication` class file (Location: `src/main/java/com/example/WorkPortal/WorkPortalApplication.java`).
2. Select `Run 'WorkPortalAppl....main()`.
3. If there issues with running the application, perform the following before repeating Step 1:
   - Click the `Maven icon` on the right.
   - Navigate to `WorkPortal/Lifecycle`.
   - Click `Clean`, then `Install`.
   - Drop all tables in the schema.
   - Close and reopen IntelliJ IDEA.
4. Enter `localhost:9001/` in your browser after seeing the message `Started WorkPortalApplication` in the `console`.
5. Explore the application.
6. To see the stored information of registered users:
   - Right-click the `schema` on the MySQL Workbench.
   - Select the `>` icon next to `Tables`. You will see the `Person`, `Manager`, and `User` tables.
   - The `Person` table includes `id`, `email`, `name`, `username`, `password`, and `role` of registered users.
   - The `Manager` table includes the `id` of registered users with the `role` of `Manager`.
   - The `User` table includes the `id` of registered users with the `role` of `User`.

## Tech Stack
- Programming: Java.
- Frameworks/Tools: Spring Boot, JUnit, Git, HTML, CSS, MySQL.
