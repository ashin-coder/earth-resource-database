<h1 align="center" id="title">Earth Resource Database</h1>

![home](https://github.com/ashin-coder/earth-resource-database/assets/73836674/3c706419-37e7-4022-ab47-30e243c28731)

The Earth Resource Database is a simple Java-based software project which I developed as part of my Software Developer internship. It showcases a few of the skills in Java programming, database management, and enterprise-level application development which I developed as part of the internship. The project features a modular design, a user-friendly interface, and thorough testing and debugging processes. It connects to an SQLite database using JDBC, ensuring scalability and security. The system includes logging features for traceability and security purposes. Overall, the Earth Resource Database is a robust tool for managing data related to continents, countries, states, agricultural resources, and mineral resources

## About the Project

The Earth Resource Database is a mini-project developed using Java and enterprise-level development practices. It offers an intuitive user interface that allows users to perform all database activities, including insertion, deletion, and updating of records. The use of JDBC to connect to an SQLite database ensures that the database is scalable, secure, and reliable. One of the key strengths of the Earth Resource Database is its modular design. The codebase is split up into relevant classes, which makes it easy to maintain and expand. Additionally, the project has been thoroughly tested and debugged across the front-end and back-end, which ensures that it is robust and reliable. Another key feature of the Earth Resource Database is its user-friendly interface. The system is designed to make it easy for users to enter and manage data, store and display records. The intuitive interface also ensures that users can perform various database operations with ease, reducing the time needed to perform routine tasks. Furthermore, the Earth Resource Database incorporates logging features, which makes it easy to track user actions. This feature ensures that the system is secure, and user actions can be easily traced in case of any issues. The Earth Resource Database is a database management project that demonstrates a thorough understanding of enterprise-level Java development practices. Its modular design, user-friendly interface, and robust testing and debugging processes make it a valuable tool for managing data related to continents, countries, states, agricultural resources and mineral resources.

## Installation

To run the Earth Resource Database project locally, follow these steps:

1. Clone the repository:
 git clone https://github.com/ashin-coder/earth-resource-database.git

2. Make sure you have Java Development Kit (JDK) installed.

3. Install a Java IDE preferably IntelliJ IDEA to prevent any kind of incompatibilities since this project was developed in IntelliJ IDEA

4. To Install “DBJdbcService-1.0.jar”, First open “JDBCService” File in the IDE

5. Open Maven Tool Window: IntelliJ IDEA (IDE used by me) provides a dedicated Maven tool window that allows you to manage and execute Maven goals.

6. To open the Maven tool window, go to View -> Tool Windows -> Maven.

7. In the Maven tool window, you can navigate through the project's Maven lifecycle phases, execute clean, validate, compile and install

8. “DBJdbcService-1.0.jar” and “DBJdbcService-1.0.pom” files should be installed in "Drive:\Users\Name\.m2\repository\com\dbtransactions\DBJdbcService\1.0\DBJdbcService-1.0.jar"

9. Open “earth-resource-database-main” File in the IDE

10. Enable annotation processing, if asked in the IDE

11. To Setup Logging of User Activity into the external file “earth0.log” file, go to Run-> Edit Configurations…

12. In Run/Debug Configurations, under “Build and run” section, click on “Modify options”

13. In Modify options, click “Add VM options” under “Java” Section

14. In Run/Debug Configurations, under “Build and run” section, in “VM options” row enter
“-Djava.util.logging.config.file=Drive:/Folder Name/earth-resource-database-main/logger.properties”

15. Click “Apply” and then “OK”

16. Finally to Start the Application right click and run the “Main.java Class” from “earth-resource-database-main\Earth_GUI\src\main\java\org\earth\swing\ui”

**Please Note**: As Mentioned before this installation is based on "IntelliJ IDEA" IDE as it was developed in the same, Project may or may not work as expected in Other IDEs.

## Features

1. Data Insertion: Users can easily add new records to the database, such as continents, countries, states, agricultural resources, and mineral resources.
2. Data Deletion: The system allows users to remove existing records from the database when they are no longer needed.
3. Data Updating: Users can modify and update existing records, enabling them to keep the data accurate and up to date.
4. Data Viewing: Users can view data based on criteria such as continent, country, state and resource.
5. User-Friendly Interface: The interface is designed to be intuitive and user-friendly, making it easy for users to navigate, enter data, and perform various database operations.
6. Scalability: The use of JDBC and the modular design allow the database to handle large amounts of data efficiently and scale with growing requirements.
7. Logging: User actions are logged into an external file, allowing for traceability and the identification of potential issues or security breaches.

## Implementation

The Earth Resource Database project is developed using Java, incorporating enterprise-level development practices. With its modular design and adherence to best practices, it demonstrates a thorough understanding of Java programming, database management, and enterprise-level application development. This project is based on Maven, a build automation and dependency management tool for Java projects. The project's key strength lies in its ability to efficiently manage data related to continents, countries, states, agricultural resources and mineral resources.

Utilizing JDBC to connect to an SQLite database, the Earth Resource Database ensures scalability, security, and reliability. The choice of SQLite as the database management system underscores the project's commitment to these critical aspects. The use of industry-standard JDBC further enhances the system's compatibility and integration capabilities. In the project, a file called “JDBCService”, provided by my internship mentor, is utilized as a framework for performing database queries. This file contains multiple classes that serve different purposes based on the database requirements. Within the JDBCService file, queries are executed using the Query Executer, allowing for seamless interaction with the database. To enhance maintainability and security, SQL queries are written in a separate text file external to the code. The JDBCService project follows a Maven structure and can be installed using the Maven tool window, enabling navigation through the project's lifecycle phases and execution of various tasks like clean, validate, compile, and install. Specifically, the installation process copies the "DBJdbcService-1.0.jar" and "DBJdbcService-1.0.pom" files to the repository at "Drive:\Users\Name\.m2\repository\com\dbtransactions\DBJdbcService\1.0\DBJdbcService-1.0.jar". These files are utilized by the WorldDBUtils class in the Earth Resource Database project, which provides methods for executing the necessary queries on the tables present in the "earth_1.db" SQLite database file.

DB Browser for SQLite is a popular and user-friendly tool used to view, modify, test, and check changes made to the Earth Resource Database, which is based on an SQLite file. DB Browser provides a graphical interface that allows users to interact with the database directly, without needing to write SQL queries manually. With DB Browser, users can easily browse the database structure, view tables, query data, and make modifications. The tool also supports advanced features such as executing SQL commands, importing and exporting data, creating and managing database backups, and running custom queries for testing and validation purposes. DB Browser's intuitive interface and comprehensive set of features make it an excellent choice for efficiently managing and monitoring changes to the Earth Resource Database based on the SQLite file.

The project undergoes thorough testing and debugging to ensure its robustness and reliability, providing a seamless user experience. Logging features are incorporated to enhance security by tracking user actions and simplifying the debugging process. The project is focused on delivering a reliable and secure database solution with a seamless user interface.

The Earth Resource Database boasts a user-friendly interface that simplifies data entry, record management, and report generation. Users can seamlessly perform various database activities, including inserting, deleting, updating and viewing existing records. This intuitive interface minimizes the time required for routine tasks, improving overall efficiency. The user interface of the Earth Resource Database is developed using Java Swing, a powerful GUI toolkit for Java applications. Java Swing's rich set of components and features are utilized to create an intuitive and visually appealing interface, allowing users to easily interact with and manipulate data. The versatility and flexibility of Java Swing contribute to the development of a robust and user-friendly environment, enhancing the overall experience of managing and visualizing Earth resource data.

The Earth Resource Database exemplifies a quality project developed with enterprise-level Java practices. Its modular design, user-friendly interface and testing and debugging processes contribute to its value as a comprehensive tool for managing diverse data related to continents, countries, states, agricultural resources, and mineral resources. In adherence to coding conventions, I have tried to follow proper file naming conventions, class documentation, and method comments. These practices ensure clarity and maintainability in the codebase, making it easier for developers to understand and work with the project.

**Please Note**: The JDBCService file used in this project is authored and provided by my internship mentor, who is also known as [@creativehummers](https://github.com/creativehummers)
 on GitHub. I do not claim ownership over this file and it is solely the work of my internship mentor.

## Project Screenshots

A few Screenshots of the Application while in use are given below :

* Home Page
![home](https://github.com/ashin-coder/earth-resource-database/assets/73836674/d9b4557a-ab91-418a-a918-0c9305e69422)

* Insertion Page
![image](https://github.com/ashin-coder/earth-resource-database/assets/73836674/d9e1bc32-4697-4cae-b569-67471d8c0d82)

* Update Page
![update](https://github.com/ashin-coder/earth-resource-database/assets/73836674/f71c2f79-2cc1-472c-abeb-85bb62489714)

* Delete Page
![delete](https://github.com/ashin-coder/earth-resource-database/assets/73836674/181dad99-4f91-4b2f-981a-34d55c481d48)

* View Tables Page
![view_tables](https://github.com/ashin-coder/earth-resource-database/assets/73836674/1fc3a9fb-7641-407e-8dd0-7e97c4be95b1)

* View Table's Data Page
![view_table_data](https://github.com/ashin-coder/earth-resource-database/assets/73836674/aee0fb24-a750-4e73-ab1c-96173555de89)

* View the Population of a given Country based on the State's Population
![population_country](https://github.com/ashin-coder/earth-resource-database/assets/73836674/d2403a80-bde8-4876-9b23-3933474b40d2)

* View No of States in a given country producing given agriculture resource
![agri_states](https://github.com/ashin-coder/earth-resource-database/assets/73836674/7e7170a5-875a-4b24-8ef9-985cbc4f3055)

* View No of States in a given country producing given mineral resource
![mineral_states](https://github.com/ashin-coder/earth-resource-database/assets/73836674/f16c9faa-5d53-418b-a09e-55697625716d)

* User Action and Events Logged into External File 
![logs](https://github.com/ashin-coder/earth-resource-database/assets/73836674/b482a492-8ec6-4356-8891-a2e14bfe1d2f)

* Database Queries are written in External File
![queries](https://github.com/ashin-coder/earth-resource-database/assets/73836674/e8debebd-ed0b-412e-91f1-4b50b91bc7ab)

## Acknowledgments

I would like to extend my heartfelt gratitude to all the individuals and organizations who have helped me in the successful development of the Earth Resource Database project. Special thanks to my internship mentor, who is known by the username [@creativehummers](https://github.com/creativehummers) on GitHub, for his guidance, support, and the provision of the JDBCService file, which played a critical role in implementing the database functionality. Additionally, I would like to acknowledge the creators and contributors of the technologies and resources utilized in this project, including Java, Java Swing, SQLite, Maven and others. Their dedication to developing and maintaining these powerful tools has greatly facilitated the project's execution and delivery. I am truly grateful for their efforts and the opportunity to leverage their expertise in creating this software application.

## Project Disclaimer: For Demonstration Purposes Only

**Please Note**: The Project provided here is for demonstration purposes only and may contain bugs or glitches. It is important to understand that this implementation may require further development and refinement before it can be considered suitable for real-world applications. As a final project for my software developer internship, I created and shared this project to demonstrate the knowledge and skills I acquired during the internship. The intention behind sharing this project is to provide a starting point and showcase the potential of the concepts and technologies used, with the encouragement for users to further enhance and improve it according to their specific needs and requirements.

Feel free to contribute, modify, or build upon this project to make it better and more robust. Your feedback, bug reports, and suggestions for improvement are highly appreciated. 

## Note on Data Used in the Project Implementation

**Please Note**: The data which includes project resources and other information including the data already present in the database used in this project is solely intended for demonstration purposes and is not owned by the project contributors. It is important to acknowledge that some of the data utilized may not be accurate or up to date and also include data sourced from the internet. It is essential to exercise caution when interpreting or relying on the information presented in Project Implementation.

We strongly encourage users to seek authorized and reliable sources for the most accurate and current data in their respective fields. The purpose of this project is to showcase the functionality and capabilities of the Application, and it should not be considered a reliable source of data.




