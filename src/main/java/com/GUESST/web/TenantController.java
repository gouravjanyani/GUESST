package com.GUESST.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GUESST.bean.Tenant;
import com.GUESST.dao.TenantDao;

/**
 * Servlet implementation class TenantController
 */
@WebServlet("/TenantController")
public class TenantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TenantDao tenantDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TenantController() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String action = request.getServletPath();
//	    System.out.println("Requested action: " + action);
	    
		String action=request.getParameter("action");

		if(action==null)
		{
			action="default";
		}

	    try {
	        switch (action) {
	        	
		        case "register":
	        		System.out.println("inside /register case");
	        		registerUser(request, response);
	                break;
	        	case "login":
	        		System.out.println("inside /login case");
	            	loginUser(request, response);
	                break;
            	case "payment-form":
	            	payRent(request, response);
	                break;
            	case "payment-approval":
	            	paymentApproval(request, response);
	                break;
	            case "new":
	                showNewForm(request, response);
	                break;
	            case "insert":
	                insertTenant(request, response);
	                break;
	            case "delete":
	                deleteTenant(request, response);
	                break;
	            case "edit":
	                showEditForm(request, response);
	                break;
	            case "update":
	                updateTenant(request, response);
	                break;
	            case "verify":
	                verifyPayment(request, response);
	                break; 
	            case "payVerified":
	            	paymentVerified(request,response);
	            	break;
	            default:
	            	System.out.println("inside default case");
	                listTenant(request, response);
	                break;
	        }
	    } catch (SQLException ex) {
	        throw new ServletException("Database error", ex);
	    }
	}
	
	private void payRent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException  {
		HttpSession session = request.getSession();
		 Tenant existingTenant = tenantDao.selectTenant((int) session.getAttribute("id"));
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Payment-upload-form.jsp");
	    request.setAttribute("tenant", existingTenant);
	    dispatcher.forward(request, response);
	}
	
	private void listTenant(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String building = (String) session.getAttribute("building");
		List<Tenant> listTenant = tenantDao.selectAllTenants(building);
	    request.setAttribute("listTenant", listTenant);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("tenant-list.jsp");
	    dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("tenant-form.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void verifyPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("PaymentApprovalServlet");
	    dispatcher.forward(request, response);
	}
	
	private void paymentVerified(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    
		int id = Integer.parseInt(request.getParameter("id"));
		Boolean flag = tenantDao.paymentVerified(id);
		response.sendRedirect("TenantController?action=list");
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
	    dispatcher.forward(request, response);
	}
	
	private void paymentApproval(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("PaymentApprovalServlet");
	    dispatcher.forward(request, response);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterServlet");
	    dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    Tenant existingTenant = tenantDao.selectTenant(id);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("tenant-form.jsp");
	    request.setAttribute("tenant", existingTenant);
	    boolean payVerification = existingTenant.isPayVerification()
;	    request.setAttribute("payVerification", payVerification);
	    dispatcher.forward(request, response);
	}

	private void insertTenant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("Bhai chal jaa ");
		String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String building = request.getParameter("building");
	    System.out.println(name +" "+ email +" "+ password +" "+ building );
	    int floor = Integer.parseInt(request.getParameter("floor"));
	    int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
	    int rent = Integer.parseInt(request.getParameter("rent"));
	    String doj = request.getParameter("DOJ");;
//	    try {
////	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//	    doj = request.getParameter("DOJ");
//	    }catch(ParseException e) {
//	    	e.printStackTrace();
//	    }
	    boolean rentStatus = "true".equals(request.getParameter("rentStatus"));
	    boolean payVerification = Boolean.parseBoolean(request.getParameter("payVerification"));
	    
//	    System.out.println(name +" "+ email +" "+ password +" "+ building +" "+ floor+" "+ roomNumber + "");
	    Tenant newTenant = new Tenant(name, password, floor, email, roomNumber, building, doj, rent, rentStatus, payVerification);
	    tenantDao.insertTenant(newTenant);
	    response.sendRedirect("TenantController?action=list");
	}

	private void updateTenant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    int floor = Integer.parseInt(request.getParameter("floor"));
	    int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
	    int rent = Integer.parseInt(request.getParameter("rent"));
	    boolean rentStatus = "true".equals(request.getParameter("rentStatus"));
	    boolean payVerification = Boolean.parseBoolean(request.getParameter("payVerification"));
	    
	    Tenant tenant = new Tenant(id, name, floor, email, roomNumber, rent, rentStatus, payVerification);
	    tenantDao.updateTenant(tenant);
	    response.sendRedirect("TenantController?action=list");
	}

	private void deleteTenant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    tenantDao.deleteTenant(id);
	    response.sendRedirect("TenantController?action=list");
	}




}



