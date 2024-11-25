package com.GUESST.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GUESST.bean.Owner;
import com.GUESST.bean.Tenant;
import com.GUESST.dao.OwnerDao;
import com.GUESST.dao.TenantDao;

/**
 * Servlet implementation class PaymentApprovalServlet
 */
@WebServlet("/PaymentApprovalServlet")
public class PaymentApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TenantDao tenantDao;
	private Tenant tenant;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		tenantDao = new TenantDao();
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String building = (String) session.getAttribute("building");
		int id = Integer.parseInt(request.getParameter("id"));
		Tenant tenant = tenantDao.selectTenantWithProof( building,id );
		
		String imageFileName = tenant.getProof();
		
		RequestDispatcher rd = request.getRequestDispatcher("PaymentVerification.jsp");
		request.setAttribute("image", imageFileName);
		request.setAttribute("id", id);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		
	}

}
