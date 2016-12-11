set TOMCAT=tomcat
set JS1=src\main\resources\static\js
set JS2=F:\tools\camunda-bpm-tomcat-7.5.0\server\apache-tomcat-8.0.24\webapps\so\WEB-INF\classes\static\js
set HTML1=src\main\resources\templates
set HTML2=F:\tools\camunda-bpm-tomcat-7.5.0\server\apache-tomcat-8.0.24\webapps\so\WEB-INF\classes\templates
if "%1" == "%TOMCAT%" (
    copy %JS1%\home.js %JS2%\home.js /Y
    copy %JS1%\workflow.js %JS2%\workflow.js /Y
    copy %HTML1%\plans\planList.html %HTML2%\plans\planList.html /Y
    xcopy target\classes\net  F:\tools\camunda-bpm-tomcat-7.5.0\server\apache-tomcat-8.0.24\webapps\so\WEB-INF\classes\net  /s/e/y
)else (
    del \smartoffice\log\so.log
    mvn spring-boot:run
)