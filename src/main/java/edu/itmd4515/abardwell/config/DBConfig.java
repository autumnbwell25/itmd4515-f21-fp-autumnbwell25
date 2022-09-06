package edu.itmd4515.abardwell.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
@DataSourceDefinition(
        name = "java:app/jdbc/itmd4515DS",
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        serverName = "localhost",
        portNumber = 3306,
        databaseName = "Chinook",
        user = "itmd4515",
        password = "itmd4515",
        properties = {
                "zeroDateTimeBehavior=CONVERT_TO_NULL",
                "useSSL=false",
                "serverTimezone=America/Chicago"
        }
)
public class DBConfig {
}
