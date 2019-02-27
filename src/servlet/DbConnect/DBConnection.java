package servlet.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import servlet.Model.Users;


public class DBConnection {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement prepStatement = null;
	private static ResultSet resultSet;
	Users usr = new Users();
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "catalin.sandica", "Password123");
			System.out.println("Connection OK");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getValuesFromDB (String dbTable) throws Exception {
		List<Users> list = new ArrayList<Users>();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM " + dbTable);
		
			while(resultSet.next()){
				
				String username = resultSet.getString("userName");
				String password = resultSet.getString("userPass");
				list.add(new Users(username,password));
				}
			
			for(int i = 0; i< list.size(); i++){
			    System.out.println(list.get(i).getName() + " " + list.get(i).getPassword());
			}
		
	}
	public List<Users> getListFromDB (String dbTable) throws Exception {
		List<Users> list = new ArrayList<Users>();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM " + dbTable);
		
			while(resultSet.next()){
				
				String username = resultSet.getString("userName");
				String password = resultSet.getString("userPass");
				list.add(new Users(username,password));
				}
			
			return list;
		
	}
	
	public String getPasswordByUsername(String username, String dbTable) throws Exception {
		prepStatement = connection.prepareStatement("SELECT userPass FROM " + dbTable +" WHERE userName = ?");
		prepStatement.setString(1, username);
		resultSet = prepStatement.executeQuery();
		resultSet.next();
		usr.setPassword(resultSet.getString("userPass"));
		return usr.getPassword();
	}
	
	public String getUsernameByID(int id, String dbTable) throws Exception {
		prepStatement = connection.prepareStatement("SELECT userName FROM " + dbTable +" WHERE userID = ?");
		prepStatement.setInt(1, id);
		resultSet = prepStatement.executeQuery();
		resultSet.next();
		usr.setPassword(resultSet.getString("userName"));
		return usr.getPassword();
	}
	
	public void insertValuesInDB (String username, String password, String dbTable) throws Exception{
		prepStatement = connection.prepareStatement("INSERT INTO "+dbTable+"(userName, userPass) values (?,?)");
		prepStatement.setString(1,username);
		prepStatement.setString(2, password);
		prepStatement.executeUpdate();
	}
	
	
	public void deleteValuesFromDB (String dbTable, int id) throws Exception{
		prepStatement = connection.prepareStatement("DELETE FROM "+dbTable+" WHERE userID = ?");
		prepStatement.setInt(1, id);
		prepStatement.executeUpdate();
	}
	
	
	public void updateValuesDB(String dbTable,String name, String password, int id) throws Exception {
		prepStatement = connection.prepareStatement("UPDATE "+dbTable+" SET userName = ?, userPass=? WHERE userID = ?");
		prepStatement.setString(1, name);
		prepStatement.setString(2, password);
		prepStatement.setInt(3, id);
		prepStatement.executeUpdate();
	}
	
	public Connection getConn() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public ResultSet getResult () {
		return resultSet;
	}
	
}

