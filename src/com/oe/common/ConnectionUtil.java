package com.oe.common;
import java.sql.Connection;

import java.sql.Date;

import java.sql.DriverManager;
/*import java.util.List;
import java.util.Map;*/

import com.oe.dao.LoginDao;


public class ConnectionUtil {
	
	public String getConnection()throws Exception{
		Connection connection = null;
		String url = null;
		String username = null;
		String password = null;
		try{
			System.out.println("Into getConnection()>>>");
			username = "postgres";
			password = "password";
			url = "jdbc:postgresql://localhost:5432/griddemo";
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Successfully got connection :"+connection);
			System.out.println("in connection util");
			if(connection!=null && !connection.isClosed()){
				connection.setAutoCommit(false);
			}
			LoginDao dao = new LoginDao();
			dao.doFetch(connection);
			
			
		}catch(Exception e){
			throw e;
		}
		LoginDao dao = new LoginDao();
		return dao.doFetch(connection);
	}

	public void setConnection(String name, String invdate, String amount, String tax, String total, String closed, String ship_via, String note)throws Exception{
		Connection connection = null;
		String url = null;
		String username = null;
		String password = null;
		
		
		//String i= id; 
		String nam=name; 
		String indate=invdate; 
		String amont=amount; 
		String tx=tax;
		String tot=total; 
		String closd=closed; 
		String shipvia=ship_via; 
		String not=note;
		try{
			System.out.println("Into getConnection()>>>");
			username = "postgres";
			password = "password";
			url = "jdbc:postgresql://localhost:5432/griddemo";
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Successfully got connection :"+connection);
			System.out.println("in connection util");
			if(connection!=null && !connection.isClosed()){
				connection.setAutoCommit(false);
			}
			LoginDao dao = new LoginDao();
			dao.doInsert(connection,nam,indate,amont,tx,tot,closd,shipvia,not);
			
		}catch(Exception e){
			throw e;
		}		
	}
	
	
	
}