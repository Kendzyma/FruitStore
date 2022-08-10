package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnect {
    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kenny","root","Tiamiyu1995");
            if (connection != null)
                System.out.println("connected");
            else System.out.println("Not connected");
        }
        catch (Exception e){
            e.getMessage();
        }
        return connection;
    }
}