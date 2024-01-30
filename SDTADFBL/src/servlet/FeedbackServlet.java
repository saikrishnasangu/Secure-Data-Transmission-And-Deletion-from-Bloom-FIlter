package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDAO;
import util.Bean;


@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target="";
		Bean b = new Bean();
		b.setName(request.getParameter("name"));
		b.setEmail(request.getParameter("email"));
		b.setAddress(request.getParameter("message"));
		try {
		int i = new SecurityDAO().feedBack(b);
		if(i!=0) 
		{
			target="contact.jsp?status=Submitd Successfully";
		}
		else 
		{
			target="contact.jsp?status=Not Submited";
		}
		}catch (Exception e) {
			target="contact.jsp?status=Check Console Error";
		}
		response.sendRedirect(target);
	}
}