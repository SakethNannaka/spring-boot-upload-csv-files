# Spring Boot Upload/Download CSV Files with MySQL database example

For more detail, please visit:
> [Spring Boot: Upload CSV file to MySQL Database | Multipart File](https://saketh.com/spring-boot-upload-csv-file/)

## Run Spring Boot application
```
mvn spring-boot:run
```
The Postman API collection has been added.
could have added procs in the database, but that would involve storing them, which would be troublesome while you were running them hence used direct queries in dao.
Before this application can be executed, mysql server must be started.This can be done using xampp or a local MySQL server.
Logs would be generated at location = /var/temp/demo.log