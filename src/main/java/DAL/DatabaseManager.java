package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection connection;

    private static String url = "jdbc:mysql://localhost:3306/school";
    private static String user = "root";
    private static String password = "";

    public DatabaseManager() {
        init();
    }

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        init();
    }

    private void init() {
        try {
//            connection.pre
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);


            if (connection != null) {
                System.out.println("Connected");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No Driver Found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }

    //thuc hien cac truy van lay du lieu
    public ResultSet executeQuery(String query) {
        try {
            if (connection != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet;
            }
        } catch (SQLException e) {
            System.out.println("Query execution failed");
            e.printStackTrace();
        }
        return null;
    }

    //thuc hien cac tuy van them sua xoa
    public int executeNonQuery(String query) {
    try {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = preparedStatement.executeUpdate();
            int lastInsertID = -1;

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                lastInsertID = generatedKeys.getInt(1);
            }

            preparedStatement.close();

            if (lastInsertID != -1) {
                return lastInsertID;
            } else {
                return rowsAffected;
            }
        }
    } catch (SQLException e) {
        System.out.println("Non-query execution failed");
        e.printStackTrace();
    }
        return -1; // lỗi
    }

    public Connection getConnection() {
        return this.connection;
    }
}
