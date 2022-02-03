# springboot-board-basic

spring 게시판 프로젝트 -> spring boot로 만들기 (maven)


## structure

* src.main.java.com.ksh
  * board
    * dao - BoardDAO
    * dto - BoardVO, PaveVO
    * service - BoardListService, BoardWriteService
      * impl - BoardListServiceImple, BoardWriteServiceImpl
    * web - BoardController
  * common
  * SpringbootBoardBasicApplication
  
* src.main.resources
  * application.properties
  * logback.xml
  * com.ksh - mybatis-config.xml
    * board.mapper - boardMappler.xml

* src.main.webapp
  * resources - css,images,js
  * web-inf
    * views.board2 - jsp
    

## setting 
* pom.xml
* logback.xml
* application.properties
* mybatis-config

### pom.xml
```xml
<!-- spring web -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- spring web services -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web-services</artifactId>
</dependency>
<!-- mybatis -->
<dependency>
  <groupId>org.mybatis.spring.boot</groupId>
  <artifactId>mybatis-spring-boot-starter</artifactId>
  <version>2.2.2</version>
</dependency>
		
<!-- devtools -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
  <optional>true</optional>
</dependency>
<!-- oracle -->
<dependency>
  <groupId>com.oracle.database.jdbc</groupId>
  <artifactId>ojdbc8</artifactId>
  <scope>runtime</scope>
</dependency>
<!-- lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
<!-- test -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
		
<!-- servlet 202006 -->
<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
  <scope>provided</scope>
</dependency>
    	
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>jstl</artifactId>
  <version>1.2</version>
</dependency>
    	
<!-- Logback -->
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.2.3</version>
</dependency>
     	
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-core</artifactId>
  <version>1.2.3</version>
</dependency>
     	
<!-- append -->    
<dependency>
  <groupId>org.bgee.log4jdbc-log4j2</groupId>
  <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
  <version>1.16</version>
</dependency>
<!-- by upload start -->
<dependency>
  <groupId>commons-fileupload</groupId>
  <artifactId>commons-fileupload</artifactId>
  <version>1.4</version>
</dependency>
		
<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.7</version>
</dependency>
		
<dependency>
  <groupId>javax.validation</groupId>
  <artifactId>validation-api</artifactId>
  <version>1.1.0.Final</version>
  <scope>runtime</scope>
</dependency>
```

### application.properties
```
spring.datasource.username= username
spring.datasource.password= password
spring.datasource.url= url
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

server.port = 7000

logging.level.jdbc.sqlonly = DEBUG
logging.level.org.springframework.web = DEBUG
logging.level.com.zaxxer.hikariDataSource:ERROR 

spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
server.servlet.jsp.init-parameters.development=true
spring.main.banner-mode=off

mybatis.type-aliases-package=com.ksh.board.dto

mybatis.config-location=classpath:/com/ksh/mybatis-config.xml
mybatis.mapper-locations=classpath:**/mapper/*Mapper.xml
```

### mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" 
  "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
  <settings>
    <setting name="cacheEnabled" value="false" />
    <setting name="useGeneratedKeys" value="true" />
    <setting name="defaultExecutorType" value="REUSE" />
  </settings>
  <!-- aliaes -->
  <typeAliases>
    <typeAlias alias="hashMap" type="java.util.HashMap" />
    <typeAlias alias="map" type="java.util.Map" />
    <typeAlias alias="bvo" type="com.ksh.board.dto.BoardVO" />
    <typeAlias alias="pvo" type="com.ksh.board.dto.PageVO" />
  </typeAliases>
</configuration>
```

### logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
  <configuration scan="true" scanPeriod="30 seconds">
    <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>C:\Users\kwons\Desktop\study\log\springboot-board-basic\dailylog\boardLog.log</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>C:\Users\kwons\Desktop\study\log\springboot-board-basic\dailylog\boardLog-%d{yyyy-MM-dd}.%i.log.zip</fileNamePattern>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
          <!-- or whenever the file size reaches 100MB -->
          <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
      </rollingPolicy>
      <encoder>
        <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>
      </encoder>
    </appender>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <layout class="ch.qos.logback.classic.PatternLayout">
        <Pattern>%d{HH:mm:ss.SSS} [%-5level] - %msg%n</Pattern>
      </layout>
    </appender>
    
    <!-- Loggers -->
    <logger name="org.apache.catalina" level="INFO">
    </logger>
    
    <logger name="org.apache.commons" level="DEBUG">
    </logger>
    
    <logger name="org.springframework" level="DEBUG" >
    </logger>
    
    <logger name="java.sql" level="DEBUG">
    </logger>
    
    <logger name="org.springframework.boot.autoconfigure.logging" level="INFO">
    </logger>
    <logger name="org.mybatis.spring" level="INFO">
    </logger>
    
    <root level="INFO">
      <appender-ref ref="ROLLING"/>
      <appender-ref ref="STDOUT" />
    </root>

  </configuration>
```

## DAO 
@Repository("boardDao") -> @Mapper

### BaordDAO(spring-board-basic)
```java
@Repository("boardDao")
public class BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);
//database 연결 위해 DI 필요
	@Autowired 
	private SqlSession sqlsession;//connection pool의  컨넥션 정보
	
	String prens= "model2.board.";
	public int getAllcount() {
		return sqlsession.selectOne(prens+"allCnt");
	}
	public List<BoardDTO> getArticles(Map<String, Integer> hmap) {
		return sqlsession.selectList(prens+"getArticles",hmap);
	}
	
	public BoardDTO getArticle(BoardDTO bdto) {
		HashMap<String, Integer> numMap 
		    = new HashMap<String, Integer>();
		numMap.put("num", bdto.getNum());
		sqlsession.update(prens+"updateReadCont", numMap);
		return sqlsession.selectOne(prens+"getArticles", numMap);
	}
	public void boardWrite(BoardDTO bdto) {
	     sqlsession.insert(prens+"boardWrite",bdto);
	}
	
	public void boardUpdate(BoardDTO bdto) {
		 sqlsession.update(prens+"updatePro",bdto);
	}
	public int getNewNum() {
		return sqlsession.selectOne(prens+"newNum");
	}
	public void deletePro(int num) {
       sqlsession.delete(prens+"deleteArticle", num);		
	}
	
}
```

### BoardDAO(springboot-board-basic)
```java
@Mapper
public interface BoardDAO {
	public int getAllcount();
	public List<BoardVO> getArticles(Map<String, Integer> hmap);
	public BoardVO getArticles(BoardVO bvo);
	public void boardWrite(BoardVO bvo);
	public void boardUpdate(BoardVO bvo);
	public void updateReadCount(BoardVO bvo);
	public int getNewNum();
	public void deletePro(int num);
}
```
