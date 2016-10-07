set TOMCAT=tomcat
set JS1=src\main\resources\static\js
set JS2=..\apache-tomcat-9.0.0.M10\webapps\so\WEB-INF\classes\static\js
set HTML1=src\main\resources\templates
set HTML2=..\apache-tomcat-9.0.0.M10\webapps\so\WEB-INF\classes\templates
if "%1" == "%TOMCAT%" (
    copy %JS1%\home.js %JS2%\home.js /Y
    copy %HTML1%\products\main.html %HTML2%\products\main.html /Y
)else (
    del \git\proj\so\log\so.log
    mvn spring-boot:run
)