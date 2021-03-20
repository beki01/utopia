package com.ss.utopia.service;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String driver= "com.mysql.cj.jdbc.Driver";
    private static final String url= "jdbc:mysql://localhost:3306/utopia";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties login = new Properties();

        try(FileReader in =  new FileReader("resources/login.properties")){
            login.load(in);
        } catch(Exception e) {
            System.out.println("Error in util.getConnection getting the login.properties to load");
//            e.printStackTrace();
        }

        String username = login.getProperty("username");
        String password = login.getProperty("password");

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }
}
