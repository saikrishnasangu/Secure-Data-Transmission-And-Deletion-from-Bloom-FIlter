package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDAO;
import util.Bean;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target="";
		Bean b = new Bean();
		b.setName(request.getParameter("name"));
		b.setPassword(request.getParameter("password"));
		b.setEmail(request.getParameter("email"));
		b.setMobile(request.getParameter("mobile"));
		b.setDob(request.getParameter("dob"));
		b.setAddress(request.getParameter("address"));
		
		try {
		int i = new SecurityDAO().reg(b);
		if(i!=0) 
		{
			target="register.jsp?status=Registration Successfull";
		}
		else 
		{
			target="register.jsp?status=Not Successfull";
		}
		}catch (Exception e) {
			e.printStackTrace();
			target="register.jsp?status=Check Console Error";
		}
		response.sendRedirect(target);
	}
}