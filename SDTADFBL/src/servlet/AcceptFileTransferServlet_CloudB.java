package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDAO;


@WebServlet("/AcceptFileTransferServlet_CloudB")
public class AcceptFileTransferServlet_CloudB extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target="";
		int i=0;
		int j=0;
		int fid = Integer.parseInt(request.getParameter("fid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		if(fid!=0) 
		{
			try {
			 i = new SecurityDAO().cloudBDeleteFileFromCloudA(fid,uid);
			 if(i!=0) 
			 {
				 System.out.println("Deleted");
				j = new SecurityDAO().cloudBActiveTranferFile(fid,uid);
			 }
			 if(j!=0) {
				 	target="TransferRequestFromCloudA_CloudB.jsp?status=File Activate Successful";
			 }
			 else 
			 {
				 target="TransferRequestFromCloudA_CloudB.jsp?status=Faild to Activate";
			 }
			}catch (Exception e) {
				e.printStackTrace();
				target="TransferRequestFromCloudA_CloudB.jsp?status=Check Console Error";
			}
		}else 
		{
			target="TransferRequestFromCloudA_CloudB.jsp?status=Fid Not Select";
		}
		response.sendRedirect(target);
	}
}
