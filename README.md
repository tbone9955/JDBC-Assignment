# JDBC-Assignment

## NoSQL Overview

NoSQL databases, also referred to as "No SQL," are designed to handle amounts of unstructured or semi-structured data with flexibility. They focus on scalability, performance, and adaptability rather than following the SQL database approach. These databases feature a schema design allowing for dynamic schema changes and easy horizontal scaling across nodes.

## Types of NoSQL Databases

### Document Oriented Databases

For instance MongoDB and Couchbase store data in JSON documents enabling dynamic schema changes.

### Key Value Stores

Examples include Redis and Amazon DynamoDB. They store data as key-value pairs to enhance data retrieval efficiency.

### Column Family Stores

Apache Cassandra and Apache HBase are examples that organize data into columns and rows for read and write operations.

### Graph Databases

Neo4j and Amazon Neptune are examples known for modeling relationships making them ideal for applications involving graph-based queries.

## Use Cases

NoSQL databases are suitable for applications dealing with volumes of data, real-time analytics needs, and high scalability requirements such as social media platforms and IoT systems. On the other hand, SQL databases excel in applications where defined schemas, complex transactions, and ACID compliance are crucial; examples include financial systems and ERP systems.

## Top NoSQL Databases and Their Characteristics

- **MongoDB**: Well-regarded for its adaptability and ability to scale, boasting document-oriented data storage and horizontal scalability via sharding.
  
- **Cassandra**: Highly esteemed for its reliability and resilience, providing scalability and adjustable levels of consistency.
  
- **Redis**: Recognized for its top-notch speed and flexibility offering, in-memory data storage capabilities, and accommodating a range of data structures.


## Setting Up and Running the Java Application

1. **Install MySQL Server:** Install MySQL Server from [MySQL website](https://www.mysql.com/).

2. **Install MySQL Connector/J:** Download the MySQL Connector/J (`mysql-connector-java-x.x.x.jar`) from the MySQL website or use a dependency management tool like Maven to include it in your project.

3. **Create Database and Table:** Using a MySQL client like MySQL Workbench or the command line, create a database named "GamesDB" and a table named "games" with the specified schema (`gameId`, `name`, `gameType`, `isMultiPlayer`).

4. **Set Up JDBC Connection:** In the Java application, ensure that the JDBC URL (`JDBC_CONNECTION_URL`), username (`USERNAME`), and password (`PASSWORD`) match your MySQL server configuration.

5. **Run the Application:** Compile the Java file (`JDBC Assignment.java`) and run the compiled class file. Make sure the MySQL server is running before executing the application.

6. **Interact with the Application:** Once the application is running, you can interact with it through the command-line interface. Follow the prompts to view, insert, update, or delete records from the "games" table in the "GamesDB" database.

7. **Clean Up Resources:** After using the application, ensure to close the JDBC resources properly in the `finally` block to release database connections, prepared statements, and result sets.

By following these steps, you can set up, compile, and run the Java application, allowing you to interact with a MySQL database using JDBC for various database operations.
