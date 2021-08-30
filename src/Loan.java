

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class Loan
 */
public class Loan extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session=req.getSession();
		int accountnumber=(int) session.getAttribute("ACCOUNTNUMBER");
		Model m=new Model();
		m.setAccountnumber(accountnumber);
		boolean val=m.applyLoan();
		if(val==true) {
			String name=m.getName();
			String email=m.getEmail();
			session.setAttribute("EMAIL", email);
			session.setAttribute("NAME", name);
			try {
				res.sendRedirect("successLoan.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				res.sendRedirect("failureLoan.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
