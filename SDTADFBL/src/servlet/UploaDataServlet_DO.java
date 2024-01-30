package servlet;

import java.io.IOException;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import dao.SecurityDAO;
import db.DbConnection;
import util.Bean;


@WebServlet("/UploaDataServlet_DO")
public class UploaDataServlet_DO extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		String extension = null;
		int count = 0;
		HttpSession ses = request.getSession();
		int uid = (Integer)ses.getAttribute("uid");
		String name = (String)ses.getAttribute("uname");
		String mail = (String)ses.getAttribute("mail");
		
		try {
			
			Connection con = new DbConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("select count(fid) from clouda where uid=?");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}
		if(count<4) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		List li = fileUpload.parseRequest(request);
		
		FileItem image=(FileItem) li.get(0);
		byte[] imagebyte = image.get();
		 String fname = image.getName();
		 extension = fname.substring(fname.lastIndexOf("."));
		 if(extension.equals(".jpg")||extension.equals(".png")||extension.equals(".jpeg")) 
		 {
			 b.setUid(uid);
			 b.setName(fname);
			 b.setEmail(mail);
			 b.setFile(imagebyte);
			 
			 int i = new SecurityDAO().doUploadFile(b);
			 if(i!=0) {
			 RequestDispatcher rd = request.getRequestDispatcher("UploadData_DataOwner.jsp?status=Uploaded Successful");
			 rd.include(request, response);
			 }
			 else 
			 {
				 RequestDispatcher rd = request.getRequestDispatcher("UploadData_DataOwner.jsp?status=Faild to Upload");
				 rd.include(request, response);
			 }
			 
		 }else 
		 {
			 RequestDispatcher rd = request.getRequestDispatcher("UploadData_DataOwner.jsp?status=Image Should be in .jpg or .png or .jpeg format");
			 rd.include(request, response);
		 }
		}else 
		{
			RequestDispatcher rd = request.getRequestDispatcher("UploadData_DataOwner.jsp?status=Your cloud Memory is not enough to upload file");
			 rd.include(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("UploadData_DataOwner.jsp?status=Check Console Error");
			 rd.include(request, response);
		}
	}

}
