# Application like Twitter

## Inititalization database for application
- Step-by-step execute scripts from folder *\minimessage-dao\src\test\resources\db*
**After executed first script you must manual swithching to new database 'MINIMESSAGES'**
- create database user and grant full privileges to database
- Create file with jdbc settings for production \minimessage-dao\src\main\resources\jdbc.properties
- And create similar file for development \minimessage-dao\src\main\resources\jdbc-dev.properties

**jdbc.properties file structure:**
_jdbc.driver=com.mysql.jdbc.Driver_
_jdbc.url=jdbc:mysql://localhost:3306/minimessage?characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false_
_jdbc.username=minimessage_user_
_jdbc.password=db_user_password_

- Execute command (from root project folder)
_gradlew war_

- Deploy war-file in web-container. For example Apache Tomcat
**path to file:** _'\minimessage-web\build\libs\minimessage-web-1.0.0.war'_

# Changes history
16.04.2017
1. Now we can get user messages by the web-interface

18.04.2017
1. Was added authentication in app though database
2. Was added admin console  (/admin/roles )
3. Add Class AccountRole

16.05.2017
1. was added cirylic support
2. now we can subscribe\unsubscribe to another users
3. was added security settings (to methods)
4.  was added some changes to the interface

01.09.2017
1. was added sql-script for init application roles (insert in database)