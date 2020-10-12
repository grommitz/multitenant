# multitenant

A test app for using mutitenancy in hibernate 5 in a web app to run on payara micro.

    java -jar payara-micro-5.201.jar --deploy target/multitenant-1.0-SNAPSHOT.war

The first problem was a clash in the jboss logging classes in bean-validator inside payara-micro.
To get round this, remove these classes from the bean-validator module and repackage it.


```

Get the latest payara component versions from the pom:

https://github.com/payara/Payara/blob/payara-server-5.2020.4/pom.xml

