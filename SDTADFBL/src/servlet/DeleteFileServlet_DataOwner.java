package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SecurityDAO;


@WebServlet("/DeleteFileServlet_DataOwner")
public class DeleteFileServlet_DataOwner extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = "";
		int fid =Integer.parseInt(request.getParameter("fid"));
		HttpSession ses = request.getSession();
		int uid = (Integer)ses.getAttribute("uid");
		
		if(fid!=0) 
		{
			try {
			int i = new SecurityDAO().dataOwnerDeleteRequest(fid,uid);
			if(i!=0) {
			t="ViewFiles_DataOwner.jsp?status=Delete Request Send to CloudA Wait For Result";
			}else 
			{
				t="ViewFiles_DataOwner.jsp?status=Not Trasformed";
			}
			}catch (Exception e) {
				e.printStackTrace();
				t="ViewFiles_DataOwner.jsp?status=Check Console Error";
			}
		}
		else 
		{
			t="ViewFiles_DataOwner.jsp?status=Fid Not There";
		}
		response.sendRedirect(t);
	}

}
