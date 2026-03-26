package hotelroomDBProject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static final String URL = "jdbc:mysql://localhost:3306/hostelroom_db";
    static final String USER = "root";
    static final String PASSWORD = "your_password";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
