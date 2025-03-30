import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws Exception{

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/lenny";
            conn = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }

        System.out.println("Database things\n");
        
        String query = "SELECT fname, lname FROM employee";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                System.out.println(fname, lname);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            conn.close();
            System.exit(1);
        }

        System.out.println("Success bitch");
        conn.close();  
    }
}
