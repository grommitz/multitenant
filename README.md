# multitenant

A test app for using mutitenancy in hibernate 5.

The first problem was a clash in the jboss logging classes in bean-validator inside payara-micro.
To get round this, remove these classes from the bean-validator module and repackage it.

Now I'm stuck on problems with the datasource. It looks like maybe it is not reading
the resources in glassfish-resources.xml. What is wrong?!

```
Caused by: javax.naming.NamingException: 
Lookup failed for 'java:app/jdbc/hibernate' in SerialContext
[myEnv={java.naming.factory.initial=com.sun.enterprise.naming.impl.SerialInitContextFactory, 
java.naming.factory.url.pkgs=com.sun.enterprise.naming, java.naming.factory.state=
com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl} 
[Root exception is javax.naming.NamingException: Invocation exception: Got null ComponentInvocation ]

```

