package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SecurityDAO;
import util.Bean;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Bean b = new Bean();
		b.setEmail(email);
		b.setPassword(password);
		
		String target="";
		
		String mail =null;
		int uid = 0;
		String name = null;
		String utype = null;
		
		if(email.equalsIgnoreCase("clouda@gmail.com")&&password.equalsIgnoreCase("clouda")) 
		{
			target ="CloudAHome.jsp?status=Welcom Cloud A";
		}
		else if(email.equalsIgnoreCase("cloudb@gmail.com")&&password.equalsIgnoreCase("cloudb")) 
		{
			target ="CloudBHome.jsp?status=WelCome Cloud  B";
		}
		else 
		{
			try {
			ArrayList<Bean> al = new SecurityDAO().login(b);
			if(!al.isEmpty()) {
			for(Bean login : al) 
			{
				mail = login.getEmail();
				uid = login.getUid();
				name = login.getName();
				utype = login.getPassword();
			}
			if(utype.equals("DO")) 
			{
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				session.setAttribute("uname", name);
				session.setAttribute("mail", mail);
				target ="DataOwnerHome.jsp?status= Welcom "+name;
			}
			}
			else 
			{
				target ="login.jsp?status= Invalid Email and Password";
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				target ="login.jsp?status=Check Console Error";
			}
		}
		response.sendRedirect(target);
	}

}
