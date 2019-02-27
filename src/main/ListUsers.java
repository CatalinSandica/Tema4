package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DbConnect.DBConnection;

@WebServlet("/ListUsers")
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
      DBConnection con = new DBConnection();
      Statement stmt = con.getStatement();
	  Connection conn = con.getConn();
	  ResultSet rs = con.getResult();
	  
    public ListUsers() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Users List";
	      
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      
	      out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor = \"#f0f0f0\">\n" +
	         "<h1 align = \"center\">" + title + "</h1>\n");
	      try {
	    	  
	         // Execute SQL query
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM users";
	         rs = stmt.executeQuery(sql);

	         // Extract data from result set
	         while(rs.next()){
	            String id = rs.getString("userID");
	            String username = rs.getString("userName");
	            String password = rs.getString("userPass");

	            //Display values
	            out.println("UserID: " + id + "<br>");
	            out.println("Username: " + username + "<br>");
	            out.println("Password: " + password + "<br><br>");
	         }
	         out.println("</body></html>");

	         
	      } catch(SQLException se) {
	         //Handle errors for JDBC
	         se.printStackTrace();
	      } catch(Exception e) {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      } 
	   
	}


	

}
