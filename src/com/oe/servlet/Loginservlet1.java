package com.oe.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oe.common.ConnectionUtil;

/**
 * Servlet implementation class Loginservlet1
 */
public class Loginservlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginservlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Into servlet>>>>>");
		String val = null;
		ConnectionUtil connUtil = null;
		//LoginUtil loginUtil = null;
		//boolean insertFlag = false;
		//try{
			connUtil = new ConnectionUtil();
			try {
				val = connUtil.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// response.setContentType("application/json");
			request.setAttribute("val",val.toString());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String val = null;
		ConnectionUtil connUtil = null;
		//LoginUtil loginUtil = null;
		//boolean insertFlag = false;
		//try{
			connUtil = new ConnectionUtil();
			try {
				val = connUtil.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("val",val);
			request.getRequestDispatcher("/Demo.jsp").forward(request, response);
	}

}
