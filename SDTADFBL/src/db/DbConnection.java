package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public Connection getConnection(){
		Connection con=null;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3307/MJCC07_2022";
            con=DriverManager.getConnection(url,"root","rootroot");
            System.out.println("Data base---->"+con);
        }catch(Exception e)
         {
             e.printStackTrace();
         }
        return con;
    }
}