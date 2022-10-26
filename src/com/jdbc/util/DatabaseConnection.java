package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static final String JDBC_DRIVER = "org.postgresql.jdbc.Driver";

  private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/org";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "postgres";
  private static Connection connection = null;

  private DatabaseConnection() {}

  static {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
      connection =
        DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return connection;
  }
}
