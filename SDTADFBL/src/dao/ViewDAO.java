package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import db.DbConnection;
import util.Bean;

public class ViewDAO extends DbConnection {
	
	Connection con =null;
	
	public ViewDAO() {
		con=getConnection();
	}
	
	public int doViewStorage(int uid)throws Exception
	{
		int i=0;
		PreparedStatement ps = con.prepareStatement("select count(fid) from clouda where uid=?");
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			i = rs.getInt(1);
		}
		return i;
	}
	
	public ArrayList<Bean> doViewFiles(int uid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,fname,status from clouda where uid=?");
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setName(rs.getString(2));
			 b.setPassword(rs.getString(3));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewUsers()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select uid,uname,email,mobile,dob,address from userdetails");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setName(rs.getString(2));
			 b.setEmail(rs.getString(3));
			 b.setMobile(rs.getString(4));
			 Date da = rs.getDate(5);
			 SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
			 String date = sd.format(da);
			 b.setDob(date);
			 b.setAddress(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewFeedback()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,name,email,feedback from feedback");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setName(rs.getString(2));
			 b.setEmail(rs.getString(3));
			 b.setMobile(rs.getString(4));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewFilesforAccept()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,cipertext,hashfunction from clouda where status='waitimg at cloudA'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFid(rs.getInt(1));
			 b.setUid(rs.getInt(2));
			 b.setEmail(rs.getString(3));
			 b.setName(rs.getString(4));
			 b.setCipher(rs.getString(5));
			 b.setHash(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> dataOwnerViewFile(int fid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select file from clouda where fid=?");
		ps.setInt(1, fid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFile(rs.getBytes(1));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewUserFiles()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select uid,email,fid,fname from clouda where status='active'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setEmail(rs.getString(2));
			 b.setFid(rs.getInt(3));
			 b.setName(rs.getString(4));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewUserFile(int fid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select file from clouda where fid=?");
		ps.setInt(1, fid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFile(rs.getBytes(1));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewTransferRequest()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,cipertext,hashfunction from clouda where status='Trasfer Request Waiting at cloudA'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFid(rs.getInt(1));
			 b.setUid(rs.getInt(2));
			 b.setEmail(rs.getString(3));
			 b.setName(rs.getString(4));
			 b.setCipher(rs.getString(5));
			 b.setHash(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudAViewDeleteRequest()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,cipertext,hashfunction from clouda where status='Delete Request Waiting at cloudA'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFid(rs.getInt(1));
			 b.setUid(rs.getInt(2));
			 b.setEmail(rs.getString(3));
			 b.setName(rs.getString(4));
			 b.setCipher(rs.getString(5));
			 b.setHash(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudBViewUserFiles()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select uid,email,fid,fname,status from cloudb where status='transfered' or status='deleted'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setEmail(rs.getString(2));
			 b.setFid(rs.getInt(3));
			 b.setName(rs.getString(4));
			 b.setPassword(rs.getString(5));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudBViewTransferRequest()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,cipertext,hashfunction from cloudb where status='transfer'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFid(rs.getInt(1));
			 b.setUid(rs.getInt(2));
			 b.setEmail(rs.getString(3));
			 b.setName(rs.getString(4));
			 b.setCipher(rs.getString(5));
			 b.setHash(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> cloudBViewDeleteRequest()throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,uid,email,fname,cipertext,hashfunction from cloudb where status='delete'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFid(rs.getInt(1));
			 b.setUid(rs.getInt(2));
			 b.setEmail(rs.getString(3));
			 b.setName(rs.getString(4));
			 b.setCipher(rs.getString(5));
			 b.setHash(rs.getString(6));
			 al.add(b);
		}
		return al;
	}
	public ArrayList<Bean> cloudBViewUserFile(int fid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select file from cloudb where fid=?");
		ps.setInt(1, fid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFile(rs.getBytes(1));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> doViewFilesFromCloudB(int uid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select fid,fname,status from cloudb where uid=?");
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setUid(rs.getInt(1));
			 b.setName(rs.getString(2));
			 b.setPassword(rs.getString(3));
			 al.add(b);
		}
		return al;
	}
	
	public ArrayList<Bean> dataOwnerViewFileFromCloudB(int fid)throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select file from cloudb where fid=?");
		ps.setInt(1, fid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean b = new Bean();
			 b.setFile(rs.getBytes(1));
			 al.add(b);
		}
		return al;
	}
}