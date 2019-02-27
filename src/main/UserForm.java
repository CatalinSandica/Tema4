package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DbConnect.DBConnection;


@WebServlet("/UserForm")
public class UserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection con = new DBConnection();
    
    public UserForm() {
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
			  .append("			<title>Form input</title>\r\n")
			  .append("		</head>\r\n")
			  .append("		<body>\r\n")
			  .append("			<form action=\"\" method=\"POST\">\r\n")
			  .append("				Enter your name: \r\n")
			  .append("				<input type=\"text\" name=\"user\" />\r\n")
			  .append("				and password: \r\n")
			  .append("				<input type=\"text\" name=\"pass\" />\r\n")
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
			con.insertValuesInDB(user, pass, "users");
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
		if (user != null && !user.trim().isEmpty()) {
			writer.append("	Welcome <b>" + user + "</b>.\r\n");
			writer.append("	You successfully registered\r\n");
		} else {
			writer.append("	You did not entered a name!\r\n");
		}
		writer.append("		</body>\r\n")
			  .append("</html>\r\n");
	}	

}
