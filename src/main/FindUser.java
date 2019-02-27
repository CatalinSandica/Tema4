package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DbConnect.DBConnection;


@WebServlet("/FindUser")
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String uNAME;
	public static String uPASS;
	public static int ID;
	DBConnection con = new DBConnection();
	private ResultSet rs = null;
    
    public FindUser() {
        super();
        
    }

	
    @Override
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
  			throws ServletException, IOException {
  		
  		// set response headers
  		response.setContentType("text/html");
  		response.setCharacterEncoding("UTF-8");
  		
  		// create HTML form
  		PrintWriter writer = response.getWriter();
  		writer.append("<!DOCTYPE html>\r\n")
  			  .append("<html>\r\n")
  			  .append("		<head>\r\n")
  			  .append("			<title>Find User</title>\r\n")
  			  .append("		</head>\r\n")
  			  .append("		<body>\r\n")
  			  .append("			<form action=\"find\" method=\"POST\">\r\n")
  			  .append("				Enter user ID: \r\n")
  			  .append("				<input type=\"text\" name=\"id\" />\r\n")
  			  .append("				<input type=\"submit\" value=\"Find\" />\r\n")
  			  .append("			</form>\r\n")
  			  .append("		</body>\r\n")
  			  .append("</html>\r\n");
  		
  	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			rs = con.getValuesByID(Integer.parseInt(id), "users");
			if(rs != null) {
				String username = rs.getString("userName");
				String password = rs.getString("userPass");
				ID = Integer.parseInt(id);
				uNAME = username;
				uPASS = password;
				response.sendRedirect("http://localhost:8080/Webserver-T4/update");
				
			}
			else {
				System.out.println("It's empty, yo!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
