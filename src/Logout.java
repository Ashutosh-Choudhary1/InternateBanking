

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
    {
        HttpSession session = request.getSession(); //Fetch session object
 
       
            session.invalidate(); 
            
            try {
				response.sendRedirect("successLogout.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	
}
