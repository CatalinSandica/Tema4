package test;

import java.util.ArrayList;
import java.util.List;

import servlet.DbConnect.DBConnection;
import servlet.Model.Users;

public class TestCon{

	public static void main(String[] args) throws Exception {
		DBConnection con = new DBConnection();
		
		List<Users> bla = new ArrayList<Users>();
		bla = con.getListFromDB("users");
		System.out.println(bla);
	}

}
