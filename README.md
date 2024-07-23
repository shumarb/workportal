# WorkPortal
## Overview
WorkPortal is a Management System designed for managing Users and Managers efficiently.
It offers essential features such as registration, login, user profile display, access to exclusive Managerial Code of Conduct pages, and logout functionality.

## Prerequisites
- Java 17 or later.
- Git.
- IntelliJ IDEA.
- MySQL, and MySQL Workbench.

## Setup
1. Clone this repository.
2. Open the `IntelliJ IDE`.
3. Select `Open`.
4. Navigate to the location of the repository cloned in step 2 and select it.
5. Create a `schema` in the MySQL Workbench.
6. Update `lines 5, 6, and 7` of the `application.properties` in the `src/main/resources/` folder with the `schema name in Step 5`, and the `Username` and `Password` of your `MySQL Workbench respectively.

## Instructions
1. Right-click the WorkPortalApplication class file (Location: src/main/java/com/example/WorkPortal/WorkPortalApplication.java)
2. If there issues with running the application, click the `Maven icon` on the right, navigate to `WorkPortal/Lifecycle`, click `Clean`, followed by `Install`, drop all tables in the schema defined earlier, and repeat Step 1.
3. Enter `localhost:9001/` in your local machine's browser after seeing the message `Started WorkPortalApplication` in the `console`.
4. Navigate through the application.

## Tech Stack
- Programming: Java
- Frameworks/Tools: Git, Spring Boot, JUnit, HTML/CSS, MySQL.
