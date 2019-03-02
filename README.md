About the Application:
---------------------
  This is a TODO Tasks Application where different Users can Signup, Login. Users can create Tasks/Delete Tasks. Users can logout from the application and they can check their tasks later.

How to Run the Application:
---------------------------
Method-1 (Easy to use): 
----------------------
Hit the URL https://18.218.189.51/tasks/
Application is deployed in AWS. Created 2 test users (test/test123) & (test1/test123). You can use these users OR you can signup new users into the application. Signup Functionality also provided.

Method-2:
--------
1.	Deployable Archive (WAR) is present in the target folder. Please copy to webapps folder in tomcat installation directory.
2.  In src/main/resources/conf folder, there are .jks & server.xml (Updated server.xml to support HTTPS) files. 
    Please copy the files to conf folder in tomcat installation director. 

URL to access Todo Tasks Application: https://{hostname}:{port}/tasks
  {hostname} – hostname of the server where the WAR is deployed
  Default it to localhost if application is deployed in local machine.
  {port} – port number of the server where the application is listening
  Default it to 8443 for tomcat server.
Ex: Default URL: https://localhost:8443/tasks

Technologies used:
-----------------
Front-end (UI) – ReactJS
CSS (Styling) – Semantic UI
Back-end (REST API) – Spring Boot
Database – HSQLDB (In Memory Database)
Code Coverage Tool - Eclemma
Applicaton Server - Tomcat
Cloud - AWS (To deploy the Application)

Security Considerations:
------------------------
1. HTTPS is being used to serve the application, to secure the data in transit.
2. Users and Passwords are stored in Database. During User signup/login, credentials will be checked against the Database.


Additional Features provided:
----------------------------
1. Signup Functionality is provided to signup New users.
2. Application deployed in AWS and the respective URL is provided above.

If given more time, I would like to do:
---------------------------------------
1.	Organize the reactjs code properly into different layers (service, UI etc.,)
2.  Learn Redux and apply in the Middleware of the UI.
2.	Probably use Caching stuff either at UI layer or in the SpringBoot service layer.
3.	Configure Jenkins job to build and deploy once the code is checked-in and deploy it in the respective machines.
4.	Apply more CSS styling to improve the look and feel of the application.	



I want to let you know..
-----------------------

1.	ReactJS is being used to develop the UI of the application, because it is a component-based framework using which we can develop re-usable components. When the project size becomes big, reusable components will be of greater benefit to us.

Note: It’s been 3 years since I have worked on any UI Framework. So, I have learnt ReactJS on my own and developed UI of this application. So, it took time for me to complete it.

2.	Semantic UI is used for applying CSS styling to the components. Its bit easy to use. So, I have used it.

3.	SpringBoot is used to develop the REST API. Its convenient and faster to develop REST API using this framework.
              DAO classes are based on spring data jpa.
        
