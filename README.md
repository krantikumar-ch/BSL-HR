# BSL-HR

Sample application to demonstrate Employees data with Spring Boot with JWT security and ReactJS as front end

Features
---------
  * Spring Security using JWT authentication
  * Custom Exception Handler to handle exceptions and send to client as a JSON object
  * Custom annotation to unique validation while creating User
  * Email Notifcation to newly created user
  * AOP implentation to display service response time
  * Implememnted Pagination to Display Employee Data
  * Implemented Common Reusbale Components like Table, Pagination table, Alert Banner 
  
Software Requirements
----------------------
  * Java8 or More 
  * Node Manager 
  * Oracle database 
  * Eclipse IDE 
  * Vs Code(Optional. Development purpose) 
  * Git Bash (Optional) 
  
  Steps
  ------
   * Click on "Clone or Download"  button 
      - If Git bash installed in your machine copy the url
      - Run this command git clone [provide url] in your machine. It will download the source code
      - Otherwise Click on "Download ZIP" icon. It will download source code as a zip file. Extract the Zip
   * Setup Database environment
      - Create a user in database and provide privileges(privileges should have table creation and data insertion). 
      - Go to db_scripts folder. Import tables from "tables.sql"(import each table)  then import insert scripts and apply commit 
      operation
   * Setup Eclipse environment
      - Open eclipse in your machine
      - Click on File then import button
      - In import wizard, Goto Maven folder, Select Existing Maven Projects
      - In Root directory box Provide application folder path then click browse and click on Finish button.
      - Go to application.properties in src/main/resources folder then provide database details in below properties
          - spring.datasource.url = provide jdbc url(jdbc:oracle:thin:@localhost:1521:xe)
          - spring.datasource.username= provide user name(bsl_hr)
          - spring.datasource.password== provide user name(bsl_hr)
      - Run your sprinboot application
          - Go to SpringBootJpaApplication.java file
          - Right click on the file Select "Run As" then click on "Java Application"
   * Setup React environment
       - Open command prompt in windows
       - Go to ui folder of application from command prompt
       - Run 'npm install' command (This is one time job to download front end dependencies)
       - Run npm start
       - Application will open in localhost:3000 address
       - Provide username kranti and password kranti to login


  
