
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class Balance
 */
public class Balance extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse res) {

		HttpSession session = request.getSession();
		int accountnumber = (int) session.getAttribute("ACCOUNTNUMBER");
		Model m = new Model();
		m.setAccountnumber(accountnumber);
		boolean val=m.fetchBalance();
		if(val==true) {
			int balance=m.getBalance();
			session.setAttribute("BALANCE", balance);
			try {
				res.sendRedirect("successBalance.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				res.sendRedirect("failurebalance.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
