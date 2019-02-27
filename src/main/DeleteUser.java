package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DbConnect.DBConnection;


@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection con = new DBConnection();
	public static String uName = null;
	
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
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
  			  .append("			<title>Delete User</title>\r\n")
  			  .append("		</head>\r\n")
  			  .append("		<body>\r\n")
  			  .append("			<form action=\"delete\" method=\"POST\">\r\n")
  			  .append("				Enter user ID: \r\n")
  			  .append("				<input type=\"text\" name=\"id\" />\r\n")
  			  .append("				<input type=\"submit\" value=\"Delete\" />\r\n")
  			  .append("			</form>\r\n")
  			  .append("		</body>\r\n")
  			  .append("</html>\r\n");
  	}
      
  	@Override
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
  			throws ServletException, IOException {
  		String id = request.getParameter("id");
  		try {
			uName = con.getUsernameByID(Integer.parseInt(id), "users");
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  		try {
  			con.deleteValuesFromDB("users", Integer.parseInt(id));
  			
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		response.setContentType("text/html");
  		response.setCharacterEncoding("UTF-8");
  		
  		// create HTML response
  		PrintWriter writer = response.getWriter();
  		writer.append("<!DOCTYPE html>\r\n")
  			  .append("<html>\r\n")
  			  .append("		<head>\r\n")
  			  .append("			<title>Welcome message</title>\r\n")
  			  .append("		</head>\r\n")
  			  .append("		<body>\r\n");
  		if (id != null && !id.trim().isEmpty()) {
  			writer.append("	The user "+uName+" with the id = <b>" + id + "</b> was deleted.\r\n");
  			writer.append("<br><br><a href=\"users\">Go to USERS</a>\r\n");
  		} else {
  			writer.append("	You did not entered an ID!\r\n");
  		}
  		writer.append("		</body>\r\n")
  			  .append("</html>\r\n");
  	}	

}
