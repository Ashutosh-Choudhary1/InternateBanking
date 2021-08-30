

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class InternetBanking
 */
public class InternetBanking extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		   String customerid=request.getParameter("CUSTOMERID");
		   String password=request.getParameter("PASSWORD");
		   
		   Model m=new Model();
		   m.setCustomerid(customerid);
		   m.setPassword(password);
		   boolean val=m.internetBanking();
			   
				   if(val==true) {
					   int accountnumber=m.getAccountnumber();
					   HttpSession session=request.getSession(true);
					   session.setAttribute("ACCOUNTNUMBER", accountnumber);
					   try {
						response.sendRedirect("home.html");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }else {
					   try {
						response.sendRedirect("failureInternet.html");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
	   }
}
