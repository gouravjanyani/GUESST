package com.GUESST.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GUESST.bean.Owner;
import com.GUESST.bean.Tenant;
import com.GUESST.dao.OwnerDao;
import com.GUESST.dao.TenantDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TenantDao tenantDao;
	private OwnerDao ownerDao;
	private Tenant tenant;
	private Owner owner;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		tenantDao = new TenantDao();
		ownerDao = new OwnerDao();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String building = request.getParameter("building");
		String password = request.getParameter("password");
		
		owner = new Owner(name, email, building, password);
		ownerDao.insertOwner(owner);
		
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);
	}

}
