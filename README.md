<h1>Food Order Project</h1>
<h2>Requirements</h2>
For building and running the application you need:
<ul>
  <li>JDK 13 or higher</li>
  <li>Maven</li>
  <li>MySQL(make sure you change username, password and url in application.properties on your setting)</li>
  <li>Tomcat(Spring Boot using 9.0.31 by default(20.03.2020))</li>
</ul>

<h3>Running the application locally</h3>
There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the by.it.academy.foodorder.parent.ParentApplication class from your IDE.

<h3>Alternatively you can use the Spring Boot Maven plugin like so:</h3>
<ul>
  <li>mvn spring-boot:run</li>
</ul>

<h2>Deploying the WAR to Tomcat</h2>
<h3>To have our WAR file deployed and running in Tomcat, we need to complete the following steps:</h3>
<ul>
  <li>Download Apache Tomcat and unpackage it into a tomcat folder</li>
  <li>Copy our WAR file from target/ to the tomcat/webapps/ folder</li>
  <li>From a terminal navigate to tomcat/bin folder and execute</li>
  <li>catalina.bat run (on Windows)</li>
  <li>catalina.sh run (on Unix-based systems)</li>
  <li>Go to http://localhost:8080/</li>
</ul>
<br>
<h3>Security:</h3>
<ul>
  <li>For admin role: name - admin, password - admin</li>
  <li>For user role create new account</li>
</ul>
