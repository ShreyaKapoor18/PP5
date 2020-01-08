package com.example.getstoredatarest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
//import org.apache.tomcat.jdbc.pool.DataSource;
import com.zaxxer.hikari.HikariDataSource;
//import org.apache.commons.dbcp.BasicDataSource;

@SpringBootApplication
public class GetstoreDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetstoreDataRestApplication.class, args);
	}
	
	@Autowired
	public DataSource dataSource() {
	        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
	        dataSourceBuilder.url("jdbc:sqlite:your.db");
	        DataSource df = dataSourceBuilder.build();
	        //dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
	        //driver-class-name= "oracle.jdbc.driver.OracleDriver"; 
	        return  df; 
	}
	

}
	

