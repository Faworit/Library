package Test;

import java.sql.*;

public class Connection implements AutoCloseable {
    public static void main(String[] args) {
        //java.sql.Connection con = null;
        //Statement st = null;
        //ResultSet rs = null;

        //Statement stmt;
        try(java.sql.Connection con = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/test?useSSL=false",
                        "root", "admin");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from hunt;");
        ) {
          Class.forName("com.mysql.jdbc.Driver");
         while(rs.next()){
             String str = rs.getString(2);
             System.out.println(str);
         }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {

    }
}
