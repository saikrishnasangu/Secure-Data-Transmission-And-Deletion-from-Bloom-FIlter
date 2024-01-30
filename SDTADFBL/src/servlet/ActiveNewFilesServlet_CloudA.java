package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDAO;


@WebServlet("/ActiveNewFilesServlet_CloudA")
public class ActiveNewFilesServlet_CloudA extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t="";
		int fid = Integer.parseInt(request.getParameter("fid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		if(fid!=0) 
		{
			try {
			 int i = new SecurityDAO().cloudAAcceptNewFiles(fid,uid);
			 if(i!=0) {
			t="AcceptUserFiles_CloudA.jsp?status=SuccessFully Updated";
			 }else 
			 {
				 t="AcceptUserFiles_CloudA.jsp?status=Faild to Update";
			 }
			}catch (Exception e) {
				e.printStackTrace();
				t="AcceptUserFiles_CloudA.jsp?status=Check Console Error";
			}
		}else 
		{
			t="AcceptUserFiles_CloudA.jsp?status=Fid Not Selected";
		}
		response.sendRedirect(t);
	}
}