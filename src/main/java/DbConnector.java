import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String URL = "jdbc:postgresql://localhost/Book";
    private static String User = "postgres";
    private static String Password = "1011";

    public static Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, User, Password);
            System.out.println("Connected");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
