
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Connect {

	private Statement stmt;
	private ResultSet rs;
	
	public Connect() {
		
	     try { 
	         
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rowan_martini","root","");  
	            stmt = con.createStatement();  
	            
	            System.out.println("Koneksi Berhasil ke " + con);
	        
	        }catch(Exception e){ 
	        
	            System.out.println(e);
	        }  
		
	}//close constructor
	
 public ResultSet executeQuery(String query) {
		    
	        try {
	            rs = stmt.executeQuery(query);
	        }catch(Exception e) {
	            System.out.println(e);
	        }
	        return rs; 
	    } 

 public void executeUpdate(String query) {
	    	
	        try {
	            stmt.executeUpdate(query);
	        } catch(Exception e) {

	            System.out.println(e);
	        }
	    }

public java.sql.PreparedStatement prepareStatement(String string) {

	return null;
}

}//close class
