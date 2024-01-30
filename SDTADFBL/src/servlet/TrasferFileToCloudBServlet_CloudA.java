package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDAO;
import db.DbConnection;
import util.Bean;

@WebServlet("/TrasferFileToCloudBServlet_CloudA")
public class TrasferFileToCloudBServlet_CloudA extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = "";
		int i = 0;
		int j = 0;
		int fid =Integer.parseInt(request.getParameter("fid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		
		if(fid!=0) 
		{
			Connection con = new DbConnection().getConnection();
			try {
			PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,file,pkey,cipertext,skey,hashfunction from clouda where fid=? and uid=?");
			ps.setInt(1, fid);
			ps.setInt(2, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				Bean b = new Bean();
				b.setFid(rs.getInt(1));
				b.setUid(rs.getInt(2));
				b.setEmail(rs.getString(3));
				b.setName(rs.getString(4));
				b.setFile(rs.getBytes(5));
				b.setPassword(rs.getString(6));
				b.setCipher(rs.getString(7));
				b.setHash(rs.getString(9));
				b.setMobile(rs.getString(8));
				
				i = new SecurityDAO().cloudATranferFileToCloudB(b);
			}
			if(i!=0) 
			{
				j = new SecurityDAO().cloudATranferFileToCloudB(fid,uid);
			}
			if(j!=0) {
				
				t="TransferRequestFromDO_CloudA.jsp?status=Transfered Request Send to Cloud B";
			}else 
			{
				t="TransferRequestFromDO_CloudA.jsp?status=Not Trasfered";
			}
			}catch (Exception e) {
				e.printStackTrace();
				t="TransferRequestFromDO_CloudA.jsp?status=Check Console Error";
			}
		}else 
		{
			t="TransferRequestFromDO_CloudA.jsp?status=Fid not Selected";
		}
		response.sendRedirect(t);
	}
}
