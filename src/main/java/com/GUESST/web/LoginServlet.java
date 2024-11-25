package com.GUESST.web;

import java.io.IOException;

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

import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(role + "----role");
		if(role.equals("tenant")) {
			
			tenant = tenantDao.selectTenantByEmail(email);
			if(tenant == null || !tenant.getPassword().equals(password)) {
				// user doesn't exists - failed login
				System.out.println("inside login not success");
			}
			if((tenant != null) && tenant.getPassword().equals(password)) {
				// Login successful
				System.out.println("inside login success");
				HttpSession session = request.getSession();
				session.setAttribute("id", tenant.getId());
				session.setAttribute("building", tenant.getBuilding());
				session.setAttribute("role", role);
				RequestDispatcher rd = request.getRequestDispatcher("TenantDashboard.jsp");
				rd.forward(request, response);
			}
		}else if(role.equals("owner")) {
			
			owner = ownerDao.selectOwnerByEmail(email);
			System.out.println("owner obj "+ owner);
			System.out.println("owner.getpassword "+ owner.getPassword());
			System.out.println("password " + password);
			
			if(owner == null || !owner.getPassword().equals(password)) {
				// user doesn't exists - failed login
				System.out.println("inside owner login not success");
			}
			if((owner != null) && owner.getPassword().equals(password)) {
				// Login successful
				System.out.println("inside login success");
				HttpSession session = request.getSession();
				session.setAttribute("id", owner.getId());
				session.setAttribute("role", role);
				session.setAttribute("building", owner.getBuildingName());
				RequestDispatcher rd = request.getRequestDispatcher("OwnerDashboard.jsp");
				rd.forward(request, response);
			}
		}
	}

}
