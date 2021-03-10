package com.crudexample.simplecrud.Factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() {
        Connection connection = null;

        String driver = "mysql";
        String dbaddress = "localhost";
        String dbname = "crudexample";
        String username = "root";
        String password = "password";

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dbaddress).append("/")
                .append(dbname);

        String connectionUrl = sb.toString();

        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Conexão estabelecida");
        } catch (Exception e) {
            System.out.println("ERRO");
        }
        return connection;
    }
}
