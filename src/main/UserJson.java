package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import servlet.DbConnect.DBConnection;


@WebServlet("/Welcome")
public class UserJson extends HttpServlet {
		private static final long serialVersionUID = 1L;
   
    	DBConnection con = new DBConnection();
    	Statement stmt = con.getStatement();
    	Connection conn = con.getConn();
    	ResultSet rs = con.getResult();
	  
    
    public UserJson() {
        super();
        
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String,String> map = new HashMap<String,String>();
    	
    	
    	JSONObject obj = new JSONObject();
	      // Set response content type
	      response.setContentType("application/json");
	      PrintWriter out = response.getWriter();
	      
	      
	      out.println("[");
	      try {
	         // Execute SQL query
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM users";
	         rs = stmt.executeQuery(sql);

	         // Extract data from result set
	         while(rs.next()){
	            
	            String username = rs.getString("userName");
	            String password = rs.getString("userPass");
	            map.put(username,password);
	         }  
	         obj.put("dbvalues",map);
             
             
             out.println(obj);
	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         conn.close();
	      } 
	      
	      catch(SQLException se) {
	         //Handle errors for JDBC
	         se.printStackTrace();
	      } catch(Exception e) {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      } finally {
	         //finally block used to close resources
	         try {
	            if(stmt!=null)
	               stmt.close();
	         } catch(SQLException se2) {
	         } // nothing we can do
	         try {
	            if(conn!=null)
	            conn.close();
	         } catch(SQLException se) {
	            se.printStackTrace();
	         } //end finally try
	      } //end try
	      out.println("]");
	}


}
