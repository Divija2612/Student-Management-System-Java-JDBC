import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/student_db",
                "postgres",
                "your_password_here"
            );

            return con;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
