package com.stackroute.springjdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class SpringJdbc {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource dataSource=ac.getBean("dataSource",DataSource.class);
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        //Creating a table
        jdbcTemplate.execute("Create table if not exists User (id int,name varchar(20),gender varchar(1))");

        //Updating records
        jdbcTemplate.update("insert into User values(?,?,?)",2,"Rohit","M");
        jdbcTemplate.update("insert into User values(?,?,?)",3,"Sabya","M");
        jdbcTemplate.update("insert into User values(?,?,?)",4,"Utkarsh","M");


        //Deleting record
        jdbcTemplate.update("Delete from User where id=?",4);

        //Reading the output
       SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet("Select * from User");
       while(sqlRowSet.next())
       {
           System.out.println(sqlRowSet.getInt(1)+sqlRowSet.getString(2));
       }


    }

}

