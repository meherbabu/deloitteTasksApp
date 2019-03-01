About the Application:


Pre-requisites to run the Application:
1.	Attached Deployable Archive (WAR) in the email. Please copy to webapps folder in tomcat.
2.	Attached the JKS file & server.xml files. (updated server.xml to support https and not support http)
Please copy to conf folder in tomcat installation directory

URL to access Todo Tasks Application: https://{hostname}:{port}/tasks
{hostname} – hostname of the server where the WAR is deployed
Default it to localhost if application is deployed in local machine.
{port} – port number of the server where the application is listening
Default it to 8443 for tomcat server.
Default URL: https://localhost:8443/tasks

Technologies used:
Front-end (UI) – ReactJS
CSS (Styling) – Semantic UI
Back-end (REST API) – Spring Boot
Database – HSQLDB (In Memory Database)


1.	ReactJS is being used to develop the UI of the application, because it is a component-based framework using which we can develop re-usable components. When the project size becomes big, reusable components will be of greater benefit to us.

Note: It’s been 3 years since I have worked on any UI Framework. So, I have learnt ReactJS on my own and developed UI of this application. So, it took time for me to complete it.

2.	Semantic UI is used for applying CSS styling to the components. Its bit easy to use. So, I have used it.

3.	SpringBoot is used to develop the REST API. Its convenient and faster to develop REST API using this framework.
              DAO classes are based on spring data jpa.
4.	As specified in the document, I have used HSQL in-memory database to persist the data.
  


 
If given more time, I would like to do …
1.	Write JUnits for both UI and Java code.
2.	Organize the reactjs code properly into different layers (service, UI etc.,)
3.	Probably use Caching stuff either at UI layer or in the SpringBoot service layer.
4.	Configure Jenkins job to build and deploy once the code is checked-in.
5.	Apply more CSS styling to improve the look and feel of the application.
6.	

