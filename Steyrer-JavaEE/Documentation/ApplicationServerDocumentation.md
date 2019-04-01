## Wildfly 15

* Gleiche Vorgehensweise wie Wildfly 14

  

## Glassfish 5 / Payara

* Payara-Konfiguration ist identisch zur Glassfish-Konfiguration
* glassfish5/glassfish/config/asenv.conf
* set AS_JAVA="/opt/java/jdk/versions/jdk8u202"

<img src="/Steyrer-JavaEE/Documentation/Glassfish_Images/Glassfish_1.jpeg"> 

* sudo chmod +x glassfish5/bin/asdmin

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_2.jpeg">

* glassfish5/bin/asdmin start-domain

* http://localhost:4848

* set admin password

  * Change Admin Password
  * Logout

* login

  * user: admin
  * password: [passme]

* create jdbc connection pool:

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_3.jpeg"> 

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_4.jpeg"> 

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_5.jpeg"> 

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_6.jpeg"> 

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_7.jpeg"> 

  

  <img src="/Steyrer-JavaEE/Documentation//Glassfish_Images/Glassfish_8.jpeg"> 

  

  * poolName: derbyPool
  * type: datasource
  * vendor: derby

* create jdbc resource

  * jndi name:  jdbc/dbDS
  * connectionDB: derbyDB

* persistence.xml

  ```xml
  <jta-data-source>jdbc/dbDS</jta-data-source>
  ```

  

## Liberty

* Set Java Home:
  * /opt/java/jakartaee/liberty/versions/wlp19.0.0.1/usr/servers/defaultServer/server.env
  * JAVA_HOME=/opt/java/jdk/versions/jdk8u202

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_1.jpeg"> 

  

* liberty/bin/server create

* liberty/bin/server run

* http://localhost:9080

* bin/installUtility install adminCenter-1.0

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_2.jpeg"> 

  

* /opt/java/jakartaee/liberty/versions/wlp19.0.0.1/usr/servers/defaultServer/server.xml

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_5.jpeg"> 

  

* ./securityUtility encode

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_3.jpeg"> 

  

* https://localhost:9443/adminCenter/

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_4.jpeg"> 

  

* Server konfigurieren:

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_7.jpeg"> 

  

  * Server:

    Untergeordnetes Element hinzufügen:

​                        JDBC Treiber

​                        Datenquelle

* JDBC-Treiber:

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_8.jpeg"> 

​            <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_9.jpeg"> 

<img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_10.jpeg"> 

Untergeordnetes Element hinzufügen:

​                        Gemeinsam genutzte Bibliothek

​			Untergeordnetes Element hinzufügen:	

​				Dateigruppe

* Datenquelle:

  <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_11.jpeg"> 

​            <img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_12.jpeg">

 

<img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_13.jpeg">

 

<img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_14.jpeg">

 

Untergeordnetes Element hinzufügen:

​                        Eigenschaften für derby​

* in server.xml addieren

<img src="/Steyrer-JavaEE/Documentation//Liberty_Images/Liberty_6.jpeg"> 

* Intellij:

  * Edit Configuration → Websphere

    

## TomEE

* Set JRE_HOME or JAVA_HOME  

* create file:
  * tomee/bin/setenv.sh 
  * JAVA_HOME=/opt/java/jdk/versions/jdk8u202

  <img src="/Steyrer-JavaEE/Documentation//TomEE_Images/TomEE_1.jpeg"> 

 

* tomee/bin/startup.sh

* tomee/bin/shutdown.sh

* /opt/java/jakartaee/tomee/versions/apache-tomee-plume-8.0.0-M2/conf/tomcat-users.xml

 <img src="/Steyrer-JavaEE/Documentation//TomEE_Images/TomEE_2.jpeg"> 

* copy derbyclient to tomee/lib
* /opt/java/jakartaee/tomee/versions/apache-tomee-plume-8.0.0-M2/conf/tomee.xml
  * add Datasource

<img src="/Steyrer-JavaEE/Documentation//TomEE_Images/TomEE_3.jpeg"> 