package com.oe.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oe.common.ConnectionUtil;


public class Loginservlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection= null;
  
    public Loginservlet1() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Into servlet>>>>>");
		String val = null;
		ConnectionUtil connUtil = new ConnectionUtil();
	
			try {
				System.out.println("in servlet");
				val = connUtil.getConnection();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
			request.setAttribute("val",val.toString());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String val = null;
		ConnectionUtil connUtil = new ConnectionUtil();
/*		id,c.name,invdate,amount,tax,total,closed,ship_via,note
*/	
		//String id = request.getParameter("id");
		String name= request.getParameter("name");
		String invdate = request.getParameter("invdate");
		String amount = request.getParameter("amount");
		String tax = request.getParameter("tax");
		String total = request.getParameter("total");
		String closed = request.getParameter("closed");
		String ship_via = request.getParameter("ship_via");
		String note = request.getParameter("note");

		
	//System.out.println("parameter is "+ id);
    System.out.println("parameter is "+name);
	System.out.println("parameter is "+invdate);
	System.out.println("parameter is "+amount);
	System.out.println("parameter is "+tax);
	System.out.println("parameter is "+total);
	System.out.println("parameter is "+closed);
	System.out.println("parameter is "+note);

			try {
				
				
				connUtil.setConnection(name,invdate,amount,tax,total,closed,ship_via,note);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			/*request.setAttribute("val",val.toString());
			request.getRequestDispatcher("/index.jsp").forward(request, response);*/
	}

}
