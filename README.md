# springMVC
springMVC framework with MySQL+hibernate+thymeleaf

This is a raw springMVC MAVEN project.

## contains

* MySQL support

* hibernate

* thymeleaf templet engine

## structure

* core

  ** frame(GenericDao, GenericService)

  ** common

* web

  ** common

  ** controller
  
## recommend

* Eclipse for EE Developers

mine -> eclipse-jee-indigo-SR2-win32

* jdk-1.6-win32

mine -> jdk-6u45-windows-i586

* tomcat6

mine -> apache-tomcat-6.0.36

* maven

mine -> apache-maven-3.0.5-bin

## few things to do

to convert to your own project, you have few changes to make.

* web.xml

> change packages to scan in `JerseyServlet` from `org.company` to your own packages.

* applicationContext.xml

> change packages to scan in `component-scan` from `org.company` to your own packages.

`org.company` represents for packages those contain any annotations except for `@Controller`.

* applicationContext-servlet.xml

> change packages to scan in `component-scan` from `org.company.web` to your own packages.

`org.company.web` represents for packages those contain `@Controller`s.

* applicationContext-datasource.xml

> change datasource settings to you own dataserver.

* now you are good to go !

## update:

* 2015-04-21 add entity ehcache support 

 [spring data jpa hibernate ehcache](http://www.csdn123.com/html/mycsdn20140110/fc/fc7d2c0bc92333ca53e6ea920bd24bef.html)


