

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class GetStatement
 */
public class GetStatement extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session=req.getSession();
		int accountnumber=(int) session.getAttribute("ACCOUNTNUMBER");
		Model m=new Model();
		m.setAccountnumber(accountnumber);
		ArrayList a=m.getStatement();
		session.setAttribute("AL", a);
		try {
			res.sendRedirect("successStatement.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
