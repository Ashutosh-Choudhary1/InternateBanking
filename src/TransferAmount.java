

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationBanking.Model;

/**
 * Servlet implementation class TransferAmount
 */
public class TransferAmount extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) {
		String amount=req.getParameter("AMOUNT");
		String toaccount=req.getParameter("TOACCOUNT");
		HttpSession session=req.getSession();
		int accountnumber=(int) session.getAttribute("ACCOUNTNUMBER");
		Model m=new Model();
		m.setAccountnumber(accountnumber);
		boolean value=m.transferAmount(amount, toaccount);
		if(value==true) {
			try {
				res.sendRedirect("successTransfer.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				res.sendRedirect("failureTransfer.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
