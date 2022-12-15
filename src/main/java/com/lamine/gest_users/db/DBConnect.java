package com.lamine.gest_users.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private volatile static DBConnect single = null;
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/crypto?characterEncoding=utf8";
    private Connection conn;

    private DBConnect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, "root", "qsdfjklm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnect getInstance() {
        if (single == null) {
            synchronized (DBConnect.class) {
                if (single == null) {
                    single = new DBConnect();
                }
            }
        }
        return single;
    }

    public Connection getConn() {
        return conn;
    }
}
