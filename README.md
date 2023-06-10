# Todo-List-App

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

## Table of Contents

- [Abstract](#abstract)
- [Get Started](#get-started)
- [Features](#features)

## Abstract

This repository contains a todo application that allows users to create and manage tasks. Tasks can be assigned tags and projects for better organization and categorization.

## Get Started

Before using the application, please follow these steps to set up the necessary prerequisites and configure the environment.

### Prerequisites

1. **Install SQLite 3**: Ensure that you have SQLite 3 installed on your machine. If it's not already installed, please visit the [SQLite website](https://www.sqlite.org/index.html) and follow the installation instructions for your operating system.

### Database setup

1. **Create an SQLite Database**: Create a local SQLite database file using the following command in your command-line interface:

```bash
sqlite3 /path/to/your/database.db
```

Replace `/path/to/your/database.db` with the desired path and filename for your SQLite database. For example:

```bash
sqlite3 ~/Desktop/oop-db/db.db
```

This command will open the SQLite prompt and create a new SQLite database file at the specified path.

**Result:**
You should see the SQLite prompt indicating that the database file has been created successfully. For example:

```bash
SQLite version 3.36.0 2021-06-18 18:36:39
Enter ".help" for usage hints.
sqlite>
```

### Application configuration

1. **Add `AppConfig.properties` File**: In the root directory of the project, create a file named AppConfig.properties. This file will hold the application configuration properties.
2. **Specify Database URL**: Open the `AppConfig.properties file and add the following property:

```properties
db.url=jdbc:sqlite:/path/to/your/database.db
```

Replace /path/to/your/database.db with the actual path to your SQLite database file. For example:

```properties
db.url=jdbc:sqlite:/Users/username/Desktop/oop-db/db.db
```

### Database schema and seeding

1. **Execute the Schema Script**: Locate the `schema.sql script under the `/src/scripts/ directory. Execute this script using the following command in your command-line interface:

```bash
sqlite3 /path/to/your/database.db < /path/to/schema.sql
```

Replace `/path/to/your/database.db with the path to your SQLite database file, and `/path/to/schema.sql with the path to the `schema.sql script. For example:

```bash
sqlite3 ~/Desktop/oop-db/db.db < ~/Desktop/oop-app/src/scripts/schema.sql
```

This command will execute the `schema.sql script and create the necessary database schema.

**Result:**
If there are no errors, the script will execute successfully, and you will see the command prompt return without any output.

2. **Optional: Seed the database**: If you want to populate the database with initial data, locate the `seed.sql` script under the `/src/scripts/` directory. Execute this script using the following command in your command-line interface:

```bash
sqlite3 /path/to/your/database.db < /path/to/seed.sql
```

Replace `/path/to/your/database.db` with the path to your SQLite database file, and `/path/to/seed.sql` with the path to the seed.sql script. For example:

```bash
sqlite3 ~/Desktop/oop-db/db.db < ~/Desktop/oop-app/src/scripts/seed.sql
```

This command will execute the `seed.sql script and insert the seed data into the database.

**Result:**
If there are no errors, the script will execute successfully, and you will see the command prompt return without any output.

## Features

- Task management: Users can create, view, update, and delete tasks.
- Tag management: Users can assign tags to tasks for categorization.
- Project management: Users can assign projects to tasks for better organization.
- Menus: The application has menus for easy navigation and interaction.
- Input/Output control: An Io Controller handles input and output operations.


## FeatureList

| Feature                                      | Description                                                                                                         |
|----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Create a task                                | Allows the user to create a new task with a title and an optional description.                                       |
| Get a task                                   | Retrieves and displays the details of a specific task by providing its ID.                                           |
| Add a tag to a task                          | Associates a tag with a specific task, allowing for categorization or organization.                                 |
| Show all open tasks                          | Displays a list of all open tasks, including their IDs, titles, descriptions, and associated tags.                  |
| Create a project                             | Enables the user to create a new project with a name.                                                                |
| Get a project                                | Retrieves and displays the details of a specific project by providing its ID.                                        |
| Add a task to a project                      | Associates a task with a specific project, allowing for task organization within projects.                          |
| Get tasks of a project                       | Retrieves and displays a list of tasks associated with a specific project, including their IDs, titles, descriptions, and associated tags. |
| Create a tag                                 | Creates a new tag with a name and an optional description.                                                          |
| Get a tag                                    | Retrieves and displays the details of a specific tag by providing its ID.                                            |
| Add a tag to a task                          | Associates a tag with a specific task, allowing for categorization or organization.                                 |
