package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DbConnect.DBConnection;


@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection con = new DBConnection();
	FindUser fu = new FindUser();
	String user = fu.uNAME;
	String pass = fu.uPASS;
	int id = fu.ID;
	
    public UpdateUser() {
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
 			  .append("			<title>Form input</title>\r\n")
 			  .append("		</head>\r\n")
 			  .append("		<body>\r\n")
 			  .append("			<form action=\"update\" method=\"POST\">\r\n")
 			  .append("				Enter new username: \r\n")
 			  .append("				<input type=\"text\" value=\""+user+"\" name=\"user\" />\r\n")
 			  .append("				and new password: \r\n")
 			  .append("				<input type=\"text\" value=\""+pass+"\"  name=\"pass\" />\r\n")
 			  .append("				<input type=\"submit\" value=\"Submit\" />\r\n")
 			  .append("			</form>\r\n")
 			  .append("		</body>\r\n")
 			  .append("</html>\r\n");
 	}
     
 	@Override
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
 			throws ServletException, IOException {
 		
 		String user = request.getParameter("user");
 		String pass = request.getParameter("pass");
 		
 		try {
 			con.updateValuesDB("users", user, pass, id);
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
 			  .append("			<title>Update</title>\r\n")
 			  .append("		</head>\r\n")
 			  .append("		<body>\r\n");
 		if (user != null && !user.trim().isEmpty()) {
 			writer.append("	Good job.\r\n");
 			writer.append("	You successfully updated user with id "+id+"\r\n");
 		} else {
 			writer.append("	You did not entered a name!\r\n");
 		}
 		writer.append("		</body>\r\n")
 			  .append("</html>\r\n");
 	}

}
