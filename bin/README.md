# ADMU Lost and Found Web Service

Built with Spring Boot to serve the Ateneo Community.

## Setup Instructions

1. Ensure MySQL and Java 8+ is installed on your machine. Afterwards, create the `lostandfound` user using the following commands in the MySQL Shell:

```shell
CREATE USER 'lostandfound'@'localhost' IDENTIFIED BY 'testpassword123';
GRANT ALL PRIVILEGES ON * . * TO 'lostandfound'@'localhost';
```
2. Afterwards, create an `admulostandfound` database using the following command in the MySQL Shell:
```shell
CREATE DATABASE admulostandfound;
```
3. Now you can successfully build and run the project on your machine. :)