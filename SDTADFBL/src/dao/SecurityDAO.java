package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import db.DbConnection;
import sun.misc.BASE64Encoder;
import util.Bean;

public class SecurityDAO extends DbConnection {
	Connection con=null;
	public SecurityDAO() {
		con=getConnection();
	}
	
	public int feedBack(Bean b)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("insert into feedback(name,email,feedback)values(?,?,?)");
		ps.setString(1, b.getName());
		ps.setString(2, b.getEmail());
		ps.setString(3, b.getAddress());
		i=ps.executeUpdate();
		return i;
	}
	
	public int reg(Bean b)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("insert into userdetails(uname,password,email,mobile,dob,address,utype)values(?,?,?,?,?,?,'DO')");
		ps.setString(1, b.getName());
		ps.setString(2, b.getPassword());
		ps.setString(3, b.getEmail());
		ps.setString(4, b.getMobile());
		String da = b.getDob();
		SimpleDateFormat sd  =new SimpleDateFormat("yyyy-MM-dd");
		Date date =new Date(sd.parse(da).getTime());
		ps.setDate(5, date);
		ps.setString(6, b.getAddress());
		i=ps.executeUpdate();
		return i;
	}
	
	public ArrayList<Bean> login(Bean b) throws Exception
	{
		ArrayList<Bean> al = new ArrayList<Bean>();
		PreparedStatement ps = con.prepareStatement("select uid,uname,email,utype from userdetails where email=? and password=?");
		ps.setString(1, b.getEmail());
		ps.setString(2, b.getPassword());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			Bean login =new Bean();
			login.setUid(rs.getInt(1));
			login.setName(rs.getString(2));
			login.setEmail(rs.getString(3));
			login.setPassword(rs.getString(4));
			al.add(login);
		}
		return al;
	}
	
	public int doUploadFile(Bean b) throws Exception
	{
		int i =0;
		PreparedStatement ps = con.prepareStatement("insert into clouda(uid,email,fname,file,status,pkey,cipertext,skey,hashfunction)values(?,?,?,?,'waitimg at cloudA',?,?,?,?)");
		ps.setInt(1, b.getUid());
		ps.setString(2, b.getEmail());
		ps.setString(3, b.getName());
		ps.setBytes(4, b.getFile());
		
		String filetxt = new String(b.getFile());
		
		Random rd = new Random();
		Long lo =  (long) rd.nextInt(1000000000);
		String key = lo.toString();
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey secretkey = keyGen.generateKey();
		String skey = secretkey.toString();
		String stringKey = Base64.encode(secretkey.getEncoded());
		System.out.println("scretkey" + stringKey);
		Cipher aescipher = Cipher.getInstance("AES");
		aescipher.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] byteDataToEncrypt = filetxt.getBytes();
		byte[] byteCipherText = aescipher.doFinal(byteDataToEncrypt);
		String cipherText = new BASE64Encoder().encode(byteCipherText);
		System.out.println(cipherText);
		String hashfunction = cipherText.substring(cipherText.lastIndexOf("/"));
		ps.setString(5, key);
		ps.setString(6, cipherText);
		ps.setString(7, skey);
		ps.setString(8, "Hash Function: "+hashfunction);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudAAcceptNewFiles(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update clouda set status='active' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int dataOwnerTrasferRequest(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update clouda set status='Trasfer Request Waiting at cloudA' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int dataOwnerDeleteRequest(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update clouda set status='Delete Request Waiting at cloudA' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudATranferFileToCloudB(Bean b)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("insert into cloudb(fid,uid,email,fname,file,status,pkey,cipertext,skey,hashfunction)value(?,?,?,?,?,'transfer',?,?,?,?)");
		ps.setInt(1, b.getFid());
		ps.setInt(2, b.getUid());
		ps.setString(3, b.getEmail());
		ps.setString(4, b.getName());
		ps.setBytes(5, b.getFile());
		ps.setString(6, b.getPassword());
		ps.setString(7, b.getCipher());
		ps.setString(8, b.getMobile());
		ps.setString(9, b.getHash());
		
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudATranferFileToCloudB(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update clouda set status='CloudA Transfer Request Send to CloudB' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudBDeleteFileFromCloudA(int fid,int uid)throws Exception
	{
		int i = 0;
		System.out.println("Fid --->"+fid);
		System.out.println("Fid --->"+uid);
		PreparedStatement ps = con.prepareStatement("delete from clouda where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudBActiveTranferFile(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update cloudb set status='transfered' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudBDeleteFile(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update cloudb set status='deleted' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudADeleteRequestToCloudB(Bean b)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("insert into cloudb(fid,uid,email,fname,file,status,pkey,cipertext,skey,hashfunction)value(?,?,?,?,?,'delete',?,?,?,?)");
		ps.setInt(1, b.getFid());
		ps.setInt(2, b.getUid());
		ps.setString(3, b.getEmail());
		ps.setString(4, b.getName());
		ps.setBytes(5, b.getFile());
		ps.setString(6, b.getPassword());
		ps.setString(7, b.getCipher());
		ps.setString(8, b.getMobile());
		ps.setString(9, b.getHash());
		
		i=ps.executeUpdate();
		return i;
	}
	
	public int cloudADeleteFileToCloudB(int fid,int uid)throws Exception
	{
		int i = 0;
		PreparedStatement ps = con.prepareStatement("update clouda set status='CloudA Delete Request Send to CloudB' where fid=? and uid=?");
		ps.setInt(1, fid);
		ps.setInt(2, uid);
		i=ps.executeUpdate();
		return i;
	}
}