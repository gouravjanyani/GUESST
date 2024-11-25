package com.GUESST.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.GUESST.bean.Tenant;
import com.GUESST.dao.TenantDao;

/**
 * Servlet implementation class PaymentVerification
 */

@MultipartConfig
@WebServlet("/PaymentVerification")
public class PaymentVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TenantDao tenantDao;

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
		
		System.out.println("inside dopost pay veri");
		
		Part file = request.getPart("proof");
		String imageFileName = file.getSubmittedFileName();
		System.out.println("file name ----" + imageFileName );
		
		String uploadPath = "/Users/jayeshjanyani/Desktop/Eclipse Projects/GUESST/src/main/webapp/assets/" + imageFileName;
		
		
		try {
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();		
		Tenant tenant = new Tenant((int)session.getAttribute("id"), imageFileName);
		
		tenantDao.updatePaymentProof(tenant);
		
		RequestDispatcher rd = request.getRequestDispatcher("TenantDashboard.jsp");
		request.setAttribute("upload", "success");
		
		rd.forward(request, response);
		
	}

}
