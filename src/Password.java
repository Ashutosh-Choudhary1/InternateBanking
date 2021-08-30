

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class Password
 */
public class Password extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) {
		String cnewPasss=req.getParameter("CNEWPASS");
		HttpSession session=req.getSession();
		int accountnumber=(int) session.getAttribute("ACCOUNTNUMBER");
		Model m=new Model();
		m.setAccountnumber(accountnumber);
		m.setPassword(cnewPasss);
		boolean val=m.changePass();
		if(val==true) {
			try {
				res.sendRedirect("successChange.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				res.sendRedirect("failreChange.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
