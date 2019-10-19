
--------------------------------
PRE-REQUESTS
---------------------------------

1. Java
2. Gradle

--------------------------------
Project Setup
--------------------------------

1. extract project to folder
2. configure database, 
  (a) create database -  byteworks_order_db
  (b) open src/main/resources/application.properties and change database username and password
  (c) import dummy data (src/main/resources/dummy-data.sql) - this is optional

3. Configure mail (Optional)
   open src/main/resources/application.properties change smtp details to your preferred method
4. Install dependencies and build project; from the extracted folder, run the command  "./gradlew build" 
5. Run project using the command "java -jar build/libs/byteworks-rest-service-0.1.0.jar" 

--------------------------------
API Documentation
--------------------------------
https://documenter.getpostman.com/view/990966/SVtbPjdt?version=latest


NOTE for windows users
This project uses gradle to manage dependencies, 
for a guide on installing gradle on windows, see https://gradle.org/install/